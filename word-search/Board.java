/*
*
* Matt Jensen
* CS145
* Assignment 1
* 4/17/19
* 
*/
import java.util.*;
import java.awt.*;

public class Board {
    protected static enum Direction {
        DOWN, UP, LEFT, RIGHT
    };
    protected char[][] board;
    protected char[][] solution;
    protected int size;
    protected String[] unplacedWords;
    protected String[] placedWords;
    protected String[] allWords;
    public Board(String[] words) {
        setWords(words);
        this.size = getMaxWordSize();
        this.board = new char[size][size];
        this.solution = new char[size][size];
        placeWords();
    }

    public void placeWords() {
        for( int i = 0; i < unplacedWords.length; i++) {
            placeWord(unplacedWords[i]);
        }
    }


    /**
    *
    * places a single word on the board.
    * picks a random index i, j
    * picks a random direction
    *
    * @return  boolean  the placement successful    
    */
    private void placeWord(String inputWord) {

        Direction direction = randomDirection();
        int rowIndex = (int) (Math.random() * (this.getSize()));
        int columnIndex = (int) (Math.random() * (this.getSize()));

        int placementTries = 0;
        for(int tries = 0; tries < 100; tries++) {
            if(isPlaced(inputWord, rowIndex, columnIndex, direction)){
                for(int i = 0; i < this.placedWords.length; i++) {
                    if(this.placedWords[i] == null) {
                        placedWords[i] = inputWord;
                        return;
                    }
                }
                return;
            }
            direction = randomDirection();
            rowIndex = (int) (Math.random() * (this.getSize()));
            columnIndex = (int) (Math.random() * (this.getSize()));
        }
    }
    private boolean isPlaced(String word, int rowIndex, int columnIndex, Direction direction) {
        char[][] currentBoard = copyBoard(this.getBoard());
        if( direction == Direction.DOWN ) {
            for( int i = 0; i < word.length(); i++) {
                if( currentBoard[i][columnIndex] == 0) {
                    currentBoard[i][columnIndex] = word.charAt(i);

                } else if( currentBoard[i][columnIndex] == word.charAt(i)){
            
                    currentBoard[i][columnIndex] = word.charAt(i);
                }
                else {
                    return false;
                }
            }
        }
        if( direction == Direction.UP ) {
            for( int i = 0; i < word.length(); i++) {
                if( currentBoard[word.length() - i - 1][columnIndex] == 0) {
                    currentBoard[word.length() - i - 1][columnIndex] = word.charAt(i);
                } else if( currentBoard[word.length() - i - 1][columnIndex] == word.charAt(i)){
                    currentBoard[word.length() - i - 1][columnIndex] = word.charAt(i);
                }
                else {
                    return false;
                }
            }
        }
        if( direction == Direction.LEFT ) {
            for( int i = 0; i < word.length(); i++) {
                if( currentBoard[rowIndex][word.length() - i - 1] == 0) {
                    currentBoard[rowIndex][word.length() - i - 1] = word.charAt(i);
                } else if( currentBoard[rowIndex][word.length() - i - 1] == word.charAt(i)){
                    currentBoard[rowIndex][word.length() - i - 1] = word.charAt(i);
                }
                else {
                    return false;
                }
            }
        }
        if( direction == Direction.RIGHT ) {
            for( int i = 0; i < word.length(); i++) {
                if( currentBoard[rowIndex][i] == 0) {
                    currentBoard[rowIndex][i] = word.charAt(i);

                } else if( currentBoard[rowIndex][i] == word.charAt(i)){
            
                    currentBoard[rowIndex][i] = word.charAt(i);
                }
                else {
                    return false;
                }
            }
        }
        setBoard(currentBoard);
        return true;
    }
    public char[][] getBoard() {
        return this.board;
    }
    private void setBoard(char[][] newBoard) {
        this.board = copyBoard(newBoard);
        this.solution = copyBoard(newBoard);
    }
    private static char[][] copyBoard(char[][] old) {
        if (old == null) {
            return null;
        }
        char[][] result = new char[old.length][];
        for (int r = 0; r < old.length; r++) {
            result[r] = old[r].clone();
        }
        return result;
    }

    /**
    *
    * get board size based on largest word input.
    *
    * @return  void    
    */
    private int getMaxWordSize() {
        int max = 0;
        for( int i = 0; i < allWords.length; i++) {
            if( allWords[i].length() > max ) {
                max = allWords[i].length();
            }
        }
        return max;
    }
    public void setWords(String[] words){
        this.allWords = words;
        this.unplacedWords = words;
        this.placedWords = new String[words.length];
    }
    public int getSize() {
        return this.size;
    }

    public String toString() {
        return "Hidden Words:\n" 
            + this.placedWordsToString() + "\n" 
            + boardToString(this.getBoard(), false);
    }
    public String solutionToString() {
        return "Words:\n" 
            + this.placedWordsToString() + "\n" 
            + boardToString(this.getBoard(), true);
    }
    public String[] getPlacedWords() {
        return this.placedWords;
    }
    private String placedWordsToString() {
        String result = "";
        for(int i = 0; i < this.getPlacedWords().length; i++) {
            if( this.getPlacedWords()[i] != null ) {
                result = result +  "| " + this.getPlacedWords()[i] + " ";
            }
        }
        return result + "|\n";
    }
    private static String boardToString(char[][] boardForPrint, boolean solutions) {
        char[] CHAR_ALPHA = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random(CHAR_ALPHA.length);
        String text = "";
        for(int i = 0; i < boardForPrint.length; i++) {
            text = text + "| ";
            for(int j = 0; j < boardForPrint[i].length; j++) {
                if(boardForPrint[i][j] == 0) {
                    if(solutions) {
                        text = text + "- ";
                    } else {
                        int randomCharIndex = random.nextInt(CHAR_ALPHA.length);
                        char randomChar = CHAR_ALPHA[randomCharIndex];
                        text = text + randomChar + " ";
                    }
                } else {
                    text = text + boardForPrint[i][j] + " ";
                }
            }
            text = text + "|\n";
        }
        return text;

    }

    protected static Direction randomDirection() {
        int index = (int) (Math.random() * 4);
        
        if( index == 0) {
            return Direction.UP;
        }
        if( index == 1) {
            return Direction.DOWN;
        }
        if( index == 2) {
            return Direction.RIGHT;
        }
        if( index == 3) {
            return Direction.LEFT;
        }
        return Direction.UP;
    }
}
