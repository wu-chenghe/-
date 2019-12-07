package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements SubjectEnemy, SubjectDoor{

    private Dungeon dungeon;
    private List<Entity> pickups;
    private PickupStrategy pstrategy;
    private List<ObserverEnemy> enemyObservers;
    //private UsePropStrategy useProp;
    private List<ObserverDoor> doorObservers;
    private boolean isAlive;
    

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.pickups = new ArrayList<>();
        this.enemyObservers = new ArrayList<>();
        this.doorObservers = new ArrayList<>();
        this.isAlive = true;
    }

    /**
     * move up
     */
    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    /**
     * move down
     */
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    /**
     * move left
     */
    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }
    
    /**
     * move right
     */
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    /**
     * set pick up strategy
     * @param pstrategy
     */
    public void setPickupStrategy(PickupStrategy pstrategy){
    	this.pstrategy = pstrategy;
    }

    /**
     * get pickups of player
     * @return
     */
    public List<Entity> getPickups(){
    	return pickups;
    }
    
    /**
     * player meet entity and set stategy
     * @return
     */
    public Entity meetEntity() {
    	Entity entity = dungeon.findEntity(getX(), getY());
    	if(entity instanceof Key) {
    		setPickupStrategy(new PickupKey());
    	}
    	if(entity instanceof Sword) {
    		setPickupStrategy(new PickupSword());
    	}
    	if(entity instanceof Treasure) {
    		setPickupStrategy(new PickupTreasure());
    	}
    	if(entity instanceof Invincibility) {
    		setPickupStrategy(new PickupInvincibility());
    	}
    	return entity;
    }
    
    /**
     * add entity to pick up
     * @param entity
     */
    public void addEntity(Entity entity) {
    	this.pickups.add(entity);
    }
    
    /**
     * remove entity from pickups
     * @param entity
     */
    public void removeEntity(Entity entity) {
    	this.pickups.remove(entity);
    }
    
    /**
     * pick up entity
     * @return
     */
    public boolean pickUp() {
    	Entity entity = meetEntity();
    	if(entity != null) {
	    	if(pstrategy != null)
	    		if(pstrategy.pickup(this, entity)) {
	    			dungeon.removeEntity(entity);
	    			return true;
	    		}
    	}
    	return false;
    }
    
    /**
     * find sword in pickups
     * @return
     */
    public Entity findSword2use() {
    	for(Entity e : pickups) {
    		if (e instanceof Sword && ((Sword) e).getHits() > 0)
    			return e;
    	}
    	return null;
    }
    
    /**
     * battle with enemy
     * @return
     */
    public boolean battleEnemy() {
    	Entity enemy = meetEntity();
    	if(enemy != null && enemy instanceof Enemy) {
	    	boolean ret =  ((Enemy) enemy).meetPlayer(this);
	    	if (ret) {
	    		if(! hasInvincibility()) {
	    			Entity sword = findSword2use();
	    			if (sword != null) {
	    				((Sword) sword).use(this);
	    			}
	    		}
	    		((Enemy) enemy).notifyGoal();
	    		dungeon.removeEntity(enemy);
	    		return ret;
	    	}else {
	    		return ret;
	    	}
    	}
    	return false;
    }
    
    /**
     * open door
     * @return
     */
    public boolean openDoor() {
    	Entity door = meetEntity();
    	if(door != null && door instanceof Door) {
    		boolean ret = ((Door)door).open();
    		if(ret) {
	    		Entity key = findKey2use();
	    		if (key != null) {
	    			((Key) key).use((Door) door);
	    		}
    			dungeon.removeEntity(door);
    		}
    		return ret;
    	}
    	return false;
    	
    }
    
    /**
     * use potion
     */
    public void useInvincibility() {
    	if(hasInvincibility()) {
    		Entity potion = findInvincibility2use();
    		((Invincibility) potion).use(this);
    		dungeon.removeEntity(potion);
    	}
    }
    
    /**
     * register observer for enemy
     */
	@Override
	public void registerEnemyObserver(ObserverEnemy o) {
		enemyObservers.add(o);
	}

	/**
	 * remove observer for enemy
	 */
	@Override
	public void removeEnemyObserver(ObserverEnemy o) {
		int i = enemyObservers.indexOf(o);
        if (i >= 0) {
            enemyObservers.remove(i);
        }
		
	}
	
	/**
     * find potion in pickups
     * @return
     */
	public Entity findInvincibility2use() {
		for (Entity e : pickups) {
			if (e instanceof Invincibility) {
				if(((Invincibility) e).getIsValid())
					return e;
			}
		}
		return null;
	}
	
	/**
	 * check if player has potion
	 * @return
	 */
	public boolean hasInvincibility() {
		if (findInvincibility2use() != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * check if player has sword
	 * @return
	 */
	public boolean hasSword() {
		if(findSword2use() != null) {
			return true;
		}
		return false;
	}

	/**
	 * notify enemy subject
	 */
	@Override
	public void notifyEnemy() {
		for (ObserverEnemy o : enemyObservers) {
            o.update(getX(), getY(), hasSword(), hasInvincibility());
        }
	}
	
	/**
	 * register observer for door
	 */
	@Override
	public void registerDoorObserver(ObserverDoor o) {
		doorObservers.add(o);
		
	}

	/**
	 * reomove door observer
	 */
	@Override
	public void removeDoorObserver(ObserverDoor o) {
		int i = doorObservers.indexOf(o);
        if (i >= 0) {
            doorObservers.remove(i);
        }
	}

	/**
	 * notify door subject
	 */
	@Override
	public void notifyDoor() {
		for (ObserverDoor o : doorObservers) {
			if(findKey2use() != null) {
				if(((Door) o).getId() == ((Key) findKey2use()).getId())
					o.update(hasKey());
			}
        }
	}
	
	/**
	 * check if player has key
	 * @return
	 */
	public boolean hasKey() {
		if(findKey2use() != null) {
			return true;
		}
		return false;
	}
	
	/**
     * find key in pickups
     * @return
     */
	public Entity findKey2use() {
		for (Entity e : pickups) {
			if (e instanceof Key) {
				if(((Key) e).getCanUse())
					return e;
			}
		}
		return null;
	}

	/**
	 * push boulder up
	 * @param boulder
	 */
	public void pushUp(Entity boulder) {
		((Boulder) boulder).moveUp();
		dungeon.turnSwitch();
	}

	/**
	 * push boulder down
	 * @param boulder
	 */	
	public void pushDown(Entity boulder) {
		((Boulder) boulder).moveDown();
		dungeon.turnSwitch();
	}

	/**
	 * push boulder left
	 * @param boulder
	 */
	public void pushLeft(Entity boulder) {
		((Boulder) boulder).moveLeft();
		dungeon.turnSwitch();
	}

	/**
	 * push boulder right
	 * @param boulder
	 */
	public void pushRight(Entity boulder) {
		((Boulder) boulder).moveRight();
		dungeon.turnSwitch();
	}

	/**
	 * set player to dead
	 */
	public void beKilled() {
		this.isAlive = false;
	}
	
	/**
	 * check if player is alive
	 * @return
	 */
	public boolean checkAlive() {
		return isAlive;
	}

}
