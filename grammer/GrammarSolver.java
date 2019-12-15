/**
*
* Matt Jensen
* CS145 - Lab 5
* 5/30/19
*
*/

import java.util.*;
import java.util.stream.Collectors;

public class GrammarSolver {

    private Map<String, List<String>> grammarMap;
    private List<String> grammarList;

    // sets grammar of object.
    public GrammarSolver(List<String> grammarList) {
        this.grammarList = grammarList;
        this.grammarMap = new TreeMap<String, List<String>>();

        // add all lines to grammer.
        for(String entry : grammarList) {
            if( this.isValidEntry(entry) ) { // throws exception if illegal.
                this.addEntry(entry);
            } 
        }
    }

    /**
    *
    * Public Methods
    *
    */

    // publicly evaluate grammar
    public String[] generate(String symbol, int times) {
        String[] result = new String[times];
        for( int i = 0; i < result.length; i++) {
            result[i] = this.generate(symbol);
        }
        return result;
    
    }

    // map of available grammars.
    public Map<String, List<String>> getGrammars() {
        return this.grammarMap;
    }

    // list of grammars. 
    public List<String> getGrammarList() {
        return this.grammarList;
    }

    // all the keys/nonterminals of the grammer
    public String getSymbols() {
        return this.getGrammars().keySet().toString();
    }

    // grammer contains a terminal key.
    public boolean grammarContains(String key) {
        return this.getGrammars().keySet().contains(key);
    }

    /**
    *
    * Private Helper Methods
    *
    */

    // recursively evaluate grammar
    private String generate(String symbol) {
        String result = "";
        Set<String> symbols = this.getGrammars().keySet();

        // base case
        if(this.grammarContains(symbol) != true ) {
            return "";
        }
        String rule = this.getRandomTerminal(symbol);

        // apply the rules
        for(String subRule : GrammarSolver.splitRule(rule)){
            if( ! result.isEmpty()) {
                result += " "; // keeps leading whitespace off.
            }
            if( this.grammarContains(subRule) ) { // test if a subrule is a nonterminal.
                result += this.generate(subRule); // evaluates nonterminal rule and appends it.
            } else {
                result += subRule;
            }
        }
        return result.trim();
    }

    // add grammar to grammars.
    private void addEntry(String entry) {
        String nonterminal = GrammarSolver.nonTerminal(entry);
        List<String> rules = GrammarSolver.terminals(entry);
        this.getGrammars().put(nonterminal, rules); 
    }
    
    // line of a grammar is valid.
    private boolean isValidEntry(String entry) {
        // errors if bad colon count
        int colonCount = entry.length() - entry.replace(":", "").length();
        if( colonCount != 1) {
            throw new IllegalArgumentException("does not contain single colon");
        }

        // errors if duplicate
        String nonterminal = GrammarSolver.nonTerminal(entry);
        if( this.getGrammars().keySet().contains(nonterminal) ) {
            throw new IllegalArgumentException("duplicate non-terminal detected");
        }
        return true;
    }

    // random element of a non-terminal's rules.
    private String getRandomTerminal(String symbol) {
        Random random = new Random();
        List<String> rules = this.getGrammars().get(symbol);
        int index = random.nextInt(rules.size());
        return rules.get(index);
    }



    /**
    *
    * Static Methods
    *
    */

    // splits a string of rules at the whitespaces.
    private static String[] splitRule(String rule) {
        String[] split =  rule.split("[ \t]");
        return split;
    }

    // pre: no whitespace.
    // pre: non-empty.
    public static String nonTerminal(String entry) {
        return entry.substring(0, entry.indexOf(':'));
    }

    // extracts terminals from a string.
    public static List<String> terminals(String entry) {
        List<String> rules = new ArrayList<String>();
        entry = entry.substring(entry.indexOf(':') + 1, entry.length());
        String[] exploded = entry.split("\\|");
        for(int i = 0; i < exploded.length; i++) {
            rules.add(exploded[i]);
        }
        return rules;
    }
}
