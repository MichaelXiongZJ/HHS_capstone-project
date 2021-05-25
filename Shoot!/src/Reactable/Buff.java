package Reactable;

import java.util.ArrayList;

import actor.Actor;
import actor.Player;
import processing.core.PApplet;

public abstract class Buff extends Actor{

	/**
	 * Construct a superclass object of buff
	 * @author Michael
	 * @param x x loc
	 * @param y y loc 
	 */
	public Buff(int x, int y) {
		super(1, x, y, 0, 0);
		setRadius(35);
	}

	/**
	 * Draw out the buff
	 * @author Michael
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
	}
	
	/**
	 * action of projectile
	 * @author Michael
	 * @param actors the arraylist of actors to interact with
	 */
	public void act(ArrayList<Actor> actors) {
		
	}
	
}
