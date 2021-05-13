package Enemy;

import actor.Actor;
import processing.core.PApplet;

import java.awt.Color;

public abstract class Enemy extends Actor{

	public Enemy() {
		super();
	}
	
	public Enemy(int hp, double x, double y) {
		super(hp, x, y, 0, 0);
		setFill(Color.BLACK);
	}
	
	
	public void act(PApplet surface, int time) {
		super.act(surface, time);
		moveAround(time);
		fire();
	}
	
	public abstract void moveAround(int time);
	
	public abstract void fire();
	
}
