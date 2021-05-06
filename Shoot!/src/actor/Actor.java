package actor;

public abstract class Actor {

	
	
	//field
	protected int hp;
	protected int x;
	protected int y;
	protected int vx;
	protected int vy;
	
	//cons
	public Actor() {
		hp = -1;
	}
	
	public Actor(int hp, int x, int y, int vx, int vy) {
		this.hp = hp;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	
	
	
	//method
	public void act() { //physical behavior
		
	}
	
	public void bounce() {
		
	}
	
	public void bounceOffWindow() {
		
	}
	
	public void intersects() {
		
	}
}
