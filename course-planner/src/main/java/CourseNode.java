import java.util.*;
class CourseNode {

    public int name;
    public String color = "white";
    public CourseNode parent = null;
    public Set<CourseNode> prerequisites;

    public CourseNode(int name) {
        this.prerequisites = new HashSet<CourseNode>();
        this.name = name;
    }
    public void addDependency(CourseNode node) {
        this.prerequisites.add(node);
    }
    public Set<CourseNode> getPrerequisites() {
        return this.prerequisites;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return this.color;
    }
    public void setParent(CourseNode parent) {
        this.parent = parent;
    }
    public CourseNode getParent() {
        return this.parent;
    }
}
