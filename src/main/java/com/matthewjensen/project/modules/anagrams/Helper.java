package com.matthewjensen.project.modules;

import java.util.*;

public class Helper {
    
    public static boolean isSolution(int max, LetterInventory inventory, Set<String> foundWords) {
        if( inventory.isEmpty() ) {
            return true;
        }
        if( foundWords.size() >= max) {
            return true;
        }
        return false;
    }

    public static void displaySolution(Set<String> foundWords) {
        System.out.println(Arrays.toString(foundWords.toArray()));
        return;
    }

    public static boolean isValid(int max, LetterInventory inventory, Set<String> foundWords, Set<String> remainingWords ) {
        String next = "";
        for( String found : foundWords ) {
            next = found;
        }
        if(inventory.contains(next)) {
            if( foundWords.size() <= max - 1 ) {
                return true;
            }
        }
        return false;
    }
    public static void applyValue(int max, LetterInventory inventory, Set<String> foundWords, Set<String> remainingWords ) {
        String next = "";
        for( String found : foundWords ) {
            next = found;
        }
        inventory.subtract(next);
        foundWords.add(next);
        remainingWords.remove(next);
        return;
    }
    public static void removeValue(int max, LetterInventory inventory, Set<String> foundWords, Set<String> remainingWords ) {
        String next = "";
        for( String found : foundWords ) {
            next = found;
        }
        inventory.add(next);
        foundWords.remove(next);
        remainingWords.add(next);
        return;
    }
}
