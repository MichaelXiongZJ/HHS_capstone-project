package Projectile;

import java.util.ArrayList;

import Enemy.Enemy;
import actor.Actor;
import actor.Player;
import processing.core.PApplet;

public class PlayerBullet extends Bullet{

	public PlayerBullet(double x, double y, double vx, double vy, boolean ignorePlayer) {	
		super(x, y, vx, vy);
		super.setSpeed(8);
		setRadius(10);
		setIgnorePlayer(true);
	}
	
	
	
	public void act(ArrayList<Actor> other, PApplet surface, int time, boolean i) {
		super.act(other, surface, time);
		
		for(int a = 0; a < other.size(); a++) {
			if(intersects(other.get(a))) {
				if(!(other.get(a) instanceof Player) && other.get(a) != this) {
					if(getCancelBullet()) {
						setHp(getHp()-1);
						other.get(a).setHp(other.get(a).getHp()-1);
					}else if(!(other.get(a) instanceof Projectile)){
						setHp(getHp()-1);
						other.get(a).setHp(other.get(a).getHp()-1);
					}
				}
				continue;
			}
		
		}
	}
	
	
}
