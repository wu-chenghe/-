package unsw.dungeon;

public class PickupSword implements PickupStrategy {

	/**
	 * sword picked up by player
	 */
	@Override
	public boolean pickup(Player player, Entity entity) {
		if(entity instanceof Sword && player.findSword2use() == null) {
			player.addEntity(entity);
			player.notifyEnemy();
			return true;
		}
		return false;
	}

}
