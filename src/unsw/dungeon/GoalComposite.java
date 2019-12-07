package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class GoalComposite implements GoalComponent {
	
	private String goal;
	private Dungeon dungeon;
	private List<GoalComponent> subgoals;
	
	public GoalComposite(String goal, Dungeon dungeon) {
		this.goal = goal;
		this.dungeon = dungeon;
		this.subgoals = new ArrayList<GoalComponent>();
	}
	
	public boolean checkgoals() {
		if (goal.equals("main goal")) {
			for (GoalComponent g : subgoals) {
				if (! g.checkgoals()) {
					return false;
				}
			}
		}
		if (goal.equals("and")) {
			for (GoalComponent g : subgoals) {
				if (! g.checkgoals()) {
					return false;
				}
			}
		}
		if (goal.equals("or")) {
			for (GoalComponent g : subgoals) {
				if (g.checkgoals()) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public void add(GoalComponent component) {
		if (! subgoals.contains(component)) {
			this.subgoals.add(component);
		}		
	}
	
	public String getGoal() {
		return this.goal;
	}
	
	public List<GoalComponent> getSubgoals() {
		return this.subgoals;
	}

	@Override
	public String getInfo() {
		String ret = "[";
		if (goal.equals("main goal")) {
			for (GoalComponent g : subgoals) {
				GoalComposite mainsub = null;
				if(g instanceof GoalComposite) {
					mainsub = (GoalComposite) g;
				}
				if(mainsub != null && mainsub.getGoal().equals("or")) {
					for (GoalComponent sg : mainsub.getSubgoals()) {
						ret += sg.getInfo() + "\nor\n";
					}
					ret = ret.substring(0, ret.length()-4);
					ret += "]";
				}else if(mainsub != null && mainsub.getGoal().equals("and")) {
					for (GoalComponent sg : mainsub.getSubgoals()) {
						ret += sg.getInfo() + "\nand\n";
					}
					ret = ret.substring(0, ret.length()-5);
					ret += "]";
				}else {
					ret = g.getInfo();
				}
				
			}
		}
		if (goal.equals("and")) {
			for (GoalComponent g : subgoals) {
				ret += g.getInfo() + " and ";
			}
			ret = ret.substring(0, ret.length()-5);
			ret += "]";
		}
		if (goal.equals("or")) {
			for (GoalComponent g : subgoals) {
				ret += g.getInfo() + " or ";
			}
			ret = ret.substring(0, ret.length()-4);
			ret += "]";
		}
		return ret;
	}

}