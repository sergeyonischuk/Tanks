package tanks;
import java.awt.Color;
import java.awt.Graphics;

import battelfield.BattleField;
import engine.ActionField;
import enums.Direction;

public class T34 extends AbstractTank {
	
	public T34(ActionField actF, BattleField battF){
		super(actF, battF);
		tankColor = new Color(255, 0, 0);
		towerColor = new Color(0, 255, 0);
	}

	public T34(ActionField actF, BattleField battF, int x, int y, Direction dir){
		super(actF, battF, x, y, dir);
		tankColor = new Color(255, 0, 0);
		towerColor = new Color(0, 255, 0);
	}

}