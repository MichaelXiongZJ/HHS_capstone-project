package Enemy;

import processing.core.PApplet;

public class Turret extends Enemy{
	

	public Turret() {
		super();
	}
	
	
	public Turret(double x, double y) {
		super(20, x, y);
	}
	
	
	
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(30));

	}
	
	
	
	
}
