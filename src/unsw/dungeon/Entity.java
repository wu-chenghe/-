package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
    }
    
    /**
     * get IntegerProperty of x
     * @return
     */
    public IntegerProperty x() {
        return x;
    }
    
    /**
     * get IntegerProperty of y 
     * @return
     */
    public IntegerProperty y() {
        return y;
    }
    
    /**
     * get y
     * @return
     */
    public int getY() {
        return y().get();
    }

    /**
     * get y
     * @return
     */
    public int getX() {
        return x().get();
    }
    
}
