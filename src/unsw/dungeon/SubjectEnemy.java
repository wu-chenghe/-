package unsw.dungeon;

public interface SubjectEnemy {
	void registerEnemyObserver(ObserverEnemy o);

    void removeEnemyObserver(ObserverEnemy o);

    void notifyEnemy();
}
