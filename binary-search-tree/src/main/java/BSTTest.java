import java.util.*;

public class BSTTest{
	public static void main(String[] args) {
        System.out.println("++++++++++++++");
        System.out.println("Stating Testing Suite");
        System.out.println("Also see jUnit automated tests in $BASE/src/test/java/*.java");
        System.out.println("++++++++++++++");
        traverse();
        insert();
        delete();
	}
    private static void delete() {
        System.out.println();
        System.out.println("++++++++++++++");
        System.out.println("Testing Delete");
        System.out.println("++++++++++++++");
        System.out.println();
        BST tree = BST.randomTree(10);
        System.out.println(tree);
        for(Integer value : tree.traverse()) {
            tree.delete(value);
            System.out.println("deleted " + value);
            System.out.println(tree);
        }
    }
    private static void insert() {
        System.out.println();
        System.out.println("++++++++++++++");
        System.out.println("Testing Insert");
        System.out.println("++++++++++++++");
        System.out.println();
        BST tree = new BST();
        for(Integer value : BST.randomArray(10)) {
            tree.insert(value);
            System.out.println("inserting " + value);
            System.out.println("search for " + value + " returns " + tree.search(value));
        }
        
    }
    private static void traverse() {
        System.out.println();
        System.out.println("++++++++++++++");
        System.out.println("Testing Traverse");
        System.out.println("++++++++++++++");
        System.out.println();
        BST tree = BST.randomTree(20);
        int previous = Integer.MIN_VALUE;
        System.out.println(tree);
        System.out.println("as array:");
        System.out.println(BST.arrayToString(tree.traverse()));
        for(Integer value : tree.traverse() ) {
            System.out.println("value " + value + " is bigger than " + previous);
            previous = value;
        }
        System.out.println("It's sorted!");
    }

	private int[] randomArray(int size) {
		// remove the two lines
		int[] arr = new int[1];
		return arr;
	}

	// the parameters and return are up to you to define; this method needs to be uncommented
	// private test() {
	//
	// }
}
