package tanks;
import java.awt.Color;
import java.awt.Graphics;

import battelfield.BattleField;
import engine.ActionField;
import enums.Direction;

public class BT7 extends AbstractTank{
	
	public BT7(BattleField battF, ActionField actF){
		super(actF, battF);
		tankColor = new Color(0, 0, 250);
		towerColor = new Color(0, 200, 200);
	}
	
	public BT7(ActionField actF, BattleField battF, int x, int y, Direction dir){
		super(actF, battF, x, y, dir);
		speed = super.speed*2;
		tankColor = new Color(0, 0, 250);
		towerColor = new Color(0, 200, 200);
	}
	
	public void attack() throws Exception{
		
		this.moveToQuadrant(3, 5);
		this.turn(direction.DOWN);
		
	}

}
