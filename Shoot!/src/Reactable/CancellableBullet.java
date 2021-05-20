package Reactable;

import java.awt.Color;
import java.util.ArrayList;

import actor.Actor;
import actor.Player;

public class CancellableBullet extends Buff{

	
	public CancellableBullet(int x, int y) {
		super(x, y);
		setFill(Color.PINK);
	}
	
	public void act(ArrayList<Actor> actors) {
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a)) && actors.get(a) instanceof Player) {
				cancelBullet();
				setHp(0);
			}
		}
	}
}
