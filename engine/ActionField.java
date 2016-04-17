package engine;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import battelfield.BattleField;
import battelfield.Brick;
import battelfield.Eagle;
import battelfield.Rock;
import battelfield.Water;
import enums.Direction;
import tanks.AbstractTank;
import tanks.BT7;
import tanks.T34;
import tanks.Tiger;


public class ActionField extends JPanel{
	final int UP = 1;
	final int DOWN = 2;
	final int LEFT = 3;
	final int RIGHT = 4;
	private boolean COLORDED_MODE = false;
	private BattleField battleField;
	
	private T34 defender;
	private Tiger agressor;
	private Bullet bullet;
	private AbstractTank tankAbs;
	
	private PrivitiveAI ai = new PrivitiveAI();

	
    public void runTheGame() throws Exception {
    	agressor.turn(Direction.DOWN);

    	
    	agressor.fire();
    	agressor.fire();
    	agressor.fire();
    	
    	
  }
    
	
	
	private boolean processInterception() throws Exception {
		String bulletCoord = getQuadrant(bullet.getX(), bullet.getY());
		int x = Integer.parseInt(bulletCoord.substring(0, bulletCoord.indexOf("_")));
		int y = Integer.parseInt(bulletCoord.substring(bulletCoord.indexOf("_") + 1));
		if( x >= 0 && x <= 8 && y >= 0 && y <= 8){	
			if(battleField.scanQuadrant(x, y).equals("B")){
				battleField.updateQuadrant(x, y, " ");
				bullet.updateX(-100);
				bullet.updateY(-100);
					return true;
			}
			if (getQuadrant(agressor.getX(), agressor.getY()).equals(bulletCoord)) {
				if(agressor.armour == 1){
					agressor.armour = 0;
					return true;
				}
				agressor.destroy();
				bullet.destroy();
				Thread.sleep(3000);
				newAgr();
			}
			if (getQuadrant(defender.getX(), defender.getY()).equals(bulletCoord)) {
	
				defender.destroy();
				bullet.destroy();
				System.out.println("GAME OVER");
			}
		}
		
			return false;
	}
	
	public String tankAgrLocation(){
		return agressor.getX() + "_" + agressor.getY();
	}
	
	public String getQuadrant(int x, int y) {
		x = x / 64;
		y = y / 64;
		return y + "_" + x;
	}
	
	public String getQuadrantXY(int v, int h) {
		return (v - 1) * 64 + "_" + (h - 1) * 64;
	}
	
	public void processMove(AbstractTank tank) throws Exception{
		int step = 1;
		
		Direction dir = tank.getDirection();
		int covered = 0;
		if((dir == Direction.UP && tank.getY() == 0) || (dir == Direction.DOWN && tank.getY() == 512)
|| (dir == Direction.LEFT && tank.getX() == 0) || (dir == Direction.RIGHT && tank.getX() == 512)){
			System.out.println("You cant leave the battlefield!");
			return;
		}
		
		
		tank.turn(dir);
			while(covered < 64){
				if(dir == Direction.UP){
					tank.updateY(-step);
					System.out.println("[move up] direction: " + dir + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				}
				if(dir == Direction.DOWN){
					tank.updateY(step);
					System.out.println("[move down] direction: " + dir + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				}
				if(dir == Direction.LEFT){
					tank.updateX(-step);
					System.out.println("[move left] direction: " + dir + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				}
				if(dir == Direction.RIGHT){
					tank.updateX(step);
					System.out.println("[move right] direction: " + dir + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
				}
				covered += step;
				repaint();
				Thread.sleep(tank.getSpeed());
			}
	}
	

	
	public void processFire(Bullet bullet, AbstractTank tank) throws Exception{
		int step = 1;
		this.bullet = bullet;
		while(bullet.getX() > -20 && bullet.getX() < 600 && bullet.getY() > -20 && bullet.getX() < 600){
			if(tank.getDirection() == Direction.UP){
					bullet.updateY(-step);
			}
			else if(tank.getDirection() == Direction.DOWN){
					bullet.updateY(+step);
			}
			else if(tank.getDirection() == Direction.LEFT){
					bullet.updateX(-step);
			}
			else if(tank.getDirection() == Direction.RIGHT){
					bullet.updateX(+step);
			}
			if(processInterception()){
				bullet.destroy();
			}
			repaint();
			Thread.sleep(bullet.getSpeed());
		}
	}
	
	public void processTurn(AbstractTank tank) throws Exception{
		repaint();
	}
	
	public int randomPositionX(){
		int ran = 1 + (int)(Math.random() * ((3 - 1) + 1));
		int x = 0;
		if(ran == 1){
			x = 64;
		}
		if(ran == 2){
			x = 192;
		}
		
		if(ran == 3){
			x = 384;
		}
		return x;
	}
	
	public void newAgr(){
		agressor.setX(randomPositionX());
		agressor.setY(64);
	}

	
	public ActionField() throws Exception{
		battleField = new BattleField();
		defender = new T34(this, battleField, 64, 256, Direction.UP);
		agressor = new Tiger(this, battleField, randomPositionX(), 64, Direction.UP);
		bullet = new Bullet (-100, -100, Direction.UP);
		
		JFrame frame = new JFrame("BATTLE FIELD, DAY 2");
		frame.setLocation(750, 150);
		frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH(), battleField.getBF_HEIGHT() +22));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int i = 0;
		Color cc;
		for (int v = 0; v < 9; v++) {
			for (int h = 0; h < 9; h++) {
				if (COLORDED_MODE) {
					if (i % 2 == 0) {
						cc = new Color(252, 241, 177);
					} else {
						cc = new Color(233, 243, 255);
					}
				} else {
					cc = new Color(180, 180, 180);
				}
				i++;
				g.setColor(cc);
				g.fillRect(h * 64, v * 64, 64, 64);
			}
		}
		
		for (int j = 0; j < battleField.getDimentionX(); j++) {
			for (int k = 0; k < battleField.getDimentionY(); k++) {

					String coordinates = getQuadrantXY(j + 1, k + 1);
					int separator = coordinates.indexOf("_");
					int y = Integer.parseInt(coordinates.substring(0, separator));
					int x = Integer.parseInt(coordinates.substring(separator + 1));
					if (battleField.scanQuadrant(j, k).equals("B")) {
						Brick brick = new Brick(battleField, x, y);
						brick.draw(g);
					}
					if (battleField.scanQuadrant(j, k).equals("R")) {
						Rock rock = new Rock(battleField, x, y);
						rock.draw(g);
					}
					if (battleField.scanQuadrant(j, k).equals("W")) {
						Water water = new Water(battleField, x, y);
						water.draw(g);
					}
					if (battleField.scanQuadrant(j, k).equals("E")) {
						Eagle eagle = new Eagle(battleField, x, y);
						eagle.draw(g);
					}
				}
			}
		
		defender.draw(g);
		agressor.draw(g);
		bullet.draw(g);

	}
	
	
}