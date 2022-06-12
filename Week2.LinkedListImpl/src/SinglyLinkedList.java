import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements Iterable<E> {
    private class Node {
        E data;
        Node next;
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
            this.currentNode = head;
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

    private Node head;
    private int size;
    private int modCount;

    public void addFront(E data) {
        Node node = new Node(data);
        node.setNext(head);
        head = node;
        size++;
        modCount++;
    }

    public Node removeFront() {
        if (isEmpty()) {
            return null;
        }
        Node removedNode = head;
        head = head.getNext(); // point to next field
        size--;
        modCount++;
        removedNode.setNext(null);
        return removedNode;
    }

    public E peek() {
        if (head != null) {
            return head.getData();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void printList() {
        Node current = head;
        System.out.print("Head -> ");
        while (current != null) {
            System.out.print(current + " -> ");
            current = current.getNext();
        }
        System.out.println("null");
    }

    @Override
    public Iterator<E> iterator() {
        return new StackIterator();
    }
}
