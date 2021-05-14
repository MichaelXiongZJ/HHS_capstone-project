package Surfaces;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import Enemy.Enemy;
import Enemy.LoopingShooter;
import Enemy.Turret;
import Projectile.Bullet;
import Reactable.Wall;
import actor.Actor;
import actor.Player;

public class DrawingSurface extends PApplet{
	

	
	private Player player;
	private ArrayList<Bullet> bullet;
	private ArrayList<Actor> actors;
	private ArrayList<Integer> keys;
	private ArrayList<Enemy> enemies;
	private PImage cursor;
	private int time;
	
	public DrawingSurface() {
	//	super();
		keys = new ArrayList<Integer>();
		actors = new ArrayList<Actor>();
		enemies = new ArrayList<Enemy>();
		bullet = new ArrayList<Bullet>();
		player = new Player(100,100);
		
		enemies.add(new Turret(300,300));
		enemies.add(new Turret(400,300));
		enemies.add(new Turret(300,400));
		enemies.add(new Turret(400,400));
		enemies.add(new LoopingShooter(500, 300));
		enemies.add(new LoopingShooter(500, 400));
		
//		actors.add(new Turret(300,300));
//		actors.add(new Turret(400,300));
//		actors.add(new Turret(300,400));
//		actors.add(new Turret(400,400));
//		actors.add(new LoopingShooter(500, 300));
//		actors.add(new LoopingShooter(500, 400));
		
		actors.add(player);
		actors.addAll(enemies);
		actors.add(new Wall(100, 50, 500, 5));
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
//		smooth(5);
	}
	
	/**
	 * draw the game with white background
	 */
	public void draw() {
		time = millis();
		background(255);
		
		for(int a = 0; a < actors.size(); a++) {
			actors.get(a).setWindowSizeActor(width, height);
			actors.get(a).act(actors, this, time);
			actors.get(a).draw(this);
		}
		player.act(actors, this, time);
		player.draw(this);
		
		controlPlayer();

//		for(int i = 0; i < bullet.size(); i++) {
//			bullet.get(i).act(actors, this, time);
//		}
//		pushMatrix();
		player.turnToward(mouseX, mouseY);
//		popMatrix();
		
		enemiesFire();
		
		checkDeath();
		
		fill(0);
		text("WASD to move",300, 50);
		text("Hit the black circles to kill them",300, 65);
		
		//debug
		displayInfo();
	}
	
	public void mousePressed() {
		bullet.add(new Bullet(player.getX(), player.getY(), player.getvx(), player.getvy()));
		actors.add(bullet.get(bullet.size()-1));
		bullet.get(bullet.size()-1).moveTowards(mouseX, mouseY);
//		for(int i = 0; i < bullet.size(); i++) {
//			bullet.get(i).draw(this);
//		}
//		actors.add(bullet);
		
//		bullet.draw(this);
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
	
	public void checkDeath() {
		if(player.getHp() == 0) {
			player = new Player(100,100);
		}
		for(int a = 0; a < actors.size(); a++) {
			if(actors.get(a).getHp() <= 0) {
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
	}
	
	public int getWindowWidth() {
		return width;
	}
	
	public int getWindowHeight() {
		return height;
	}
	
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
	
	public void displayInfo() {
		text("x: " + (double)Math.round(player.getX()* 100000d) / 100000d, 600, 60);
		text("y: " + (double)Math.round(player.getY()* 100000d) / 100000d, 600, 70);
		text("vx: " + (double)Math.round(player.getvx()* 100000d) / 100000d, 600, 80);
		text("vy: " + (double)Math.round(player.getvy()* 100000d) / 100000d, 600, 90);
		text("mouseX: " + mouseX, 600, 100);
		text("mouseY: " + mouseY, 600, 110);
		text("Player HP: " + player.getHp(), 600, 120);
		text("Timer: " + time, 600, 130);
		for(int a = 0; a < actors.size(); a++) {
			text("Num: " + a + " HP: " + actors.get(a).getHp(), 600, 140+a*10);
		}
	}
	
	public void enemiesFire() {
		while(time%1000 == 0) {
			for(int a = 0; a < enemies.size(); a++) {
				bullet.add(new Bullet(enemies.get(a).getX(), enemies.get(a).getY(), enemies.get(a).getvx(), enemies.get(a).getvy()));
				actors.add(bullet.get(bullet.size()-1));
				bullet.get(bullet.size()-1).moveTowards(enemies.get(a).getX()+1, enemies.get(a).getY()+1);
			}
		}
	}
}
