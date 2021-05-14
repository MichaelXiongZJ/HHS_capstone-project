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
	
	public void draw(PApplet marker) {
		super.draw(marker);
		updateColor();//?????
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
	}
	
	
	public void act(ArrayList<Actor> actors, PApplet surface, int time) {
		super.act(actors, surface, time);
		moveAround(time);
		
		for(Actor a : actors) {
			if(intersects(a)) {
				if(!(a instanceof Projectile) && !(a == this)) {
					bounce();
					setBounce(true);
				}
				continue;
			}
			else {
				setBounce(false);
			}
		
		}
	}
	
	public abstract void moveAround(int time);
	
	
	public void updateColor() {
		setFill(new Color(255*((getHp()-MAX_HP)/MAX_HP)));
	}
}
