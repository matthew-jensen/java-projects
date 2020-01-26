/*
// this is the testing class
// DO NOT MODIFY THIS CLASS AND ITS METHODS
*/
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import java.util.Random;

public class SortingPackTest {
    public int[] randomArray(int size) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt()/1000000; // storing random integers in an array
        }
        return arr;
    }

    public String arrayToString(int[] arr) {
        String str = "[";
        for (int i = 0; i < arr.length-1; i++) {
            str += arr[i] + ", ";
        }
        str += arr[arr.length-1] + "]";
        return str;
    }

    @Test public void insertionSortTest() {
        int[] arr1 = randomArray(5);
        int[] arr2 = new int[5];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }

    	assertThat("\nThis is the test on your insertionSort method." +
            "\nThe given array is " + arrayToString(arr1) + "\n" +
            "\nAfter being sorted using InsertionSort",
            SortingPack.insertionSort(arr1),
            is(RightSolution.insertionSort(arr2)));
    }

    @Test public void quickSortTest() {
        int[] arr1 = randomArray(8);
        int[] arr2 = new int[8];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }

        assertThat("\nThis is the test on your quickSort method." +
            "\nThe given array is " + arrayToString(arr1) + "\n" +
            "\nAfter being sorted using quickSort",
            SortingPack.quickSort(arr1),
            is(RightSolution.quickSort(arr2)));
    }

    @Test public void mergeSortTest() {
        int[] arr1 = randomArray(6);
        int[] arr2 = new int[6];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }

        assertThat("\nThis is the test on your mergeSort method." +
            "\nThe given array is " + arrayToString(arr1) + "\n" +
            "\nAfter being sorted using mergeSort",
            SortingPack.mergeSort(arr1),
            is(RightSolution.mergeSort(arr2)));
    }

    @Test public void heapSortTest() {
        int[] arr1 = randomArray(7);
        int[] arr2 = new int[7];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }

        assertThat("\nThis is the test on your heapSort method." +
            "\nThe given array is " + arrayToString(arr1) + "\n" +
            "\nAfter being sorted using heapSort",
            SortingPack.heapSort(arr1),
            is(RightSolution.mergeSort(arr2)));
    }

}
