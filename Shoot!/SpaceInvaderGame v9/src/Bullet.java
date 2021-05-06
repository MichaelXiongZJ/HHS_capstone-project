/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 3
 * Notes: 
 */
import java.awt.*;

import javax.swing.ImageIcon;

public abstract class Bullet extends GameEntity {
	private Image image;
	private Image explosion;
	public int DAMAGE;
	private CoordinateSystem coords;
	public int distancePerSec;
	public boolean exploded = false;
	public boolean getRid = false;
	public int x, y;
	public static final boolean MOVING = true, NOT_MOVING = false, MOVABLE = true, NOT_MOVABLE = false;
	private boolean moveStatus = NOT_MOVING, movability = NOT_MOVABLE;
	
	
	public Bullet(Image pic, Image pic2, int damage, int speed, int x, int y, boolean moveStatus, boolean movability) {
		super(x, y, pic);
		DAMAGE    = damage;
		image     = pic;
		explosion = pic2;
		coords = new CoordinateSystem(x, y, image);
		distancePerSec = speed;
		this.x = x;
		this.y = y;
		this.setMovability(movability);
		this.setMoveStatus(moveStatus);
	}
	
	public Bullet (Bullet bullet, int xCoord, int yCoord) {
		super(xCoord, yCoord, bullet.image);
		DAMAGE       = bullet.DAMAGE;
		image       = bullet.image;
		explosion   = bullet.explosion;
		x = xCoord;
		y = yCoord;
	}
	
	public void draw (Graphics g) {
		if (!exploded)
			coords.drawImage(g, image);
		else if (!getRid) {
			coords.drawImage(g, explosion);
			getRid = true;
		}
	}
	
	
	public void setMoveStatus(boolean moveStatus) {
		this.moveStatus = moveStatus;
	}
	
	public boolean getMoveStatus() {
		return this.moveStatus;
	}
	
	public void setMovability(boolean movability) {
		this.movability = movability;
	}

	public boolean getMovability() {
		return this.movability;
	}
}