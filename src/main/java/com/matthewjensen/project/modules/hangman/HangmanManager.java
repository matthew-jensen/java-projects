/*
* Matt Jensen
* CS145 - Lab 4
* 5/21/19
*
// runs the hangman game
*/
import java.util.*;

public class HangmanManager {

    private List<String> dictionary;
    private Set<String> words;
    private int length;
    private int max;
    private SortedSet<Character> guesses;
    private int tries;

    /**
    *
    * @param   dictionary    contains all the possible solutions.
    * @param   length   length of solutions.
    * @param   max   maximum guesses.
    * @return   manager
    *
    */
    public HangmanManager(List<String> dictionary, int length, int max) {
        if(length < 1 || max < 0){
            throw new IllegalArgumentException("length or max are incorrect");
        }
        this.setDictionary(dictionary);
        this.setLength(length);
        this.setMax(max);
        this.setTries(0);
        this.setGuesses();
        this.setWords();
    }

    /**
    * set of words being considered my manager.
    *
    * @return   words
    *
    */
    public Set<String> words()  {
        return this.words;
    }

    /**
    * guesses the player has left in the game.
    *
    */
    public int guessesLeft() {
        return this.max - this.tries;
    }

    /**
    * past guesses by player.
    *
    * @return   guesses
    *
    */
    public SortedSet<Character> guesses() {
        return this.guesses;
    }

    /**
    * representation of the current state of the game.
    *
    * @return   pattern
    *
    */
    public String pattern() {
        if(this.getWords().isEmpty()){
            throw new IllegalStateException("words are empty");
        }
        String word = this.getWords().iterator().next();
        return pattern(word, this.getGuesses());
    }
    // generated the string mask for a word given a set of guessses.
    public static String pattern(String word, Set<Character> guessMask) {
        String pattern = "";
        for( int i = 0; i < word.length(); i++) {
            if( guessMask.contains(word.charAt(i)) ) {
                pattern += " " + word.charAt(i);
            } else {
                pattern += " -";
            }
        }
        return pattern;
          
    }

    // accessor for guesses set.
    private SortedSet<Character> getGuesses() {
        return this.guesses;
    }

    /**
    * records a guess.
    *
    * @return   matches     number of matches of the guess.
    *
    */
    public int record(char guess) {
        if(this.guessesLeft() < 1){
            throw new IllegalStateException("no tries left");
        }
        if( ! this.getWords().isEmpty() && this.getGuesses().contains(guess)){
            throw new IllegalArgumentException();
        }
        int count = 0;
        this.addGuess(guess);
        // maps a count to and array of possibilities

        SortedMap<String, List<String>> possibilities = getPossibilities(guess);
        if( possibilities.size() >= 1 ) {
            this.setWords(mostEvil(possibilities));
        }
        count = countMatches(this.getWords().iterator().next(), guess);
        if(count == 0){
            this.addTry();
        }
        return count;
    }
    /**
     * counts number of chars in word
     * 
     * @param word
     * @param character
     * @return
     */
    public static int countMatches(String word, char character){
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == character){
                count++;
            }
        }
        return count;
    }
    /**
     * returns the most evil list of words in the map.
     * @param map   the map of all possible values.
     * @return  list of the most evil words.
     */
    private List<String> mostEvil(Map<String, List<String>> map){
        // check if the key is the smallest guess.gg
        String maxKey = "";
        int maxCount = 0;
        for(String key : map.keySet()){
            if( map.get(key).size() > maxCount) {
                maxKey = key;
                maxCount = map.get(key).size();
            }
        }
        return map.get(maxKey);
    }
    /**
     * get a map with patterns as keys and words as values.
     * 
     * @param guess     the additional guess.
     * @return  possibilities   the possible words
     */
    private SortedMap<String, List<String>> getPossibilities(char guess) {
        SortedMap<String, List<String>> map = new TreeMap<String, List<String>>();
        String pattern = "";
        Set<String> keys = new HashSet<String>();
        for(String word : this.getWords()) {
            keys.add(pattern(word, this.getGuesses()));
        }
        for(String key : keys){
            map.put(key, new ArrayList<String>());
        }
        for(String word : this.getWords()) {
            pattern = pattern(word, this.getGuesses());
            map.get(pattern).add(word);
        }
        return map;
    }

    /**
     * 
     * Setters and Getters for object attributes.
     * 
     */
    
    private Set<String> getWords() {
        return this.words;
    }
    private void addGuess(char guess) {
        this.guesses.add(guess);
    }

    private void setDictionary(List<String> dictionary) {
        this.dictionary = dictionary;
    }
    private void setLength(int length) {
        this.length = length;
    }
    private void setMax(int max) {
        this.max = max;
    }
    private void setTries(int tries) {
        this.tries = tries;
    }
    private void addTry(){
        this.tries++;
    }
    private void setGuesses() {
        this.guesses = new TreeSet<Character>();
    }    
    private void setWords() {
        this.setWords(this.dictionary);
    }
    private void setWords(List<String> words) {
        this.words = new TreeSet<String>();
        for(String word : words) {
            if( word.length() == this.length) {
                this.words.add(word);
            }
        }
    }
}
