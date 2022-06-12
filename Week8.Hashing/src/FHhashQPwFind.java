import java.util.NoSuchElementException;

public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E> {

    public FHhashQPwFind(int tableSize) {
        super(tableSize);
    }

    public E find(KeyType key) throws NoSuchElementException {
        int index = findPostKey(key);
        if (index < 0) {
            throw new NoSuchElementException();
        }
        return mArray[index].data;
    }

    private int myHashKey(KeyType key) {
        int hashVal;
        hashVal = key.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;
        return hashVal;
    }

    private int findPostKey(KeyType key) {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while (mArray[index].state != EMPTY && !mArray[index].data.equals(key)) {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }
}
