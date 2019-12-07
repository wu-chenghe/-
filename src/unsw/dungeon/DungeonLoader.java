package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        
        JSONObject goals = json.getJSONObject("goal-condition");
        dungeon.addGoal(setUpGoals(dungeon, goals));
        
        return dungeon;
    }
    
    private GoalComponent setUpGoals(Dungeon dungeon, JSONObject json) {
    	String firstLine = json.getString("goal");
    	GoalComposite finalGoal = new GoalComposite("main goal", dungeon);
    	if (! firstLine.equals("AND") && !firstLine.equals("OR")) {
    		finalGoal.add(makeLeaf(firstLine, dungeon));
    	}else {
    		if (firstLine.equals("AND")) {
    			JSONArray subgoals = json.getJSONArray("subgoals");
    			finalGoal.add(makeAndGoal(dungeon, subgoals));
    		}else if (firstLine.equals("OR")) {
    			JSONArray subgoals = json.getJSONArray("subgoals");
    			finalGoal.add(makeOrGoal(dungeon, subgoals));
    		}
    	}
    	return finalGoal;
    }
    
    private GoalComposite makeAndGoal(Dungeon dungeon, JSONArray subgoals) {
    	GoalComposite subcomposite = new GoalComposite("and", dungeon);
    	for (int i = 0; i < subgoals.length(); i++) {
    		JSONObject goal = subgoals.getJSONObject(i);
    		String firstLine = goal.getString("goal");
    		if (!firstLine.equals("AND") && !firstLine.equals("OR")) {
    			subcomposite.add(makeLeaf(firstLine, dungeon));
    		}else {
    			if (firstLine.equals("AND")) {
        			JSONArray andsubs = goal.getJSONArray("subgoals");
        			subcomposite.add(makeAndGoal(dungeon, andsubs));
        		}else if (firstLine.equals("OR")) {
        			JSONArray orsubs = goal.getJSONArray("subgoals");
        			subcomposite.add(makeOrGoal(dungeon, orsubs));
        		}
    		}
    	}
    	return subcomposite;
    }
    
    private GoalComposite makeOrGoal(Dungeon dungeon, JSONArray subgoals) {
    	GoalComposite subcomposite = new GoalComposite("or", dungeon);
    	for (int i = 0; i < subgoals.length(); i++) {
    		JSONObject goal = subgoals.getJSONObject(i);
    		String firstLine = goal.getString("goal");
    		if (!firstLine.equals("AND") && !firstLine.equals("OR")) {
    			subcomposite.add(makeLeaf(firstLine, dungeon));
    		}else {
    			if (firstLine.equals("AND")) {
        			JSONArray andsubs = goal.getJSONArray("subgoals");
        			subcomposite.add(makeAndGoal(dungeon, andsubs));
        		}else if (firstLine.equals("OR")) {
        			JSONArray orsubs = goal.getJSONArray("subgoals");
        			subcomposite.add(makeOrGoal(dungeon, orsubs));
        		}
    		}
    	}
    	return subcomposite;
    }
    
    private GoalComponent makeLeaf(String goal, Dungeon dungeon) {
    	
    	GoalComponent leaf = null;
    	switch (goal) {
    	case "exit":
    		leaf = createExitGoal(dungeon);
    		break;
    	case "enemies":
    		leaf = createEnemyGoal(dungeon);
    		break;
    	case "boulders":
    		leaf = createBoulderGoal(dungeon);	
    		break;
    	case "treasure":
    		leaf = createTreasureGoal(dungeon);
    		break;
    	}
    	return leaf;
    	//return new GoalLeaf(goal, dungeon);
    }
    
    private GoalExit createExitGoal(Dungeon dungeon) {
		Exit exit = dungeon.getExit();
		Player player = dungeon.getPlayer();
		GoalExit eg = new GoalExit(player, exit);
		return eg;
	}
    
    private GoalEnemy createEnemyGoal(Dungeon dungeon) {
		int count = dungeon.getEnemyCount();
		GoalEnemy eg = new GoalEnemy(count);
		dungeon.setObserverEnemyGoal(eg);
		return eg;
	}
    
    private GoalBoulder createBoulderGoal(Dungeon dungeon) {
		//int count = dungeon.getBoulderCount();
		GoalBoulder sg = new GoalBoulder(dungeon);
		//dungeon.setObserverBoulderGoal(sg);
		return sg;
	}
    
    private GoalTreasure createTreasureGoal(Dungeon dungeon) {
		int count = dungeon.getTreasureCount();
		GoalTreasure tg = new GoalTreasure(count);
		dungeon.setObserverTreasureGoal(tg);
		return tg;
	}

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "switch":
        	FloorSwitch floorswitch = new FloorSwitch(x, y);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "key":
        	int keyId = json.getInt("id");
        	Key key = new Key(x, y, keyId);
        	//int key_id = json.getInt("ID");
        	onLoad(key);
        	entity = key;
        	break;
        case "portal":
        	int portalId = json.getInt("id");
        	Portal portal = new Portal(x, y, portalId);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(x, y);
        	onLoad(invincibility);
        	entity = invincibility;
        	break;
        case "door":
        	int doorId = json.getInt("id");
        	Door door = new Door(x, y, doorId);
        	//int door_id = json.getInt("ID");
        	onLoad(door);
        	entity = door;
        	break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    public abstract void onLoad(FloorSwitch floorswitch);
    
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Exit exit);

    // TODO Create additional abstract methods for the other entities
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Key key);
    
    public abstract void onLoad(Portal portal);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Invincibility invincibility);

    public abstract void onLoad(Door door);
}
