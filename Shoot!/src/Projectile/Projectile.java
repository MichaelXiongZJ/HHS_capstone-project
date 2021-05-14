package Projectile;

import java.awt.Color;

import actor.Actor;
import actor.Player;

public abstract class Projectile extends Actor{

	public Projectile() {
		super();
	}
	
	/**
	 * 
	 * @param hp starting hp
	 * @param x x coord
	 * @param y y coord
	 * @param vx starting x velocity
	 * @param vy starting y velocity
	 * @author Michael
	 */
	public Projectile(int hp, double x, double y, double vx, double vy) {
		super(hp, x, y , vx, vy);
		setFill(Color.YELLOW);
	}
	
	/**
	 * set x coord
	 * @author Nont
	 */
	public void setX(double x) {
		super.setX(x);
	}
	
	/**
	 * set y coord
	 * @author Nont
	 */
	public void setY(double y) {
		super.setY(y);
	}
	
	/**
	 * get x coord
	 * @author Nont
	 */
	public double getX() {
		return super.getX();
	}
	
	/**
	 * get y coord
	 * @author Nont
	 */
	public double getY() {
		return super.getY();
	}
	

	/**
	 * Bounce bullet, and -1 hp at the same time.
	 * @author Michael
	 */
	public boolean bounce() {
		setvx(-getvx());
		setvy(-getvy());
		setHp(getHp()-1);
		return true;
	}
}
