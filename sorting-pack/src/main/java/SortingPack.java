import java.util.*;
import java.util.Collections;

class SortingPack {
    // just in case you need tis method for testing
    public static void main(String[] args) {
        // something
    }

    // implementation of insertion sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] insertionSort(int[] unsortedArr) {
      int key;
      int j;
      for (int i = 0; i < unsortedArr.length; i++)  {
        key = unsortedArr[i];
        j = i-1;
        while (j >= 0 && unsortedArr[j] > key) {
            key = unsortedArr[i];
            unsortedArr[i]  = unsortedArr[j];
            unsortedArr[j] = key;
            j--;
            i--;
        }
      }
      return unsortedArr;
    }
    // implementation of merge sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] mergeSort(int[] unsortedArr) {
        if (unsortedArr.length > 1) {
          int midPoint = unsortedArr.length/2;
          int[] leftHalf = cloneArr(unsortedArr, 0, midPoint);
          int[] rightHalf = cloneArr(unsortedArr, midPoint, unsortedArr.length);
          mergeSort(leftHalf);
          mergeSort(rightHalf);
          int k = 0;
          int i = 0;
          int j = 0;
          while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] < rightHalf[j]) {
              unsortedArr[k] = leftHalf[i];
              k++;
              i++;
            } else {
              unsortedArr[k] = rightHalf[j];
              k++;
              j++;
            }
          }
          while (i < leftHalf.length) {
            unsortedArr[k] = leftHalf[i];
            k++;
            i++;
          }
          while (j < rightHalf.length) {
            unsortedArr[k] = rightHalf[j];
            k++;
            j++;
          }
        }
        return unsortedArr;
    }

    // implementation of heap sort
    // parameters: int array unsortedArr
    // return: sorted int array
    public static int[] heapSort(int[] unsortedArr) {
        HeapMax heap = new HeapMax();
        heap.build(unsortedArr);
        int i = 0;
        int[] sorted = new int[unsortedArr.length];
        while( heap.getData().size() != 0) {
            sorted[i] = heap.removeMax();
            i++;
        }
        int start = 0;
        int end = sorted.length - 1;
        int temp;
        while (start < end) {
            temp = sorted[start];
            sorted[start] = sorted[end];
            sorted[end] = temp;
            start++;
            end--;
        }
        return sorted;
    }

    /**
    *
    * Private Methods
    *
    **/

    private static int[] cloneArr(int[] array, int first, int last) {
      int[] cloneArr = new int[last - first];
      int k = 0;
      while(first < last) {
        cloneArr[k] = array[first];
        first++;
        k++;
      }
      return cloneArr;
    }

    // implementation of quick sort
    // parameters: int array unsortedArr
    // return: sorted int array
        // pick a pivot
        // put smaller on left
        // put bigger on the right
        // return index of pivot
        // use pivot index to partition
    public static int[] quickSort(int[] unsortedArr) {

        int first = 0;
        int last = unsortedArr.length - 1;
        int[] sorted = quickSort(unsortedArr, first, last);
        return sorted;
    }
    private static int[] quickSort(int[] sequence, int first, int last) {
        if( first < last ) {
            int partitionIndex = partition(sequence, first, last);
            quickSort(sequence, first, partitionIndex - 1);
            quickSort(sequence, partitionIndex + 1, last);
        }
        return sequence;
    }
    private static int partition( int[] sequence, int first, int last ) {
        int pivotValue = sequence[first];
        int l = first + 1;
        int r = last;
        boolean done = false;
        while (!done) {
            while (l <= r && sequence[l] <= pivotValue) {
                l++;
            }
            while (l <= r && sequence[r] >= pivotValue) {
                r--;
            }
            if (r < l) {
                done = true;
            } else {
                swap(sequence, r, l);
            }
        }
        sequence[first] = sequence[r];
        sequence[r] = pivotValue;
        return r;
    }
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
