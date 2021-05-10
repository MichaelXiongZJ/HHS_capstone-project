package Projectile;

import java.awt.Color;

import actor.Actor;
import actor.Player;

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
	
	public boolean intersects(Actor other) {
		double d = getDistance(other);
		if(d <= (getRadius()+other.getRadius())/2) {
			setHp(getHp()-1);
			if(!(other instanceof Player)) {
				other.setHp(other.getHp()-1);
			}
			return true;
		}else {
			return false;
		}
	}
}
