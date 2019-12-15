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

public class Giant extends Critter {
    private Color color = Color.GRAY;
    
    private String symbol;
    
    public int moveCount;

      
    public Giant() {
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public String toString() {
        this.changeSymbol();
        return this.symbol;
    }
    
    private void changeSymbol() {
         if( this.moveCount > 24 ) {
            this.moveCount = 0;
         }
         if( this.moveCount <= 6) {
            this.symbol = "FEE";
         }
         if( this.moveCount > 6 && this.moveCount <= 12 ) {
            this.symbol = "FIE";
         }
         if ( this.moveCount > 12 && this.moveCount <= 18) {
            this.symbol = "FOO";
         }
         if ( this.moveCount > 18 ) {
            this.symbol = "FUM";
         }
    }
    
    public Action getMove(CritterInfo info) {
         this.moveCount++;
         Neighbor front = info.getFront();
         if( front == Neighbor.OTHER) {
            return Action.INFECT;
         }
         if( front == Neighbor.EMPTY) {
            return Action.HOP;
         }
         return Action.RIGHT;
    }

}
