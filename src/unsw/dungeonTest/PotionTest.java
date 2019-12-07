  package unsw.dungeonTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unsw.dungeon.*;

class PotionTest {

	Dungeon dungeon;
	Entity potion;
	
	@BeforeEach
	void init() {
		dungeon = new Dungeon(5, 5);
		potion = new Invincibility(1, 1);
		dungeon.addEntity(potion);
	}
	
	@Test
	void PotionCanBeStepOn() {
		assertTrue(potion.getX() == 1);
		assertTrue(potion.getY() == 1);
		assertTrue(dungeon.canGoThere(1, 1));
		System.out.println("PotionCanBeStepOn passed");
	}
	
	@Test
	void EnemyRunAwayWhenInvincible() {
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon, 4, 4);
		dungeon.addEntity(enemy);
		dungeon.setEnemyObserver();
		player.moveDown();
		player.moveRight();
		assertTrue(player.pickUp());
		player.useInvincibility();
		
		try {
			Thread.sleep(4000);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		// 4 seconds later
		assertTrue(enemy.getX() != 4 || enemy.getY() != 4);
		System.out.println("EnemyRunAwayWhenInvincible passed");
	}
	
	
	@Test
	void PotionUselessAfterFiveSeconds() {
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		player.moveDown();
		player.moveRight();
		assertTrue(player.pickUp());
		player.useInvincibility();
		assertTrue(player.hasInvincibility());
		
		try {
			Thread.sleep(5001);
		}
		catch (InterruptedException e){
			e.printStackTrace();
		}
		// 5.001 seconds later
		assertTrue(!player.hasInvincibility());
		System.out.println("PotionUselessAfterFiveSeconds passed");
	}

}
