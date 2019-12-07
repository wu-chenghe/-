package unsw.dungeon;

public class Door extends Entity implements ObserverDoor{
	
	private StateCanOpen canOpen;
	private StateCanOpen cannotOpen;
	private StateCanOpen state;
	private int id;
	
	public Door(int x, int y, int id) {
		super(x, y);
		this.canOpen = new CanOpenState(this);
		this.cannotOpen = new CannotOpenState(this);
		this.state = cannotOpen;
		this.id = id;
	}
	
	/**
	 * get door id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param player has key or not
	 * update 
	 */
	@Override
	public void update(boolean hasKey) {
		if(hasKey) {
			doorCanOpen();
		}else {
			doorCannotOpen();
		}
	}
	
	/**
	 * set the state to can not open
	 */
	private void doorCannotOpen() {
		//System.out.println("cannot open");
		state = cannotOpen;
	}

	/**
	 * set state to can open
	 */
	public void doorCanOpen() {
		//System.out.println("can open");
		state = canOpen;
	}
	
	/**
	 * check if can open
	 * @return boolean
	 */
	public boolean open() {
		return state.openable();
	}
	

}
