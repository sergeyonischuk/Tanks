package battelfield;
import java.awt.Color;
import java.awt.Graphics;

public class Water extends AbstractObject{

	public Water(BattleField battF, int x, int y) {
		super(battF, x, y);

	}

	@Override
	public void draw(Graphics g) {
			g.setColor(new Color(0, 50, 0));
			g.fillRect(x, y, 64, 64);
		}
		
	
}
