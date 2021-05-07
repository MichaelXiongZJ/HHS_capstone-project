package actor;

import java.awt.Color;

import processing.core.PApplet;

public abstract class Actor {	
	
	//field
	private int hp, strokeWidth;
	private double x, y, vx, vy, radius, dir;
	private Color strokeColor, fillColor;
	private boolean filled;
	private boolean isBouncing;
	
	/**
	 * Initialize actor with default values
	 */
	public Actor() {
		hp = -1;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
		radius = 0;
		dir = 0;
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
		radius = 0;
		dir = 0;
		strokeColor = Color.BLACK;
		filled = true;
		fillColor = Color.WHITE;
		strokeWidth = 1;
	}
	
	
	/**
	 * indicates action of the actor in 1 frame
	 */
	public void act(PApplet surface) { //physical behavior
		double x = getX();
		double y = getY();
		x += vx;
		y += vy;
		setX(x);
		setY(y);
		
		bounceOffWindow(surface);

	}

	/**
	 * bounces off object when hit
	 */
	public boolean bounce() {
		vx = -1.5*vx;
		vy = -1.5*vy;
		return true;
	}
	
	/**
	 * bounces off window when hit to prevent getting out of the grid
	 */
	public void bounceOffWindow(PApplet surface) {
		if(x <= getRadius()/2 || y <= getRadius()/2 || x >= surface.displayWidth - getRadius()/2 || y >= surface.displayWidth - getRadius()/2)
			bounce();
	}

	
	public boolean getBounce() {
		return isBouncing;
	}
	
	
	public void setBounce(boolean newBounce) {
		isBouncing = newBounce;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int newHp) {
		hp = newHp;
	}
	
	/**Return the x-coordinate of the shape.
	 * 
	 * @return x-coordinate.
	 */
	public double getX() {
		return x;
	}
	
	/**Return the y-coordinate of the shape.
	 * 
	 * @return y-coordinate.
	 */
	public double getY() {
		return y;
	}
	
	/**Set the x-coordinate as x.
	 * 
	 * @param x the new x-coordinate.
	 * @post Side affect: The x-coordinate changes to x.
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**Set the y-coordinate as y.
	 * 
	 * @param y the new y-coordinate.
	 * @post Side affect: The y-coordinate changes to y.
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**Set the stroke color.
	 * 
	 * @param c The new stroke color.
	 * @post Side affect: Stroke color changes to c.
	 */
	public void setStroke(Color c) {
		this.strokeColor = c;
	}
	
	/**Set the fill color.
	 * 
	 * @param c The new fill color.
	 * @post Side affect: fill color changes to c.
	 */
	public void setFill(Color c) {
		this.fillColor = c;
	}
	
	/**Set the shape filled.
	 * 
	 * @param f a boolean that tells if the shape is filled.
	 * @post the fill state will be updated.
	 */
	public void setFilled(boolean f) {
		this.filled = f;
	}
	
	/**Set the width of the stroke width.
	 * 
	 * @param w width of the stroke.
	 * @post width of stroke become w.
	 */
	public void setStrokeWidth(int w) {
		this.strokeWidth = w;
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
	
	public double getRadius() {
		return radius;
	}
	
	public void setRadius(double r) {
		radius = r;
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
	
	public double getDistance(Actor other) {
		return Math.sqrt(Math.pow(x-other.x, 2)+Math.pow(y-other.y, 2));
	}
	
	/**
	 * find whether or not the actor intersects with others
	 *
	 * @return true if intersects with other actors, false if not
	 */
	public boolean intersects(Actor other) {
		double d = getDistance(other);
		if(d <= (getRadius()+other.getRadius())/2) {
			other.setHp(other.getHp()-1);
			return true;
		}else {
			return false;
		}
	}

	
	/**
	 * Determines whether the point x,y is contained inside this actor.
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
	
	public void turn(double dir) {
		this.dir = dir;
	}
	
	public void turnToward(int x, int y) {
		dir = Math.atan(((double)this.y-y)/(this.x-x));
		if (this.x > x)
			dir += Math.PI;
	}
	
	public double getDir() {
		return dir;
	}
	

}
