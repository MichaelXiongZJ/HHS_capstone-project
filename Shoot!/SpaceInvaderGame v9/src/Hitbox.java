/**Name: Christopher L, Nont K, Abbas D
 * Date: 5/26/20
 * Rev: 6
 * Notes: Chris: Draws a healthbar and HP of the alien 
 */


import java.awt.Color;
import java.awt.Graphics;

public class Hitbox {
	private int radius;
	private int topLeftx;
	private int topLefty;
	private int xCenter, yCenter;
	private Hitbox hitbox;
	private int hitpoints;
	
	public Hitbox (int x, int y, int r) {
		radius   = r;
		topLeftx = x;
		topLefty = y;
		xCenter = topLeftx + radius;
		yCenter = topLefty + radius;
	}
	
	public boolean isHit (int x, int y) {
		double distance = Math.sqrt(Math.pow(((topLeftx+radius)-x), 2) + Math.pow(((topLefty+radius)-y), 2));
		return distance <= radius;
	}
	
	public void move (int x, int y) {
		topLeftx += x;
		topLefty += y;
		xCenter = topLeftx + radius;
		yCenter = topLefty + radius;
	}
	
	public void draw(Graphics g) {
		
		g.drawOval(topLeftx, topLefty, radius, radius);
	}
	public void draw(Graphics g, String subtext) {
		g.setColor(Color.RED);
		g.drawOval(topLeftx, topLefty, radius, radius);
		g.setColor(Color.WHITE);
		g.drawString(subtext, topLeftx, yCenter + 20);
	}
	public void draw(Graphics g, String subtext, String subtext2) {
		xCenter = topLeftx + radius;
		yCenter = topLefty + radius;
		
		if (Game.SHOW_HITBOX_CIRCLE) {
			g.setColor(Color.RED);
			g.drawOval(topLeftx, topLefty, radius, radius);
		}
		
		g.setColor(Color.WHITE);
		g.drawString(subtext, topLeftx, yCenter + 20);
		g.drawString(subtext2, topLeftx, yCenter + 40);

	}
	public void draw(Graphics g, String subtext2, int maxHealth, int health) {
		xCenter = topLeftx + radius;
		yCenter = topLefty + radius;
		
		if (Game.SHOW_HITBOX_CIRCLE) {
			g.setColor(Color.RED);
			g.drawOval(topLeftx, topLefty, radius, radius);
		}
		double xRatio = (health+ 0.0 )/maxHealth;

		if (xRatio >= 0.6) {
			g.setColor(Color.GREEN);
		}
		else if (xRatio >= (1.0/3)) {
			g.setColor(Color.YELLOW);
		}
		else if (xRatio >= 0) {
			g.setColor(Color.RED);
		}
		
		g.fillRect(topLeftx, yCenter -5, (int)(50 * xRatio), 5);
		
		g.setColor(Color.WHITE);
		g.drawString("HP: " + health, topLeftx, yCenter + 15);
//		g.drawString(subtext2, topLeftx, yCenter + 30);

	}
	
	
	
}