package unsw.dungeon;

public class Sword extends Entity{
	
	private int hits;

	public Sword(int x, int y) {
		super(x, y);
		this.hits = 5;
	}
	
	/**
	 * minus total hits by 1
	 */
	public void hit() {
		if(hits != 0) {
			hits--;
		}
	}
	
	/**
	 * get total hits 
	 * @return
	 */
	public int getHits() {
		return hits;
	}
	
	/**
	 * used by player
	 * @param player
	 */
	public void use(Player player) {
		this.hit();
		player.notifyEnemy();
	}
}

