package com.matthewjensen.project.modules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class QuestionTreeTest {
    @Test
    public void testExistance() {
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        assertTrue(true);
    }

    @Test
    public void load() {
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        String file = "Q:Is it an animal?\nA:cat\nQ:Does it go on your feet?\nA:shoe\nA:computer";
        tree.load(new Scanner(file));
        assertEquals("Is it an animal?", tree.getRoot().toString());
    }

    @Test
    public void replace() {
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        String file = "Q:Is it an animal?\nA:cat\nA:table?";
        tree.load(new Scanner(file));
        QuestionNode newQuestion = new QuestionNode("Is it black?", true);
        tree.replaceYes(newQuestion, tree.getRoot());
        assertEquals("Is it black?", tree.getRoot().getYes().toString());
    }

    @Test
    public void otherload() throws FileNotFoundException {
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        tree.load(new Scanner(new File("question2.txt")));
        assertEquals("Is it an animal?", tree.getRoot().toString());
    }
    @Test
    public void save() throws FileNotFoundException, IOException {
        // setup
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        tree.load(new Scanner(new File("question1.txt")));
        PrintStream out = new PrintStream(result);
        tree.save(out);
        String expected = "Q:Is it an animal?\nA:cat\nQ:Does it go on your feet?\nA:shoe\nA:computer";
        assertEquals(expected, result.toString());
        result.close();
    }
    @Test
    public void saveLonger() throws FileNotFoundException, IOException {
        // setup
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        tree.load(new Scanner(new File("question2.txt")));
        PrintStream out = new PrintStream(result);
        tree.save(out);
        String expected = "Q:Is it an animal?\nQ:Can it fly?\nA:bird\nQ:Does it have a tail?\nA:mouse\nA:spider\nQ:Does it have wheels?\nA:bicycle\nQ:Is it nice?\nA:TA\nA:teacher";
        assertEquals(expected, result.toString());
        result.close();
    }
    public void play() throws FileNotFoundException, IOException {
        // setup
        UserInterface ui = new QuestionMain();
        QuestionTree tree = new QuestionTree(ui); 
        tree.load(new Scanner(new File("question2.txt")));
        ByteArrayOutputStream result = new ByteArrayOutputStream();
    }
}
