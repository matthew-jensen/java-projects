/*
*
* Matt Jensen
* CS 145, Spring 2019
* Assignment X
* 5/15/19
*
*/

import java.util.*;
import java.awt.*;
import java.io.*;

public class PhoneBookClient {

    // holds the clients phonebooks
    private static ArrayList<PhoneBook> phonebooks = new ArrayList<PhoneBook>();

    // controls the command prompt and manages class relationships.
    public static void main( String[] args ) {
        //seed();
        Scanner console = new Scanner( System.in );
        Prompt.intro();
        char option = Prompt.forOption(console);
        String name;
        PhoneBook phonebook = null;
        TelephoneNode currentNode = null;
        String[] data;
        int index;
        while(option != 'q') {
            if( option == 'c') {
                name = Prompt.forString(console);
                System.out.println(name);
                PhoneBook newPhonebook = new PhoneBook( name );
                phonebooks.add(newPhonebook);
            } else if( phonebooks.size() < 1 ) {
                System.out.println("No phonebooks yet.");
                System.out.println();
                option = Prompt.forOption(console);
                continue;
            }
            if( option == 'a' ) {
                phonebook = phonebooks.get(Prompt.forPhoneBookIndexOf(phonebooks, console));
                data = new String[3];
                TelephoneNode newNode = new TelephoneNode(Prompt.forTelephoneNodeData(data, console));
                phonebook.add(newNode);
            }
            if( option == 'r' ) {
                index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                if( index != -1 ) {
                    phonebook = phonebooks.get(index);
                }
                index = Prompt.forTelephoneNodeIndexOf(phonebook, console);
                if( index != -1 ) {
                    phonebook.remove(index);
                }
            }
            if( option == 'm' ) {
                index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                if( index != -1 ) {
                    phonebook = phonebooks.get(index);
                    index = Prompt.forTelephoneNodeIndexOf(phonebook, console);
                    if( index != -1 ) {
                        currentNode = phonebook.get(index);
                        data = new String[3];
                        data = Prompt.forTelephoneNodeData(data, console);
                        currentNode.setAll(data);
                    }
                }
            }
            if( option == 'p' ) {
                index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                if( index != -1 ) {
                    phonebook = phonebooks.get(index);
                }
                System.out.println("PhoneBook:");
                System.out.println(phonebook);
            }
            if( option == 's' ) {
                index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                if( index != -1 ) {
                    phonebook = phonebooks.get(index);
                    index = Prompt.forTelephoneNodeIndexOf(phonebook, console);
                    if( index != -1 ) {
                        currentNode = phonebook.get(index);
                        System.out.println("Entry Found:");
                        System.out.println(currentNode);
                    }
                }
            }
            if( option == 't' ) {
                index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                if( index != -1 ) {
                    PhoneBook fromPhoneBook = phonebooks.get(index);
                    index = Prompt.forTelephoneNodeIndexOf(fromPhoneBook, console);
                    if( index != -1 ) {
                        int nodeIndex = index;
                        currentNode = fromPhoneBook.get(index);
                        System.out.println("Destination:");
                        index = Prompt.forPhoneBookIndexOf(phonebooks, console);
                        PhoneBook toPhoneBook = phonebooks.get(index);
                        fromPhoneBook.transfer(nodeIndex, toPhoneBook);
                    }
                }
            }
            System.out.println();
            option = Prompt.forOption(console);
        }
        System.out.println("Goodbye!");
        return;
    }
    private static void seed() {
        PhoneBook book = new PhoneBook("Seattle");

        String[] phoneName = new String[]{"Matt", "555555555", "1234 High St."};
        book.add(new TelephoneNode(phoneName));

        phoneName = new String[]{"Other", "2525252525", "5555 Main"};
        book.add(new TelephoneNode(phoneName));

        phoneName = new String[]{"And Another", "111111111", "5555 Main"};
        book.add(new TelephoneNode(phoneName));

        phonebooks.add(book);

        book = new PhoneBook("Bellingham");
        phoneName = new String[]{"Another", "000000000", "5555 Main"};
        book.add(new TelephoneNode(phoneName));

        phonebooks.add(book);

    }
}
