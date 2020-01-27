import java.util.ArrayList;

public class Node {
    int key;
    Node left, right, parent;

    public Node() {}

    public Node(int num) {
        key = num;
        left = null;
        right = null;
        parent = null;
    }
    public ArrayList<Node> children() {
        ArrayList<Node> children = new ArrayList<Node>();
        if( this.left != null ) {
            children.add(this.left);
        }
        if( this.right != null ) {
            children.add(this.right);
        }
        return children;
    }
}
