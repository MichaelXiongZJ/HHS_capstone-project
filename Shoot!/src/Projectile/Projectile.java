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
	
	
	
}
