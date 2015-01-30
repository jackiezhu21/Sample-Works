import java.lang.reflect.Array;

/**
 * Set manages an array of Students
 *
 * @author Jacqueline Zhu
 * @version 1.0
 *
 */
public class Set<T extends Comparable<T>> {

    /**
     * DO NOT MODIFY
     */
    private T[] sortedArray;

    /**
     * DO NOT MODIFY
     */
    private Class<T> type;

    /**
     * DO NOT MODIFY
     */
    private static final int INITIAL_SIZE = 10;

    private int size;
    private T[] backingArray;

    /**
     * Sets size to 0, sets instance variable type,
     * creates the backingArray with size of initialSize
     * @param type
     * @param initialSize
     */
    public Set(Class<T> type, int initialSize) {
        size = 0;
        this.type = type;
        backingArray = createArray(initialSize);
    }
    /**
     * Calls other constructor
     * @param type
     */
    public Set(Class<T> type) {
        this(type, INITIAL_SIZE);
    }

    /**
     * Add the specified entry into the backing array and return true.
     * If the entry already exists, don't add and return false.
     * throw IllegalArgumentException if input is null.
     * @param entry
     * @return returns true if entry is successfully added and false if
     * entry already exists
     */
    public boolean add(T entry) {
        if (entry == null) {
            throw new IllegalArgumentException();
        }
        if (!contains(entry)) {
            if (size == backingArray.length) {
                backingArray = doubleArr(backingArray);
            }
            for (int i = 0; i < backingArray.length; i++) {
                if (backingArray[i] == null) {
                    backingArray[i] = entry;
                    size = size + 1;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method that creates an array that's double the
     * size of the backingArray
     * @param array to be doubled
     * @return doubled array
     */
    private T[] doubleArr(T[] arr) {
        int newSize = backingArray.length * 2;
        T[] tempArr = createArray(newSize);
        for (int j = 0; j < backingArray.length; j++) {
            if (backingArray[j] != null) {
                tempArr[j] = backingArray[j];
            }
        }
        return tempArr;
    }
    /**
     * Remove and return the specified entry from the backing array.
     * If the entry doesn't exist, return null.
     * throw IllegalArgumentException if input is null.
     * @param entry
     * @return entry that was removed or null if entry doesn't exist
     */
    public T remove(Object entry) {
        if (entry == null) {
            throw new IllegalArgumentException();
        }
        if (!contains((T) entry)) {
            return null;
        }
        for (int x = 0; x < backingArray.length; x++) {
            if (entry.equals(backingArray[x])) {
                T temp = backingArray[x];
                remove(x);
                return temp;
            }
        }
        return null;
    }

    /**
     * Remove and return the entry at specified index from the backing array.
     * If the index is negative or greater than size, return null.
     * @param index
     * @return removed entry a specified index
     */
    public T remove(int index) {
        if (size == 0) {
            return null;
        }
        if (index < 0 || index > size) {
            return null;
        }
        T removed = (T) backingArray[index];
        // backingArray[index] = null;
        // T temp = (T) backingArray[index];
        // backingArray[index] = null;
        for (int i = index; i < backingArray.length; i++) {
            if (i < (backingArray.length - 1)) {
                backingArray[i] = backingArray[i + 1];
            } else {
                backingArray[i] = null;
            }
        }
        size--;
        return removed;
        // size = size - 1;
        // return temp;
    }

    /**
     * Return true if backing array contains the specified entry.
     * Return false otherwise.
     * throw IllegalArgumentException if input is null.
     * @param entry
     * @return true if backing array contains entry,
     * false is entry is not in backing array
     */
    public boolean contains(T entry) {
        if (entry == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < backingArray.length; i++) {
            if (entry.equals(backingArray[i])) {
                return true;
            }
        }
        return false;
    }
    /**
     * Return a COPY of the backing array.
     * Don't return the backing array.
     * @return copy of backingArray
     */
    public T[] toArray() {
        T[] arr = createArray(backingArray.length);
        for (int x = 0; x < arr.length; x++) {
            arr[x] = backingArray[x];
        }
        return arr;
    }

    /**
     * Return true if the backing array is empty.
     * Return false otherwise.
     * @return true if backing array is empty, false if it's not empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /**
     * Reset the backing array to an empty array
     * of length INITIAL_SIZE
     */
    public void clear() {
        T[] empty = createArray(INITIAL_SIZE);
        backingArray = empty;
        size = 0;
    }

    /**
     * Return the number of non-null objects in the backing array.
     * @return number of non-null objects
     */
    public int size() {
        return size;
    }

    /**
     * Return string representation of the backing array.
     * Please check the pdf for correct format.
     * @return string representation of backing array
     */
    public String toString() {
        String str = "The gradebook:\n";
        for (int i = 0; i < backingArray.length; i++) {
            if (backingArray[i] != null) {
                str += backingArray[i].toString() + "\n";
            }
        }
        return str;
    }



    // DO NOT MODIFY CODE BELOW

    /**
     * IMPORTANT!!
     * Return a generics array of input type and has length
     * equivalent to input size. Use this method to create
     * any new arrays in this class.
     * @param size
     * @return array
     */
    @SuppressWarnings("unchecked")
    private T[] createArray(int size) {
        return (T[]) Array.newInstance(type, size);
    }

    /**
     * Return the sorted version of the backing array.
     * @return (T[]) the sorted array
     */
    public T[] sort() {
        if (size == 0) {
            return null;
        }
        sortedArray = createArray(backingArray.length);
        for (int i = 0; i < backingArray.length; i++) {
            sortedArray[i] = backingArray[i];
        }
        sort(0, size - 1);
        return sortedArray;
    }

    /**
     * An awesome sorting technique.
     * Can you guess what sort this is?
     * A picture of Aaron is the prize
     * @param start (int)
     * @param end (int)
     */
    private void sort(int start, int end) {
        int firstHalf = start;
        int lastHalf = end;
        int pivotIndex = start + (int) (Math.random() * (end - start));
        T pivot = sortedArray[pivotIndex];
        while (firstHalf <= lastHalf) {
            while (sortedArray[firstHalf].compareTo(pivot) > 0) {
                firstHalf++;
            }
            while (sortedArray[lastHalf].compareTo(pivot) < 0) {
                lastHalf--;
            }
            if (firstHalf <= lastHalf) {
                T temp = sortedArray[firstHalf];
                sortedArray[firstHalf] = sortedArray[lastHalf];
                sortedArray[lastHalf] = temp;
                firstHalf++;
                lastHalf--;
            }
        }
        if (start < lastHalf) {
            sort(start, firstHalf);
        }
        if (firstHalf < end) {
            sort(firstHalf, end);
        }
    }
}
