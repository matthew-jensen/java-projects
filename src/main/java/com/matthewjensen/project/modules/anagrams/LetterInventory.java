package com.matthewjensen.project.modules;

/**
*
* Matt Jensen
* CS145 - Lab 3
* 5/30/19
*
* Add, subtract and manage inventories of letters.
* Lower case, alphabetical inventories only.
*
*/

import java.util.*;

public class LetterInventory {
    //todo
    public boolean contains(String needle) {
        return false;
    }
    //todo
    public static int getInstanceCount() {
        return 1;
    }
    public static int resetInstanceCount() {
        return 1;
    }

    // stored the inventory and count.
    private Map<Character, Integer> inventory;

    // add phrase to inventory. 
    // instantiated inventory to 0 for all letters.
    public LetterInventory(String phrase) {
        this.setInventory();
        this.add(phrase);
    }
    
    // empty constructor without default string. 
    // For testing.
    public LetterInventory() {
        this.setInventory();
    }

    // adds characters of string from inventory.
    public void add(String string) {
        char character;
        for(int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            this.add(character);
        }
    }

    // adds characters from another inventory from inventory.
    // returns copy of inventory.
    public LetterInventory add(LetterInventory inventory) {
        LetterInventory newInventory = new LetterInventory();
        int count = 0;
        for(Character character : this.getInventory().keySet() ) {
            count = this.get(character) + inventory.get(character);
            newInventory.set(character, count);
        }
        return newInventory;
    }

    // removes characters from another inventory from inventory.
    // returns copy of inventory.
    public LetterInventory subtract(LetterInventory inventory) {
        LetterInventory newInventory = new LetterInventory();
        for(Character character : this.getInventory().keySet() ) {
            newInventory.set(character, this.get(character) - inventory.get(character));
        }
        return newInventory;
    }
    // removes characters of string from inventory.
    public void subtract(String string) {
        char character;
        for(int i = 0; i < string.length(); i++) {
            character = string.charAt(i);
            this.subtract(character);
        }
    }

    // count number of characters in inventory.
    public int get(Character character) {
        if( ! this.has(character) ) {
            return 0;
        }
        return this.getInventory().get(Character.toLowerCase(character));
    }

    // set inventory count of character.
    // converts upper to lower.
    // ignores non-alphabetic characters.
    public void set(Character character, int count) {
        if( ! Character.isAlphabetic(character) ) {
            return;
        }
        character = Character.toLowerCase(character);
        if( ! this.has(character) ) {
            throw new IllegalArgumentException("character not found" + character);
        }
        this.inventory.put(character, count);
    }

    // represents the inventory in string.
    // one letter for each count in the inventory map.
    public String toString() {
        String string = "[";
        for(char character : this.getInventory().keySet()) { 
            int count = this.getInventory().get(character);
            for(int i = 0; i < count; i++) {
                string += character;
            }
        }
        string += "]";
        return string;
    }

    // counts all letters in inventory.
    // duplicates counted as well.
    public int size() {
        int sum = 0;
        for( Integer count : this.getInventory().values() ) {
            sum += count;
        }
        return sum;
    }

    // tests whether inventory is empty.
    public boolean isEmpty() {
        return this.size() <= 0;
    }

    // setters and getters.

    // instantiated inventory to 0 for all letters.
    private void setInventory() {
        this.inventory = new TreeMap<Character, Integer>();
        for(int i = 0; i <= 25; i++) {
            this.inventory.put( (char) (i + 97), 0);
        }
    }

    // returns private inventory.
    private Map<Character, Integer> getInventory() {
        return this.inventory;
    }
    
    // test whether character is in inventory.
    private boolean has(Character character) {
        return this.getInventory().keySet().contains(Character.toLowerCase(character));
    }

    // decrements inventory count of character in inventory.
    private void subtract(Character character) {
        if( ! this.has(character) || this.get(character) <= 0 ) {
            return;
        } else {
            int count = this.get(character);
            count--;
            this.set(character, count);
        }
    }

    // increments inventory count of character in inventory.
    private void add(Character character) {
        if( ! this.has(character) ) {
            return;
        } else {
            int count = this.get(character);
            this.set(character, count + 1);
        }
    }
}
