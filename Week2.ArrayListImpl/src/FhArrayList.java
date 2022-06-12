import java.util.ConcurrentModificationException;

public class FhArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 100;
    private static final int NOT_FOUND = -1;
    private int modCount = 0;
    private int size;
    private E[] objectArray;

    public FhArrayList() {
        clear();
    }

    public FhArrayList(int initCapacity) {
        ensureCapacity(initCapacity);
    }

    public void ensureCapacity(int minCapacity) {
        if (objectArray != null) {
            if (minCapacity <= objectArray.length) {
                return;
            }
        }
        E[] srcArray = objectArray;
        objectArray = (E[]) new Object[minCapacity];
        if (size > 0) {
            System.arraycopy(srcArray, 0, objectArray, 0, size);
        }
    }

    public boolean add(E x) {
        if (objectArray.length == size) {
            ensureCapacity(2*size + 1);
        }
        objectArray[size++] = x;
        return true;
    }

    public void add(int index, E x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (objectArray.length == size) {
            ensureCapacity(2*size + 1);
        }
        System.arraycopy(objectArray, index, objectArray, index + 1, size - index);
        objectArray[index] = x;
        size++;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return objectArray[index];
    }

    public E set(int index, E x) {
        E oldValue;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        oldValue = objectArray[index];
        objectArray[index] = x;
        return oldValue;
    }

    public E remove(int index) {
        E oldValue;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        oldValue = objectArray[index];
        System.arraycopy(objectArray, index + 1, objectArray, index, size - index - 1);
        size--;
        return oldValue;
    }

    public int indexOf(Object o) {
        int k;
        if (o != null) {
            for (k = 0; k < size; k++) {
                if (o.equals(objectArray[k])) {
                    return k;
                }
            }
        } else {
            for (k = 0; k < size; k++) {
                if (objectArray[k] == null) {
                    return k;
                }
            }
        }
        return NOT_FOUND;
    }

    public boolean remove(Object o) {
        int k = indexOf(o);
        if (k == NOT_FOUND) {
            return false;
        }
        remove(k);
        return true;
    }

    public boolean contains(Object o) {
        return (indexOf(o) != NOT_FOUND);
    }

    public int size() {
        return size;
    }

    public Object clone() throws CloneNotSupportedException {
        FhArrayList newObject = (FhArrayList)super.clone();
        // shallow copy but not too shallow
        newObject.objectArray = (E[])objectArray.clone();
        return newObject;
    }

    public boolean equals(Object o) {
        int k;
        FhArrayList<E> that;
        if (!(o instanceof FhArrayList<?>)) {
            return false;
        }
        that = (FhArrayList<E>) o;
        if (that.size() != size) {
            return false;
        }
        for (k = 0; k < size; k++) {
            if (!objectArray[k].equals(that.get(k))) {
                return false;
            }
        }
        return true;
    }

    // any method increases or decreases list size must implement modCount
    public void clear() {
        size = 0;
        objectArray = (E[]) new Object[DEFAULT_CAPACITY];
        modCount++;
    }

    // Iterator
    public java.util.Iterator<E> iterator() {
        return new FhIterator();
    }

    // inner class
    private class FhIterator implements java.util.Iterator<E> {
        protected static final int NOT_VALID = -1;
        protected static final int NEXT = 10;
        protected static final int PREVIOUS = 11;
        protected int current;

        // for ConcurrentModificationExceptions
        protected int iterModCount = modCount;

        // for IllegalStateExceptions
        protected int lastIndexReturned = NOT_VALID; // valid: 0 to size-1
        protected int lastIteration = NOT_VALID;   // valid: NEXT or PREVIOUS

        FhIterator() {
            current = 0;
        }

        public boolean hasNext() {
            return current < size;
        }

        public E next() {
            lastIndexReturned = current++;
            lastIteration = NEXT;
            return objectArray[lastIndexReturned];
        }

        public void remove() {
            // Test to see if there is a difference between the local and outer counters.
            // If they don't match, that means the client must have called an outer mutator like the method add() directly after an iterator was born.
            // So, we're in an illegal state!
            if (iterModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (lastIndexReturned == NOT_VALID) {
                throw new IllegalStateException();
            }
            FhArrayList.this.remove(lastIndexReturned);
            if (lastIteration == NEXT) {
                current--;
            }
            // Increments the local mIterModCount to keep pace with the outer modCount
            iterModCount++;
            lastIndexReturned = NOT_VALID;
        }
    }
}
