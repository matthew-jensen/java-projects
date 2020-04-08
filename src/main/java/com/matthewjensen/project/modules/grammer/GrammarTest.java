import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import java.util.*;

public class GrammarTest {

    @Test
    public void testMixedNonAndTerminals() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:<b> <c>|<b>");
        grammar.add("<b>:Test");
        grammar.add("<c>:");
        GrammarSolver solver = new GrammarSolver(grammar);
        String[] result = solver.generate("<a>", 1);
        String[] expected = new String[1]; 
        Arrays.fill(expected, "Test");
        assertArrayEquals(expected, result);
    }
    @Test(expected = IllegalArgumentException.class) 
    public void testGenerateNonTermDoesntExist() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test|:Other");
        GrammarSolver solver = new GrammarSolver(grammar);
        solver.generate("<b>", 1);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGenerateTimesInvalid() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test|:Other");
        GrammarSolver solver = new GrammarSolver(grammar);
        solver.generate("<a>", 0);
        solver.generate("<a>", -1);
    }

    @Test
    public void testGenerate() {
        String[] expected = new String[2]; 
        Arrays.fill(expected, "Test");

        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test");
        GrammarSolver solver = new GrammarSolver(grammar);

        String[] result = solver.generate("<a>", 2);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testMultipleRulePaths() {
        String[] expected = new String[3]; 
        Arrays.fill(expected, "Test Nest");

        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:<b> <c>");
        grammar.add("<b>:Test");
        grammar.add("<c>:Nest");
        GrammarSolver solver = new GrammarSolver(grammar);

        String[] result = solver.generate("<a>", 3);
        assertArrayEquals(expected, result);
    }
    @Test
    public void testMultipleSymbolsAsRules() {
        String[] expected = new String[3]; 
        Arrays.fill(expected, "Nest Nest");

        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:<b> <c>");
        grammar.add("<b>:<c>");
        grammar.add("<c>:Nest");
        GrammarSolver solver = new GrammarSolver(grammar);

        String[] result = solver.generate("<a>", 3);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testGenerateNestedTwice() {
        String[] expected = new String[3]; 
        Arrays.fill(expected, "Nest");

        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:<b>");
        grammar.add("<b>:<c>");
        grammar.add("<c>:Nest");
        GrammarSolver solver = new GrammarSolver(grammar);

        String[] result = solver.generate("<a>", 3);
        assertArrayEquals(expected, result);
    }
    @Test
    public void testGenerateNested() {
        String[] expected = new String[2]; 
        Arrays.fill(expected, "Nest");

        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:<b>");
        grammar.add("<b>:Nest");
        GrammarSolver solver = new GrammarSolver(grammar);

        String[] result = solver.generate("<a>", 2);
        assertArrayEquals(expected, result);
    }
    @Test
    public void testGrammarListImmutable() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test");
        GrammarSolver solver = new GrammarSolver(grammar);
        String expected = grammar.get(0);
        String actual = solver.getGrammarList().get(0);
        assertEquals("got back different than instantiation.", expected, actual);
        assertArrayEquals("different grammars.", grammar.toArray(), solver.getGrammarList().toArray());
    }
    @Test
    public void testGrammarListImmutableWithPipe() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test|Other");
        GrammarSolver solver = new GrammarSolver(grammar);
        assertArrayEquals("different grammars.", grammar.toArray(), solver.getGrammarList().toArray());
    }

    @Test
    public void testNonTerminalExtract() {
        String entry = "<a>:Test";
        String expected = "<a>";
        String actual = GrammarSolver.nonTerminal(entry);
        assertEquals("failure - extraction failed", expected, actual);
    }
    @Test
    public void testRuleExtractContent() {
        String entry = "<a>:Test";
        List<String> expected = new ArrayList<String>();
        expected.add("Test");
        List<String> actual = GrammarSolver.terminals(entry);
        assertArrayEquals("failure - rule extraction failed", expected.toArray(), actual.toArray());
    }
    @Test
    public void testRuleCount() {
        String entry = "<a>:Test";
        List<String> expected = new ArrayList<String>();
        expected.add("Test");
        List<String> actual = GrammarSolver.terminals(entry);
        assertEquals("rule count different", expected.size(), actual.size());
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testNoColon() { 
        List<String> grammar = new ArrayList<String>();
        grammar.add("test");
        GrammarSolver solver = new GrammarSolver(grammar);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testDuplicateGrammar() { 
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test|Other");
        grammar.add("<a>:Test|Other");
        GrammarSolver solver = new GrammarSolver(grammar);
    }

    @Test(expected = IllegalArgumentException.class) 
    public void testGrammarContainsSingleColon() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test|:Other");
        GrammarSolver solver = new GrammarSolver(grammar);
    }

    @Test
    public void testGrammarContains() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test");
        GrammarSolver solver = new GrammarSolver(grammar);
        assertEquals(true, solver.grammarContains("<a>"));
        assertEquals(false, solver.grammarContains("a"));
        assertEquals(false, solver.grammarContains("<b>"));
    }
    @Test
    public void testGrammarContainsCaseInsensitive() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test");
        GrammarSolver solver = new GrammarSolver(grammar);
        assertEquals(true, solver.grammarContains("<a>"));
        assertEquals(false, solver.grammarContains("<A>"));
    }

    @Test
    public void testGetSymbols() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<a>:Test");
        grammar.add("<b>:Test|Other");
        GrammarSolver solver = new GrammarSolver(grammar);
        assertEquals("[<a>, <b>]", solver.getSymbols());
    }
    @Test
    public void testGetSymbolsSorted() {
        List<String> grammar = new ArrayList<String>();
        grammar.add("<C>:Test|Other");
        grammar.add("<A>:Test");
        grammar.add("<b>:Test|Other");
        GrammarSolver solver = new GrammarSolver(grammar);
        assertEquals("[<A>, <C>, <b>]", solver.getSymbols());
    }
}
