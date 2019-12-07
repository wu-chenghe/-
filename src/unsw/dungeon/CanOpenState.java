package unsw.dungeon;

public class CanOpenState implements StateCanOpen {
		
	private Door door;
	
	public CanOpenState(Door door) {
		this.door = door;
	}

	/**
	 * return true if door can open
	 */
	@Override
	public boolean openable() {
		return true;
	}

}
