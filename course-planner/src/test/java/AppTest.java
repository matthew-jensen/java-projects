/*
// this is the testing class
// DO NOT MODIFY THIS CLASS AND ITS METHODS
*/
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.rules.*;
import org.junit.*;
import java.util.Random;
import java.util.*;
  
public class AppTest {
    @Rule
    public ErrorCollector collector= new ErrorCollector();

    @Test public void checkMap() {
        int[][] prerequisites = {
          {241, 145},
          {241, 201},
        };
        int n = 12;
       // Map<Integer, Set<Integer>> map = CoursePlanner.mapDependencies(prerequisites);
    	//  assertThat(map.get(241).size(), is(2));
    }

    @Test public void checkCheck() {
        int[][] possible = {
            {1,0},
            {2,0},
            {3,1},
        };
        collector.checkThat("Should be able to take 4 courses: " + arrToString(possible), CoursePlanner.check(4, possible), is(true));
        collector.checkThat("Should be able to take 2 courses: " + arrToString(possible), CoursePlanner.check(2, possible), is(true));
        collector.checkThat("There are less classes than asking for" + arrToString(possible), CoursePlanner.check(CoursePlanner.graphCourses(possible).size() + 1, possible), is(false));
    }
    @Test public void checkNotPossible() {
        int[][] prerequisites = {
          {0, 1},
          {1, 0},
        };
    	  assertThat(CoursePlanner.check(2, prerequisites), is(false));
    }

    private static int randomVal(int min, int max) {
      if (min >= max) {
  			throw new IllegalArgumentException("max must be greater than min");
  		}

  		Random r = new Random();
  		return r.nextInt((max - min) + 1) + min;
    }

    private static int[][] random2DArr(int size) {
      int[][] result = new int[size][2];
      for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[i].length; j++) {
          result[i][j] = randomVal(0, 10);
        }
      }
      return result;
    }

    private static String arrToString(int[][] arr) {
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

}
