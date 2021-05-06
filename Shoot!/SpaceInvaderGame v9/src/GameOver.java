/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 1
 * Notes: 
 */

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GameOver extends GameEntity{
	private Image gameOverPic;
	public GameOver(int x, int y, Image gameOverPic) {
		super(x, y, gameOverPic.getScaledInstance(620, 800, java.awt.Image.SCALE_SMOOTH));
		this.gameOverPic = gameOverPic;
		// TODO Auto-generated constructor stub
	}
	public void draw(Graphics g) {
		getCoords().drawImage(g, gameOverPic);
	}
}
