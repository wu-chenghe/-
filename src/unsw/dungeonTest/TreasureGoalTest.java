package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class TreasureGoalTest {

	Dungeon dungeon;
	Player player;
	GoalTreasure treasureGoal;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
	}
	
	@Test
	void ShowTreasureGoal() {
		treasureGoal = new GoalTreasure(1);
		dungeon.addGoal(treasureGoal);
		assertTrue(dungeon.getGameGuide().equals("(pick up all treasure)"));
		System.out.println("ShowTreasureGoal passed");
	}
	
	@Test
	void CompleteSingleTreasureGoal() {
		Treasure trea = new Treasure(1, 1);
		dungeon.addEntity(trea);
		treasureGoal = new GoalTreasure(1);
		dungeon.setObserverTreasureGoal(treasureGoal);
		dungeon.addGoal(treasureGoal);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteSingleTreasureGoal passed");
	}
	
	@Test
	void CompleteMultipleTreasureGoal() {
		Treasure trea1 = new Treasure(1, 1);
		Treasure trea2 = new Treasure(2, 2);
		Treasure trea3 = new Treasure(3, 3);
		dungeon.addEntity(trea1);
		dungeon.addEntity(trea2);
		dungeon.addEntity(trea3);
		treasureGoal = new GoalTreasure(3);
		dungeon.setObserverTreasureGoal(treasureGoal);
		dungeon.addGoal(treasureGoal);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		player.moveDown();
		player.moveRight();
		player.pickUp();
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteMultipleTreasureGoal passed");
	}
	
	@Test
	void FailTreasureGoal() {
		Treasure trea1 = new Treasure(1, 1);
		Treasure trea2 = new Treasure(2, 2);
		dungeon.addEntity(trea1);
		dungeon.addEntity(trea2);
		treasureGoal = new GoalTreasure(2);
		dungeon.setObserverTreasureGoal(treasureGoal);
		dungeon.addGoal(treasureGoal);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(!dungeon.checkgoal());
		System.out.println("FailTreasureGoal passed");
	}
}
