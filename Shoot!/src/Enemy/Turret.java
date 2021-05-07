package Enemy;

import processing.core.PApplet;

public class Turret extends Enemy{
	
	
	public Turret() {
		super();
	}
	
	
	public Turret(double x, double y) {
		super(20, x, y);
		setRadius(40);
	}
	
	
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));

	}

	
	/**
	 * Determines whether the point x,y is contained inside this Circle.
	 * @param x The X-coordinate of the point.
	 * @param y The Y-coordinate of the point.
	 * @return True if point is inside, false if point is outside.
	 */
	public boolean isPointInside(double x, double y) {
        if(Math.sqrt(Math.pow(this.getX()-x, 2.0)+Math.pow(this.getY()-y, 2.0)) <= getRadius())
            return true;
        else
            return false;
    }

	
	
}
