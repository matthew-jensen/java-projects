package com.matthewjensen.project.modules;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import java.util.*;

public class QuestionNodeTest {
    @Test
    public void exists() {
        QuestionNode node = new QuestionNode("tail"); 
        //assertEquals("tail", node.getQuestion());
    }
}
