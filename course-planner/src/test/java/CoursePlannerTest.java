/*
// this is the testing class
// DO NOT MODIFY THIS CLASS AND ITS METHODS
*/
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import java.util.Random;

public class CoursePlannerTest {
    public static void main(String[] args) {
      int test = randomVal(0, 10);
      System.out.println(test);

      int[][] testArr = random2DArr(6);
      String testStr = arrToString(testArr);
      System.out.println(testStr);
    }


    public static int randomVal(int min, int max) {
      if (min >= max) {
  			throw new IllegalArgumentException("max must be greater than min");
  		}

  		Random r = new Random();
  		return r.nextInt((max - min) + 1) + min;
    }

    public static int[][] random2DArr(int size) {
      int[][] result = new int[size][2];
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[i].length; j++) {
          result[i][j] = randomVal(0, 10);
        }
      }
      return result;
    }

    public static String arrToString(int[][] arr) {
      String result = "[";

      for (int i = 0; i < arr.length; i++) {
        result += "[";
        for (int j = 0; j < arr[i].length; j++) {
          if (j == 0) result += arr[i][j] + ", ";
          else result += arr[i][j];
        }
        result += "]";

        if (i != arr.length-1) result += ", ";
      }

      result += "]";

      return result;
    }

    @Test public void checkTest() {
        int[][] prerequisites = random2DArr(5);
        int n = 12;

    	  assertThat("\nThis is the test on your check method." +
            "\nThere are " + n + " courses.\nThe given prerequisites are " + arrToString(prerequisites) +
            "\nIs it possible to schedule and take all the given courses?",
            CoursePlanner.check(n, prerequisites),
            is(RightSolution.check(n, prerequisites)));
    }

    @Test public void checkTest2() {
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}};
        int n = 5;

        assertThat("\nThis is the test on your check method." +
            "\nThere are " + n + " courses.\nThe given prerequisites are " + arrToString(prerequisites) +
            "\nIs it possible to schedule and take all the given courses?",
            CoursePlanner.check(n, prerequisites),
            is(RightSolution.check(n, prerequisites)));
    }

    @Test public void checkTest3() {
        int[][] prerequisites = random2DArr(4);
        int n = 17;

        assertThat("\nThis is the test on your check method." +
            "\nThere are " + n + " courses.\nThe given prerequisites are " + arrToString(prerequisites) +
            "\nIs it possible to schedule and take all the given courses?",
            CoursePlanner.check(n, prerequisites),
            is(RightSolution.check(n, prerequisites)));
    }
}
