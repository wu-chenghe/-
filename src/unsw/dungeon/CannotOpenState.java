package unsw.dungeon;

public class CannotOpenState implements StateCanOpen {

	private Door door;
	
	public CannotOpenState(Door door) {
		this.door = door;
	}
	
	/**
	 * return false if door  can not open
	 */
	@Override
	public boolean openable() {
		return false;
	}

}
