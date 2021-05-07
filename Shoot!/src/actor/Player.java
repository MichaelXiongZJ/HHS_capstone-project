package actor;

import java.awt.Color;
import java.util.ArrayList;

import processing.core.PApplet;

public class Player extends Actor{
	
	private static int hp = 50;
	private double friction;
	public Player() {
		super();
	}
	
	public Player(double x, double y) {
		super(hp, x, y, 0, 0);
		setFill(Color.GREEN);
		friction = 0.75;
		setRadius(30);
	}
	
	public void draw(PApplet marker) {
        super.draw(marker);
        marker.circle((float)getX(),(float)getY(),(float)(getRadius()));
        marker.push();
        marker.translate((float)getX(), (float)getY());
        marker.rotate((float) getDir());
        marker.rect(0, 0, 30, 40, 5);
        marker.pop();

    }
	
	public void act(ArrayList<Actor> other, PApplet surface) {
		super.act(surface);
		setvx(getvx()*friction);
		setvy(getvy()*friction);
		
		for(Actor a : other) {
			if(intersects(a)) {
				bounce();
				setBounce(true);
				continue;
			}
			else {
				setBounce(false);
			}
		
		}
	}

	public void fire() {
		
	}
	
	
	
	
	
	
	
	
}
