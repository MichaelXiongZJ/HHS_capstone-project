package actor;

public abstract class Actor {

	
	
	//field
	protected int hp;
	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	
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
	 * find wheter or not the actor intersects with others
	 *
	 * @return true if intersects with other actors, reactables, or projectiles, false if not
	 */
	public boolean intersects() {
		return false;
	}
}
