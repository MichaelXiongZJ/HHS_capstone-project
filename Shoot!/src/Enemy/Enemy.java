package Enemy;

import actor.Actor;

public abstract class Enemy extends Actor{

	public Enemy() {
		super();
	}
	
	public Enemy(int hp, double x, double y) {
		super(hp, x, y, 0, 0);
	}
}
