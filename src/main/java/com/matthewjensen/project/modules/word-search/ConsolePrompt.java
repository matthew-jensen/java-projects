/*
*
* Matt Jensen
* CS145
* Assignment 1
* 4/25/19
* 
*/

import java.util.*;
import java.awt.*;
import java.io.*;

public class ConsolePrompt {

    private static String[] options = {"Generate a new word search", "Print out your word search", "Show the solutions to your word search", "Quit the program"};
    private static String[] values = {"g", "p", "s", "q"};
    private static boolean DEBUG = false;

    // print the introduction to the program on start up.
    public static void intro() {
        System.out.println("Welcome to my word search generator");
        System.out.println("This program allows you to generate your own word search puzzle");
    }

    // prompts for words. 
    // puts them in an array.
    // pulls from a file if not null
    public static String[] forWords(int count, String fileName,  Scanner console) throws FileNotFoundException {
        String[] words = new String[count];
        if(fileName != null) {
            Scanner scanner = new Scanner(new File("words.txt"));
            for(int i = 0; i < words.length; i++) {
                words[i] = scanner.nextLine();
            }
        }
        else {
            String question = "Next word:";
            String input = "";
            for(int i = 0; i < words.length; i++) {
                input = prompt(question, console);
                words[i] = input;
            }
        
        }
        return words;
    }

    // prompts for filename
    // TODO: check for file existance
    public static String forFileName( Scanner console) {
        String question = "Generate from file?";
        boolean fromFile = forBoolean(question, console);
        if(!fromFile) {
            return null;
        }
        question = "Filename?";
        String fileName = prompt(question, console);
        while(  ! isFile(fileName) ) {
            System.out.println("Unrecognized input.");
            fileName = prompt(question, console);
        }
        return fileName;
    }
    
    // prompts for word cound
    // prompts again if not an integer
    public static int forWordCount(Scanner console) {
        String question = "How many words would you like to include in the word search?";
        String input = prompt(question, console);
        while(  ! isInteger(input) ) {
            System.out.println("Unrecognized input.");
            input = prompt(question, console);
        }
        return getInteger(input);
    }

    // TODO: validate if an integer
    private static boolean isInteger(String input) {
        return true;
    }
    // TODO: validate if file exists
    private static boolean isFile(String input) {
        return true;
    }
    // gets integer form input
    private static int getInteger(String input) {
        return Integer.parseInt(input);
    }

    private static String getString(String input) {
        return input;
    }

    private static String prompt(String prompt, Scanner console) {
        System.out.println(prompt);
        String response = console.next();
        System.out.println();
        return response;
    }
    // returns all the possible options
    public static String getOptionPrompt() {
        String prompt = "Please select an option below:\n";
        for( int i = 0; i < options.length; i++ ) {
            prompt += options[i] + " (" + values[i] + ")\n";
        }
        return prompt;
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
    // validates if character is an option
    private static boolean isOption(String input) {
        for(int i = 0; i < values.length; i++) {
            if( input.startsWith(values[i]) ) {
                return true;
            }
        }
        return false;
    }
    // return first character of input for options.
    private static char getOption(String input) {
        return input.charAt(0);
    }
    
    // prompts for boolean value
    public static boolean forBoolean( String prompt, Scanner console ) {
        String input = prompt(prompt, console);
        while(  ! isBooleanInput(input) ) {
            System.out.println("Unrecognized input.");
            input = prompt(prompt, console);
        }
        return getBooleanInput(input);
    }
    // validates boolean value
    private static boolean isBooleanInput(String input) {
        return input.startsWith("y") || input.startsWith("Y") || input.startsWith("n") || input.startsWith("N");
    }
    // convert string input to boolean value.
    private static boolean getBooleanInput(String input) {
        if(input.startsWith("y") || input.startsWith("Y")) {
            return true;
        }
        if(input.startsWith("n") || input.startsWith("N")) {
            return false;
        }
        return false;
    }
}
