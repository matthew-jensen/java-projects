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

public class WordSearch {

    protected static boolean DEBUG;
    protected static Board board;
    private static int wordCount = 20;

    public static void main( String[] args ) throws FileNotFoundException {
        Scanner console = new Scanner( System.in );
        ConsolePrompt.intro();
        char option = ConsolePrompt.forOption(console);
        while(option != 'q') {
            if(option == 'g') {
                String fileName = ConsolePrompt.forFileName(console);
                generate(fileName, console);
            }
            if(option == 's') {
                showSolution(console);
            }
            if(option == 'p') {
                showBoard(console);
            }
            System.out.println();
            option = ConsolePrompt.forOption(console);
        }
        System.out.println("Goodbye!");
        return;
    }

    /**
    *
    * generates a new board from the set words.
    *
    * @param   console console for printing prompts to.
    * @return  void    
    */
    protected static void generate(String fromFile, Scanner console) throws FileNotFoundException {
        int wordCount = ConsolePrompt.forWordCount(console);
        board = new Board(
            ConsolePrompt.forWords(wordCount, fromFile, console)
        );
    }
    



    /**
    *
    * prints the solution (x's for letters between words)
    *
    * @param   console console for printing prompts to.
    * @return  void    
    */
    protected static void showSolution(Scanner console) {
        System.out.println(board.solutionToString());
    }

    /**
    *
    * prints the entire board with words.
    *
    * @param   console console for printing prompts to.
    * @return  void    
    */
    protected static void showBoard(Scanner console) {
        System.out.println(board);
    }

}
