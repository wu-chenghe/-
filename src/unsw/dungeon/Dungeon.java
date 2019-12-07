/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private GoalComponent goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }
    
    /**
     * add goal to dungeon
     * @param goal
     */
    public void addGoal(GoalComponent goal) {
    	this.goal = goal;
    }
    
    /**
     * check dungeon goals
     * @return
     */
    public boolean checkgoal() {
    	return goal.checkgoals();
    }

    /**
     * get width of dungeon
     * @return
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * get height of dungeon
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * get player of dungeon
     * @return
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * set player to the dungeon
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * add entity to dungeon
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * set enemy observer
     */
    public void setEnemyObserver() {
    	for (Entity e : entities) {
    		if(e instanceof Enemy) {
    			player.registerEnemyObserver((Enemy) e);
    		}
    	}
    }
    
    /**
     * set door observer
     */
    public void setDoorObserver() {
    	for (Entity e : entities) {
    		if(e instanceof Door) {
    			player.registerDoorObserver((Door) e);
    		}
    	}
    }
    
    /**
     * find entity at (x, y)
     * @param x
     * @param y
     * @return
     */
    public Entity findEntity(int x, int y) {
    	for (Entity e : entities) {
    		if (e.getX() == x && e.getY() == y && !(e instanceof Player) && !(e instanceof FloorSwitch)) {
    			return e;
    		}
    	}
    	return null;
    }
    
    public void printEntity(int x, int y) {
    	for (Entity e : entities) {
    		if (e.getX() == x && e.getY() == y) {
    			System.out.println(e);
    		}
    	}
    }
    
    /**
     * find switch in the dungeon
     * @param x
     * @param y
     * @return
     */
    public Entity findSwitch(int x, int y) {
    	for(Entity e : entities) {
    		if (e.getX() == x && e.getY() == y && e instanceof FloorSwitch) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * find boulder in the dungeon
     * @param x
     * @param y
     * @return
     */
    public Entity findBoulder(int x, int y) {
    	for(Entity e : entities) {
    		if (e.getX() == x && e.getY() == y && e instanceof Boulder) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * turn on switch 
     */
    public void turnSwitch() {
    	for(Entity e : entities) {
    		if(e instanceof FloorSwitch) {
    			if (findBoulder(e.getX(), e.getY()) instanceof Boulder) {
    				((FloorSwitch) e).update(true);
    				//((FloorSwitch) e).notifyGoal();
    			}else {
    				((FloorSwitch) e).update(false);
    			}
    			((FloorSwitch) e).notifyGoal();
    		}
    	}
    }
    
    /**
     * remove entity in dungeon
     * @param entity
     */
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    }
    
    /**
     * check if dungeon has switch
     * @return
     */
    public boolean hasSwitch() {
    	for(Entity e : entities) {
    		if(e instanceof FloorSwitch) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * check if switch is on
     * @return
     */
    public boolean checkSwitch() {
    	for(Entity e : entities) {
    		if(e instanceof FloorSwitch) {
    			if(!((FloorSwitch) e).checkIsOn()){
    				return false;
    			}
    			
    		}
    	}
    	return hasSwitch();
    }
    
    /**
     * find player in the dungeon
     * @param x
     * @param y
     * @return
     */
    public Entity findPlayer(int x, int y) {
    	for(Entity e : entities) {
    		if (e.getX() == x && e.getY() == y && e instanceof Player) {
    			return e;
    		}
    	}
    	return null;
    }
    
    /**
     * check if player is on exit
     * @return
     */
    public boolean checkExit() {
    	for(Entity e : entities) {
    		if (e instanceof Exit) {
    			if(findPlayer(e.getX(), e.getY()) instanceof Player) {
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    /**
     * check if entity can go there
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean canGoThere(int x1, int y1, int x2, int y2) {
    	Entity entity1 = findEntity(x1, y1);
    	Entity entity2 = findEntity(x2, y2);
		if(entity1 instanceof Wall) {
			return false;
		}else if (entity1 instanceof Door && !((Door) entity1).open()) {
			return false;
		}else if (entity1 instanceof Boulder && entity2 instanceof Boulder) {
			return false;
		}else if (entity1 instanceof Boulder && entity2 instanceof Wall) {
			return false;
		}
		return true;
	}
    
    /**
     * run portal
     */
    public void checkPortal() {
    	for(Entity e : entities) {
    		if(e instanceof Portal) {
    			if(((Portal) e).portalRun(this))
    				break;
    		}
    	}
    }
    
    /**
     * find portal in dungeon
     */
    public Portal findPortal(int x, int y, int id) {
    	for(Entity e : entities) {
    		if(e instanceof Portal) {
	    		if ((e.getX() != x || e.getY() != y) && ((Portal) e).getId() == id) {
	    			return (Portal) e;
	    		}
    		}
    	}
    	return null;
    }
    
    /**
     * get all entities in dungeon
     * @return
     */
    public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
	
	/**
	 * check if entity can go there
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canGoThere(int x, int y) {
    	Entity entity1 = findEntity(x, y);
    	if(entity1 instanceof Wall) {
			return false;
		}else if (entity1 instanceof Door && !((Door) entity1).open()) {
			return false;
		}else if (entity1 instanceof Boulder) {
			return false;
		}
    	return true;
    }
	
	/**
	 * check if enemy can go there
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean enemyCanGoThere(int x, int y) {
    	Entity entity1 = findEntity(x, y);
    	if(entity1 instanceof Wall) {
			return false;
		}else if (entity1 instanceof Door) {
			return false;
		}else if (entity1 instanceof Boulder) {
			return false;
		}
    	return true;
    }
	
	
	/**
	 * check if dungeon has enemy
	 * @return
	 */
    public boolean hasEnemy() {
    	for(Entity e : entities) {
    		if (e instanceof Enemy) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * check if dungeon has treasure
     * @return
     */
	public boolean hasTreasure() {
		for(Entity e : entities) {
			if ( e instanceof Treasure) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * get exit in the dungeon
	 * @return
	 */
	public Exit getExit() {
		for(Entity e : this.entities) {
			if (e instanceof Exit) {
				return (Exit) e;
			}
		}
		return null;
	}
	
	/**
	 * count enemy in the dungeon
	 * @return
	 */
	public int getEnemyCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Enemy) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * count treasure in the dungeon
	 * @return
	 */
	public int getTreasureCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Treasure) {
				count++;
			}
		}
		return count;
	}
	
	/*
	public int getBoffffulderCount() {
		int count = 0;
		for(Entity e : this.entities) {
			if (e instanceof Boulder) {
				count++;
			}
		}
		return count;
	}
	*/
	
	/**
	 * set Observer for Enemy Goal
	 * @param goal
	 */
	public void setObserverEnemyGoal(GoalEnemy goal) {
		for (Entity e : entities) {
			if (e instanceof Enemy) {
				Enemy enemy = (Enemy) e;
				enemy.registerGoalObserver(goal);
			}
		}
		
	}
	
	/**
	 * set obseerver for treasure goal
	 * @param goal
	 */
	public void setObserverTreasureGoal(GoalTreasure goal) {
		for (Entity e : entities) {
			if (e instanceof Treasure) {
				((Treasure) e).registerGoalObserver(goal);
			}
		}
		
	}
	
	/**
	 * set observer for boulder goal
	 * @param goal
	 */
	public void setObserverBoulderGoal(GoalBoulder goal) {
		for (Entity e : entities) {
			if (e instanceof FloorSwitch) {
				((FloorSwitch) e).registerGoalObserver(goal);
			}
		}
		
	}
	
	/**
	 * get goal info of dungeon
	 * @return
	 */
	public String getGameGuide() {
		return goal.getInfo();
	}

}
