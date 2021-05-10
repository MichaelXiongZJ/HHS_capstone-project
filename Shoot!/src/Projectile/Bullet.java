package Projectile;

import actor.Actor;
import processing.core.PApplet;

public class Bullet extends Projectile{

	private static int hp = 1;
	private static double MAXSPEED;
	
	public Bullet() {
		super();
	}
	
	public Bullet(double x, double y, double vx, double vy) {
		super(hp, x, y, vx, vy);
		setRadius(5);
	}
	
	public void moveTowards(double x2, double y2) {
		double angle = Math.atan((y2-getX())/(x2-getY()));
		setvx(20*Math.cos(angle));
		setvy(20*Math.sin(angle));
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(getRadius())/4);
	}
	
}