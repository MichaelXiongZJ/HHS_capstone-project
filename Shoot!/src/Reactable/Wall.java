package Reactable;

import java.awt.Color;
import java.util.ArrayList;

import Projectile.Projectile;
import actor.Actor;
import processing.core.PApplet;

public class Wall extends Actor{

	private double width;
	private double height;
	
	public Wall() {
		super();
	}
	
	/**
	 * 
	 * @param x x coord
	 * @param y y coord
	 * @param width width of the rect
	 * @param height height of the rect
	 * @author Michael
	 */
	public Wall(int x, int y, double width, double height) {
		super(1, x, y, 0, 0);
		this.width = width;
		this.height = height;
		setFill(Color.black);
		setInvincible(true);
	}
	
	/**
	 *  @param surface The surface PApplet draws on.
	 *  @author Michael
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.rect((float)(getX()-width/2),(float)(getY()-height/2),(float)width, (float)height);
	}
	
	/**
	 * indicates action of the actor in 1 frame
	 * @author Nont & Michael
	 */
	public void act(ArrayList<Actor> actors, PApplet surface, int time) {
		super.act(actors, surface, time);
		
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a))) {
				if(!(actors.get(a) instanceof Projectile) && !(actors.get(a) == this)) {
					actors.get(a).bounce();
				}
				continue;
			}
			else {
//				setBounce(false);
			}
		
		}
	}
	
	
	public boolean intersects(Actor other) {
		
		return false;
	}
}
