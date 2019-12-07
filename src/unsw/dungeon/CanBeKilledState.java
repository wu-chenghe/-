package unsw.dungeon;

public class CanBeKilledState implements StateKillable {
	
	private Enemy enemy;
	
	public CanBeKilledState(Enemy enemy) {
		this.enemy = enemy;
	}

	/**
	 * return true if enemy meet player
	 */
	@Override
	public boolean meetPlayer(Entity player) {
		//System.out.println("Enemy die");
		return true;
	}

}
