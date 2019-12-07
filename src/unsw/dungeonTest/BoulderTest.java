package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class BoulderTest {
	
	Dungeon dungeon;
	Entity boulder;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		boulder = new Boulder(3, 3);
		dungeon.addEntity(boulder);
	}
	
	@Test
	void BoulderCanMove() {
		((Boulder) boulder).moveUp();
		assertTrue(boulder.getX() == 3 && boulder.getY() == 2);
		((Boulder) boulder).moveDown();
		assertTrue(boulder.getX() == 3 && boulder.getY() == 3);
		((Boulder) boulder).moveLeft();
		assertTrue(boulder.getX() == 2 && boulder.getY() == 3);
		((Boulder) boulder).moveRight();
		assertTrue(boulder.getX() == 3 && boulder.getY() == 3);
		System.out.println("BoulderCanMove passed");
	}

	@Test
	void BoulderCannotMoveBoundaries() {
		((Boulder) boulder).moveUp();
		((Boulder) boulder).moveUp();
		((Boulder) boulder).moveUp();
		assertTrue(boulder.getX() == 3 && boulder.getY() == 0);
		((Boulder) boulder).moveLeft();
		((Boulder) boulder).moveLeft();
		((Boulder) boulder).moveLeft();
		assertTrue(boulder.getX() == 0 && boulder.getY() == 0);
		System.out.println("BoulderCannotMoveBoundaries passed");
	}
	
	@Test
	void BoulderCannotMoveWalls() {
		Entity wall = new Wall(2, 2);
		dungeon.addEntity(wall);
		assertTrue(!dungeon.canGoThere(2, 2));
		System.out.println("BoulderCannotMoveWalls passed");
	}
	
	@Test
	void PlayerCanPushRightBoulder() {
		Player player = new Player(dungeon, 2, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.pushRight(boulder);
		assertTrue(boulder.getX() == 4 && boulder.getY() == 3);
		System.out.println("PlayerCanPushRightBoulder passed");
	}
	
	@Test
	void PlayerCanPushLeftBoulder() {
		Player player = new Player(dungeon, 4, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.pushLeft(boulder);
		assertTrue(boulder.getX() == 2 && boulder.getY() == 3);
		System.out.println("PlayerCanPushLeftBoulder passed");
	}
	
	@Test
	void PlayerCanPushUpBoulder() {
		Player player = new Player(dungeon, 3, 4);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.pushUp(boulder);
		assertTrue(boulder.getX() == 3 && boulder.getY() == 2);
		System.out.println("PlayerCanPushUpBoulder passed");
	}
	
	@Test
	void PlayerCanPushDownBoulder() {
		Player player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.pushDown(boulder);
		assertTrue(boulder.getX() == 3 && boulder.getY() == 4);
		System.out.println("PlayerCanPushDownBoulder passed");
	}
	
	@Test
	void PlayerCannotPushTwoBoulder() {
		Player player = new Player(dungeon, 3, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		Entity bld = new Boulder(3, 4);
		dungeon.addEntity(bld);
		dungeon.canGoThere(3, 3, 3, 4);
		System.out.println("PlayerCannotPushTwoBoulder passed");
	}
	
	
}
