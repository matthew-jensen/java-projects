package com.matthewjensen.project.modules;
/**
*
* Matt Jensen
* CS145 - Lab 6
* 5/30/19
*
*/

import java.io.*;
import java.util.*;

public class QuestionTree {
    
    private QuestionNode root;
    private UserInterface ui;
    private Stack<QuestionNode> unanswered;        
    private int totalGames;
    private int gamesWon;
    private QuestionNode lastQuestion;

    public QuestionTree(UserInterface ui) {
        this.setUserInterface(ui);
        this.setRoot(null);
    }
    public QuestionTree(UserInterface ui, QuestionNode root) {
        this.setUserInterface(ui);
        this.setRoot(root);
    }

    /**
    *
    * Public Methods.
    *
    */
    public void replaceYes(QuestionNode newQuestion, QuestionNode parent) {
        parent.addYes(newQuestion);
    }

    public void save(PrintStream output) {
        this.print(output, this.getRoot());
        //output.print("hello");
    }
    private void print(PrintStream output, QuestionNode current) {
        if(current == null) {
            return;
        } else {
            output.print(current.toString(true));
            if(current.hasYes()){
                output.println();
                this.print(output, current.getYes());
            } 
            if(current.hasNo()){
                output.println();
                this.print(output, current.getNo());
            } 
        }
        return;

    }
    public void play() {
        this.lastQuestion = null;
        if( this.getRoot() == null ) {
            this.ui.print("First question?");
            this.setRoot(new QuestionNode(this.ui.nextLine(), true));
            this.ui.print("If yes?");
            this.getRoot().addYes(new QuestionNode(this.ui.nextLine()));
            this.ui.print("If no?");
            this.getRoot().addNo(new QuestionNode(this.ui.nextLine()));
        }
        QuestionNode guess = this.guess(this.getRoot());
        this.ui.print("Would your object happen to be " + guess.toString() + "?");
        boolean correctGuess = this.ui.nextBoolean();
        if( correctGuess ) {
            this.ui.println("I win!");
            gamesWon++;
        } else {
            this.ui.print("I lose.");
            this.addDistinction(guess);
        }
        totalGames++;
    }
    private QuestionNode getActual(QuestionNode guess) {
        this.ui.print("What is your object?");
        QuestionNode actual = new QuestionNode(this.ui.nextLine(), false);
        return actual;
    }   
    private QuestionNode getNewQuestion(QuestionNode guess) {
        this.ui.println("Type a yes/no question to distinguish your item from " + guess.toString() + ":");
        QuestionNode newQuestion = new QuestionNode(this.ui.nextLine(), true);
        return newQuestion;
    }   
    private boolean getReplaceYes(QuestionNode guess, QuestionNode newQuestion) {
        this.ui.println("And what is the answer for " + newQuestion + "?");
        boolean replaceYes = this.ui.nextBoolean();
        return replaceYes;
    }   
    private void addDistinction(QuestionNode guess) {
        QuestionNode actual = this.getActual(guess);
        QuestionNode newQuestion = this.getNewQuestion(guess);
        boolean replaceYes = this.getReplaceYes(guess, newQuestion);
        if( replaceYes ) {
            newQuestion.addYes(actual);
            newQuestion.addNo(guess);
        } else {
            newQuestion.addNo(actual);
            newQuestion.addYes(guess);
        }
        this.replaceYes(newQuestion, this.lastQuestion);
    }
    
    private QuestionNode guess(QuestionNode current) {
        if( current == null || current.isAnswer() ) {
            return current;
        } else {
            this.ui.print(current.toString());
            this.lastQuestion = current;
            if( this.ui.nextBoolean() ) {
                return this.guess(current.getYes());
            } else {
                return this.guess(current.getNo());
            }
        }
    }
    public int totalGames() {
        return this.totalGames;
    }
    public int gamesWon() {
        return this.gamesWon;
    }
    public QuestionNode getRoot() {
        return this.root;
    }

    /**
    *
    * Private Methods.
    *
    */

    private void setRoot(QuestionNode root) {
        this.root = root;
    }

    // pre: ui not null
    private void setUserInterface(UserInterface ui) {
        if( ui == null) {
            throw new IllegalArgumentException();
        }
        this.ui = ui;
    }

    // pre: coming from a file.
    private boolean containsQuestion(String line) {
        return line.charAt(0) == 'Q';
    }

    public void load(Scanner input) {
        // base case
        if( ! input.hasNextLine() ) {
            return;
        }
        Stack<QuestionNode> remaining = new Stack<QuestionNode>();
        this.build(input, remaining);
        return;
    }
    private void build(Stack<QuestionNode> remaining, QuestionNode root) {

    }
    private void build(Scanner input, Stack<QuestionNode> remaining) {
        // base case
        if( ! input.hasNextLine() ) {
            if( ! remaining.empty() ) {
                throw new IllegalArgumentException("question unanswered");
            }
            return;
        } else {
            String line = input.nextLine();
            QuestionNode current = new QuestionNode(line, containsQuestion(line));
            if( remaining.empty() ) {
                if( this.getRoot() == null ) {
                    this.setRoot(current);
                } else {
                    throw new IllegalArgumentException("bad root element");
                }
            } else {
                QuestionNode last = remaining.pop();
                last.addAnswer(current);
                if(last.needsAnswer() ) {
                    remaining.add(last);
                }
            }
            if( ! current.isAnswer() ) {
                remaining.add(current);
            }
            this.build(input, remaining);
        }
        return;
    }
}
