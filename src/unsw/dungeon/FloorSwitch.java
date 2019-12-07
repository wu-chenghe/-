package unsw.dungeon;

import java.util.ArrayList;

public class FloorSwitch extends Entity implements SubjectGoal{
	private boolean fswitch;
	private ArrayList<ObserverGoal> observers;

	public FloorSwitch(int x, int y) {
		super(x, y);
		this.fswitch = false;
		this.observers = new ArrayList<ObserverGoal>();
	}
	
	/**
	 * turn on switch
	 */
	public void turnOn() {
		this.fswitch = true;
	}
	
	/**
	 * turn off switch
	 */
	public void turnOff() {
		this.fswitch = false;
	}
	
	/**
	 * check if switch is on
	 * @return
	 */
	public boolean checkIsOn() {
		return fswitch;
	}
	
	/**
	 * update observer
	 * @param ison
	 */
	public void update(boolean ison) {
		if(ison) {
			turnOn();
		}else {
			turnOff();
		}
	}
	
	/**
	 * register observer for goal
	 */
	@Override
	public void registerGoalObserver(ObserverGoal o) {
		observers.add(o);
	}
	
	/**
	 * remove observer for goal
	 */
	@Override
	public void removeGoalObserver(ObserverGoal o) {
		// TODO Auto-generated method stub
		observers.remove(o);
	}
	
	/**
	 * notify goal subject
	 */
	@Override
	public void notifyGoal() {
		for (ObserverGoal o : observers) {
			o.update(this);
		}
	}
}
