package Projectile;

import java.util.ArrayList;

import actor.Actor;
import actor.Player;
import processing.core.PApplet;

public class PlayerBullet extends Bullet{

	public PlayerBullet(double x, double y, double vx, double vy) {
		super(x, y, vx, vy);
		setRadius(5);
	}

	public void act(ArrayList<Actor> other, PApplet surface, int time) {
		super.act(other, surface, time);
		
		for(int a = 0; a < other.size(); a++) {
			if(intersects(other.get(a))) {
				if(!(other.get(a) instanceof Projectile) && !(other.get(a) instanceof Player)) {
					setHp(getHp()-1);
					other.get(a).setHp(other.get(a).getHp()-1);
				}
				continue;
			}
		
		}
	}
	
	
	
}
