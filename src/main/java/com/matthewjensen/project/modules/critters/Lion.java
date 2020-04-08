/*
*
* Matt Jensen
* CS145
* Lab 2 - Critters
* 4/23/19
* Partners: Melissa, Devante
* 
*/
import java.awt.*;
import java.util.*;

public class Lion extends Critter {

    private Color color = Color.BLUE;
        
    private int moves = 0;
    
    public Lion() {
        this.changeColor();
    }
    
    public int getMoveCount() {
        return this.moves;
    }

    public Color getColor() {
        return this.color;
    }
    
    public String toString() {
        return "L";
    }
    
    public void changeColor() {
        if(this.getMoveCount() == 0 || this.getMoveCount() % 3 == 0) {
            int rand = this.randomWithRange(0, 2);
            if(rand == 0 ) {
                this.color = Color.RED;
            }
            if(rand == 1 ) {
                this.color = Color.GREEN;
            }
            if(rand == 2 ) {
                this.color = Color.BLUE;
            }
        }
    }
    public Action getMove(CritterInfo info) {
        this.moves = this.moves++;
        this.changeColor();
        
        Neighbor front = info.getFront();
        
        if( front == Neighbor.OTHER ) { // check for enemy in front.
            return Action.INFECT;
        }
        if ( front == Neighbor.WALL || info.getRight() == Neighbor.WALL ) { // check for obstacle.
            return Action.LEFT;
        }
        if ( front == Neighbor.SAME ) { // check for obstacle.
            return Action.RIGHT;
        }
        return Action.HOP;
    }
    public int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;
    }

}
