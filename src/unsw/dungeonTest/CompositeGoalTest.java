/*
package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class CompositeGoalTest {

	Dungeon dungeon;
	Player player;
	GoalComponent treasureGoal;
	GoalComponent boulderGoal;
	GoalComponent exitGoal;
	GoalComponent enemyGoal;
	GoalComponent goals;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		treasureGoal = new GoalTreasure(dungeon);
		boulderGoal = new GoalBoulder(dungeon);
		exitGoal = new GoalExit(player);
		enemyGoal = new GoalEnemy(dungeon);
	}
	
	@Test
	void ShowANDGoals() {
		goals = new GoalComposite("and", dungeon);
		goals.add(treasureGoal);
		goals.add(boulderGoal);
		goals.add(exitGoal);
		goals.add(enemyGoal);
		dungeon.addGoal(goals);
		assertTrue(dungeon.getGameGuide().equals("[(pick up all treasure) and\n" + "(turn on all floor switches) and\n" + "(getting to exit) and\n" + "(kill all enemies)]"));           
		System.out.println("ShowANDGoals passed");
	}
	
	@Test
	void ShowORGoals() {
		goals = new GoalComposite("or", dungeon);
		goals.add(treasureGoal);
		goals.add(boulderGoal);
		goals.add(exitGoal);
		goals.add(enemyGoal);
		dungeon.addGoal(goals);
		assertTrue(dungeon.getGameGuide().equals("[(pick up all treasure) or\n" + "(turn on all floor switches) or\n" + "(getting to exit) or\n" + "(kill all enemies)]"));           
		System.out.println("ShowORGoals passed");
	}
	
	@Test
	void ShowComplexGoals() {
		goals = new GoalComposite("or", dungeon);
		GoalComponent goal1 = new GoalComposite("and", dungeon);
		goal1.add(treasureGoal);
		goal1.add(exitGoal);
		GoalComponent goal2 = new GoalComposite("and", dungeon);
		goal2.add(boulderGoal);
		goal2.add(enemyGoal);
		goals.add(goal1);
		goals.add(goal2);
		dungeon.addGoal(goals);
		assertTrue(dungeon.getGameGuide().equals("[[(pick up all treasure) and\n" + "(getting to exit)] or\n" + "[(turn on all floor switches) and\n" + "(kill all enemies)]]"));           
		System.out.println("ShowComplexGoals passed");
	}
	
	@Test
	void CompleteANDGoals() {
		goals = new GoalComposite("and", dungeon);
		goals.add(treasureGoal);
		goals.add(exitGoal);
		dungeon.addGoal(goals);
		Treasure trea = new Treasure(1, 1);
		Exit exit = new Exit(2, 2);
		dungeon.addEntity(trea);
		dungeon.addEntity(exit);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(!dungeon.checkgoal());
		player.moveDown();
		player.moveRight();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteANDGoals passed");
	}
	
	@Test
	void CompleteORGoals() {
		goals = new GoalComposite("or", dungeon);
		goals.add(treasureGoal);
		goals.add(exitGoal);
		dungeon.addGoal(goals);
		Treasure trea = new Treasure(1, 1);
		Exit exit = new Exit(2, 2);
		dungeon.addEntity(trea);
		dungeon.addEntity(exit);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteORGoals passed");
	}
	
	@Test
	void CompleteComplexGoals() {
		goals = new GoalComposite("or", dungeon);
		GoalComponent goal1 = new GoalComposite("and", dungeon);
		goal1.add(treasureGoal);
		goal1.add(exitGoal);
		GoalComponent goal2 = new GoalComposite("and", dungeon);
		goal2.add(boulderGoal);
		goal2.add(enemyGoal);
		goals.add(goal1);
		goals.add(goal2);
		dungeon.addGoal(goals);
		Treasure trea = new Treasure(1, 1);
		Exit exit = new Exit(2, 2);
		dungeon.addEntity(trea);
		dungeon.addEntity(exit);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		player.moveDown();
		player.moveRight();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteComplexGoals passed");
	}
	
	@Test
	void CannotCompleteIfExitFirst() {
		goals = new GoalComposite("and", dungeon);
		goals.add(treasureGoal);
		goals.add(exitGoal);
		dungeon.addGoal(goals);
		Treasure trea = new Treasure(2, 2);
		Exit exit = new Exit(1, 1);
		dungeon.addEntity(trea);
		dungeon.addEntity(exit);
		player.moveDown();
		player.moveRight();
		assertTrue(!dungeon.checkgoal());
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(!dungeon.checkgoal());
		System.out.println("CannotCompleteIfExitFirst passed");
	}
}*/
