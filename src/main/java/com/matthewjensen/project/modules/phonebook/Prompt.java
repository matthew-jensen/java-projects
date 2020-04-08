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

public class Prompt {

    private static String[] options = {"Create Directory", "Add Telephone to Directory", "Remove Telephone from Directory", "Modify a Telephone entry", "Tranfer a Telephone to New Directory", "Search a Directory", "Print Directory", "Quit"};
    private static String[] values = {"c", "a", "r", "m", "t", "s", "p", "q"};
    private static boolean DEBUG = false;

    // print the introduction to the program on start up.
    public static void intro() {
        System.out.println("Welcome to my word search generator");
        System.out.println("This program allows you to generate your own word search puzzle");
    }
    public static TelephoneNode forTelephoneNodeInPhoneBook(String name, PhoneBook phonebook, Scanner console) {
        return phonebook.get(0);
    }


    // prompts from option input
    // rejects options outside of class variable
    public static char forOption( Scanner console ) {
        String question = getOptionPrompt();
        String input = prompt(question, console);
        while(  ! isOption(input) ) {
            System.out.println("Unrecognized input.");
            input = prompt(question, console);
        }
        return getOption(input);
    }
    // prompts for string
    public static String forString( Scanner console ) {
        String input = prompt("City?", console);
        return getString(input);
    }
    // prompts for telephonenode data
    public static String[] forTelephoneNodeData( String[] data, Scanner console ) {
        data[0]  = getString(prompt("Name?", console));
        data[1]  = getString(prompt("Phone?", console));
        data[2]  = getString(prompt("Address?", console));
        return data;
    }
    // prompts for telephonenode data
    public static int forTelephoneNodeIndexOf( PhoneBook phonebook, Scanner console ) {
        String input = prompt("Which Telephone Entry?", console);
        return phonebook.search(input);
    }

    // prompts for PhoneBook Name
    public static int forPhoneBookIndexOf(ArrayList<PhoneBook> phonebooks, Scanner console ) {
        String input = prompt("Which Phonebook?", console);
        return getPhoneBookIndex(phonebooks, input);
    }

    public static boolean hasPhoneBookNamed(ArrayList<PhoneBook> phonebooks, String name) {
        int index = 0;
        PhoneBook current = phonebooks.get(index);
        for(PhoneBook phonebook : phonebooks) {
            if(index != 0) {
                current = phonebooks.get(index);
            }
            if(current.getName().indexOf(name.toLowerCase()) >= 0) {
                return true;
            }
            index++;
        }
        return false;
    }

    private static int getPhoneBookIndex(ArrayList<PhoneBook> phonebooks, String name) {
        name = name.toLowerCase();
        int index = 0;
        String currentName = "";
        PhoneBook current = phonebooks.get(index);
        for(PhoneBook phonebook : phonebooks) {
            if(index != 0) {
                current = phonebooks.get(index);
            }
            currentName = current.getName().toLowerCase();
            if(currentName.indexOf(name) >= 0) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // validates if character is an option
    private static boolean isOption(String input) {
        for(int i = 0; i < values.length; i++) {
            if( input.startsWith(values[i]) ) {
                return true;
            }
        }
        return false;
    }
    private static String getString(String input) {
        return input;
    }
    // return first character of input for options.
    private static char getOption(String input) {
        return input.charAt(0);
    }
    private static String prompt(String prompt, Scanner console) {
        System.out.println(prompt);
        String response = console.nextLine();
        System.out.println();
        return response;
    }

    // returns all the possible options
    private static String getOptionPrompt() {
        String prompt = "Please select an option below:\n";
        for( int i = 0; i < options.length; i++ ) {
            prompt += options[i] + " (" + values[i] + ")\n";
        }
        return prompt;
    }
}
