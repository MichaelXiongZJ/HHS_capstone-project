/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 22
 * Notes: Chris:Go to line 52 to change Alien HP, alien speed, etc.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class AlienArmy {
	private Alien[][] aliens; // Aliens
	private int noShootColumn; // a column where no alien shoots, so the game doesn't get impossible
	public int wave; // the wave #, as game progresses more aliens spawn and the bullets move faster
	private int alienNumber; // how many aliens are still alive, so it is easy to check if another wave should be spawned
	private Alien currentAlien; // the blueprint for the current alien, they have the same bullet, 
	private int score = 0;
	private char direction = 'r'; // 'l' or 'r' or 'n'
	private Image aPic = (new ImageIcon("Alien.png")).getImage();
	private Image bPic = (new ImageIcon("bullet.png")).getImage();
	private Image explosionPic = (new ImageIcon("explosion.png")).getImage();
	private int maxDispLeft;
	private int maxDispRight;
	private int disp;
	
	//Chris: debugging variables
	public static int numberOfMoveDowns = 0;
	private final int INITIAL_WAVE = 0;
	private final int moveDownAmount = 30;
	
	public static int numRows = 5, numColumns = 8;
	
	//Values that change as the waves progress:
	public static int waveSpeed = 40;
	public static int alienSpeed = 40;
	public static int initialHP = 20;

	
	
	
	public AlienArmy () {
		aliens = new Alien[numRows][numColumns];
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				aliens[i][j] = new Alien (new Alien (1, 2, initialHP, aPic, new AlienBullet(bPic, explosionPic, 1, 1, 1, 1)));
		wave = INITIAL_WAVE;
		noShootColumn = 1;
		alienNumber = 0;
	}
	
	public void changeValues() {
		if (wave== 2) {
		}
		if (wave == 3) {
			Game.NUMBER_OF_BULLETS = 2;
			initialHP = 40;
			waveSpeed = 40;
		}
		if (wave == 4) {
			initialHP = 60;
		}
		if (wave == 5) {
			Game.NUMBER_OF_BULLETS = 3;
			initialHP = 100;
		}
		if (wave == 6) {
			initialHP = 110;
		}
		if (wave == 7) {
			initialHP = 120;
			waveSpeed = 80;
			Game.NUMBER_OF_BULLETS = 4;
		}
		if (wave == 8) {
			initialHP = 130;
		}
		if (wave == 10) {
			initialHP = 150;
			waveSpeed = 100;

		}
		if (wave == 12) {
			initialHP = 175;
		}
		
		if (wave == 15) {
			initialHP = 200;
			waveSpeed = 150;
			Game.NUMBER_OF_BULLETS = 5;

		}
		if (wave == 17) {
			initialHP = 250;
		}
		if (wave == 20) {
			initialHP = 300;
			Game.NUMBER_OF_BULLETS = 5;
		}
		if (wave == 25) {
			Game.NUMBER_OF_BULLETS = 5;
			waveSpeed = 200;

		}
		if (wave > 20) {
			initialHP = 300 + ((wave-10)/10 ) * 50;
			waveSpeed = 200 + ((wave-10)/10 ) * 20;
		}
		
	}
	
	
	
	public void spawnNewArmy () {
		disp = 0;
		wave += 1;
		changeValues();
		maxDispLeft = 0;
		maxDispRight = 0;
		currentAlien = new Alien(1, 2, initialHP, aPic, new AlienBullet(bPic, explosionPic, 1, 1, 1, 1));
		System.out.println("Wave: "+ wave);
		numberOfMoveDowns = 0;
		alienSpeed = waveSpeed;
		double probForAlien = (1 / (1 + Math.exp(0.2 * (-wave + 10))));
		for (int i = 0; i<numRows; i++) {
			for (int j = 0; j<numColumns; j++) {
				if (wave > 20) {
					probForAlien = 0.60;
				}
				if (probForAlien >= Math.random()) {
					aliens[i][j] = new Alien(currentAlien, 15 + j*60, 20 + i*60);
					alienNumber++;
				} else {
					aliens[i][j] = new Alien(currentAlien); 
					aliens[i][j].die();
					aliens[i][j].setHP(-1);
					//Chris: currentAlien I think is a dummy alien that doesn't get drawn
				}
			}
		}
		
		boolean bool = true;
		
		for (int i = (numColumns - 1) ; i >= 0; i--) {
			for (int j = 0; j < numRows; j++) {
				if (!aliens[j][i].isDead()) {
					bool = false;
					break;
				}
					
			}
			if (!bool) {
				maxDispRight = (numColumns +1 - i) * 60;
				break;
			}
		}
		
		bool = true;
		
		for (int i = 0; i < numColumns; i++) {
			for (int j = 0; j < numRows; j++) {
				if (!aliens[j][i].isDead()) {
					bool = false;
					break;
				}
					
			}
			if (!bool) {
				maxDispLeft = i * -60;
				break;
			}
		}
		
		if (maxDispRight == 0 && maxDispLeft == 0) {
			direction = 'n';
		}
//		System.out.println("maxdisp left: "+maxDispLeft);
//		System.out.println("maxdisp right: " +maxDispRight);
		if (!aliens[0][0].isDead() || aliens[0][0].getHitpoints()>0) {
			killAlien(0,0);
//			alienNumber --;
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
	}
	
	public void moveArmy (int x) {
		disp += x;
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				aliens[i][j].move(x, 0);
	}
	
	public void moveArmy (int x, int y) {
		disp += x;
		for (int i = 0; i < numRows; i++)
			for (int j = 0; j < numColumns; j++)
				aliens[i][j].move(x, y);
	}
	
	public void tick () {
		Game.debugMsg += "alienNumber= " + alienNumber;
		
		
		
		if (alienNumber <= 0) {
            spawnNewArmy();
		}
        else {
            if (direction == 'n') {
                if (maxDispRight > 0) {
                	direction = 'r';
                }
                else if (maxDispLeft < 0) {
                	direction = 'l';
                }
            }
            else if (direction == 'r') {
                if (alienSpeed/Game.TICK_PER_SEC + disp > maxDispRight) {
//                    System.out.print("hi");
                    moveArmy(2*(maxDispRight - disp)-alienSpeed/Game.TICK_PER_SEC);
                    direction = 'l';
                    moveArmy(0, moveDownAmount);
                    numberOfMoveDowns++;
                }
                else {
                    moveArmy(alienSpeed/Game.TICK_PER_SEC);
                    if (disp >= maxDispRight) {
//                        System.out.println("hi");
                        direction = 'l';
                        if (numberOfMoveDowns == 0) {
                            moveArmy(0,2* moveDownAmount);
                        }
                        else {
                            moveArmy(0, moveDownAmount);

                        }
                        numberOfMoveDowns++;
                    }
                }
            } else if (direction == 'l') {
                if (disp - alienSpeed/Game.TICK_PER_SEC < maxDispLeft) {
                    moveArmy(alienSpeed/Game.TICK_PER_SEC-(disp - maxDispRight));
                    direction = 'r';
                    moveArmy(0, moveDownAmount);
                    numberOfMoveDowns++;
                }
                else {
                    moveArmy(-alienSpeed/Game.TICK_PER_SEC);
                    if (disp == maxDispLeft) {
                        direction = 'r';
                        moveArmy(0, moveDownAmount);
                        numberOfMoveDowns++;
                    }
                }
            }
        }
//		if (alienNumber <= 2) {
//			alienSpeed = waveSpeed + (4-alienNumber)*20;
//		}
		
		Game.debugMsg += "<From" +maxDispLeft + "to "+ maxDispRight+ ">";
		
		
    }//End of tick()
	
	public void draw(Graphics g) {
		for (int i = 0; i < aliens.length; i++)
			for (int j = 0; j < aliens[i].length; j++)
				aliens[i][j].draw(g);
	}
	
	
	public void killAlien (int row, int column) {
		alienNumber -= 1;
		aliens[row][column] = new Alien(currentAlien);
		aliens[row][column].die();
		boolean bool = true;
		if (row == 9 - maxDispRight/60) {
			for (int i = 9 - maxDispRight/60; i >= 0; i--) {
				for (int j = 0; j < numRows; j++) {
					if (!aliens[i][j].isDead()) {
						bool = false;
						break;
					}
					
				}
				if (!bool) {
					maxDispRight += (9 - maxDispRight/60 - i) * 60;
					break;
				}
			}
		}
		if (row == maxDispLeft/-60) {
			bool = true;
//			for (int i = maxDispLeft/60; i<numColumns; i++) {
			for (int i = 0; i<numColumns; i++) {
				for (int j = 0; j < numRows; j++) {
					if (!aliens[j][i].isDead()) {
						bool = false;
						break;
					}
					
				}
				if (!bool) {
					maxDispLeft = i * -60;
					break;
				}
			}
		}
	}
	
	public void alienShoot() {
		
	}
	
	
	
	
	public boolean damageAlien(Bullet bullet, int x, int y) {
		boolean result = false;
		for (int i = 0; i < numRows; i++) alienLoop:{
			for (int j = 0; j < numColumns; j++) {
				if (aliens[i][j].isHit(bullet, x, y)){
					this.killAlien(i, j);
					result = true;
					break alienLoop;
				}
				
				
			}
		}
		return result;
	}
	
	public boolean damageAlien2(Bullet bullet, int x, int y) {
		boolean result = false;
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) alienLoop:{
				if (aliens[i][j].isHit(bullet, x, y)){
					
					
					aliens[i][j].changeHP(-bullet.DAMAGE);
					
					if (aliens[i][j].getHitpoints() <= 0) {
						this.killAlien(i, j);

					}
					result = true;
//					Game.debugMsg += "<Y>";
//					System.out.println("\n Hit HERE");
					
					return result;
				}
				else {
//					Game.debugMsg += " N";
				}
				
				
			}
		}
		return result;
	}
	public boolean damageAlien2(Bullet bullet, int x, int y, int damage) {
		boolean result = false;
		
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) alienLoop:{
				if (aliens[i][j].isHit(bullet, x, y)){
					
					
					aliens[i][j].changeHP(-damage);
					
					if (aliens[i][j].getHitpoints() <= 0) {
						this.killAlien(i, j);

					}
					result = true;
//					Game.debugMsg += "<Y>";
//					System.out.println("\n Hit HERE");
					
					return result;
				}
				else {
//					Game.debugMsg += " N";
				}
				
				
			}
		}
		return result;
	}
	
	public void drawHitboxes(Graphics g) {
		
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[i].length; j++) {
				int health = aliens[i][j].getHitpoints();
				if (i != 0 || j != 0) {
//					aliens[i][j].getHitbox().draw(g, "HP: " + health, "("+i +"," +j+")" );
					aliens[i][j].getHitbox().draw(g, "("+i +"," +j+")", initialHP, health);
					
				}
			
			}
		}
	}
	
//	
	public int farthestDown() {
		int y = 0;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				if(aliens[i][j].getHitpoints() > 0 && !aliens[i][j].isDead() && aliens[i][j].canDraw()) {
					if (aliens[i][j].getY() >= y) {
						y = aliens[i][j].getY();
//						Game.debugMsg += "y=" +y;
					}
				}
			}
		}
		return y;
	}
	
	public int farthestDown2() { //this would work but getY() doesnt work
		int y = 0;
		boolean bool = true;
		int i;
		int j;
		for (i = numRows -1; i >= 0; i--) {
			for (j = 0; j < numColumns; j++) {
				if (!aliens[i][j].isDead()) {
					bool = false;
					
					Game.debugMsg += "\n"+i + "," + j + " is alive!\n";
					break;
				}
				
			}
			if (!bool) {
				y = aliens[i][j].getY();
				break;
			}
		}
		return y;
	}
	
	public int farthestDown3() { //this would work but getY() doesnt work
		int y = 0;
		boolean bool = true;
		int i;
		int j;
		for (i = numRows -1; i >= 0; i--) {
			for (j = 0; j < numColumns; j++) {
				if (!aliens[i][j].isDead()) {
					bool = false;
					
					Game.debugMsg += "\n"+i + "," + j + " is alive!\n";
					break;
				}
				
			}
			if (!bool) {
				y = (i* 60 )+ numberOfMoveDowns * moveDownAmount;
				
				break;
			}
		}
		return y;
	}
	
	public int getSpecificX(int row, int column) {
		int x = 10+ (column * 60) + (numberOfMoveDowns * moveDownAmount);
		return x;
		
	}
	public int getSpecificY(int row, int column) {
		int y = 20 + (row* 60 )+ numberOfMoveDowns * moveDownAmount;
		return y;
	}

	
	public Alien[][] getAliens () {
		return aliens;
	}
	
	public int getWave() {
		return wave;
	}
	
//	public int getAlienX(int row, int column) {
//		return aliens[row][column].getX();
//	}
//	
//	public int getAlienY(int row, int column) {
//		return aliens[row][column].getX();
//	}

	public void printAllPositions() {
		for (int i = 0; i < aliens.length; i++) {
			for (int j = 0; j < aliens[i].length; j++) {
				String msg = "";
				int x,y;
				if (!aliens[i][j].isDead()|| aliens[i][j].getHitpoints()>0) {
					x = getSpecificX(i,j);
					y = getSpecificY(i,j);
					msg = "("+i+","+j+") at " +"("+x+","+y+")";
					System.out.println(msg);
				}
			}
		}
	}
	
	
	public void drawGridLines(Graphics g) {
		g.setColor(Color.YELLOW);
		for (int i = 0; i < (Game.getWindowHeight() - 100)/10; i++) {
			g.drawLine(0, i * 10, Game.getWindowWidth(), i*10);
		}
		
		for (int i = 0; i < Game.getWindowWidth()/10; i++) {
			g.drawLine(i*10, 0, i*10, Game.getWindowHeight()-100);
		}
	}
	
	
}