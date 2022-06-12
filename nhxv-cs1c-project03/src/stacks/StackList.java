package stacks;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * a singly linked list which implements Iterable to store back links and forward links
 * @author Foothill College, Vinh Ngo
 *
 */
public class StackList<E> implements Iterable<E> {
    // inner classes: Node, StackIterator
    private class Node {
        private Node next;
        private E data;

        Node(E data) {
            this.data = data;
        }

        E getData() {
            return data;
        }

        void setData(E data) {
            this.data = data;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        public String toString() {
            return data.toString();
        }
    }

    private class StackIterator implements Iterator<E> {
        private Node currentNode;
        private int currentIndex;
        private static final int NOT_VALID = -1;
        private static final int NEXT = 10;
        private int iterModCount = modCount;
        private Node lastNodeReturned = null;
        private int lastIteration = NOT_VALID;

        public StackIterator() {
            this.currentNode = top;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastNodeReturned = currentNode;
            currentNode = currentNode.getNext();
            currentIndex++;
            lastIteration = NEXT;
            return lastNodeReturned.getData();
        }
    }

    private String name;
    private Node top;
    private int size;
    private int modCount;

    public StackList(String name) {
        this.name = name;
        this.modCount = 0;
    }

    // add to the top
    public void push(E data) {
        if (data != null) {
            Node node = new Node(data);
            node.setNext(top);
            top = node;
            size++;
            modCount++;
        }
    }

    // remove from the top
    public Node pop() {
        if (!isEmpty()) {
            Node removedNode = top;
            top = top.getNext();
            removedNode.setNext(null);
            size--;
            modCount++;
            return removedNode;
        }
        return null;
    }

    // return generic type for data at the top
    public E peek() {
        if (top != null) {
            return top.getData();
        }
        return null;
    }

    public void clear() {
        size = 0;
        top = null;
        modCount++;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    @Override
    public String toString() {
        Node current = top;
        String listStr = "with " + size + " links: \n" + "[";
        if (size != 0) {
            while (current.getNext() != null) {
                listStr += current + ", ";
                current = current.getNext();
            }
            listStr += current;
        } else {
            listStr += " ";
        }
        listStr += "]";
        return listStr;
    }
}
