package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import unsw.dungeon.*;

import org.junit.jupiter.api.Test;

class DoorKeyTest {
	
	Dungeon dungeon;
	Player player;
	Entity door;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		player = new Player(dungeon, 0, 0);
		door = new Door(1, 1, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		dungeon.addEntity(door);
		dungeon.setDoorObserver();
	}
	
	@Test
	void CannotGoThroughClosedDoor() {
		assertTrue(door.getX() == 1 && door.getY() == 1);
		assertTrue(((Door) door).open() == false);
		assertTrue(dungeon.canGoThere(1, 1) == false);
		System.out.println("CannotGoThroughClosedDoor passed");
	}
	
	@Test
	void PlayerCanGoThroughOpenDoor() {
		assertTrue(door.getX() == 1 && door.getY() == 1);
		assertTrue(((Door) door).open() == false);
		((Door) door).doorCanOpen();
		assertTrue(((Door) door).open() == true);
		assertTrue(dungeon.canGoThere(1, 1) == true);
		System.out.println("PlayerCanGoThroughOpenDoor passed");
	} 
	
	@Test
	void PlayerCannotCarryMultipleKey() {
		Entity key1 = new Key(2, 2, 0);
		Entity key2 = new Key(3, 3, 1);
		dungeon.addEntity(key1);
		dungeon.addEntity(key2);
		player.moveDown();
		player.moveRight();
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(player.getPickups().size() == 1);
		assertTrue(player.hasKey());
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(player.getPickups().size() == 1);
		System.out.println("PlayerCannotCarryMultipleKey passed");
	}
	
	@Test
	void PlayerCanOpenDoorWithRightKey() {
		assertTrue(door.getX() == 1 && door.getY() == 1);
		assertTrue(((Door) door).open() == false);
		Key key = new Key(1, 0, 0);
		dungeon.addEntity(key);
		player.moveRight();
		player.pickUp();
		player.moveDown();
		assertTrue(player.openDoor() == true);
		System.out.println("PlayerCanOpenDoorWithRightKey passed");
	}
	
	@Test
	void PlayerCannotOpenDoorWithWrongKey() {
		assertTrue(door.getX() == 1 && door.getY() == 1);
		assertTrue(((Door) door).open() == false);
		Key key = new Key(1, 0, 1);
		dungeon.addEntity(key);
		player.moveRight();
		player.pickUp();
		player.moveDown();
		assertTrue(player.openDoor() == false);
		System.out.println("PlayerCannotOpenDoorWithWrongKey passed");
	}
	
	@Test
	void EnemyCanGoThroughOpenDoor() {
		Enemy enemy = new Enemy(dungeon, 3, 3);
		dungeon.addEntity(enemy);
		assertTrue(((Door) door).open() == false);
		((Door) door).doorCanOpen();
		assertTrue(((Door) door).open() == true);
		for (int i = 0; i < enemy.getDistance(3, 3, 1, 1); i++) {
			enemy.go2position(1, 1);
		}
		assert(enemy.getX() == 1 && enemy.getY() == 1);
		System.out.println("EnemyCanGoThroughOpenDoor passed");
	}
	
	@Test
	void BoulderCanGoThroughOpenDoor() {
		Boulder bld = new Boulder(1, 0);
		dungeon.addEntity(bld);
		assertTrue(((Door) door).open() == false);
		((Door) door).doorCanOpen();
		assertTrue(((Door) door).open() == true);
		bld.moveDown();
		assertTrue(bld.getX() == 1 && bld.getY() == 1);
		bld.moveDown();
		assertTrue(bld.getX() == 1 && bld.getY() == 2);
		System.out.println("BoulderCanGoThroughOpenDoor passed");
	}
	
}
