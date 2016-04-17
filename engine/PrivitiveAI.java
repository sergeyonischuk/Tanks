package engine;

import enums.Direction;
import tanks.AbstractTank;

public class PrivitiveAI {
	
	public void attack(AbstractTank tank) throws Exception{
		tank.moveToQuadrant(64, 256);
		tank.turn(Direction.DOWN);
		tank.fire();
		tank.move();
		tank.move();
		tank.fire();
		tank.move();
		tank.fire();
		tank.fire();
		tank.fire();
	}

}
