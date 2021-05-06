package actor;

import java.awt.Color;

import processing.core.PApplet;

public abstract class Actor {	
	
	//field
	private int hp;
	private double x;
	private double y;
	private double vx;
	private double vy;
	private int strokeWidth;
	private Color strokeColor;
	private Color fillColor;
	private boolean filled;
	
	/**
	 * Initialize actor with default values
	 */
	public Actor() {
		hp = -1;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		filled = false;
	}
	
	/**
	 * Initialize actor with parameter values
	 * 
	 * @param hp Hitpoint
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @param vx velocity in x axis
	 * @param vy velocity in y axis
	 */
	public Actor(int hp, double x, double y, double vx, double vy) {
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		strokeColor = Color.BLACK;
		filled = true;
		fillColor = Color.WHITE;
		strokeWidth = 1;
	}
	
	
	/**
	 * indicates action of the actor in 1 frame
	 */
	public void act() { //physical behavior
		
	}
	
	/**
	 * bounces off object when hit
	 */
	public void bounce() {
		vx = -vx;
		vy = -vy;
	}
	
	/**
	 * bounces off window when hit to prevent getting out of the grid
	 */
	public void bounceOffWindow() {
		vx = -vx;
		vy = -vy;
	}
	
	/**
	 * find whether or not the actor intersects with others
	 *
	 * @return true if intersects with other actors, reactables, or projectiles, false if not
	 */
	public boolean intersects() {
		return false;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double xNew) {
		x = xNew;
	}
	
	public void setY(double yNew) {
		y = yNew;
	}
	
	public double getvx() {
		return vx;
	}
	
	public double getvy() {
		return vy;
	}
	
	public void setvx(double vxNew) {
		vx = vxNew;
	}
	
	public void setvy(double vyNew) {
		vy = vyNew;
	}
	
	public void accelerate(double ax, double ay) {
		vx += ax;
		vy += ay;
	}
	
	/**A drawing surface that will set a random color of the shape.
	 * @param surface The surface PApplet draws on.
	 */
	public void draw(PApplet surface) {
		if (filled) {
			surface.fill(fillColor.getRed(), 
					fillColor.getGreen(),
					fillColor.getBlue());
		} else {
			surface.noFill();
		}
		surface.strokeWeight(strokeWidth);
		surface.stroke(strokeColor.getRed(), 
				strokeColor.getGreen(), 
				strokeColor.getBlue());
	}
	
}
