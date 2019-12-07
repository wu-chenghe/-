package unsw.dungeon;

public interface GoalComponent {
	
	public void add(GoalComponent component);
    
    public String getInfo();
    
    public boolean checkgoals();
}
