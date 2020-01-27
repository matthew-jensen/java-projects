import java.util.*;

public class HeapMaxTest{
	public static void main(String[] args) {
		// test one
		System.out.println("Testing of HeapMax starts.");
		HeapMax h = new HeapMax();
		int[] arr = randomArray(10);
		for (int num : arr) {
			h.insert(num);
		}
		h.removeMax();
		h.display();
		System.out.println("Testing of HeapMax ends.");

		// test two

		// test three
		
	}
    public static int[] randomArray(int size) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt()/1000000; // storing random integers in an array
        }
        return arr;
    }
}
