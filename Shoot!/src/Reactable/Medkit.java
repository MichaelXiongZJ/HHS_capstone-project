package Reactable;

import java.awt.Color;
import java.util.ArrayList;

import actor.Actor;
import actor.Player;

public class Medkit extends Buff{

	private int healthAmount;
	
	public Medkit(int x, int y) {
		super(x, y);
		healthAmount = 5;
		setFill(Color.GREEN);
	}
	
	public void act(ArrayList<Actor> actors) {
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a)) && actors.get(a) instanceof Player) {
				actors.get(a).setHp(actors.get(a).getHp()+healthAmount);
				setHp(0);
			}
		}
	}
}
