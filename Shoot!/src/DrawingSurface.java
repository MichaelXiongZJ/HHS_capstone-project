
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import Enemy.Turret;
import Projectile.Bullet;
import actor.Player;

public class DrawingSurface extends PApplet{
	

	
	private Player player;
	private ArrayList<Bullet> bullets;
	private ArrayList<Integer> keys;
	private Turret turret;
	private PImage cursor;
	
	public DrawingSurface() {
//		super();
		keys = new ArrayList<Integer>();
		player = new Player(100,100);
		turret = new Turret(300,300);
	}

	/**
	 * Set up when first open
	 */
	public void setUp() {
		frameRate(170);
		cursor = super.loadImage("img/bullseye.png");
	//	cursor(cursor);
	}
	
	/**
	 * draw the game with white background
	 */
	public void draw() {
		background(255);
	//	pushMatrix();//?
		turret.draw(this);
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

		player.act();
		turret.act();
		
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
