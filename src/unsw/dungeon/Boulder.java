package unsw.dungeon;

public class Boulder extends Entity{
		
	public Boulder(int x, int y) {
		super(x, y);
	}
	
	/**
	 * move up
	 */
	public void moveUp() {
		y().set(getY() - 1);
    }
	
	/**
	 * move dowm
	 */
    public void moveDown() {
    	y().set(getY() + 1);
    }
    
    /**
	 * move left
	 */
    public void moveLeft() {
    	x().set(getX() - 1);
    }
    
    /**
	 * move right
	 */
    public void moveRight() {
    	x().set(getX() + 1);
    }
    
}
