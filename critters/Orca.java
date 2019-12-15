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

public class Orca extends Critter {
    private Color color = Color.BLACK;
    
    private String symbol = "0-<";
    
    public int moveCount;

      
    public Orca() {
    }
    
    public Color getColor() {
         if( this.color == Color.BLACK) {
            this.color = Color.WHITE;
         }
         else{
              this.color = Color.BLACK;
         }
        return this.color;
    }
    
    public String toString() {
        return this.symbol;
    }
    
    public Action getMove(CritterInfo info) {
         this.moveCount++;
         Neighbor front = info.getFront();
         
         if(front == Neighbor.OTHER) {
              return Action.INFECT;
         }
         if(front == Neighbor.EMPTY) {
              return Action.HOP;
         }
         if(front == Neighbor.WALL) {
              return Action.RIGHT;
         }
         return Action.LEFT;
    }

}
