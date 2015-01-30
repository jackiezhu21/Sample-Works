import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests to test your Heap and Priority Queue classes
 *
 * @author CS 1332 TAs
 * @version 1.1
 */
public class StudentTests {
    private Heap<Integer> heap;
    private PriorityQueue<Integer> pQueue;

    @Before
    public void setUp() {
        heap = new Heap<Integer>();
        pQueue = new PriorityQueue<Integer>();
    }

    @Test(timeout = 200)
    public void testBasicAddInOrder1() {
        basicAdd(1, 2, 3);

        checkBackingArray(1, 2, 3);
    }

    @Test(timeout = 200)
    public void testPeek1() {
        basicAdd(4, 6, 1, 3, 2, 5);
        assertEquals(new Integer(1), heap.peek());
        assertEquals(6, heap.size());
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());

        basicAdd(1, 2, 3, 4, 5, 6);

        assertFalse(heap.isEmpty());
    }

    /**
     * Adds all of the numbers from an array to the heap.
     *
     * @param arr The array of numbers to add.
     */
    private void basicAdd(Integer... arr) {
        int i = 0;
        while (i < arr.length && arr[i] != null) {
            heap.add(arr[i]);
            i++;
        }
    }

    @Test(timeout = 200)
    public void testBasicResize() {
        @SuppressWarnings("rawtypes")
        Comparable[] array = heap.toArray();

        // 10 should be the default size of the heap backing array
        assertEquals(10, array.length);

        int num = 11;
        addMany(num);

        array = heap.toArray();
        assertEquals(20, array.length);
    }


    @Test(timeout = 200)
    public void testBasicInsert() {
        pQueueBasicAdd();

        assertArrayEquals(new Comparable[] {null, 21, 34, 38, 82, 73, 56, null,
            null, null}, pQueue.toArray());
    }

    @Test(timeout = 200)
    public void testBasicRemove() {
        basicAdd(5, 2, 8, 12, 31, 4, 6, 11, 15);
        assertEquals((Integer) 2, heap.remove());

        checkBackingArray(4, 5, 6, 11, 31, 8, 15, 12);
    }

    @Test(timeout = 200)
    public void testPQueueRemove() {
        pQueueBasicAdd();

        assertEquals((Integer) 21, pQueue.findMin());
        assertEquals((Integer) 21, pQueue.deleteMin());

        assertArrayEquals(new Comparable[] {null, 34, 56, 38, 82, 73, null,
            null, null, null}, pQueue.toArray());
    }

    /**
     * Adds num elements to the heap.
     *
     * @param num The number of elements to add to the heap.
     */
    private void addMany(int num) {
        for (int i = 1; i <= num; i++) {
            heap.add(i);
        }
    }

    /**
     * Checks that the backing array maintains the right properties.
     *
     * @param numbers The numbers to compare to.
     */
    private void checkBackingArray(Integer... numbers) {
        @SuppressWarnings("rawtypes")
        Comparable[] backingArray = heap.toArray();
        assertNull(backingArray[0]);

        for (int i = 1; i < backingArray.length; i++) {
            if (i > numbers.length) {
                assertNull(backingArray[i]);
            } else {
                assertEquals(numbers[i - 1], backingArray[i]);
            }
        }
    }

    private void pQueueBasicAdd() {
        pQueue.insert(34);
        pQueue.insert(82);
        pQueue.insert(38);
        pQueue.insert(21);
        pQueue.insert(73);
        pQueue.insert(56);
    }


    @Test(timeout = 200)
    public void orderedAdd() throws Exception {
        basicAdd(20, 30, 40, 31, 37, 44, 42, 80, 43);
        checkBackingArray(20, 30, 40, 31, 37, 44, 42, 80, 43);
    }

    @Test(timeout = 200)
    public void unorderedAdd() throws Exception {
        basicAdd(20, 37, 22, 38, 46, 4);
        checkBackingArray(4, 37, 20, 38, 46, 22);
        assertEquals(6, heap.size());
        basicAdd(27);
        checkBackingArray(4, 37, 20, 38, 46, 22, 27);
        assertEquals(7, heap.size());
    }

    @Test(timeout = 200)
    public void removal() throws Exception {
        basicAdd(20, 37, 22, 38, 46, 4, 27);
        assertEquals(7, heap.size());
        assertEquals(new Integer(4), heap.remove());
        assertEquals(6, heap.size());
        checkBackingArray(20, 37, 22, 38, 46, 27);
    }

    @Test(timeout = 200)
    public void unorderedAddPQ() throws Exception {
        basicAddPQ(20, 37, 22, 38, 46, 4);
        checkBackingPQ(4, 37, 20, 38, 46, 22);
        basicAddPQ(27);
        checkBackingPQ(4, 37, 20, 38, 46, 22, 27);
    }

    @Test(timeout = 200)
    public void orderedAddPQ() throws Exception {
        basicAddPQ(20, 30, 40, 31, 37, 44, 42, 80, 43);
        checkBackingPQ(20, 30, 40, 31, 37, 44, 42, 80, 43);
    }

    @Test(timeout = 200)
    public void removalPQ() throws Exception {
        basicAddPQ(20, 37, 22, 38, 46, 4, 27);
        assertEquals(new Integer(4), pQueue.deleteMin());
        checkBackingPQ(20, 37, 22, 38, 46, 27);
    }


    private void basicAddPQ(Integer... arr) {
        int i = 0;
        while (i < arr.length && arr[i] != null) {
            pQueue.insert(arr[i]);
            i++;
        }
    }


    private void checkBackingPQ(Integer... numbers) {
        @SuppressWarnings("rawtypes")
        Comparable[] backingArray = pQueue.toArray();
        assertNull(backingArray[0]);

        for (int i = 1; i < backingArray.length; i++) {
            if (i > numbers.length) {
                assertNull(backingArray[i]);
            } else {
                assertEquals(numbers[i - 1], backingArray[i]);
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNull() {
        heap.add(null);
    }

    /**
     * the expected keyword is a way to check for exceptions
     * in a junit test
     */
    @Test(expected = IllegalArgumentException.class)
    public void insertNull() {
        pQueue.insert(null);
    }


    /**
     * Tests generally contain assert statements
     * In this case, I'm testing to be sure that size works,
     * it works when the heap has a size of 0
     * it works when the heap has size > 0
     * it works when the heap has had items removed from it
     * this means that my handling of size is correct
     *
     * When writing this test, I caught an error in my code
     */
    @Test(timeout = 200)
    public void testSize() {
        assertEquals(0, heap.size());
        heap.add(1);
        heap.add(2);
        heap.add(3);
        assertEquals(3, heap.size());
        heap.remove();
        assertEquals(2, heap.size());
    }

    /**
     * This one tests the heap.isEmpty() method
     * it works on a newly instantiated heap
     * it works on a heap with more than 0 items
     * it works on a heap which had lots of items and now has none
     */
    @Test(timeout = 200)
    public void testIsEmpty1() {
        assertTrue(heap.isEmpty());
        heap.add(1);
        assertFalse(heap.isEmpty());
        heap.remove();
        assertTrue(heap.isEmpty());
    }

    /**
     * the documentation specifies nothing about peek on a new array
     * peek works when there is one element
     * peek works after a smaller element is added
     * peek works after a larger element is added
     * In essence, I've tested all of the options
     * either there were no prior items, the last item to be added
     * was smaller than head, or the last item to be added was larger
     */
    @Test(timeout = 200)
    public void testPeek() {
        heap.add(10);
        assertEquals((Integer) 10, heap.peek());
        heap.add(3);
        assertEquals((Integer) 3, heap.peek());
        heap.add(15);
        assertEquals((Integer) 3, heap.peek());
    }

    /**
     * Similar to the last one
     * when I remove things, it works
     * also, just to be sure, remove decreases the size
     * I'm not explicitly testing add beyond the null case
     * because for any of these to work, add must function
     */
    @Test(timeout = 200)
    public void testRemove() {
        heap.add(10);
        assertEquals((Integer) 10, heap.remove());
        assertEquals(0, heap.size());
        heap.add(10);
        heap.add(5);
        heap.add(3);
        assertEquals((Integer) 3, heap.remove());
    }


    /**
     * This one tests removing from the heap when the numbers
     * are ordered from left to right
     *
     * this caught a rather obscure error in my implementation
     */
    @Test(timeout = 200)
    public void testRemoveIncreasing() {
        heap.add(5);
        heap.add(10);
        heap.add(15);
        assertEquals((Integer) 5, heap.peek());
        assertEquals((Integer) 5, heap.remove());
        assertEquals((Integer) 10, heap.peek());

    }

    /**
     * Doing everything again for the Priority Queue
     */
    @Test(timeout = 200)
    public void testQueuePeek() {
        pQueue.insert(5);
        pQueue.insert(10);
        pQueue.insert(15);
        assertEquals((Integer) 5, pQueue.findMin());
        assertEquals((Integer) 5, pQueue.deleteMin());
        assertEquals((Integer) 10, pQueue.findMin());
    }

    /**
     * makes sure that the queue makeEmpty method works
     * I can't think of a fancy way to test to be sure
     * this is O(1) though
     */
    @Test(timeout = 200)
    public void testQueueMakeEmpty() {
        pQueue.insert(10);
        pQueue.insert(5);
        pQueue.insert(3);
        assertFalse(pQueue.isEmpty());
        pQueue.makeEmpty();
        assertTrue(pQueue.isEmpty());
        assertNull(pQueue.findMin());
    }

}
