package Surfaces;



import java.awt.Point;
import java.awt.Rectangle;


public class HowToPlay extends Screen {

	private DrawingSurface surface;
	
	private Rectangle button;

	public HowToPlay(DrawingSurface surface) {
		super(800,600);
		this.surface = surface;

		button = new Rectangle(0,0,50,25);
	}


	public void draw() {
		surface.pushStyle();
		surface.fill(0);
		surface.background(255);
		surface.rect(button.x, button.y, button.width, button.height, 10, 10, 10, 10);
		surface.fill(255);
		String str = "back";
		float w = surface.textWidth(str);
		surface.text(str, (float)(button.x+button.width/1.5-w/1.5), (float)(button.y+button.height/1.5));
		
		surface.popStyle();
	}



	
	public void buttonPressed() {
		Point p = /*surface.actualCoordinatesToAssumed(*/new Point(surface.mouseX,surface.mouseY);
		if (button.contains(p))
			surface.switchScreen(ScreenSwitcher.MENU_SCREEN);
	}
	

}

