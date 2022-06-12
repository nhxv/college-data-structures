import java.util.NoSuchElementException;

public class Tree<E extends Comparable<? super E>> {

    // inner class: Node
    private class TreeNode {
        private E data;
        private TreeNode left;
        private TreeNode right;

        TreeNode(E data, TreeNode left, TreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        E getData() {
            return data;
        }

        void setData(E data) {
            this.data = data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }

        public String toString() {
            return data.toString();
        }
    }

    private TreeNode root;
    private int size;

    public Tree() {
        clear();
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void insert(E value) {
        if (this.root == null) {
            this.root = new TreeNode(value, null, null);
        } else {
            this.root = insert(this.root, value);
        }
    }

    private TreeNode insert(TreeNode root, E value) {
        if (root == null) {
            size++;
            return new TreeNode(value, null, null);
        }
        // avoid multiple call to compareTo() method
        int compareResult = value.compareTo(root.getData());
        if (compareResult < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new TreeNode(value, null, null));
            } else {
                insert(root.getLeft(), value);
            }
        } else if (compareResult > 0) {
            if (root.getRight() == null) {
                root.setRight(new TreeNode(value, null, null));
            } else {
                insert(root.getRight(), value);
            }
        }
        return root;
    }

    public void traverseInOrder() {
        if (root != null) {
            traverseInOrder(root);
        }
    }

    private void traverseInOrder(TreeNode root) {
        if (root.getLeft() != null) {
            traverseInOrder(root.getLeft());
        }
        System.out.println("Tree data: " + root.getData());
        if (root.getRight() != null) {
            traverseInOrder(root.getRight());
        }
    }

    public E find(E value) {
        if (this.root != null) {
            if (find(this.root, value) != null) {
                return find(this.root, value).getData();
            }
        }
        throw new NoSuchElementException();
    }

    private TreeNode find(TreeNode root, E value) {
        if (root == null) {
            return null;
        }
        int compareResult = value.compareTo(root.getData());
        if (compareResult < 0) {
            return find(root.getLeft(), value);
        }
        if (compareResult > 0) {
            return find(root.getRight(), value);
        }
        // found when root == value
        return root;
    }

    public E findMin() {
        if (this.root != null) {
            if (findMin(this.root) != null) {
                return findMin(this.root).getData();
            }
        }
        throw new NoSuchElementException();
    }

    private TreeNode findMin(TreeNode root) {
        if (root != null) {
            if (root.getLeft() == null) {
                return root;
            }
            return findMin(root.getLeft());
        }
        return null;
    }

    public E findMax() {
        if (this.root != null) {
            if (findMax(this.root) != null) {
                return findMax(this.root).getData();
            }
        }
        throw new NoSuchElementException();
    }

    private TreeNode findMax(TreeNode root) {
        if (root != null) {
            if (root.getRight() == null) {
                return root;
            }
            return findMax(root.getRight());
        }
        return null;
    }

    public void remove(E value) {
        root = remove(root, value);
    }

    private TreeNode remove(TreeNode root, E value) {
        if (root == null) {
            return null;
        }
        int compareResult = value.compareTo(root.getData());
        if (compareResult == 0) {
            if (root.getLeft() != null && root.getRight() != null) {
                root.setData(findMin(root.getRight()).getData());
                root.setRight(remove(root.getRight(), root.getData()));
            } else {
                root = (root.getLeft() == null) ? root.getLeft() : root.getRight();
            }
        }
        if (compareResult < 0) {
            root.setLeft(remove(root.getLeft(), value));
        } else if (compareResult > 0) {
            root.setRight(remove(root.getRight(), value));
        }
        return root;
    }
}
