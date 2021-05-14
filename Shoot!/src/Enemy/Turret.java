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
	
	
//	public void draw(PApplet marker) {
//		super.draw(marker);
//		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
//
//	}


	@Override
	public void moveAround(int time) {
		
	}


}
