/**
*
* implements a max heap
*
* @author Matthew Jensen
* @date 7-18-19
*
**/

import java.util.*;

class HeapMax {

    // we go with arraylist instead of array for size flexibility
    private ArrayList<Integer> data;

    // default constructor
    public HeapMax() {
      data = new ArrayList<Integer>(0);
    }

    // DO NOT MODIFY THIS METHOD
    public ArrayList<Integer> getData() {
      return data;
    }

    // insert a new element and restore max heap property
    public void insert(int element) {
        this.data.add(element);
        int i = this.data.size() - 1;
        while( this.data.get(i) > this.data.get(parentIndex(i))){
            swap(this.data, i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    // return max
    public int getMax() {
      return data.get(0);
    }

    // remove max and restore max heap property
    public int removeMax() {
        int max = this.getMax();
        this.data.remove(0);
        heapify(this.data, 0);
        return max;
    }

    // heap builder
    // heapify is called on all non-leaf node: leaves are 1-element heaps.
    // post: max heap in this.data.
    public void build(int[] arr) {
        this.data = new ArrayList<Integer>(0);
        for(Integer element : arr) {
            this.data.add(element);
        }

        int half = this.data.size() / 2 - 1;
        for (int i = half; i >= 0; i--) { 
            heapify(this.data, i); 
        } 
    }
    
    // heapifies data by reference.
    // pre: subtrees above and beside root are already heapified.
    // post: max heap at root.
    private static void heapify(ArrayList<Integer> data, int root) {
        int largest = root; 
        int left = leftIndex(root);
        int right = rightIndex(root);
        int size = data.size();
        if( right < size && data.get(largest) < data.get(right) ) {
            largest = right;
        }
        if( left < size && data.get(largest) < data.get(left) ) {
            largest = left;
        }
        if( largest != root ) {
            swap(data, largest, root);

            // heapify sub tree 
            heapify(data, largest);
        }
    }
    // pre: i, j are valid indicies of data.
    // post: values at i, j are swapped in data.
    private static void swap(ArrayList<Integer> data, int i, int j){
        int swap = data.get(i); 
        data.set(i, data.get(j)); 
        data.set(j, swap); 
    }
    
    // pre: index is in a heap
    private static int parentIndex(int index) {
        return (index - 1) / 2;
    }
    // pre: index is in a heap
    private static int leftIndex(int index) {
        return 2 * index + 1;
    }
    // pre: index is in a heap
    private static int rightIndex(int index) {
        return 2 * index + 2;

    }

    // print out heap as instructed in the handout
    public void display() {

    }
}
