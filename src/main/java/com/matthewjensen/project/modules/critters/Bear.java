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

public class Bear extends Critter {
    private Color color = Color.BLACK;
    private String symbol = "/";
    // white if polar
    public Bear(boolean polar) {
        if(polar) {
            this.color = Color.WHITE;
        }
        if( Math.random() > 0.5) {
            this.changeSymbol();
        }
    }
    //getter
    public Color getColor() {
        return this.color;
    }
    //getter
    public String toString() {
        return this.symbol;
    }
    public Action getMove(CritterInfo info) {
        this.changeSymbol();
        Neighbor front = info.getFront();
        if( front == Neighbor.OTHER ) { // check for enemy in front.
            return Action.INFECT;
        }
        if ( front == Neighbor.EMPTY ) { // check for obstacle.
            return Action.HOP;
        }
        return Action.LEFT;
    }
    // toggles between / and \
    public void changeSymbol() {
        if(this.symbol.equals("/")) {
            this.symbol = "\\";
        }
        else {
            this.symbol = "/";
        }
    }

}
