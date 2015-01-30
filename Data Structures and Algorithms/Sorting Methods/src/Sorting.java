import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
  * Sorting implementation
  * CS 1332 : Fall 2014
  * @author Jacqueline Zhu
  * @version 1.0
  */
public class Sorting implements SortingInterface {

    // Do not add any instance variables.

    @Override
    public <T extends Comparable<? super T>> void bubblesort(T[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j - 1].compareTo(arr[j]) > 0) {
                    T temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                i = arr.length;
            }
        }
    }

    @Override
    public <T extends Comparable<? super T>> void insertionsort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T current = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(current) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    @Override
    public <T extends Comparable<? super T>> void selectionsort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            T temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }

    }

    @Override
    public <T extends Comparable<? super T>> void quicksort(T[] arr, Random r) {
        arr = quickHelp(arr, 0, arr.length - 1, r);
    }

    @Override
    public <T extends Comparable<? super T>> void mergesort(T[] arr) {
        @SuppressWarnings("unchecked")
        T[] temp = (T[]) new Comparable[arr.length];
        mergeHelper(0, arr.length - 1, arr, temp);
        arr = temp;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int[] radixsort(int[] arr) {
        boolean cont = true;
        int exp = 1;
        LinkedList<Integer>[] bucketPos = new LinkedList[10];
        LinkedList<Integer>[] bucketNeg = new LinkedList[10];
        ArrayList<Integer> pos = new ArrayList<Integer>();
        ArrayList<Integer> negs = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            bucketPos[i] = new LinkedList<Integer>();
            bucketNeg[i] = new LinkedList<Integer>();
        }
        while (cont) {
            cont = false;
            for (int i = 0; i < arr.length; i++) {
                int hashIndex = (arr[i] / exp) % 10;
                if (hashIndex > 0) {
                    cont = true;
                }
                if (arr[i] < 0) {
                    bucketNeg[Math.abs(hashIndex)].add(-arr[i]);
                } else {
                    bucketPos[Math.abs(hashIndex)].add(arr[i]);
                }
            }
            exp *= 10;
            for (int i = 0; i < 10; i++) {
                while (!bucketNeg[i].isEmpty()) {
                    int val = bucketNeg[i].peek() * -1;
                    bucketNeg[i].poll();
                    pos.add(val);
                }
                while (!bucketPos[i].isEmpty()) {
                    int val = bucketPos[i].peek();
                    bucketPos[i].poll();
                    negs.add(val);
                }
            }
        }
        int ind = 0;
        for (Integer i : pos) {
            negs.add(i);
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = negs.get(i);
        }
        return arr;
    }


    /*
     * Finds a random pivot and then puts everything less than that pivot
     * to the left of the array, and everything greater than that pivot to
     * the right of the array.
     * @param arr array to be sorted
     * @param left index of lower bound of current
     *      part of the array to be sorted
     * @param right index of upper bound of current
     *      part of the array to be sorted
     * @param r Random object
     * @return T[] sorted array
     */
    private <T extends Comparable<? super T>> T[] quickHelp(T[] arr,
            int left, int right, Random r) {
        if (right > left) {
            int pivot = r.nextInt((right - left)) + left;
            int newPivot = partition(arr, left, right, pivot);
            quickHelp(arr, left, newPivot - 1, r);
            quickHelp(arr, newPivot + 1, right, r);
        }
        return arr;
    }

    /*
     * Separates given part of array into two parts, the left
     * half with elements less than the right half.
     * @param arr to be sorted
     * @param left lower index bound
     * @param right upper index bound
     * @param pivot where the array is split
     * @return int of new pivot
     */
    private <T extends Comparable<? super T>> int partition(T[] arr, int left,
            int right, int pivot) {
        T current = arr[pivot];
        T temp = arr[pivot];
        arr[pivot] = arr[right];
        arr[right] = temp;
        int index = left;
        for (int i = left; i < right; i++) {
            if (arr[i].compareTo(current) <= 0) {
                temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
                index++;
            }
        }
        temp = arr[right];
        arr[right] = arr[index];
        arr[index] = temp;
        return index;
    }

    /*
     * Recursively divides array and sorts.
     * @param left lower bound of array
     * @param right upper bound of array
     * @param arr array to be sorted
     * @param temp copy array that helps in sorting process
     */
    private <T extends Comparable<? super T>> void mergeHelper(int left,
            int right, T[] arr, T[] temp) {
        if (left < right) {
            int pivot = left + (right - left) / 2;
            mergeHelper(left, pivot, arr, temp);
            mergeHelper(pivot + 1, right, arr, temp);
            merge(left, pivot, right, arr, temp);
        }
    }

    /*
     * Merges sorted lists into one.
     * @param left lower bound of array
     * @param pivot middle index
     * @param right upper bound of array
     * @param arr original array
     * @param temp helper array
     */
    private <T extends Comparable<? super T>> void merge(int left, int pivot,
            int right, T[] arr, T[] temp) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }
        int i = left;
        int j = left;
        int k = pivot + 1;
        while (i <= pivot && k <= right) {
            if (temp[i].compareTo(temp[k]) <= 0) {
                arr[j] = temp[i];
                i++;
            } else {
                arr[j] = temp[k];
                k++;
            }
            j++;
        }
        while (i <= pivot) {
            arr[j] = temp[i];
            i++;
            j++;
        }
    }

}