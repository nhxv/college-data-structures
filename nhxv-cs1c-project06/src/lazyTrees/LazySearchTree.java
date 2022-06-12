package lazyTrees;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A Binary Search Tree with lazy deletion
 * @author Vinh Ngo
 *
 */
public class LazySearchTree<E extends Comparable<? super E>> implements Cloneable {

    // inner class
    private class LazySTNode {
        // use public access so the tree or other classes can access members
        LazySTNode lftChild, rtChild;
        E data;
        LazySTNode myRoot;  // needed to test for certain error
        boolean deleted;

        LazySTNode( E d, LazySTNode lft, LazySTNode rt ) {
            lftChild = lft;
            rtChild = rt;
            data = d;
            deleted = false;
        }
        LazySTNode() {
            this(null, null, null);
        }
    }
    protected int mSize;
    protected int mSizeHard;
    protected LazySTNode mRoot;
    protected ArrayList<LazySTNode> garbageList;

    /**
     * Instantiate Lazy Search Tree
     */
    public LazySearchTree() {
        // initialCapacity  = SuperMarket.GARBAGE_COLLECTION_THRESHOLD
        this.garbageList = new ArrayList<>(4);
        clear();
    }

    public boolean empty() {
        return (mSize == 0);
    }

    // only reflect un-deleted node
    public int size() {
        return mSize;
    }

    // reflect both lazy deleted and un-deleted node
    public int sizeHard() {
        return mSizeHard;
    }

    // check soft memory
    public boolean contains(E x) {
        return find(mRoot, x) != null;
    }

    public void clear() {
        mSize = 0;
        mSizeHard = 0;
        mRoot = null;
    }

    /**
     * Public call of finding min the tree
     * @return minimum node found
     */
    public E findMin() {
        if (mRoot == null) {
            throw new NoSuchElementException();
        }
        return findMin(mRoot).data;
    }

    /**
     * Soft find min
     * @param root
     * @return soft minimum of the lazy deleted binary search tree
     */
    protected LazySTNode findMin(LazySTNode root) {
        if (root == null) {
            return null;
        }
        LazySTNode temp = findMin(root.lftChild);
        if (temp != null) {
            return temp;
        }
        if (!root.deleted) {
            return root;
        }
        return findMin(root.rtChild);
    }

    /**
     * Hard find min
     * @param root
     * @return hard minimum of the binary search tree
     */
    protected LazySTNode findMinHard(LazySTNode root) {
        if (root == null)
            return null;
        if (root.lftChild == null)
            return root;
        return findMin(root.lftChild);
    }

    /**
     * Public call of finding max item in the tree
     * @return max node in the tree that aren't lazily deleted
     */
    public E findMax() {
        if (mRoot == null) {
            throw new NoSuchElementException();
        }
        return findMax(mRoot).data;
    }

    /**
     * Soft find max
     * @param root
     * @return soft maximum of the lazy deleted binary search tree
     */
    protected LazySTNode findMax(LazySTNode root) {
        if (root == null) {
            return null;
        }
        LazySTNode temp = findMax(root.rtChild);
        if (temp != null) {
            return temp;
        }
        if (!root.deleted) {
            return root;
        }
        return findMax(root.lftChild);
    }

    /**
     * Hard find max
     * @param root
     * @return hard maximum of the binary search tree
     */
    protected LazySTNode findMaxHard(LazySTNode root) {
        if (root == null)
            return null;
        if (root.rtChild == null)
            return root;
        return findMax(root.rtChild);
    }

    /**
     * Public call of finding a node in a tree
     * @param x
     * @return node found
     */
    public E find(E x) {
        LazySTNode resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * Soft find
     * @param root
     * @param x node that needed to be found
     * @return founded soft node in the lazy deleted binary search tree
     */
    protected LazySTNode find(LazySTNode root, E x) {
        if (root == null)
            return null;
        int compareResult = x.compareTo(root.data);
        if (compareResult < 0) {
            // x < root
            return find(root.lftChild, x);
        } else if (compareResult > 0) {
            // x > root
            return find(root.rtChild, x);
        } else {
            // x == root
            if (!root.deleted) {
                return root;
            } else {
                return null;
            }
        }
    }

    /**
     * Public call of inserting an item to the tree
     * @param x
     * @return whether insert success or not
     */
    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Soft insert a node
     * @param root
     * @param x node to be inserted
     * @return root
     */
    protected LazySTNode insert(LazySTNode root, E x) {
        if (root == null) { // reach a leaf
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null);
        }
        int compareResult = x.compareTo(root.data);
        if (compareResult < 0) {
            // x < root
            root.lftChild = insert(root.lftChild, x);
        } else if (compareResult > 0) {
            // x > root
            root.rtChild = insert(root.rtChild, x);
        } else {
            // x == root
            if (root.deleted) {
                root.deleted = false;
                mSize++;
                garbageList.remove(root);
            }
        }
        return root;
    }

    /**
     * Public call of removing an item in the tree
     * @param x
     * @return whether remove success or not
     */
    public boolean remove(E x) {
        int oldSize = mSize;
        mRoot = remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Soft remove a node
     * @param root
     * @param x node needed to be removed
     * @return root
     */
    protected LazySTNode remove(LazySTNode root, E x) {
        if (root == null)
            return null;

        int compareResult = x.compareTo(root.data);
        // x < root
        if (compareResult < 0)
            root.lftChild = remove(root.lftChild, x);
        // x > root
        else if (compareResult > 0)
            root.rtChild = remove(root.rtChild, x);
        // x == root
        else {
            if (!root.deleted) {
                root.deleted = true;
                mSize--;
                garbageList.add(root);
            }
        }
        return root;
    }

    /**
     * Hard remove
     * @param root
     * @param x
     * @return x node needed to be removed
     */
    protected LazySTNode removeHard(LazySTNode root, E x) {
        if (root == null)
            return null;

        int compareResult = x.compareTo(root.data);
        if ( compareResult < 0 )
            root.lftChild = removeHard(root.lftChild, x);
        else if ( compareResult > 0 )
            root.rtChild = removeHard(root.rtChild, x);

        // found the node
        else if (root.lftChild != null && root.rtChild != null) {
            if (!root.rtChild.deleted) {
                root.deleted = false;
            }
            root.data = findMinHard(root.rtChild).data;
            root.rtChild = removeHard(root.rtChild, root.data);
        } else {
            root = (root.lftChild != null) ? root.lftChild : root.rtChild;
            mSizeHard--;
        }
        return root;
    }

    /**
     * Remove all garbage in lazy search tree when garbage reach threshold
     * @return true/false
     */
    public boolean collectGarbage() {
        if (mRoot == null || mSize == mSizeHard) {
            return false;
        }
        mRoot = collectGarbage(mRoot);
        return true;
    }

    /**
     * Call removeHard() method to remove all garbage in garbageList
     * @param root
     * @return root
     */
    protected LazySTNode collectGarbage(LazySTNode root) {
        // find the lazily deleted node
        if (root == null) {
            return null;
        }
        for (LazySTNode garbage : garbageList) {
            root = removeHard(root, garbage.data);
        }
        garbageList.clear();
        return root;
    }

    /**
     * public call to traverseSoft
     * @param func
     * @param <F>
     */
    public <F extends Traverser<? super E>> void traverseSoft(F func) {
        traverseSoft(func, mRoot);
    }

    /**
     * Print all nodes in the tree that're not lazily deleted
     * @param func
     * @param treeNode
     * @param <F>
     */
    protected <F extends Traverser<? super E>> void traverseSoft(F func, LazySTNode treeNode) {
        if (treeNode == null) {
            return;
        } else if (treeNode.deleted) {
            traverseSoft(func, treeNode.lftChild);
            traverseSoft(func, treeNode.rtChild);
        } else {
            traverseSoft(func, treeNode.lftChild);
            func.visit(treeNode.data);
            traverseSoft(func, treeNode.rtChild);
        }
    }

    /**
     * Public call to traverse all hard nodes in the tree
     * @param func
     * @param <F>
     */
    public < F extends Traverser<? super E > > void traverseHard(F func) {
        traverseHard(func, mRoot);
    }

    /**
     * Print all nodes in the tree that're not null
     * @param func
     * @param treeNode
     * @param <F>
     */
    protected <F extends Traverser<? super E>> void traverseHard(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * Public call to clone the tree
     * @return new tree
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;

        return newObject;
    }

    /**
     * Clone subtree
     * @param root
     * @return
     */
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;
        // does not set myRoot which must be done by caller
        newNode = new LazySTNode(root.data, cloneSubtree(root.lftChild), cloneSubtree(root.rtChild));
        return newNode;
    }
}
