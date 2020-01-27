import java.util.*;
import java.lang.StringBuilder;

class BST {
    public static void main(String[] args) {
    }
    // do not change this
    private Node root;
    public Node root() {
        return this.root;
    }

    // do not change this
    public BST() {
        root = null;
    }

    /**
    *
    * determine if the value exists in the tree.
    *
    * @param    target  value to be searched for in keys of the tree.
    * @return   exists  true if target in tree.
    *
    **/
    public boolean search(int target) {
        for( Integer value : this.traverse() ) {
            if( value == target ) {
                return true;
            }
        }
        return false;
    }

    /**
    *
    * insert a value into the tree
    *
    * @param    target    key for insertion
    * @return   node    root of tree
    *
    **/
    public Node insert(int target) {
        this.root = insert(this.root, target); 
        return this.root;
    }

    /**
    *
    * deletes a value from the tree
    *
    * @param    target  value to be deleted from tree
    * @return   node    root node of tree
    *
    **/
    public Node delete(int target) {
        delete(target, this.root);
        return this.root;
    }
    /**
    *
    * get the max value in a tree starting at node
    *
    * @param    node    root of tree to search
    * @return   max     max node in tree
    *
    **/
    public static Node getMaximum(Node node) {
        if( node != null ) {
            if( node.right != null ) {
                node = getMaximum(node.right);
            }
        }
        return node;
    }

    /**
    *
    * get the min value in a tree starting at node
    *
    * @param    node    root of tree to search
    * @return   min     min node in tree
    *
    **/
    public static Node getMinimum(Node node) {
        if( node != null ) {
            if( node.left != null ) {
                node = getMinimum(node.left);
            }
        }
        return node;
    }

    /**
    *
    * deletes a value from the tree starting at the root
    *
    * @param    target  value to be deleted from tree
    * @param    node  root of tree from which value will be deleted
    * @return   void
    *
    **/
    private void delete(int target, Node node) {
        if(node != null) {
            if( node.key == target){
                if( node.children().size() == 0) {
                    replace_node_in_parent(node, null);
                } else if( node.children().size() == 1 ) {
                    if( node.right != null ) {
                        replace_node_in_parent(node, node.right);
                    } else {
                        replace_node_in_parent(node, node.left);
                    }
                } else {
                    Node neighbor = getSuccessor(node);
                    int value = neighbor.key;
                    delete(value);
                    node.key = value;
                }
            } else if( target < node.key ) {
                delete(target, node.left);
            } else {
                delete(target, node.right);
            } 
        }
    }
    /**
    *
    * inserts a value into a tree
    *
    * @param    target  value to be inserted from tree
    * @param    node  root of tree in which to place the value
    * @return   node root of the tree
    *
    **/
    private Node insert(Node node, int target) {
        if( node == null ) {
            node = new Node(target);
        } else if ( target < node.key ) {
            node.left = insert(node.left, target);
            node.left.parent = node;
        } else if ( target > node.key ) {
            node.right = insert(node.right, target);
            node.right.parent = node;
        }
        return node;
    }

    /**
    *
    * replaces a references in the parent with a new subtree
    *
    * @param    node  node being replaced
    * @param    node  node being transplanted
    * @return   void
    *
    **/
    private void replace_node_in_parent(Node node, Node replacement) {
        if( node.parent == null ) {
            this.root = replacement;
        } else {
            if( node.parent.left != null && node.parent.left.key == node.key) {
                node.parent.left = replacement;
            } else if( node.parent.right != null && node.parent.right.key == node.key) {
                node.parent.right = replacement;
            }
        }
        if ( replacement != null ) {
            replacement.parent = node.parent;
        }
    }

    /**
    *
    * get the next largest value
    *
    * @param    node  current node
    * @return   node  next node
    *
    **/
    public static Node getSuccessor(Node node) {
        Node cur = null;
        if( node.right != null ) {
            return getMinimum(node.right);
        }
        cur = node.parent;
        while( cur != null && node == cur.right ) {
            node = cur;
            cur = cur.parent;
        }
        return cur;
    }
    /**
    *
    * get the next smallest value
    *
    * @param    node  current node
    * @return   node  previous node
    *
    **/
    public static Node getPredecessor(Node node) {
        Node cur = null;
        if( node.left != null ) {
            return getMaximum(node.left);
        }
        cur = node.parent;
        while( cur != null && node == cur.left ) {
            node = cur;
            cur = cur.parent;
        }
        return cur;
    }

    // in-order traversal into an array
    public int[] traverse() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        return traverse(this.root, list);
    }
    private static int[] traverse(Node node, ArrayList<Integer> list) {
        if( node != null ) {
            traverse(node.left, list); 
            list.add(node.key);
            traverse(node.right, list); 
        }
        return convertIntegers(list);
    }

    // in-order traversal of nodes
    public List<Node> traverseNodes() {
        ArrayList<Node> list = new ArrayList<Node>();
        return traverseNodes(this.root, list);
    }
    private static List<Node> traverseNodes(Node node, List<Node> list) {
        if( node != null ) {
            traverseNodes(node.left, list); 
            list.add(node);
            traverseNodes(node.right, list); 
        }
        return list;
    }
    public Node get(int value) {
        return get(value, this.root());
    }
    private Node get(int value, Node node) {
        if( node == null ) {
            return null;
        }
        if( value < node.key ) {
            return get(value, node.left);
        } 
        if ( value > node.key ) {
            return get(value, node.right);
        }
        return node;
    }

    /**
    *
    *  Helper Methods.
    *
    **/
    public String toString() {
        StringBuilder builder = new StringBuilder("\n");
        builder.append("\n");
        builder.append("TREE:   \n");
        if( this.root == null ) {
            return "[ ]";
        }
        //return Arrays.toString(this.traverse());
        this.nodeToString(this.root, builder, "", "", "root");
        builder.append("\n");
        return builder.toString();
    }
    // see: https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram/8948691#8948691
    private void nodeToString(Node node, StringBuilder buffer, String prefix, String childrenPrefix, String post) {
        buffer.append("│  " + prefix);
        buffer.append(node.key);
        if( post != "" ) {
            buffer.append(" (" + post + ")");
        }
        buffer.append("\n");
        Iterator<Node> iterator = node.children().iterator(); // asc values of node children
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (iterator.hasNext()) {
                this.nodeToString(next, buffer, childrenPrefix + "L: ├── ", childrenPrefix + "   │   ", "");
            } else {
                this.nodeToString(next, buffer, childrenPrefix + "R: └── ", childrenPrefix + "       ", "");
            }
        }
    }
    private static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
    public static BST randomTree(int size) {
        BST tree = new BST();
        int[] array = randomArray(size);
        for(Integer value : array) {
            tree.insert(value);
        }
        return tree;
    }
    public static int[] randomArray(int size) {
        Random rd = new Random(); // creating Random object
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rd.nextInt()/1000000; // storing random integers in an array
        }
        return arr;
    }
    public static String arrayToString(int[] arr) {
        String str = "[";
        for (int i = 0; i < arr.length-1; i++) {
            str += arr[i] + ", ";
        }
        str += arr[arr.length-1] + "]";
        return str;
    }
}
