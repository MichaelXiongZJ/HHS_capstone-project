
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import Enemy.Turret;
import Projectile.Bullet;
import actor.Actor;
import actor.Player;

public class DrawingSurface extends PApplet{
	

	
	private Player player;
	private ArrayList<Actor> actors;
	private ArrayList<Integer> keys;
	private PImage cursor;
	
	public DrawingSurface() {
//		super();
		keys = new ArrayList<Integer>();
		actors = new ArrayList<Actor>();
		player = new Player(100,100);
		actors.add(new Turret(300,300));
	}

	/**
	 * Set up when first open
	 */
	public void setUp() {
//		size(600, 400);
//		pixelDensity(displayDensity());
//		frameRate(170);
//		cursor = super.loadImage("img/bullseye.png");
//		cursor(cursor);
//		 smooth(5);
	}
	
	/**
	 * draw the game with white background
	 */
	public void draw() {
		background(255);
	//	pushMatrix();//?
		for(Actor a : actors) {
			a.draw(this);
		}
		player.draw(this);
	//	popMatrix();//?
		
		
		if (isPressed(KeyEvent.VK_W)){
			player.setvy(player.getvy()-1);
		//	player.setY(player.getY()-1);
		}
		if (isPressed(KeyEvent.VK_A)){
			player.setvx(player.getvx()-1);
		//	player.setX(player.getX()-1);
		}
		if (isPressed(KeyEvent.VK_S)){
			player.setvy(player.getvy()+1);
		//	player.setY(player.getY()+1);
		}
		if (isPressed(KeyEvent.VK_D)){
			player.setvx(player.getvx()+1);
		//	player.setX(player.getX()+1);
		}

		player.act(actors);
		
		fill(0);
		text("WASD to move",300, 50);
	}
	
	
	public void keyPressed() {
		keys.add(keyCode);
	}

	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
}
