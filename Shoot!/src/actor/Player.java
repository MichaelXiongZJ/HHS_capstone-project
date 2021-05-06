package actor;

import java.awt.Color;

import processing.core.PApplet;

public class Player extends Actor{
	
	private static int hp = 50;
	
	
	public Player() {
		super();
	}
	
	public Player(double x, double y) {
		super(hp, x, y, 0, 0);
		setFill(Color.GREEN);
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.circle((float)getX(),(float)getY(),(float)(30));

	}
	
}
