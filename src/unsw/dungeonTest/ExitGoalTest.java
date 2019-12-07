package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class ExitGoalTest {

	Dungeon dungeon;
	Player player;
	GoalExit exitGoal;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Exit exit = new Exit(4, 4);
		dungeon.addEntity(exit);
		exitGoal = new GoalExit(player, exit);
		dungeon.addGoal(exitGoal);
	}
	
	@Test
	void ShowExitGoal() {
		assertTrue(dungeon.getGameGuide().equals("(getting to exit)"));
		System.out.println("ShowExitGoal passed");
	}
	
	@Test
	void CompleteExitGoal() {
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteExitGoal passed");
	}
	
	@Test
	void FailExitGoal() {
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		assertTrue(!dungeon.checkgoal());
		System.out.println("FailExitGoal passed");
	}

}
