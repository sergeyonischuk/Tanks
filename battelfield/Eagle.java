package battelfield;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.Destroyable;

public class Eagle extends AbstractObject implements Destroyable{




	public Eagle(BattleField battF, int x, int y) {
		super(battF, x, y);

	}

	@Override
	public void destroy() {

		
	}

	@Override
	public void draw(Graphics g) {
			g.setColor(new Color(60, 15, 60));
			g.fillRect(x, y, 64, 64);
		}
		
}
