package Enemy;

import actor.Actor;
import processing.core.PApplet;

import java.awt.Color;
import java.util.ArrayList;

import Projectile.Projectile;

public abstract class Enemy extends Actor{

	private int MAX_HP;
	
	public Enemy() {
		super();
	}
	
	public Enemy(int hp, double x, double y) {
		super(hp, x, y, 0, 0);
		MAX_HP = hp;
	//	setFill(Color.BLACK);
	}
	
	/**A drawing surface that will set a random color of the shape.
	 * @param surface The surface PApplet draws on.
	 * @author Michael
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		updateColor();//?????
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
	}
	
	/**
	 * indicates action of the actor in 1 frame
	 * @author Michael
	 */
	public void act(ArrayList<Actor> actors, PApplet surface, int time) {
		super.act(actors, surface, time);
		moveAround(time);
		
		for(int a = 0; a < actors.size(); a++) {
			if(intersects(actors.get(a))) {
				if(!(actors.get(a) instanceof Projectile) && !(actors.get(a) == this)) {
//					bounce();
//					setBounce(true);
					actors.get(a).bounce();
				}
				continue;
			}
			else {
//				setBounce(false);
			}
		
		}
	}
	
	public abstract void moveAround(int time);
	
	
	public void updateColor() {
		setFill(new Color(255*((getHp()-MAX_HP)/MAX_HP)));
	}
}
