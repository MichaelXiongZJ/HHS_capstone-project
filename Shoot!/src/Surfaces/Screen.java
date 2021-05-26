package Surfaces;


public abstract class Screen {

	public final int DRAWING_WIDTH, DRAWING_HEIGHT;
	
	/**
	 * Abstract class for various screen
	 * @author Nont (Copied from Mr. Shelby's code)
	 * @param width width of the window
	 * @param height height of the window
	 */
	public Screen(int width, int height) {
		this.DRAWING_WIDTH = width;
		this.DRAWING_HEIGHT = height;
	}
	
	/**
	 * set up the screen template
	 */
	public void setup() {
		
	}
	
	/**
	 * drawing template
	 */
	public void draw() {
		
	}
	
	/**
	 * What to do when button is pressed template
	 */
	public void buttonPressed() {
		
	}
	
	/**
	 * What to do when mouse is moved template
	 */
	public void mouseMoved() {
		
	}
	
	/**
	 * WHat to do when mouse dragged template
	 */
	public void mouseDragged() {
		
	}
	
	/**
	 * What to do when mouse is released template
	 */
	public void mouseReleased() {
		
	}
	
	
	
}
