/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 3
 * Notes: Chris:not working yet
 */
import java.awt.Image;

public class AlienBullet extends Bullet {
	public AlienBullet (Image pic, Image pic2, int damage, int speed, int x, int y) {
		super(pic, pic2, damage, speed, x, y, false, false);
	}
}