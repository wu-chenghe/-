package unsw.dungeon;

public interface SubjectDoor {
	void registerDoorObserver(ObserverDoor o);

    void removeDoorObserver(ObserverDoor o);

    void notifyDoor();
}
