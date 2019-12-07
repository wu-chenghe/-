package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class FloorSwitchTest {
	
	Dungeon dungeon;
	Entity floorswitch;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		floorswitch = new FloorSwitch(3, 3);
		dungeon.addEntity(floorswitch);
	}
	
	@Test
	void FloorSwitchCanBeStepOn() {
		assertTrue(dungeon.canGoThere(3, 3));
		Entity fs1 = new FloorSwitch(1, 1);
		Entity fs2 = new FloorSwitch(2, 2);
		dungeon.addEntity(fs1);
		dungeon.addEntity(fs2);
		assertTrue(dungeon.canGoThere(1, 1));
		assertTrue(dungeon.canGoThere(2, 2));
		System.out.println("FloorSwitchCanBeStepOn passed");
	}
	
	@Test
	void TriggerFloorSwitch() {
		Entity bld = new Boulder(2, 3);
		dungeon.addEntity(bld);
		((Boulder) bld).moveRight();
		assertTrue(bld.getX() == 3 && bld.getY() == 3);
		dungeon.turnSwitch();
		assertTrue(((FloorSwitch) floorswitch).checkIsOn());
		System.out.println("TriggerFloorSwitch passed");
	}

	@Test
	void UntriggerFloorSwitch() {
		Entity bld = new Boulder(2, 3);
		dungeon.addEntity(bld);
		((Boulder) bld).moveRight();
		dungeon.turnSwitch();
		((Boulder) bld).moveRight();
		assertTrue(bld.getX() == 4 && bld.getY() == 3);
		dungeon.turnSwitch();
		assertTrue(!((FloorSwitch) floorswitch).checkIsOn());
		System.out.println("UntriggerFloorSwitch passed");
	}
}
