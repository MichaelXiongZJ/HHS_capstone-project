package Reactable;

import java.util.ArrayList;

import actor.Actor;
import actor.Player;

public class Medkit extends Buff{

	private int healthAmount;
	
	public Medkit(int x, int y) {
		super(x, y);
		healthAmount = 5;
	}
	
	public void act(ArrayList<Actor> actors) {
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a)) && actors.get(a) instanceof Player) {
				actors.get(a).setHp(getHp()+healthAmount);
				setHp(0);
			}
		}
	}
}
