package actor;

public abstract class Actor {	
	
	//field
	private int hp;
	private double x;
	private double y;
	private double vx;
	private double vy;
	
	/**
	 * Initialize actor with default values
	 */
	public Actor() {
		hp = -1;
		x = 0;
		y = 0;
		vx = 0;
		vy = 0;
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
	public Actor(int hp, int x, int y, int vx, int vy) {
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
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
}
