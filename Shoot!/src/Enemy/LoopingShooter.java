package Enemy;

import java.awt.Color;

import processing.core.PApplet;

public class LoopingShooter extends Enemy{
	
	
	public LoopingShooter() {
		super();
	}
	
	public LoopingShooter(double x, double y) {
		super(25, x, y);
		setFill(Color.GRAY);
		setRadius(40);
	}
	
//	public void draw(PApplet marker) {
//		super.draw(marker);
//		marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
//
//	}
	
	@Override
	public void moveAround(int time) {
		// TODO Auto-generated method stub
		setvx(Math.sin(Math.toRadians(time/10))*3);
		setvy(Math.sin(Math.toRadians(time/10)+Math.PI/2)*3);
	}

}
