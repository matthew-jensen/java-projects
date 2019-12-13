import java.util.*;

public interface Recursion {
    public static void main(String[] args) {

    }
    private boolean findSolutions(int n) {
        // check if solution
        boolean foundSolution = false;
        if(foundSolution) {
            displaySolution();
            return true;
        }
        // guesses.
        List<String> values = new ArrayList<String>();
        for( String value : values ) {
            if( isValid(value, n) ) {
                applyValue(value, n);
                if( findSolutions(n - 1) ) {
                    return true;
                }
                removeValue(value, n);
            }

        }
        return false;
    }

    private boolean isValid(String value, int n) {
        return true;
    }
    private void removeValue(String value, int n) {

    }
    private void applyValue(String value, int n) {

    }
    private void displaySolution() {
        System.out.println();
    }
}
