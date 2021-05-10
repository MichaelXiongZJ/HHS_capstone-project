package Projectile;

import java.awt.Color;

import actor.Actor;

public abstract class Projectile extends Actor{

	public Projectile() {
		super();
	}
	
	public Projectile(int hp, double x, double y, double vx, double vy) {
		super(hp, x, y , vx, vy);
		setFill(Color.YELLOW);
	}
	
	public void setX(double x) {
		super.setX(x);
	}
	
	public void setY(double y) {
		super.setY(y);
	}
	
	public double getX() {
		return super.getX();
	}
	
	public double getY() {
		return super.getY();
	}
}
