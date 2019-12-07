package unsw.dungeon;

public class GoalExit implements GoalComponent{
	
	private String goal;
	private Player player;
	private Exit exit;
	
	public GoalExit(Player player, Exit exit) {
		this.goal = "exit";
		this.player = player;
		this.exit = exit;
	}

	/**
	 * get goal info
	 */
	@Override
	public String getInfo() {
		return "(getting to exit)";
	}

	/**
	 * get if goal is finished
	 */
	@Override
	public boolean checkgoals() {
		return player.getX() == exit.getX() && player.getY() == exit.getY();
	}

	@Override
	public void add(GoalComponent component) {
		// TODO Auto-generated method stub
		
	}

}
