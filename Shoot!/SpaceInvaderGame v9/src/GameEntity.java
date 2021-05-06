import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class GameEntity {
	private CoordinateSystem coords;
	private int xCoord;
	private int yCoord;
	
	public GameEntity (int x, int y, Image image) {
		coords = new CoordinateSystem(x, y, image);
	}
	
	public void move (int x, int y) {
		xCoord += x;
		yCoord += y;
		coords.shift(x, y);
	}
	
	public int getX() {
		return xCoord;
	}
	
	public int getY() {
		return yCoord;
	}
	
	public CoordinateSystem getCoords() {
		return coords;
	}
}