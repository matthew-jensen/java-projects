/*
*
* Matt Jensen
* CS 145, Spring 2019
* Assignment X
* 5/15/19
*
*/

// stores telephone nodes
import java.util.*;

public class PhoneBook {

    private String name;
    private TelephoneNode front;
    private TelephoneNode back;
    private int size;

    public PhoneBook( String name ) {
        this.name = name;
        this.size = 0;
    }
    public void add(TelephoneNode node) {
        this.add(this.size, node);
    }
    public void add( int index, TelephoneNode node ) {
        if( index == 0 ) {
            node.next = this.front;
            this.front = node;
        } else {
            TelephoneNode nodeBeforeIndex = this.get( index - 1 );
            //TelephoneNode nodeAtIndex = this.get( index );
            //node.next = nodeAtIndex.next;
            nodeBeforeIndex.next = node;
        }
        this.size++;
    }
    public TelephoneNode get(int index) {
        TelephoneNode currentNode = this.front;
        for( int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode; // change back.next from null to node
    }
    public int search(String name) {
        TelephoneNode currentNode = this.front;
        name = name.toLowerCase();
        String nodeName;
        for( int i = 0; i < this.size; i++) {
            nodeName = currentNode.getName().toLowerCase();
            if(nodeName.indexOf(name) >= 0) {
                return i;
            }
            currentNode = currentNode.next;
        }
        return -1;
    }
    public void transfer(int index, PhoneBook destination){
        TelephoneNode node = this.get(index);
        this.remove(index);
        destination.add(node);
    }
    public void remove(int index) {
        if( index == 0 ) {
            this.front = this.front.next;
        } else {
            TelephoneNode nodeBeforeIndex = this.get( index - 1 );
            TelephoneNode nodeAtIndex = this.get( index );
            nodeBeforeIndex.next = nodeAtIndex.next;
        }
    }
    public String getName() {
        return this.name;
    }
    public String toString() {
        String string = this.getName() + "\n";
        TelephoneNode currentNode = this.front; 
        while( currentNode != null ) {
            string += currentNode.toString() + "\n"; 
            currentNode = currentNode.next;
        }
        return string;
    }
}
