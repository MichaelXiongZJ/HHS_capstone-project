package Projectile;

import java.awt.Color;
import java.util.ArrayList;

import Enemy.Enemy;
import actor.Actor;
import actor.Player;
import processing.core.PApplet;

public class Bullet extends Projectile{

	private static int hp = 1;
	private static double MAXSPEED;
//	private boolean ignorePlayer;
//	private double speed;
	
	/**
	 * Bullet class default constructor
	 * @author Michael
	 * 
	 */
	public Bullet() {
		super();
	}
	
	/**
	 * Bullet class constructor for enemies
	 * @author Michael
	 * @param x x coord
	 * @param y y coord
	 * @param vx x speed
	 * @param vy y speed
	 * 
	 */
	public Bullet(double x, double y, double vx, double vy) {
		super(hp, x, y, vx, vy);
		super.setSpeed(2);
		setFill(new Color(153, 70, 57));
		setRadius(25);
	}
	
	/**
	 * Bullet class constructor for player
	 * @author Michael
	 * @param x x coord
	 * @param y y coord
	 * @param vx x speed
	 * @param vy y speed
	 * 
	 */
	public Bullet(double x, double y, double vx, double vy, boolean ignorePlayer) {
		super(hp, x, y, vx, vy, ignorePlayer);
//		this.ignorePlayer = ignorePlayer;
		super.setSpeed(15);
		setRadius(5);
	}
	
	/**
	 * set velocity towards target coordinate
	 * @author Nont
	 * @param x2 x coord of target
	 * @param y2 y coord of target
	 */
//	public void moveTowards(double x2, double y2) {
//		double angle = Math.atan((y2-getY())/(x2-getX()));
//		if (x2-getX() < 0) {
//				setvx(-speed*Math.cos(angle));
//				setvy(-speed*Math.sin(angle));
//
//		}else {
//				setvx(speed*Math.cos(angle));
//				setvy(speed*Math.sin(angle));
//		}
//	}
	
	/**
	 * make bullet move
	 * check if it hits other actors
	 * @author Michael
	 * 
	 */
//	public void act(ArrayList<Actor> other, PApplet surface, int time) {
//		super.act(other, surface, time);
//		
//		for(int a = 0; a < other.size(); a++) {
//			if(intersects(other.get(a))) {
//				if(!(other.get(a) instanceof Projectile)) {
//					if(ignorePlayer && other.get(a) instanceof Player) {
//						
//					}else if(!ignorePlayer && other.get(a) instanceof Enemy){
//						
//					}
//					else {
//						setHp(getHp()-1);
//						other.get(a).setHp(other.get(a).getHp()-1);
//					}
//				}
//				continue;
//			}
//		
//		}
//	}
	
	/**
	 * Draw the bullets
	 * @author Nont
	 */
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
	}
	

}