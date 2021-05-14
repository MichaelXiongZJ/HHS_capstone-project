package Enemy;

import processing.core.PApplet;

public class Turret extends Enemy{
	
	/**A stationary enemy that shoots
	 * @author Michael
	 */
	public Turret() {
		super();
	}
	
	/**
	 * @author Michael
	 * A stationary enemy that shoots
	 * @param x x coord
	 * @param y y coord
	 */
	public Turret(double x, double y) {
		super(5, x, y);
		setRadius(40);
	}
	
	/**
	 * override method
	 */
	public void moveAround(int time) {
		
	}
	
	
}
