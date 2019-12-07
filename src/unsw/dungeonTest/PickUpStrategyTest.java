package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class PickUpStrategyTest {

	Dungeon dungeon;
	Player player;
	Enemy enemy;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		enemy = new Enemy(dungeon, 5, 5);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		dungeon.addEntity(enemy);
	}
	
	@Test
	void PlayerPickUpTreasureTest() {
		List<Entity> pickUps = player.getPickups();
		assertEquals(0, pickUps.size());
		Entity treasure = new Treasure(1, 1);
		dungeon.addEntity(treasure);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(1, pickUps.size());
		assertTrue(dungeon.findEntity(1, 1) == null);
		assertTrue(player.meetEntity() == null);
		System.out.println("PlayerPickUpTreasureTest passed");
	}
	
	@Test
	void PlayerPickUpSwordTest() {
		List<Entity> pickUps = player.getPickups();
		assertEquals(0, pickUps.size());
		Entity sword = new Sword(1, 1);
		dungeon.addEntity(sword);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(1, pickUps.size());
		assertTrue(dungeon.findEntity(1, 1) == null);
		assertTrue(player.meetEntity() == null);
		System.out.println("PlayerPickUpSwordTest passed");
	}
	
	@Test
	void PlayerPickUpKeyTest() {
		List<Entity> pickUps = player.getPickups();
		assertEquals(0, pickUps.size());
		Entity key = new Key(1, 1, 0);
		dungeon.addEntity(key);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(1, pickUps.size());
		assertTrue(dungeon.findEntity(1, 1) == null);
		assertTrue(player.meetEntity() == null);
		System.out.println("PlayerPickUpKeyTest passed");
	}
	
	@Test
	void PlayerPickUpPotionTest() {
		List<Entity> pickUps = player.getPickups();
		assertEquals(0, pickUps.size());
		Entity potion = new Invincibility(1, 1);
		dungeon.addEntity(potion);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(1, pickUps.size());
		assertTrue(dungeon.findEntity(1, 1) == null);
		assertTrue(player.meetEntity() == null);
		System.out.println("PlayerPickUpPotionTest passed");
	}
	
	@Test
	void PlayerPickUpAllTest() {
		List<Entity> pickUps = player.getPickups();
		assertEquals(0, pickUps.size());
		Entity treasure = new Treasure(1, 1);
		Entity sword = new Sword(2, 2);
		Entity key = new Key(3, 3, 0);
		Entity potion = new Invincibility(4, 4);
		dungeon.addEntity(sword);
		dungeon.addEntity(key);
		dungeon.addEntity(treasure);
		dungeon.addEntity(potion);
		// picking up treasure
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(1, pickUps.size());
		
		// picking up sword
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(2, pickUps.size());
		
		// picking up key
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(3, pickUps.size());
		
		// picking up potion
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertEquals(4, pickUps.size());
		
		assertTrue(dungeon.findEntity(1, 1) == null);
		assertTrue(dungeon.findEntity(2, 2) == null);
		assertTrue(dungeon.findEntity(3, 3) == null);
		assertTrue(dungeon.findEntity(4, 4) == null);
		System.out.println("PlayerPickUpAllTest passed");
	}
	
	@Test
	void EnemyCannotPickUpTreasureTest() {
		Entity treasure = new Treasure(1, 1);
		dungeon.addEntity(treasure);
		((Enemy) enemy).go2position(1, 1);
		assertTrue(dungeon.findEntity(1, 1) != null);
		System.out.println("EnemyCannotPickUpTreasureTest passed");
	}
	
	@Test
	void EnemyCannotPickUpSwordTest() {
		Entity sword = new Sword(1, 1);
		dungeon.addEntity(sword);
		((Enemy) enemy).go2position(1, 1);
		assertTrue(dungeon.findEntity(1, 1) != null);
		System.out.println("EnemyCannotPickUpSwordTest passed");
	}
	
	@Test
	void EnemyCannotPickUpKeyTest() {
		Entity key = new Key(1, 1, 0);
		dungeon.addEntity(key);
		((Enemy) enemy).go2position(1, 1);
		assertTrue(dungeon.findEntity(1, 1) != null);
		System.out.println("EnemyCannotPickUpKeyTest passed");
	}
	
	@Test
	void EnemyCannotPickUpPotionTest() {
		Entity potion = new Invincibility(1, 1);
		dungeon.addEntity(potion);
		((Enemy) enemy).go2position(1, 1);
		System.out.println(enemy.getX() + ","+ enemy.getY());
		assertTrue(dungeon.findEntity(1, 1) != null);
		System.out.println("EnemyCannotPickUpPotionTest passed");
	}
}
