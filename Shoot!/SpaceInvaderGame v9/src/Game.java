
/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 20
 * Notes: Chris: Go to line 230 to change only the turret's bullet damage, 
 * 			-other values, go to AlienArmy
 */

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener, KeyListener {
	private static int windowWidth = 620, windowHeight = 850;
	public static AlienArmy aliens;
	private Timer ticker;
	private static boolean timerOn = true;
	private static boolean leftKeyPressed, rightKeyPressed, spaceKeyPressed = false;
	private Image bulletPic = (new ImageIcon("bullet2.png")).getImage();
	private Image explosionPic = (new ImageIcon("explosion.png")).getImage();
	private Image turretPic = (new ImageIcon("turret2.png")).getImage();
	private Image gameOverPic = (new ImageIcon("gameOver.jpg")).getImage();
	
	
	private int turretX = 500, turretY = 700;
	private int[] bulletX = new int[NUMBER_OF_BULLETS+15], bulletY = new int[NUMBER_OF_BULLETS+15];
	private Turret turret = new Turret(turretX, turretY, turretPic);
	private TurretBullet[] turretBullet;
	private final int turretStep = 20, bulletStep = 50;
	private boolean shooting = false;
	private long counter = 0;
	private String toolbarText = "";
	public static String debugMsg = "";
	
	
	//Editable fields for debugging
	public static final int TICK_PER_SEC = 20;



	private static final boolean SHOW_HITBOXES = true; 
	public static final boolean SHOW_HITBOX_CIRCLE = false;
	private static final boolean SHOW_GRIDLINES = false;
	
	
	public static boolean gameRunning = true;
	
	public static final int LOSING_Y_VALUE = 530;
	
	//Values that change as the waves progress:
	public static int NUMBER_OF_BULLETS = 1;
	public static int TRUE_TURRET_DAMAGE = 20;
	
	
	
	
	
	
	public static int BULLET_DAMAGE = 2*(TRUE_TURRET_DAMAGE -10);
	
	
	public Game() {
		turretBullet = new TurretBullet[NUMBER_OF_BULLETS +15];
		aliens = new AlienArmy();
		BULLET_DAMAGE = 2*(TRUE_TURRET_DAMAGE -10);
		for (int i = 0; i < NUMBER_OF_BULLETS +15; i++) {
			bulletX[i] = turretX;
			bulletY[i] = turretY;
			turretBullet[i] = new TurretBullet(bulletPic,
					explosionPic.getScaledInstance(20, 20, 1), BULLET_DAMAGE, bulletX[i], bulletY[i], false, false);
		}
		turretBullet[0].setMovability(Bullet.MOVABLE);
		 
		addKeyListener(this);
		setFocusable(true);
		ticker = new Timer(1000 / TICK_PER_SEC, this);
		ticker.start();
		JFrame w = new JFrame("Space Invaders");
		Container c = w.getContentPane();
		w.setBounds(50, 50, windowWidth, windowHeight);
		w.setResizable(false);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.add(this);
		setBackground(Color.BLACK);
		setVisible(true);
		w.setVisible(true);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		g.setColor(Color.GREEN.darker().darker());
		g.fillRect(0, 730, 620, 20);
		g.setColor(Color.WHITE);
		g.fillOval(400, 223, 5, 5);
		g.fillOval(234, 472, 3, 3);
		g.fillOval(100, 500, 4, 4);
		g.fillOval(200, 600, 2, 2);
		g.fillOval(49, 700, 5, 5);
		g.fillOval(500, 32, 5, 5);
		g.fillOval(50, 50, 4, 4);
		g.fillOval(70, 480, 4, 4);
		g.fillOval(300, 400, 4, 4);
		g.fillOval(550, 680, 6, 6);
		g.fillOval(540, 600, 4, 4);
		g.fillOval(300, 589, 5, 5);
		g.fillOval(300, 100, 3, 3);
		g.fillOval(120, 200, 4, 4);
		g.fillOval(80, 130, 4, 4);
//		g.drawLine(0, 20, 620, 20);
//		g.drawLine(0, 80, 620, 80);
//		g.drawLine(0, 140, 620, 140);
		
		if (SHOW_GRIDLINES) {
			aliens.drawGridLines(g);	
		}
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 745, windowWidth, 100);
		g.setColor(Color.BLACK);
		
		toolbarText = "Level: " + (aliens.getWave());
		String toolbarText2 = "Max # of Bullets: " + NUMBER_OF_BULLETS;
		String toolbarText3 = "Bullet Damage: "+ TRUE_TURRET_DAMAGE;
		String toolbarText4 = "Press left and right to move, spacebar to shoot. Defeat the Aliens before they reach the red line. ";
//		aliens.printAllPositions();
		
		g.drawString(toolbarText, 10, 760);
		g.drawString(toolbarText2, 10, 780);
		g.drawString(toolbarText3, 10, 800);
		g.setColor(Color.GRAY.darker());
		g.drawString(toolbarText4, 10, 820);

		
		int farthestDown = aliens.farthestDown3();
		
//		if (AlienArmy.numberOfMoveDowns >= 8) {
//			gameRunning = false;
//			System.out.println("loser");
//			GameOver endgame = new GameOver(0,0, gameOverPic);
//			endgame.draw(g);
//			ticker.stop();
//		}
//		
		debugMsg += "\nfarthestdown: " + farthestDown;

		if (farthestDown > LOSING_Y_VALUE) {
			gameRunning = false;
			System.out.println("loser");
			GameOver endgame = new GameOver(0, 0, gameOverPic);
			endgame.draw(g);
			ticker.stop();
		}

		if (gameRunning == true) {
			aliens.draw(g);
			for (int i = 0; i < NUMBER_OF_BULLETS; i++) {
				turretBullet[i].draw(g);
			}
			turret.draw(g);
			
			
			
			//Chris: show Hitboxes
			if (SHOW_HITBOXES) {
				g.setColor(Color.RED);
				aliens.drawHitboxes(g);
				
			}
			counter ++;
			if (counter % 10 == 0) {
				debugMsg += "\n\nTicks: " +counter;
//				System.out.println(debugMsg);
			}
		}
		else {
//			g.setColor(Color.BLACK);
//			g.fillRect(0, 0, windowWidth, 700);
//			g.setColor.(Color.RED.darker());
//			
		}
		
		//draw death line
		g.setColor(Color.RED);
		g.fillRect(0, 580, windowWidth, 4);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 50, 100);
		
//		ticker.stop(); //only use this if you do not want the game to redraw stuff
	}

	public void actionPerformed(ActionEvent e) {
		debugMsg = "";
 
		aliens.tick();
		
		
		int width = turretPic.getWidth(null);

		if (leftKeyPressed && turretX >= width) {
//			System.out.println("move left");
			turretX += -turretStep;
			for (int i = 0; i < NUMBER_OF_BULLETS; i++) {
				if (bulletY[i] == turretY) {
					bulletX[i] += -turretStep;
				}
			}
		} else if (rightKeyPressed && turretX <= windowWidth - width) {
//			System.out.println("move right");
			turretX += turretStep;
			for (int i = 0; i < NUMBER_OF_BULLETS; i++) {
				if (bulletY[i] == turretY) {
					bulletX[i] += turretStep;
				}
			}
		}

		for (int i = 0; i < NUMBER_OF_BULLETS-1; i++) {
			
			if (aliens.wave >= 5) {
				TRUE_TURRET_DAMAGE = 30; 
				BULLET_DAMAGE = (TRUE_TURRET_DAMAGE );
				turretBullet[i].DAMAGE = BULLET_DAMAGE;
				turretBullet[i+1].DAMAGE = BULLET_DAMAGE;
			}
			if(aliens.wave >= 10) {
				TRUE_TURRET_DAMAGE = 40; 
				BULLET_DAMAGE = (TRUE_TURRET_DAMAGE );
				turretBullet[i].DAMAGE = BULLET_DAMAGE;
				turretBullet[i+1].DAMAGE = BULLET_DAMAGE;

			}
			
			if(aliens.wave >= 15) {
				TRUE_TURRET_DAMAGE = 50; 
				BULLET_DAMAGE = (TRUE_TURRET_DAMAGE );
				turretBullet[i].DAMAGE = BULLET_DAMAGE;
				turretBullet[i+1].DAMAGE = BULLET_DAMAGE;

			}
			if(aliens.wave >= 20) {
				TRUE_TURRET_DAMAGE = 60; 
				BULLET_DAMAGE = (TRUE_TURRET_DAMAGE );
				turretBullet[i].DAMAGE = BULLET_DAMAGE;
				turretBullet[i+1].DAMAGE = BULLET_DAMAGE;

			}
			
			
			
			if (turretBullet[i].getMoveStatus() == Bullet.NOT_MOVING)
				turretBullet[i + 1].setMovability(Bullet.NOT_MOVABLE);
			else
				turretBullet[i + 1].setMovability(Bullet.MOVABLE);
		}

		if (spaceKeyPressed)
			shooting = true;

		// Chris can you add hitting alien method right here?
		
		for (int i = 0; i < NUMBER_OF_BULLETS; i++) BulletLoop:{
			
			boolean result = false;
			if (shooting /*|| turretBullet[i].getMoveStatus() == Bullet.MOVING*/) {
				if (turretBullet[i].getMovability() == Bullet.MOVABLE
						&& turretBullet[i].getMoveStatus() == Bullet.NOT_MOVING && bulletY[i] >= 0) {
//					bulletY[i] += -bulletStep;
					turretBullet[i].setMoveStatus(Bullet.MOVING);
				} else if (bulletY[i] <= 0/*or hit alien*/) {
					shooting = false;
//					bulletX[i] = turretX;
//					bulletY[i] = turretY;
					turretBullet[i].setMoveStatus(Bullet.NOT_MOVING);
					
					
				}

//				if (i < NUMBER_OF_BULLETS - 1 && turretBullet[i].getMoveStatus() == Bullet.MOVING) {
//					turretBullet[i + 1].setMovability(Bullet.MOVABLE);
//				}
				
//				if (turretBullet[i].getMoveStatus() == Bullet.MOVING) {
//					turretBullet[i].setMovability(Bullet.MOVABLE);
//				}
			}
//			result = turretBullet[i].isNearAlien2(aliens);
			result = turretBullet[i].isNearAlien3(aliens);

			if (result) {
//				debugMsg += "\t|Result true: " +result;
				turretBullet[i].setMoveStatus(false);
				shooting = false;
//				break BulletLoop;
			}
			else {
//				debugMsg += "false: " +result;
			}
			if (turretBullet[i].getMoveStatus() == Bullet.MOVING) {
				bulletY[i] += -bulletStep;
			}
			else if (turretBullet[i].getMoveStatus() == Bullet.NOT_MOVING) {
				bulletX[i] = turretX;
				bulletY[i] = turretY;
			}

			// Nont, change the turretBullet to whatever your bullet is that you're using
			// Abbas's original bullet image doesn't work
			
			
			//Chris: TurretBullet hitting Alien method
			
			
			
			
			
			turretBullet[i] = new TurretBullet(bulletPic, explosionPic.getScaledInstance(20, 20, 1), BULLET_DAMAGE/2, bulletX[i],
					bulletY[i], turretBullet[i].getMoveStatus(), turretBullet[i].getMovability());
			
//			turretBullet[i].tick();
			
//			System.out.println(i + " Move status " + turretBullet[i].getMoveStatus() + "\n  Movability "
//					+ turretBullet[i].getMovability());
			
		
		
			
		}//end of for loop for all turretBullets

		turret = new Turret(turretX, turretY, turretPic, 3);

		invalidate();
		repaint();
		validate();
	}

	public void keyTyped(KeyEvent e) {
		//leave blank
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			leftKeyPressed = true;
//			System.out.println("left Pressed");
		}
		if (key == KeyEvent.VK_RIGHT) {
			rightKeyPressed = true;
//			System.out.println("right Pressed");
		}
		if (key == KeyEvent.VK_SPACE) {
			spaceKeyPressed = true;
//			System.out.println("space Pressed");
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftKeyPressed = false;
//			System.out.println("left release");
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightKeyPressed = false;
//			System.out.println("right release");
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			spaceKeyPressed = false;
//			System.out.println("space release");
		}
	}
	
	
	

	public static boolean timerOn() {
		return timerOn;
	}

	public static int getWindowWidth() {
		return windowWidth;
	}
	public static int getWindowHeight() {
		return windowHeight;
	}
	public static void main(String args[]) {
		new Game();
	}
	
	
}