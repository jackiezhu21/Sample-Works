public class Heap<T extends Comparable<? super T>> implements HeapInterface<T>,
       Gradable<T> {

    @SuppressWarnings("unchecked")
    private T[] arr = (T[]) new Comparable[10];
    private int size;


    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException();
        } else {
            size++;
            resize();
            arr[size] = item;
            buildHeap(size);
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T peek() {
        return arr[1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T temp = arr[1];
            arr = (T[]) new Comparable[10];
            size = 0;
            return temp;
        } else {
            T removed = arr[1];
            T temp = arr[size];
            arr[size] = null;
            arr[1] = temp;
            heapify(1);
            size--;
            return removed;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T[] toArray() {
        return arr;
    }

    /**
     * Doubles size of array if array is full.
     */
    private void resize() {
        if (arr.length - 1 == size) {
            int newSize = arr.length * 2;
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Comparable[newSize];
            for (int i = 1; i < arr.length; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }

    }

    /**
     * Starts from a leaf node and swaps child and
     * parent if child has smaller value than parent.
     * Calls method again on new child if previous
     * child and parent swapped.
     * @param index
     */
    private void buildHeap(int index) {
        if (size > 1) {
            int parent = index / 2;
            if (parent > 0 && arr[index].compareTo(arr[parent]) < 0) {
                T temp = arr[index];
                arr[index] = arr[parent];
                arr[parent] = temp;
                buildHeap(parent);
            }
        }
    }

    /**
     * Starts at root and swaps with child if child
     * has greater value than parent, and swaps with
     * child with least value if both children have
     * greater values than the parent. Calls the
     * method on the new parent if previous parent
     * and child got swapped.
     * @param index
     */
    private void heapify(int index) {
        int child = 0;
        if (index * 2 <= size && index * 2 + 1 <= size
                && arr[index * 2] != null && arr[index * 2 + 1] != null) {
            int compareLeft = arr[index].compareTo(arr[index * 2]);
            int compareRight = arr[index].compareTo(arr[(index * 2) + 1]);
            if (compareLeft > 0 && compareRight > 0) {
                int leftRight = arr[index * 2].compareTo(arr[index * 2 + 1]);
                if (leftRight < 0) {
                    child = index * 2;
                } else {
                    child = (index * 2) + 1;
                }
            } else if (compareLeft > 0) {
                child = index * 2;
            } else if (compareRight > 0) {
                child = index * 2 + 1;
            }
        } else if (index * 2 <= size && arr[index * 2] != null) {
            if (arr[index].compareTo(arr[index * 2]) > 0) {
                child = index * 2;
            }
        } else if (index * 2 + 1 <= size && arr[index * 2 + 1] != null) {
            if (arr[index].compareTo(arr[(index * 2) + 1]) > 0) {
                child = index * 2 + 1;
            }
        }
        if (arr[child] != null) {
            T temp = arr[index];
            arr[index] = arr[child];
            arr[child] = temp;
            heapify(child);
        }
    }
}
