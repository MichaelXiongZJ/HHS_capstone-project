package Reactable;

import java.awt.Color;
import java.util.ArrayList;

import Projectile.PlayerBullet;
import actor.Actor;
import actor.Player;

public class CancellableBullet extends Buff{

	/**
	 * Construct a CancellableBullet object
	 * @author Michael
	 * @param x x loc
	 * @param y y loc
	 */
	public CancellableBullet(int x, int y) {
		super(x, y);
		setFill(Color.PINK);
	}
	
	/**
	 * Actions of a CancellableBullet
	 * @author Michael
	 */
	public void act(ArrayList<Actor> actors) {
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a)) && actors.get(a) instanceof Player) {
				PlayerBullet.cancelBullet();
				setHp(0);
			}
		}
	}
}
