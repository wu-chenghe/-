package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class EnemyGoalTest {

	Dungeon dungeon;
	Player player;
	GoalEnemy enemyGoal;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		//enemyGoal = new GoalEnemy(dungeon);
		//dungeon.addGoal(enemyGoal);
	}
	
	@Test
	void ShowEnemyGoal() {
		enemyGoal = new GoalEnemy(1);
		dungeon.addGoal(enemyGoal);
		assertTrue(dungeon.getGameGuide().equals("(kill all enemies)"));
		System.out.println("ShowEnemyGoal passed");
	}
	
	@Test
	void CompleteSingleEnemyGoal() {
		Enemy enemy = new Enemy(dungeon, 4, 4);
		dungeon.addEntity(enemy);
		dungeon.setEnemyObserver();
		enemyGoal = new GoalEnemy(1);
		dungeon.addGoal(enemyGoal);
		dungeon.setObserverEnemyGoal(enemyGoal);
		Sword sword = new Sword(1, 1);
		dungeon.addEntity(sword);
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.pickUp();
		player.moveDown();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		player.battleEnemy();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteSingleEnemyGoal passed");
	}
	
	@Test
	void CompleteMultipleEnemyGoal() {
		Enemy enemy1 = new Enemy(dungeon, 3, 4);
		Enemy enemy2 = new Enemy(dungeon, 4, 4);
		dungeon.addEntity(enemy1);
		dungeon.addEntity(enemy2);
		dungeon.setEnemyObserver();
		enemyGoal = new GoalEnemy(2);
		dungeon.addGoal(enemyGoal);
		dungeon.setObserverEnemyGoal(enemyGoal);
		Sword sword = new Sword(1, 1);
		dungeon.addEntity(sword);
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.pickUp();
		player.moveDown();
		player.notifyEnemy();
		player.battleEnemy();
		player.moveDown();
		player.notifyEnemy();
		player.battleEnemy();
		assertTrue(dungeon.checkgoal());
		System.out.println("CompleteMultipleEnemyGoal passed");
	}
	
	@Test
	void FailEnemyGoal() {
		Enemy enemy = new Enemy(dungeon, 4, 4);
		dungeon.addEntity(enemy);
		dungeon.setEnemyObserver();
		enemyGoal = new GoalEnemy(1);
		dungeon.addGoal(enemyGoal);
		dungeon.setObserverEnemyGoal(enemyGoal);
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		player.battleEnemy();
		assertTrue(!dungeon.checkgoal());
		System.out.println("FailEnemyGoal passed");
	}
}
