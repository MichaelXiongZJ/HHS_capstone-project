package Projectile;

import java.awt.Color;
import java.util.ArrayList;

import Enemy.Enemy;
import actor.Actor;
import actor.Player;
import processing.core.PApplet;

public abstract class Projectile extends Actor{

	private boolean ignorePlayer;
	private double speed;
	
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
	 * Projectile class constructor for player
	 * @author Michael
	 * @param x x coord
	 * @param y y coord
	 * @param vx x speed
	 * @param vy y speed
	 * 
	 */
	public Projectile(int hp, double x, double y, double vx, double vy, boolean ignorePlayer) {
		super(hp, x, y, vx, vy);
		this.ignorePlayer = ignorePlayer;
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
	
	/**
	 * make bullet move
	 * check if it hits other actors
	 * @author Michael
	 * 
	 */
	public void act(ArrayList<Actor> other, PApplet surface, int time) {
		super.act(other, surface, time);
		
		for(int a = 0; a < other.size(); a++) {
			if(intersects(other.get(a))) {
				if(!(other.get(a) instanceof Projectile)) {
					if(ignorePlayer && other.get(a) instanceof Player) {
						
					}else if(!ignorePlayer && other.get(a) instanceof Enemy){
						
					}
					else {
						setHp(getHp()-1);
						other.get(a).setHp(other.get(a).getHp()-1);
					}
				}
				continue;
			}
		
		}
	}
	
	
	public void bounceOffWindow(PApplet surface) {
		if(getX() < getRadius()/2 || getY() < getRadius()/2 || getX() > surface.displayWidth - getRadius()/2 ||  getY() > surface.displayHeight - getRadius()/2) {
			bounce();
		}
	}
	
	
	
	/**
	 * set velocity towards target coordinate
	 * @author Nont
	 * @param x2 x coord of target
	 * @param y2 y coord of target
	 */
	public void moveTowards(double x2, double y2) {
		double angle = Math.atan((y2-getY())/(x2-getX()));
		if (x2-getX() < 0) {
				setvx(-speed*Math.cos(angle));
				setvy(-speed*Math.sin(angle));

		}else {
				setvx(speed*Math.cos(angle));
				setvy(speed*Math.sin(angle));
		}
	}
	
	/**
	 * setter for speed
	 * @param speed speed of projectile
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	/**
	 * getter for speed
	 * @return speed of projectile
	 */
	public double getSpeed() {
		return speed;
	}
}
