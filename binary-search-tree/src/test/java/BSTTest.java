import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import org.junit.rules.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class BSTTest {
    @Rule
    public ErrorCollector collector= new ErrorCollector();

    @Test 
    public void buildTest() {
        BST tree = new BST();
      	assertThat("tree has no elements " + tree, tree.search(1), is(false));
    }

    /**
    *
    * Helper Tests
    *
    **/
    @Test 
    public void getMaximum() {
        BST tree = new BST();
        int[] array = randomArray(5);
        int max = array[0];
        for(Integer value : array) {
            tree.insert(value);
            max = Math.max(value, max);
            collector.checkThat("tree has max " + tree, BST.getMaximum(tree.root()).key, is(max));
        }
    }
    @Test 
    public void getMinimum() {
        BST tree = new BST();
        int[] array = randomArray(5);
        int min = array[0];
        for(Integer value : array) {
            tree.insert(value);
            min = Math.min(value, min);
            collector.checkThat("tree has min " + tree, BST.getMinimum(tree.root()).key, is(min));
        }
    }

    /**
    *
    * Insert Tests
    *
    **/
    @Test 
    public void parentReferenceSetOnInsert() {
        BST tree = BST.randomTree(15);
        for(Node node : tree.traverseNodes() ) {
            if( node.children().size() != 0 ) {
                for( Node child : node.children() ) { 
                    collector.checkThat("Child " + child + " should have parent reference " + node, child.parent.key, is(node.key));
                }
            }
        }
        
    }

    @Test 
    public void insertAndTraverseLength() {
        BST tree = new BST();
        int length = 0;
        for(Integer value : randomArray(20) ) {
            tree.insert(value);
            length++;
            collector.checkThat("tree is correct length.", tree.traverse().length, is(length) );
        }
    }

    @Test
    public void traverseIsSorted() {
        BST tree = BST.randomTree(20);
        int previous = Integer.MIN_VALUE;
        for(Integer value : tree.traverse() ) {
            collector.checkThat("value should be bigger than last", value > previous, is(true));
            previous = value;
        }
    }

    /**
    *
    * Search Tests
    *
    **/
    @Test 
    public void searchForKey() {
        BST tree = BST.randomTree(20);
        for(Integer value : tree.traverse()) {
            collector.checkThat("search for value returns true.", tree.search(value), is(true));
        }
    }

    /**
    *
    * Delete Tests
    *
    **/
    @Test 
    public void deleteNodeWithNoChildren() {
        BST tree = BST.randomTree(20);
        for(Node node : tree.traverseNodes()) {
            if( node.children().size() == 0 ) {
                tree.delete(node.key);
                collector.checkThat("node without children " + node.key + " is removed" + tree, tree.search(node.key), is(false));
            }
        }
    }
    @Test 
    public void deleteNodeWithOneChild() {
        BST tree = BST.randomTree(20);
        for(Node node : tree.traverseNodes()) {
            if( node.children().size() == 1 ) {
                tree.delete(node.key);
                collector.checkThat("node with 1 child " + node.key + " is removed" + tree, tree.search(node.key), is(false));
            }
        }
    }
    @Test 
    public void deleteNodeWithTwoChildren() {
        BST tree = BST.randomTree(8);
        for(Node node : tree.traverseNodes()) {
            if( node.children().size() == 2 ) {
                int key = node.key;
                String helper = "node 2 children " + node.key + " is removed.\n\nbefore: " + tree;
                tree.delete(key);
                helper += "\nafter: " + tree;
                collector.checkThat(helper, tree.search(key), is(false));
            }
        }
    }
    @Test 
    public void deleteShortenTree() {
        int length = 20;
        BST tree = BST.randomTree(length);
        int count = 0;
        for(Integer value : tree.traverse()) {
            collector.checkThat(count++ + " iteration. delete " + value + " shortens the tree " + tree, tree.traverse().length, is(length));
            tree.delete(value);
            length--;
        }
    }


    /**
    *
    * Helpers Methods
    *
    **/
    private int[] randomArray(int size) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt()/1000000; // storing random integers in an array
        }
        return arr;
    }

    private String arrayToString(int[] arr) {
        String str = "[";
        for (int i = 0; i < arr.length-1; i++) {
            str += arr[i] + ", ";
        }
        str += arr[arr.length-1] + "]";
        return str;
    }

    private String arrayListToString(ArrayList<Integer> arr) {
        String str = "[";
        for (int i = 0; i < arr.size()-1; i++) {
            str += arr.get(i) + ", ";
        }
        str += arr.get(arr.size()-1) + "]";
        return str;
    }


}
