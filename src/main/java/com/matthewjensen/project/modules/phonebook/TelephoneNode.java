/*
*
* Matt Jensen
* CS 145, Spring 2019
* Assignment X
* 5/15/19
*
*/

// an individual telephone in a phone book.
public class TelephoneNode {

    private String name;
    private String number;
    private String address;

    public TelephoneNode next;

    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getName() {
        return this.name;
    }

    public TelephoneNode() {
        this(null, null);
    }
    public void setAll(String[] data){
        this.setData(data);
    }

    public TelephoneNode(String[] data, TelephoneNode next) {
        if( data != null ) {
            this.setData(data);
        }
        this.next = next;
    }
    public TelephoneNode(String[] data) {
        this(data, null);
    }
    private void setData(String[] data) {
        this.name = data[0];
        this.number = data[1];
        this.address = data[2];
    }
    public String toString() {
        return this.name + " " + this.address + " " + this.number;
    }
}
