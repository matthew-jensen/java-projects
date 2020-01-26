import java.util.*;

public class PlanTest {
    public static void main(String[] args) {
        int[][] prerequisites = random2DArr(8);
        int n = 3;
        System.out.println("testing:\n" + arrToString(prerequisites));
        CoursePlanner.plan(n, prerequisites);

        int[][] newPre = {
                {500, 241},
                {450, 241},
                {333, 241},
                {241, 145},
                {145, 141},
                {141, 101}
                };
        System.out.println("testing:\n" + arrToString(newPre));
        CoursePlanner.plan(n, newPre);

        int[][] anotherPre = {
                {241, 145},
                {145, 141},
                {141, 101}
                };
        System.out.println("testing:\n" + arrToString(anotherPre));
        CoursePlanner.plan(n, anotherPre);

        int[][] impossible = {
                {0, 1},
                {1, 0}
                };
        System.out.println("testing:\n" + arrToString(impossible));
        CoursePlanner.plan(2, impossible);
    }

    // this method generates a list of prerequisits as a 2D array
    private int[][] prerequisiteGenerator(int size) {
        return random2DArr(size);
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
