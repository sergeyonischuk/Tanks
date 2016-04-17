package battelfield;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.Destroyable;

public class Rock extends AbstractObject implements Destroyable{



	public Rock(BattleField battF, int x, int y) {
		super(battF, x, y);

	}

	@Override
	public void destroy() {

		
	}

	@Override
	public void draw(Graphics g) {
			g.setColor(new Color(0, 50, 50));
			g.fillRect(x, y, 64, 64);
		
	}
	

}
