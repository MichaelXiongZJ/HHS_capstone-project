package Reactable;

import java.awt.Color;

import actor.Actor;
import processing.core.PApplet;

public class Wall extends Actor{

	private double width;
	private double height;
	
	public Wall() {
		super();
	}
	
	public Wall(int x, int y, double width, double height) {
		super(1, x, y, 0, 0);
		this.width = width;
		this.height = height;
		setFill(Color.black);
	}
	
	public void draw(PApplet marker) {
		super.draw(marker);
		marker.rect((float)getX(),(float)getY(),(float)width, (float)height);
	}
	
}
