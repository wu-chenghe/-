package unsw.dungeon;

public class Key extends Entity {
	
	private boolean canUse;
	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		this.canUse = true;
	}
	
	/**
	 * use key to door
	 * @param door
	 */
	public void use(Door door) {
		canUse = false;
	}
	
	/**
	 * check if key can be used
	 * @return
	 */
	public boolean getCanUse() {
		return this.canUse;
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

}
