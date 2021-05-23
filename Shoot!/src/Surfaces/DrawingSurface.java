package Surfaces;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import Enemy.Enemy;
import Enemy.LoopingShooter;
import Enemy.Turret;
import Projectile.Bullet;
import Projectile.PlayerBullet;
import Projectile.Projectile;
import Reactable.Buff;
import Reactable.CancellableBullet;
import Reactable.Medkit;
import Reactable.Wall;
import actor.Actor;
import actor.Player;

public class DrawingSurface extends PApplet{
	

	
	private Player player;
	private ArrayList<Bullet> bullet;
	private ArrayList<PlayerBullet> playerBullets;
	private ArrayList<Actor> actors;
	private ArrayList<Integer> keys;
	private ArrayList<Enemy> enemies;
	private ArrayList<Buff> buffs;
	private PImage cursor;
	private int time;
	private int killCount = 0;
	private Screen activeScreen;
	private ArrayList<Screen> screens;
	public float ratioX, ratioY;
	private FirstScreen screen1;
	private SecondScreen screen2;
	
	/**
	 * A surface which the game is being drawn on
	 * @author Nont & Michael
	 */
	public DrawingSurface() {
		super();
		init();
//		keys = new ArrayList<Integer>();
//		actors = new ArrayList<Actor>();
//		enemies = new ArrayList<Enemy>();
//		bullet = new ArrayList<Bullet>();
//		playerBullets = new ArrayList<PlayerBullet>();
//		buffs = new ArrayList<Buff>();
//		player = new Player(100,100);
//		
//		enemies.add(new Turret(300,300));
//		enemies.add(new Turret(400,300));
//		enemies.add(new Turret(300,400));
//		enemies.add(new Turret(400,400));
//		enemies.add(new LoopingShooter(600, 300));
//		enemies.add(new LoopingShooter(600, 400));
//		buffs.add(new Medkit(200, 200));
//		buffs.add(new CancellableBullet(200, 300));
////		actors.add(new Turret(300,300));
////		actors.add(new Turret(400,300));
////		actors.add(new Turret(300,400));
////		actors.add(new Turret(400,400));
////		actors.add(new LoopingShooter(500, 300));
////		actors.add(new LoopingShooter(500, 400));
//		
//		actors.add(player);
//		actors.addAll(enemies);
////		actors.add(new Wall(400, 50, 700, 20));
////		actors.add(new Wall(400, 750, 700, 20));
////		actors.add(new Wall(50, 400, 20, 700));
////		actors.add(new Wall(750, 400, 20, 700));
//		
//		screens = new ArrayList<Screen>();
//		screen1 = new FirstScreen(this);
//		screen2 = new SecondScreen(this);
//		screens.add(screen1);
//		screens.add(screen2);
//		activeScreen = screens.get(0);
	}
	
	public void init() {
		keys = new ArrayList<Integer>();
		actors = new ArrayList<Actor>();
		enemies = new ArrayList<Enemy>();
		bullet = new ArrayList<Bullet>();
		playerBullets = new ArrayList<PlayerBullet>();
		buffs = new ArrayList<Buff>();
		player = new Player(100,100);
		
		enemies.add(new Turret(300,300));
		enemies.add(new Turret(400,300));
		enemies.add(new Turret(300,400));
		enemies.add(new Turret(400,400));
		enemies.add(new LoopingShooter(600, 300));
		enemies.add(new LoopingShooter(600, 400));
		buffs.add(new Medkit(200, 200));
		buffs.add(new CancellableBullet(200, 300));
//		actors.add(new Turret(300,300));
//		actors.add(new Turret(400,300));
//		actors.add(new Turret(300,400));
//		actors.add(new Turret(400,400));
//		actors.add(new LoopingShooter(500, 300));
//		actors.add(new LoopingShooter(500, 400));
		
		actors.add(player);
		actors.addAll(enemies);
//		actors.add(new Wall(400, 50, 700, 20));
//		actors.add(new Wall(400, 750, 700, 20));
//		actors.add(new Wall(50, 400, 20, 700));
//		actors.add(new Wall(750, 400, 20, 700));
		
		screens = new ArrayList<Screen>();
		screen1 = new FirstScreen(this);
		screen2 = new SecondScreen(this);
		screens.add(screen1);
		screens.add(screen2);
		activeScreen = screens.get(0);
	}

	/**
	 * Set up when first open
	 */
	public void setUp() {

	}
	
	/**
	 * draw the game with white background
	 * @author Nont & Michael
	 */
	public void draw() {
		ratioX = (float)width/activeScreen.DRAWING_WIDTH;
		ratioY = (float)height/activeScreen.DRAWING_HEIGHT;

		pushMatrix();
		
		scale(ratioX, ratioY);
		
		activeScreen.draw();
		
		popMatrix();
		activeScreen.draw();
	}
	public void draw2() {
//		cursor = super.loadImage("src/bullseye.png");
//		cursor(cursor);
		time = millis();
		background(129, 199, 212);
		
		for(int a = actors.size() - 1; a >= 0; a--) {
			actors.get(a).setWindowSizeActor(width, height);
	//		actors.get(a).act(actors, this, time);
			actors.get(a).draw(this);
		}
		
		for(int a = enemies.size()-1; a >=0; a--) {
			enemies.get(a).act(actors, this, time);
		}

		for(int a = bullet.size() - 1; a >= 0;  a--) {
			bullet.get(a).act(actors, this, time);
		}
		
		for(int a = playerBullets.size() -1 ; a >= 0; a--) {
			playerBullets.get(a).act(actors, this, time);
		}
		
		for(int a = 0; a < buffs.size(); a++) {
			buffs.get(a).act(actors);
			buffs.get(a).draw(this);
		}
		player.act(actors, this, time);
	//	player.draw(this);
		
		controlPlayer();

//		for(int i = 0; i < bullet.size(); i++) {
//			bullet.get(i).act(actors, this, time);
//		}
//		pushMatrix();
		player.turnToward(mouseX, mouseY);
//		popMatrix();
		if(enemies.size() <= 3) 
			spawnEnemies();
		
		enemiesFire();
		
		checkDeath();
		
		pushMatrix();
		fill(0);
		textSize(17);
		text("Use WASD keys to move around",250, 50);
		text("Point your mouse at the direction of the target, and left click to shoot",250, 65);
		text("Fire at the targets to destory them!",250, 80);
		textSize(14);
		text("Player HP: " + player.getHp(), 700, 100);
		//debug
		displayInfo();
		popMatrix();
	}
	
	/**
	 * Called every time mouse is pressed
	 * shoots the bullet from player
	 * @author Nont
	 */
	public void mousePressed() {

		if (activeScreen instanceof SecondScreen) {
			init();
			playerBullets.add(new PlayerBullet(player.getX(), player.getY(), player.getvx(), player.getvy(), true));
			actors.add(playerBullets.get(playerBullets.size() - 1));
			playerBullets.get(playerBullets.size() - 1).moveTowards(mouseX, mouseY);
		}
		activeScreen.mousePressed();
//		for(int i = 0; i < bullet.size(); i++) {
//			bullet.get(i).draw(this);
//		}
//		actors.add(bullet);
		
//		bullet.draw(this);
	}
	
	/**
	 *Called every time a key is pressed
	 *Set keys to the key pressed
	 *@author Michael (based on Example provided by Mr. Shelby)
	 */
	public void keyPressed() {
		keys.add(keyCode);
	}

	/**
	 *Called every time a key is released
	 *Remove keys when the key is released
	 *@author Michael (based on Example provided by Mr. Shelby)
	 */
	public void keyReleased() {
		while(keys.contains(keyCode))
			keys.remove(new Integer(keyCode));
	}

	/**
	 * @author Michael (based on Example provided by Mr. Shelby)
	 * @param code contains key code
	 * @return true if valid key is pressed.
	 *
	 */
	public boolean isPressed(Integer code) {
		return keys.contains(code);
	}
	
	/**
	 * Check if the HP of each actors is 0, if it is then remove from arraylist
	 */
	public void checkDeath() {
		if(player.getHp() <= 0) {
			player = new Player(100,100);
			actors.set(0, player);
		}
		for(int a = 1; a < actors.size(); a++) {
			if(actors.get(a).getHp() <= 0 && !actors.get(a).getInvincible()) {
				actors.remove(a);
				a--;
			}
		}
		for(int a = 0; a < bullet.size(); a++) {
			if(bullet.get(a).getHp() <= 0) {
				bullet.remove(a);
				a--;
			}
		}
		for(int a = 0; a < playerBullets.size(); a++) {
			if(playerBullets.get(a).getHp() <= 0) {
				playerBullets.remove(a);
				a--;
			}
		}
		for(int a = 0; a < enemies.size(); a++) {
			if(enemies.get(a).getHp() <= 0) {
				enemies.remove(a);
				killCount++;
				a--;
			}
		}
		for(int a = 0; a < buffs.size(); a++) {
			if(buffs.get(a).getHp() <= 0) {
				buffs.remove(a);
				a--;
			}
		}
	}
	
	/**
	 * @author Nont
	 * @return width of window
	 */
	public int getWindowWidth() {
		return width;
	}
	
	/**
	 * @author Nont
	 * @return height of window
	 */
	public int getWindowHeight() {
		return height;
	}
	
	/**
	 * @author Nont & Michael
	 * gets key WASD and move player accordingly
	 */
	public void controlPlayer() {
		if (!player.getBounce()) {
			if (isPressed(KeyEvent.VK_W)) {
				player.setvy(player.getvy() - 1);
			}
			if (isPressed(KeyEvent.VK_A)) {
				player.setvx(player.getvx() - 1);
			}
			if (isPressed(KeyEvent.VK_S)) {
				player.setvy(player.getvy() + 1);
			}
			if (isPressed(KeyEvent.VK_D)) {
				player.setvx(player.getvx() + 1);
			}
//			if (mousePressed) {
//				bullet.add(new Bullet(player.getX(), player.getY(), player.getvx(), player.getvy()));
//				actors.add(bullet.get(bullet.size()-1));
//				bullet.get(bullet.size()-1).moveTowards(mouseX, mouseY);
//				for(int i = 0; i < bullet.size(); i++) {
//					bullet.get(i).draw(this);
//				}
//			}
		}
	}
	
	/**
	 * @author Nont & Michael
	 * Shows debugging info
	 */
	public void displayInfo() {
		text("x: " + (double)Math.round(player.getX()* 100000d) / 100000d, 700, 60);
		text("y: " + (double)Math.round(player.getY()* 100000d) / 100000d, 700, 75);
		text("vx: " + (double)Math.round(player.getvx()* 100000d) / 100000d, 700, 90);
		text("vy: " + (double)Math.round(player.getvy()* 100000d) / 100000d, 700, 105);
		text("mouseX: " + mouseX, 700, 120);
		text("mouseY: " + mouseY, 700, 135);
		text("Player HP: " + player.getHp(), 700, 150);
		text("FrameRate: " + frameCount, 700, 165);
		text("killCount: " + killCount, 700, 180);
		int b = 1;
		for(int a = 0; a < actors.size(); a++) {
			if(!(actors.get(a) instanceof Projectile)) {
				text("Num: " + a + " HP: " + actors.get(a).getHp(), 700, 195+ b*15);
				b++;
			}

		}
	}
	
	/**
	 * @author Nont
	 * Enemies fire bullet automatically based on framerate.
	 */
	public void enemiesFire() {
		if(frameCount%20 == 0) {
			for(int a = 0; a < enemies.size(); a++) {
				bullet.add(new Bullet(enemies.get(a).getX(), enemies.get(a).getY(), enemies.get(a).getvx(), enemies.get(a).getvy()));
				actors.add(bullet.get(bullet.size()-1));
//				bullet.get(bullet.size()-1).moveTowards(enemies.get(a).getX()+1, enemies.get(a).getY()+1);
				bullet.get(bullet.size()-1).moveTowards(enemies.get(a).getX()+Math.cos(1*frameCount/180.0*Math.PI), enemies.get(a).getY()-Math.sin(1*frameCount/180.0*Math.PI));
			}
		}
	}
	
	/**
	 * @author Nont
	 * Spawn enemies based on kill count.
	 */
	public void spawnEnemies() {
		for (int j = 0; j < 1; j++) {
			for (; enemies.size()-3 < killCount/3;) {
				double turretX, turretY;
				boolean isEnoughSpace = true;
				turretX = Math.random() * width;
				turretY = Math.random() * height;
				Turret test = new Turret(turretX, turretY);
				for (Actor a : actors) {
					if (test.intersects(a)) {
						isEnoughSpace = false;
					}
				}
				test = null;
				if (isEnoughSpace) {
					enemies.add(new Turret(turretX, turretY));
					actors.add(enemies.get(enemies.size() - 1));
				} else
					j--;
			}
		}
	}
	
	public Point assumedCoordinatesToActual(Point assumed) {
		return new Point((int)(assumed.getX()*ratioX), (int)(assumed.getY()*ratioY));
	}

	public Point actualCoordinatesToAssumed(Point actual) {
		return new Point((int)(actual.getX()/ratioX) , (int)(actual.getY()/ratioY));
	}
	
	public void switchScreen(int i) {
		activeScreen = screens.get(i);
	}

}
