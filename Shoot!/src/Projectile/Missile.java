package Projectile;

import java.awt.Color;

public class Missile extends Projectile {

	public Missile() {
		super();
	}

	public Missile(double x, double y, double vx, double vy) {
		super(hp, x, y, vx, vy);
		super.setSpeed(5);
		setFill(new Color(153, 70, 57));
		setRadius(20);
	}
	
	public Missile(double x, double y, double vx, double vy, boolean ignorePlayer) {
		super(hp, x, y, vx, vy, ignorePlayer);
		super.setSpeed(15);
		setRadius(5);
	}
	
}
