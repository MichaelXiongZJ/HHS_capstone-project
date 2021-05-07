package Projectile;

public class Bullet extends Projectile{

	private static int hp = 1;
	
	
	public Bullet() {
		super();
	}
	
	public Bullet(double x, double y, double vx, double vy) {
		super(hp, x, y, vx, vy);
	}
}
