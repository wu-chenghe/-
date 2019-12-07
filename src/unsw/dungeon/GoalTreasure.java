package unsw.dungeon;

public class GoalTreasure implements GoalComponent, ObserverGoal{
	
	private String goal;
	private int total;
	private int collected;
	
	public GoalTreasure(int total) {
		this.goal = "treasure";
		this.total = total;
		this.collected = 0;
	}
	
	@Override
	public void add(GoalComponent component) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * get goal info
	 */
	@Override
	public String getInfo() {
		return "(pick up all treasure)";
	}

	/**
	 * check if goal is finished
	 */
	@Override
	public boolean checkgoals() {
		return this.total == this.collected;
	}

	/**
	 * update observer
	 */
	@Override
	public void update(Entity entity) {
		if (entity instanceof Treasure) {
			this.collected++;
		}
	}
}
