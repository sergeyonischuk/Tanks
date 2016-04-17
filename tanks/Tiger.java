package tanks;
import java.awt.Color;
import java.awt.Graphics;

import battelfield.BattleField;
import engine.ActionField;
import enums.Direction;

public class Tiger extends AbstractTank{
	
	public int armour = 1;
	
	public Tiger(ActionField actF, BattleField battF){
		super(actF, battF);
		tankColor = new Color(0, 255, 0);
		towerColor = new Color(255, 0, 0);
	}
	
	public Tiger(ActionField actF, BattleField battF, int x, int y, Direction dir){
		super(actF, battF, x, y, dir);
		tankColor = new Color(0, 255, 0);
		towerColor = new Color(255, 0, 0);
	}

}