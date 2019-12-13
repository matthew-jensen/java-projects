/**
*
* Matt Jensen
* 5/28/19
* CS 145
* Assignment 3 - Anagrams
* 
* Finds and prints all anagrams for a specific phrase.
* Uses recursive backtracking.
* 
*/

import java.util.*;

public class Anagrams {

    private Set<String> dictionary;
    private int max;
    private String phrase;
    private List<String> solution = new ArrayList<String>();
    private Set<String[]> foundSolutions = new HashSet<String[]>();
    private Map<String, LetterInventory> inventories = new HashMap<String, LetterInventory>();

    public Anagrams(Set<String> dictionary){
        this.setDictionary(dictionary);
    }

    // @returns	words	anagrams in dictionary made with the word
    public Set<String> getWords(String phrase){
        Set<String> anagrams = new TreeSet<String>();
        if( ! this.inventories.keySet().contains(phrase) ) {
            this.inventories.put(phrase, new LetterInventory(phrase));
        }
        LetterInventory inventory = this.getInventory(phrase);
        for(String dictionaryWord : this.dictionary) {
            if(inventory.contains(dictionaryWord)){
                anagrams.add(dictionaryWord);
            }
        }
        return anagrams;
    }
    // easier signature without max.
    public void print(String phrase){
        print(phrase, this.getWords(phrase).size());
    }
    // populates foundSolutions then prints each to console.
    public void print(String phrase, int max){
        this.setPhrase(phrase);
        this.setMax(max);
        this.findSolutions();
        for(String[] solution : this.getSolutions() ) {
            System.out.println(Arrays.toString(solution));
        }
    }

    // private methods.
    // called to populate foundSolutions.
    private void findSolutions() {
        this.foundSolutions = new HashSet<String[]>();
        this.solution = new ArrayList<String>();
        LetterInventory inventory = this.getInventory(this.getPhrase());
        this.findSolutions(inventory);
    }
    
    private void findSolutions(LetterInventory remainingInventory) {
        // check if solution
        if(remainingInventory.isEmpty()) {
            this.addSolution();
            return;
        }
        // guesses.
        Set<String> values = this.getWords(phrase);
        for( String value : values ) {
            if( isValid(value, remainingInventory) ) {
                applyValue(value, remainingInventory);
                findSolutions(remainingInventory);
                removeValue(value, remainingInventory);
            }

        }
        return;
    }

    // tests is the value makes the decision tree dead end.
    private boolean isValid(String value, LetterInventory inventory) {
        if( ! inventory.contains(value)) {
            return false;
        }
        return true;
    }
    // backtracks the apply value step.
    private void removeValue(String value, LetterInventory inventory) {
        inventory.add(value);
        this.solution.remove(value);
    }
    // adds the value to solution currently being built.
    private void applyValue(String value, LetterInventory inventory) {
        inventory.subtract(value);
        this.solution.add(value);
    }

    // methods for saving a found solution
    private void addSolution() {
        if( this.solution.size() > this.max) {
            return;
        }
        int i = 0;
        String[] newSolution = new String[this.solution.size()];
        for(String word : this.solution) {
            newSolution[i] = word;
            i++;
        }
        this.foundSolutions.add(newSolution);
    }

    // setters and getters for class variables.
    private Set<String[]> getSolutions() {
        return this.foundSolutions;
    }
    private List<String> getSolution() {
        return this.solution;
    }
    private LetterInventory getInventory(String phrase) {
        return this.inventories.get(phrase);
    }
    private void setDictionary(Set<String> dictionary) {
        this.dictionary = dictionary;
    }
    // sets the max or as the total number of found words in the phrase.
    private void setMax(int max) {
        if( max > 0 ) {
        this.max = max;
        }
        else{
            this.max = this.getWords(this.getPhrase()).size();
        }
    }
    // errors if phrase is null or sets class phrase variable.
    private void setPhrase(String phrase) {
        if(phrase == null) {
            throw new IllegalArgumentException();
        }
        this.phrase = phrase;
    }
    private String getPhrase() {
        return this.phrase;
    }
}
