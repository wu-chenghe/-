package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class EnemyTest {
	
	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		enemy = new Enemy(dungeon, 4, 4);
		dungeon.addEntity(enemy);
		dungeon.setEnemyObserver();
	}
	
	@Test
	void EnemyCannotGoThroughWall() {
		Wall wall = new Wall(1, 1);
		dungeon.addEntity(wall);
		assertTrue(!dungeon.canGoThere(1, 1));
		System.out.println("EnemyCannotGoThroughWall passed");
	}
	
	@Test
	void ApproachPlayerOneSqrPerMove() {
		player.moveRight();
		assertTrue(player.getX() == 1 && player.getY() == 0);
		player.notifyEnemy();
		assertTrue(enemy.getX() == 3 || enemy.getX() == 5 || enemy.getY() == 3 || enemy.getY() == 5);
		System.out.println("ApproachPlayerOneSqrPerMove passed");
	}
	
	@Test
	void KillPlayerWithoutWeapon() {
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		assertTrue(player.battleEnemy() == false);
		assertTrue(!player.checkAlive());
		System.out.println("KillPlayerWithoutWeapon passed");
		//System.out.println(enemy.getX() +  "," + enemy.getY());
	}
	
	@Test
	void KilledByPlayerWithSword() {
		Sword sword = new Sword(1, 1);
		dungeon.addEntity(sword);
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.pickUp();
		assertTrue(player.hasSword());
		player.moveDown();
		player.notifyEnemy();
		player.moveDown();
		player.notifyEnemy();
		assertTrue(player.battleEnemy());
		assertTrue(player.checkAlive());
		assertTrue(!dungeon.hasEnemy());
		System.out.println("KilledByPlayerWithSword passed");
	}
	
	@Test
	void KilledByPlayerWithPotion() {
		Invincibility potion = new Invincibility(1, 1);
		dungeon.addEntity(potion);
		player.moveDown();
		player.notifyEnemy();
		player.moveRight();
		player.notifyEnemy();
		player.pickUp();
		assertTrue(player.hasInvincibility());
		player.useInvincibility();
		player.moveDown();
		player.moveDown();
		player.moveDown();
		player.moveRight();
		player.notifyEnemy();
		player.moveRight();
		assertTrue(player.hasInvincibility());
		assertTrue(player.battleEnemy());
		assertTrue(player.checkAlive());
		assertTrue(!dungeon.hasEnemy());
		System.out.println("KilledByPlayerWithPotion passed");
	}


}
