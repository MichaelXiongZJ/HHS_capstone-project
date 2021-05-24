package Projectile;

import java.awt.Color;

import processing.core.PApplet;

public class Missile extends PlayerBullet {
	
	private static int hp = 1;
//	public Missile() {
//		super();
//	}
	
	public Missile(double x, double y, double vx, double vy) {
		super(x, y, vx, vy, true);
		super.setSpeed(1);
		setFill(new Color(153, 70, 57));
		setRadius(25);
	}

//	public Missile(double x, double y, double vx, double vy) {
//		super(hp, x, y, vx, vy);
//		super.setSpeed(5);
//		setFill(new Color(153, 70, 57));
//		setRadius(20);
//	}
//	
//	public Missile(double x, double y, double vx, double vy, boolean ignorePlayer) {
//		super(hp, x, y, vx, vy, ignorePlayer);
//		super.setSpeed(15);
//		setRadius(5);
//	}
	
	/**
	 * Draw the missile
	 * @author Nont
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		float xDist = (float) (1.81*getRadius()*Math.sin(25.0*Math.PI/180)/Math.tan(50.0*Math.PI/180));
		float yDist = (float) (1.81*getRadius()*Math.sin(25.0*Math.PI/180));
		marker.triangle((float) ((float)getX()+getRadius()), (float)getY(), (float)getX()-xDist, (float)getY()-yDist, (float)getX()-xDist, (float)getY()+yDist);
	}
}
