package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class SwordTest {
	
	Dungeon dungeon;
	Entity sword;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		sword = new Sword(1, 1);
		dungeon.addEntity(sword);
	}
	
	@Test
	void SwordCanBeStepOn() {
		assertTrue(sword.getX() == 1);
		assertTrue(sword.getY() == 1);
		assertTrue(dungeon.canGoThere(1, 1));
		System.out.println("SwordCanBeStepOn passed");
	}
	
	@Test
	void SwordHasFiveHits() {
		assertTrue(((Sword) sword).getHits() == 5);
		System.out.println("SwordHasFiveHits passed");
	}
	
	@Test
	void PlayerCannotCarryMultipleSword() {
		Player player = new Player(dungeon, 0, 0);
		Sword sw2 = new Sword(2, 2);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		dungeon.addEntity(sw2);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(player.getPickups().size() == 1);
		assertTrue(player.hasSword());
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(player.getPickups().size() == 1);
		System.out.println("PlayerCannotCarryMultipleSword passed");
	}
	
	@Test
	void SwordUselessAfterFiveHits() {
		Player player = new Player(dungeon, 0, 0);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		player.moveDown();
		player.moveRight();
		player.pickUp();
		assertTrue(((Sword) sword).getHits() == 5);
		((Sword) sword).hit();
		((Sword) sword).hit();
		((Sword) sword).hit();
		((Sword) sword).hit();
		((Sword) sword).hit();
		assertTrue(((Sword) sword).getHits() == 0);
		assertTrue(player.findSword2use() == null);
		System.out.println("SwordUselessAfterFiveHits passed");
	}
	
}
