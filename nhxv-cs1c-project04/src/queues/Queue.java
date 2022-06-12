package queues;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Objects of type Queue manage items in a singly linked list where we can enqueue() items to the end and dequeue() items from the front of the queue.
 * @author Foothill College, Vinh Ngo
 */
public class Queue<E> implements Iterable<E> {
    /**
     * Inner Node
     */
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

    /**
     * Inner Iterator
     */
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

    /**
     * @param name playlist name
     */
    public Queue(String name) {
        this.name = name;
    }

    /**
     * Insert item to the front of the list
     * @param data element to insert
     */
    public void enqueue(E data) {
        if (data != null) {
            Node node = new Node(data);
            if (tail == null) {
                head = tail = node;
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

    /**
     * Remove item at the end of the list
     * @return item removed from the list
     */
    public E dequeue() {
        if (head != null) {
            Node toRemove = head;
            head = head.getNext();
            toRemove.setNext(null);
            size--;
            modCount++;
            return toRemove.getData();
        }
        throw new NoSuchElementException();
    }

    /**
     * @return item from the front of the list
     */
    public E peek() {
        if (head != null) {
            return head.getData();
        }
        return null;
    }

    /**
     *
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     *
     * @return size of the queue
     */
    public int size() {
        return size;
    }

    /**
     *
     * @return name of the queue
     */
    public String getName() {
        return name;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    /**
     *
     * @return string presentation of the queue
     */
    @Override
    public String toString() {
        Node current = head;
        String listStr = name + ":\n" + "[";
        if (size != 0) {
            while (current.getNext() != null) {
                listStr += current + ";\n";
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
