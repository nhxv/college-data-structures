import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListQueue<E> implements Iterable<E> {

    // inner class: Node
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

    // Inner Iterator
    private class QueueIterator implements Iterator<E> {

        private Node currentNode;
        private int currentIndex;
        private static final int NOT_VALID = -1;
        private static final int NEXT = 10;
        private int iterModCount = modCount;
        private Node lastNodeReturned = null;
        private int lastIteration = NOT_VALID;

        public QueueIterator() {
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

    private String name;
    private Node head;
    private Node tail;
    private int size;
    private int modCount;

    public LinkedListQueue(String name) {
        this.name = name;
        this.modCount = 0;
    }

    // add item to the end of list
    public void enqueue(E data) {
        if (data != null) {
            Node node = new Node(data);
            if (tail == null) {
                head = node;
                tail = node;
                size++;
                modCount++;
                return;
            }
            tail.setNext(node);
            node.setNext(null);
            tail = node;
            size++;
            modCount++;
        }
    }

    // remove item in front of list
    public Node dequeue() {
        if (!isEmpty()) {
            Node toRemove = head;
            head = head.getNext();
            toRemove.setNext(null);
            size--;
            modCount++;
            if (head == null) {
                tail = null;
            }
            return toRemove;
        }
        throw new NoSuchElementException();
    }

    // return item in front of list
    public E peek() {
        if (!isEmpty()) {
            return head.getData();
        }
        return null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void showList() {
        if (!isEmpty()) {
            Node node = head;
            while (node != null) {
                System.out.println(node);
                node = node.getNext();
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }
}
