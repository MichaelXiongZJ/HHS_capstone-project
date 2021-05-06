/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 5
 * Notes: Go to line 52 to change Alien HP, alien speed, etc.
 */
import java.awt.Graphics;
import java.awt.Image;

public class Alien extends GameEntity {
	private Hitbox hitbox;
	private int turretHP;
	private Image image;
	private boolean dead = false;
	private AlienBullet[] bullets = new AlienBullet[4];
	private AlienBullet bulletType;
	private boolean doNotDraw = false;
	private int y;
	
	//Editable fields:
	private final int HITBOX_RADIUS = 50;
	
	
	public Alien (int x, int y, int hp, Image alien, AlienBullet bullet) {
		super(x, y, alien.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
		bulletType = bullet;
		image = alien.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
		hitbox = new Hitbox(x-5, y-5, HITBOX_RADIUS);
		turretHP = hp;
		this.y = y;
		
	}
	
	public Alien (Alien original, int x, int y) {
		super(x, y, original.getImage());
		bulletType = original.getBullet();
		image = original.getImage();
		hitbox = new Hitbox(x -5, y-5, HITBOX_RADIUS);
		turretHP = original.getHitpoints();
		this.y = y;
	}
	
	public Alien (Alien alien) {
		super(0, 1, alien.getImage());
		image = alien.getImage();
		turretHP = alien.getHitpoints();
		hitbox = alien.hitbox;
		bulletType = alien.bulletType;
		dead = true;
		this.y = alien.getY();
		
	}
	
	public void move (int x, int y) {
		if (!dead) {
			super.move(x, y);
			hitbox.move(x, y);
			this.y = y;
		}
	}
	
	public boolean isHit (Bullet bullet, int x, int y) { //get hit by a Bullet type
		if (!dead) {
			boolean bool = hitbox.isHit(x, y);
			if (bool) {
				turretHP -= bullet.DAMAGE;
				bullet.exploded = true;
				bullet.move(0, getY() - bullet.getY());
				if (turretHP <= 0) {
					dead = true;
				}
			}
			return bool;
		} else return false;
	}
	
	public void draw(Graphics g) {
		if(!dead) getCoords().drawImage(g, image);
	}
	
	public Image getImage () {
		return image;
	}
	
	public boolean isDead () {
		return dead;
	}
	public void die() {
		dead = true;
		doNotDraw = true;
	}
	public boolean canDraw() {
		return !doNotDraw;
	}
	
	public void shoot () {
		
	}
	
	public int getY() {
		return this.y;
	}
	public void changeHP(int dHP) {
		turretHP += dHP;
	}
	
	public void setHP(int newHP) {
		turretHP = newHP;
	}
	
	public int getHitpoints () {
		return turretHP;
	}
	
	public AlienBullet getBullet() {
		return bulletType;
	}
	public Hitbox getHitbox() {
		return hitbox;
	}
	
	
	
}