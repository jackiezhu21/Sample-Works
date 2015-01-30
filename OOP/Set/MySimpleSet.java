import java.util.ArrayList;
import java.util.Iterator;

/**
 * MySimpleSet implements MonotonicSet using an ArrayList
 * to store a collection that cannot contain duplicates.
 *
 * The set is non-decreasing because it can only be added
 * to and no duplicates are allowed, its size can stay
 * the same or increase, but it will never decrease.
 *
 * @author Jacqueline Zhu
 * @version 1.0 3/26/14
 */

public class MySimpleSet<E> implements MonotonicSet<E> {

    private ArrayList<E> arr;

    /**
     * Creates a MySimpleSet and initializes this ArrayList
     *
     */
    public MySimpleSet() {
        arr = new ArrayList<E>();
    }

    /**
     * Adds an element to this Arraylist only if the ArrayList
     * does not already contain the element
     *
     * @param the element to be added
     */
    public void add(E element) {
        if (!arr.contains(element)) {
            arr.add(element);
        }
    }

    /**
     * Gets the size of this ArrayList
     *
     * @return size of this ArrayList
     */
    public int size() {
        return arr.size();
    }

    /**
     * Creates a String representation of this ArrayList
     *
     */
    @Override
    public String toString() {
        String aStr = "[";
        for (int x = 0; x < arr.size(); x++) {
            aStr += arr.get(x);
            if (x != arr.size() - 1) {
                aStr += ", ";
            }
        }
        aStr += "]";
        return aStr;
    }

    /**
     * Overrides the abstract iterator() method of Iterable
     *
     * @return the iterator for this MonotonicSet
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterate = arr.iterator();
        return iterate;
    }

}