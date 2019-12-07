package unsw.dungeon;


public class Invincibility extends Entity{
	private boolean isValid;
	
	public Invincibility(int x, int y) {
		super(x, y);
		isValid = true;
	}
	
	/**
	 * activate potion
	 */
	public void active() {
		this.isValid = false;
	}
	
	/**
	 * get valid or not
	 * @return
	 */
	public boolean getIsValid() {
		return isValid;
	}
	 
	/**
	 * use potion by player
	 * @param player
	 */
	public void use(Player player) {
		PotionThread p1 = new PotionThread(player, this);
		PotionThread2 p2 = new PotionThread2(player, this);
		p1.start();
		p2.start();
	}

}
