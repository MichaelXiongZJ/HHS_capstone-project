package Projectile;

import java.util.ArrayList;

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
		double angle = Math.atan((y2-getY())/(x2-getX()));
		if (x2-getX() < 0) {
			setvx(-20.0*Math.cos(angle));
			setvy(-20.0*Math.sin(angle));
		}else {
			setvx(20.0*Math.cos(angle));
			setvy(20.0*Math.sin(angle));
		}
	}
	
	public void act(ArrayList<Actor> other, PApplet surface, int time) {
		super.act(other, surface, time);
		
		for(int a = 0; a < other.size(); a++) {
			if(intersects(other.get(a))) {
				if(!(other.get(a) instanceof Projectile)) {
					setHp(getHp()-1);
					other.get(a).setHp(other.get(a).getHp()-1);
				}
				continue;
			}
		
		}
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
	}
	
}