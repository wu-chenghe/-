package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class ExitTest {
	
	Dungeon dungeon;
	Entity exit;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		exit = new Exit(4, 4);
		dungeon.addEntity(exit);
	}
	
	@Test
	void ExitCanBeStepOn() {
		assertTrue(exit.getX() == 4);
		assertTrue(exit.getY() == 4);
		assertTrue(dungeon.canGoThere(4, 4));
	}

}
