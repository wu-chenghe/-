package unsw.dungeon;

public class GoalEnemy implements GoalComponent, ObserverGoal{
	
	private String goal;
	private int total;
	private int dead;
	
	public GoalEnemy(int total) {
		this.goal = "enemies";
		this.total = total;
		this.dead = 0;
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
		return "(kill all enemies)";
	}

	/**
	 * get if goal is finished
	 */
	@Override
	public boolean checkgoals() {
		return this.total == this.dead;
	}

	/**
	 * update observer
	 */
	@Override
	public void update(Entity entity) {
		if (entity instanceof Enemy) {
			this.dead++;
		}
	}

}
