package unsw.dungeon;

public class PickupInvincibility implements PickupStrategy {
	
	/**
	 * potion picked up by player 
	 */
	@Override
	public boolean pickup(Player player, Entity entity) {
		if(entity instanceof Invincibility) {
			player.addEntity(entity);
			return true;
		}
		return false;
	}

}
