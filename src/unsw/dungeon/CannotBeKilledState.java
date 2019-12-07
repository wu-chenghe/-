package unsw.dungeon;

public class CannotBeKilledState implements StateKillable {
	
	private Enemy enemy;
	
	public CannotBeKilledState(Enemy enemy) {
		this.enemy = enemy;
	}
	
	/**
	 * return true and kill player if enemy meet
	 */
	@Override
	public boolean meetPlayer(Entity player) {
		((Player) player).beKilled();
		return false;
	}
	
}
