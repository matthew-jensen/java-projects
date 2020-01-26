import java.util.*;

public class CoursePlanner {

    // this method prints out: whether it is possible to take all the given courses and
    // one possible schedule for the given courses
    public static void plan(int numberOfCourses, int[][] prerequisites) {
        if( check(numberOfCourses, prerequisites) ) {
            System.out.println("It is possible to take " + numberOfCourses + " of the given courses:");
            Set<CourseNode> courses = graphCourses(prerequisites);
            Stack<CourseNode> stack = new Stack<CourseNode>();
            for(CourseNode course : courses ) {
                course.setColor("white");
                course.setParent(null);
            }
            for(CourseNode course : courses ) {
                if( course.getColor() == "white" ) {
                    visit(courses, course, stack);
                }
            }
            int index = 0;
            System.out.print("[ "); 
            for(CourseNode node : stack) {
                if( numberOfCourses <= index ) {
                    break;
                }
                if( index == 0 ) {
                    System.out.print(node.name); 
                } else {
                    System.out.print(" -> " + node.name); 
                }
                index++;
            }
            System.out.println("]"); 
        } else {
            System.out.println("It is impossible to take " + numberOfCourses + " of the given courses");
        }
        return;
    }

    // this is a helper method for plan; it returns a boolean to indicate if a given series of courses can be possibly scheduled
    public static boolean check(int numberOfCourses, int[][] prerequisites) {
        Set<CourseNode> courses = graphCourses(prerequisites);
        Stack<CourseNode> stack = new Stack<CourseNode>();
        for(CourseNode course : courses ) {
            course.setColor("white");
            course.setParent(null);
        }
        for(CourseNode course : courses ) {
            if( course.getColor() == "white" ) {
                if( ! visit(courses, course, stack) ) {
                    return false;
                }
            }
        }
        if( stack.size() >= numberOfCourses) {
            return true;
        }
        return false;
    }

    // recursively explore the course graph.
    // dfs the nodes through their prerequisites.
    private static boolean visit(Set<CourseNode> courses, CourseNode course, Stack<CourseNode> stack) {
        course.setColor("grey");
        for(CourseNode neighbor : course.getPrerequisites()) {
            if( neighbor.getColor() == "white" ) {
                neighbor.setParent(course);
                visit(courses, neighbor, stack);
            } else if( neighbor.getColor() == "grey" ) {
                return false;
            } else {
                // cross edge
            }
        }
        course.setColor("black");
        stack.push(course);
        return true;
    }
    // converts edge pairs to a adjacency map.
    public static Set<CourseNode> graphCourses(int[][] prerequisites){
        Map<Integer, CourseNode> map = new HashMap<Integer, CourseNode>();
        for(int i = 0; i < prerequisites.length; i++) {
            CourseNode parent = null;
            CourseNode child = null;
            int key = prerequisites[i][0];
            int value = prerequisites[i][1];
            if( map.get(key) != null ) {
                child = map.get(key);
            } else {
                child = new CourseNode(key);
            }

            if( map.get(value) != null ) {
                parent = map.get(value);
            } else {
                parent = new CourseNode(value);
            }

            child.addDependency(parent);
            map.put(key, child);
            map.put(value, parent);
        }
        Set<CourseNode> set = new HashSet<CourseNode>();
        for(Integer course : map.keySet() ) {
            set.add(map.get(course));
        }
        return set;
    }

    // pretty print the adjaceny list.
    private static void printMap(Map<Integer, Set<Integer>> map) {
        for( Integer key : map.keySet() ) {
            if( ! map.get(key).isEmpty() ) {
                System.out.print(key + " => [");
                int index = 0;
                for(Integer value : map.get(key) ) {
                    if( index != 0 ) {
                        System.out.print(", " + value);
                    } else {
                        System.out.print(value);
                    }
                    index++;
                }
                System.out.println("]");
            }
            
        }
    }
}
