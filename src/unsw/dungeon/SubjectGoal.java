package unsw.dungeon;

public interface SubjectGoal {
	void registerGoalObserver(ObserverGoal o);

    void removeGoalObserver(ObserverGoal o);

    void notifyGoal();
}
