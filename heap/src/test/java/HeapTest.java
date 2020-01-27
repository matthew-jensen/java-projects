/*
// this is the testing class
// DO NOT MODIFY THIS CLASS AND ITS METHODS
*/
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import java.util.Random;
import java.util.ArrayList;

public class HeapTest {
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

    public String arrayListToString(ArrayList<Integer> arr) {
        String str = "[";
        for (int i = 0; i < arr.size()-1; i++) {
            str += arr.get(i) + ", ";
        }
        str += arr.get(arr.size()-1) + "]";
        return str;
    }

    @Test public void buildTest() {
        int[] arr1 = randomArray(5);
        int[] arr2 = new int[5];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        HeapMaxRight h1 = new HeapMaxRight();
        h1.build(arr1);
        HeapMax h2 = new HeapMax();
        h2.build(arr2);

      	assertThat("\nThis is the test on your build method." +
            "\nThe given array is " + arrayToString(arr1) + "\n" +
            "\nAfter building a max heap based on the array",
            h2.getData(),
            is(h1.getData()));
    }

    @Test public void insertTest() {
        int[] arr1 = randomArray(5);
        int[] arr2 = new int[5];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        int num = 900;
        HeapMaxRight h1 = new HeapMaxRight();
        h1.build(arr1);
        h1.insert(num);
        HeapMax h2 = new HeapMax();
        h2.build(arr2);
        h2.insert(num);

        assertThat("\nThis is the test on your insert method." +
            "\nThe given heap is build from " + arrayToString(arr1) + "\n" +
            "\nAfter inserting " + num,
            h2.getData(),
            is(h1.getData()));
    }

    // test isParticallyValid
    @Test public void removeMaxTest() {
        int[] arr1 = randomArray(5);
        int[] arr2 = new int[5];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        HeapMaxRight h1 = new HeapMaxRight();
        h1.build(arr1);
        h1.removeMax();
        HeapMax h2 = new HeapMax();
        h2.build(arr2);
        h2.removeMax();

        assertThat("\nThis is the test on your removeMax method." +
            "\nThe given heap is build from " + arrayToString(arr1) + "\n" +
            "\nAfter calling removeMax for one time",
            h2.getData(),
            is(h1.getData()));
    }
    // test maintainsHeapProperty
    @Test public void maintainsHeapProperty() {
        int[] arr = randomArray(5);
        HeapMax heap = new HeapMax();
        heap.build(arr);
        this.testHeapProperty(heap.getData());
        heap.insert(900);
        this.testHeapProperty(heap.getData());
        heap.removeMax();
        this.testHeapProperty(heap.getData());
    }
    private void testHeapProperty(ArrayList<Integer> data) {
        for(int i = 0; i < data.size(); i++) {
            if( data.get( i / 2 ) != null ) {
                assertTrue("\nThis tests if heap property is maintained.\n" +
                    "Heap data: " + data + "\n" +
                    "The test element: (" + i + ")" + data.get(i) + "\n" +
                    "The parent element: (" + (i-1) /2 + ")" + data.get( (i-1) / 2 ),
                    data.get(i) <= data.get( (i-1) / 2)
                );
            }
        }

    }
}
