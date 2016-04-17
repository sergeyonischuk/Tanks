package battelfield;
import java.awt.Color;
import java.awt.Graphics;

import interfaces.Destroyable;

public class Brick extends AbstractObject implements Destroyable {


	public Brick(BattleField battF, int x, int y) {
		super(battF, x, y);

	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(60, 0, 0));
		g.fillRect(x, y, 64, 64);
	}

}
