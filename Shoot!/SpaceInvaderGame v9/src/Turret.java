/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 2
 * Notes: Go to line 52 to change Alien HP, alien speed, etc.
 */
import java.awt.Image;

import java.awt.Graphics;

public class Turret
{
	private static final int HITBOX_RADIUS = 50;
	private Image picture;
	private CoordinateSystem coordinates;
	private int x, y;
	private Hitbox hitbox;
	private int hitpoints;
	private boolean dead = false;
//	private AlienBullet[] bullets = new AlienBullet[4];
	private boolean doNotDraw = false;
  
	// Constructor
	public Turret(int x, int y, Image pic) {
		this.x = x;
		this.y = y;
		picture = pic;
		coordinates = new CoordinateSystem(x, y, pic);
		this.y = y;
	}
	
	public Turret(int x, int y, Image pic, int hp) {
		this.x = x;
		this.y = y;
		picture = pic;
		coordinates = new CoordinateSystem(x, y, pic);
		hitbox = new Hitbox(x, y, HITBOX_RADIUS);
		hitpoints = hp;
		this.y = y;
	}

	public void moveDownward(int distance) {
		coordinates.shift(0, distance);
		this.x += distance;
	}

	public void moveSideways(int distance) {
		coordinates.shift(distance, 0);
		this.y -= distance;
//		hitbox.moveTo(x, y);
	}

	public void draw(Graphics g) {
		coordinates.drawImage(g, picture);
	}

	public boolean isHit(Bullet bullet, int x, int y) { // get hit by a Bullet type
		if (!dead) {
			boolean bool = hitbox.isHit(x, y);
			if (bool) {
				hitpoints -= bullet.DAMAGE;
				bullet.exploded = true;
				bullet.move(0, getY() - bullet.getY());
				if (hitpoints <= 0) {
					dead = true;
				}
			}
			return bool;
		} else
			return false;
	}

	public void shoot() {

	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return picture;
	}

}

