package unsw.dungeon;

public class Portal extends Entity{
	private int id;
	private Portal oppositePortal;

	public Portal(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}
	
	/**
	 * get id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * set id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * run portal
	 * @param dungeon
	 * @return
	 */
	public boolean portalRun(Dungeon dungeon) {
		this.oppositePortal = dungeon.findPortal(getX(), getY(), id);
		for(Entity e : dungeon.getEntities()) {
    		if (e.getX() == getX() && e.getY() == getY() && e instanceof Player) {
    			transportPlayer(e);
    			return true;
    		}
    	}
		return false;
	}
	
	/**
	 * transport player
	 * @param player
	 */
	public void transportPlayer(Entity player) {
		if(oppositePortal != null) {
			player.x().set(oppositePortal.getX());
			player.y().set(oppositePortal.getY());
		}
	}
}
