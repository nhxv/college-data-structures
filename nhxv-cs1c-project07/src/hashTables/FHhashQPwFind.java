package hashTables;

import java.util.NoSuchElementException;

/**
 * A hash table with quadratic probing, extends from FHhashQP
 * @author Foothill College, Vinh Ngo
 *
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>> extends FHhashQP<E> {

    /**
     * Instantiate hashtable with initial size 97 if tableSize is smaller than 97
     * @param tableSize
     */
    public FHhashQPwFind(int tableSize) {
        super(tableSize);
    }

    /**
     * Return found object in hashtable
     * @param key
     * @return data
     * @throws NoSuchElementException
     */
    public E find(KeyType key) throws NoSuchElementException {
        HashEntry<E> searchResult = mArray[findPostKey(key)];
        if (searchResult.state == ACTIVE) {
            return searchResult.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     * Use given key to hash
     * @param key
     * @return hash value
     */
    private int myHashKey(KeyType key) {
        int hashVal;
        hashVal = key.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;
        return hashVal;
    }

    /**
     * Use given key to get the position
     * @param key
     * @return index of given key in array
     */
    private int findPostKey(KeyType key) {
        int kthOddNum = 1;
        int index = myHashKey(key);

        while (mArray[index].state != EMPTY && mArray[index].data.compareTo(key) != 0) {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }
}
