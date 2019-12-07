package unsw.dungeon;

public class GoalBoulder implements GoalComponent, ObserverGoal{
	
	private String goal;
	private int total;
	private int pushed;
	private Dungeon dungeon;
	
	public GoalBoulder(Dungeon dungeon) {
		this.goal = "boulders";
		this.total = 0;
		this.pushed = 0;
		this.dungeon = dungeon;
	}
	
	@Override
	public void add(GoalComponent component) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * get info of goal
	 */
	@Override
	public String getInfo() {
		return "(turn on all floor switches)";
	}
	
	/**
	 * check if goal is done
	 */
	@Override
	public boolean checkgoals() {
		//return this.pushed == this.total;
		return dungeon.checkSwitch();
	}

	/**
	 * update observer
	 */
	@Override
	public void update(Entity entity) {
		if (entity instanceof FloorSwitch) {
			System.out.println(this.pushed);
			if (((FloorSwitch) entity).checkIsOn()) {
				this.pushed++;
			}
			else {
				//this.pushed--;
			}
		}
	}
}
