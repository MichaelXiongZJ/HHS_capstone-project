/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/24/20
 * Rev: 3
 * Notes: 
 */

import java.awt.Image;

public class TurretBullet extends Bullet {
	private static final int SPEED = 50;
	private int x, y;
	public int DAMAGE;
	
	public TurretBullet (Image pic, Image pic2, int damage, int x, int y, boolean movability, boolean moveStatus) {
		super(pic, pic2, damage, SPEED, x, y, movability, moveStatus);
		this.x = x;
		this.y = y;
		this.DAMAGE = super.DAMAGE;
	}
	
//	public void tick () {
//		if (getY() - SPEED/Game.TICK_PER_SEC < 140 && getY() > 180) {
//			for (int i = 0; i < 10; i++)
//				if (Game.aliens.getAliens()[2][i].isHit(this, 160, getX())) {
//					if (Game.aliens.getAliens()[2][i].isDead()) {
//						Game.aliens.killAlien(2, i);
//						System.out.println("kill alien 1");
//					}
//					
//					break;
//				}
//		} else if (getY() > 120 && getY() - SPEED/Game.TICK_PER_SEC < 80 )
//			for (int j = 0; j < 10; j++)
//				if (Game.aliens.getAliens()[1][j].isHit(this, 100, getX())) {
//					if (Game.aliens.getAliens()[1][j].isDead()) {
//						Game.aliens.killAlien(1, j);
//						System.out.println("kill alien 2");
//					}
//					break;
//				}
//		else if (getY() >= 60 && getY() - SPEED/Game.TICK_PER_SEC < 20) 
//			for (int k = 0; k < 10; k++) 
//				if (Game.aliens.getAliens()[1][k].isHit(this, 40, getX())) {
//					if (Game.aliens.getAliens()[1][k].isDead()) {
//						Game.aliens.killAlien(1, k);
//						System.out.println("kill alien 3");
//					}
//					break;
//				}
//		else if (getY() - SPEED/Game.TICK_PER_SEC < 0) getRid = true;
//		else if (!exploded) move(0, (int)(-SPEED/Game.TICK_PER_SEC));
//	}
	
	
	public void move(int dx,int dy) {
		x+= dx;
		y += dy;
	}
	

	
	public boolean isNearAlien(AlienArmy alienArmy){//unused
		boolean result = false;
		for (int i = 0; i < 3; i++) alienLoop:{
			for (int j = 0; j < 10; j++) {
				exploded = true;
				result = alienArmy.damageAlien(this, x, y);
				if (result) {
//					Game.debugMsg += "|B:Y";
					break alienLoop;
				}
				else {
//					Game.debugMsg += "|N";
				}
//				System.out.println("test damage");
//				result = aliens[i][j].isHit(this, this.x, this.y);
			}
			
		}
		
		return result;
	}
	
	public boolean isNearAlien2(AlienArmy alienArmy){
		boolean result = false;
		for (int i = 0; i < 3; i++) alienLoop:{
			for (int j = 0; j < 10; j++) {
				exploded = true;
				result = alienArmy.damageAlien2(this, x, y);
				if (result) {
//					Game.debugMsg += "|2B:Y";
					result = true;
					return true;
				}
				else {
//					Game.debugMsg += "|2N";
				}
//				System.out.println("test damage");
//				result = aliens[i][j].isHit(this, this.x, this.y);
			}
			
		}
		
		return result;
	}
	public boolean isNearAlien3(AlienArmy alienArmy){
		boolean result = false;
		for (int i = 0; i < 3; i++) alienLoop:{
			for (int j = 0; j < 10; j++) {
				exploded = true;
				result = alienArmy.damageAlien2(this, x, y, this.DAMAGE);
//				System.out.println(this.DAMAGE);
				if (result) {
//					Game.debugMsg += "|2B:Y";
					result = true;
					return true;
				}
				else {
//					Game.debugMsg += "|2N";
				}
//				System.out.println("test damage");
//				result = aliens[i][j].isHit(this, this.x, this.y);
			}
			
		}
		
		return result;
	}
	
	
	public void die() {
		
	}
	
	
	
	
//	public void tick2(Alien[][] aliens) {
//		this.isNearAlien(aliens);
//	}

}