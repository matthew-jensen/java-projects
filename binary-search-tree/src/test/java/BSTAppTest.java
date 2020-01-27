import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import org.junit.rules.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class BSTAppTest {
    @Rule
    public ErrorCollector collector= new ErrorCollector();

    @Test 
    public void basicTest() {
        BST tree = new BST();
        int[] array = {0, 10, 100, 200};
        for( Integer value : array ) {
            tree.insert(value);
        }
      	collector.checkThat("min diff is small" + tree, BSTApp.minDiff(tree.root()), is(10));
        tree.delete(0);
      	collector.checkThat("min diff is large" + tree, BSTApp.minDiff(tree.root()), is(90));
    }

    @Test 
    public void testDiff() {
        Random random = new Random();
        int diff = 1;
        BST tree = new BST();
        int rand = random.nextInt();

        while( diff < 100 ) {
            tree = new BST();
            rand = random.nextInt();
            tree.insert(rand);
            tree.insert(rand + diff);
            collector.checkThat("diff of " + rand + " and " + (rand + diff) + " is " + diff + " for tree " + tree, BSTApp.minDiff(tree.root()), is(diff));
            diff += 10;
        }
    }
}
