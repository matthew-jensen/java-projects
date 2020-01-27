import java.util.*;

public class BSTApp{

    public static int size = 20;
    public static void main(String[] args) {
        BST tree = BST.randomTree(size);
        System.out.println("Traversing random tree (length = " + size + "):\n");
        System.out.println(tree);
        System.out.println("The minimum absolute difference is: " + minDiff(tree.root()));
        System.out.println("In order sequence: " + BST.arrayToString(tree.traverse()));
    }

    // public helper method
    public static int minDiff(Node root) {
        return minDiff(root, Integer.MAX_VALUE);
    }

    /**
    *
    * get the minimum distance between any two node in a tree
    *
    * @param    node  tree root
    * @return   diff  minimum distance
    *
    **/
    private static int minDiff(Node node, int diff) {
        if( node != null ) {
            Node next = BST.getSuccessor(node);
            int newDiff = Integer.MAX_VALUE;
            if( next != null ) {
                newDiff = Math.abs(node.key - next.key);
                System.out.println("abs(" + node.key + " - " + next.key + ") = " + newDiff);
                diff = Math.min(minDiff(node.right), newDiff);
                diff = Math.min(minDiff(node.left), diff);
                System.out.println("smallest here: " + diff);
            }
        }
        return diff;
    }
}
