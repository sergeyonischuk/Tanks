package battelfield;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.Drowable;

public abstract class AbstractObject implements Drowable{
	
	private BattleField battF;
	
	protected int x;
	protected int y;
	protected Color battFColor;
	
	

    public AbstractObject(BattleField battF, int x, int y) {
        this.battF = battF;
        this.x = x;
        this.y = y;
    }
	
	public void drow(Graphics g){
		g.setColor(battFColor);
		g.fillRect(x, y, 64, 64);
		
		
	}
	

}
