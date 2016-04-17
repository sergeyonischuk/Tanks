package tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import battelfield.BattleField;
import engine.ActionField;
import engine.Bullet;
import enums.Direction;
import interfaces.Destroyable;
import interfaces.Drowable;

public abstract class AbstractTank implements Drowable, Destroyable{
	
	protected int speed = 20;
	protected int x;
	protected int y;
	
	protected Direction direction;
	protected ActionField actF;
	protected BattleField battleF;
	
	protected Color tankColor;
	protected Color towerColor;
	
	public AbstractTank(ActionField actF, BattleField battF, int x, int y, Direction direction){
		this.actF = actF;
		this.battleF = battF;
		this.x = x;
		this.y = y;
		this.direction = direction;
		
	}
	
	public AbstractTank(ActionField actF, BattleField battF){
		this.actF = actF;
		this.battleF = battF;
	}
	
	public void turn(Direction direction) throws Exception {
		this.direction = direction;
		actF.processTurn(this);
		}
	
	public void fire() throws Exception{
		if(direction == Direction.UP){
			Bullet bullet = new Bullet(x+25, y-10, direction);
			actF.processFire(bullet, this);
		}
		
		if(direction == Direction.DOWN){
			Bullet bullet = new Bullet(x+25, y+65, direction);
			actF.processFire(bullet, this);
		}
		
		if(direction == Direction.LEFT){
			Bullet bullet = new Bullet(x-26, y+25, direction);
			actF.processFire(bullet, this);
		}
		
		if(direction == Direction.RIGHT){
			Bullet bullet = new Bullet(x+65, y+25, direction);
			actF.processFire(bullet, this);
		}

	}
	public void move() throws Exception{
		actF.processMove(this);
	}
	
	public int updateX(int x) {
		this.x += x;
		return x;
	}

	public int updateY(int y) {
		this.y += y;
		return y;
	}

	public Direction getDirection() {
		return direction;
	}
	public int getSpeed() {
		return speed;
	}
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public ActionField getActionField() {
		return actF;
	}
	public BattleField getBattleField() {
		return battleF;
	}
	
	
	public void moveRandom() throws Exception { 
		Random r = new Random();
		int i;
		while (true) {
			i = r.nextInt(5);
			if (i > 0) {
				if(i == 1){
					direction = Direction.UP;
				}
				if(i == 2){
					direction = Direction.DOWN;
				}
				if(i == 3){
					direction = Direction.LEFT;
				}
				if(i == 4){
					direction = Direction.RIGHT;
				}
				actF.processTurn(this);
				actF.processMove(this);
			}
		}
	}
	
	public void clean() throws Exception{
		for(int y = 1; y <= 9; y++){
			for(int x = 1; x <= 9; x++){
				moveToQuadrant(x,y);
				if(x == 1 && x <= 9 && y != 1){
					moveToQuadrant(1, y++);
				}
			}
			System.out.println("IT IS CLEAR!");
		}
	}
	
	public void moveToQuadrant(int y, int x) throws Exception {
		if (getX() < x) {
			this.turn(Direction.RIGHT);
			while (getX() != x) {
				actF.processMove(this);
				if(getX() == x){
					break;
				}
			}
		} else {
			this.turn(Direction.LEFT);
			while (getX() != x) {
				actF.processMove(this);
				if(getX() == x){
					break;
				}
			}
		}

		if (getY() < y){
			this.turn(Direction.DOWN);
			while (getY() != y) {
				actF.processMove(this);
				if(getY() == y){
					break;
				}
			}
		} else {
			this.turn(Direction.UP);
			while (getY() != y) {
				actF.processMove(this);
				if(getY() == y){
					break;
				}
			}
		}
	}
	
	public void destroy(){
		this.x = -100;
		this.y = -100;
		actF.repaint();
	}
	
	 public void draw(Graphics g) {
			g.setColor(new Color(255, 0, 0));
			g.fillRect(this.getX(), this.getY(), 64, 64);

			
			g.setColor(new Color(0, 255, 0));
			if (this.getDirection() == Direction.UP) {
				g.fillRect(this.getX() + 20, this.getY(), 24, 34);
			} else if (this.getDirection() == Direction.DOWN) {
				g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
			} else if (this.getDirection() == Direction.LEFT) {
				g.fillRect(this.getX(), this.getY() + 20, 34, 24);
			} else if (this.getDirection() == Direction.RIGHT){
				g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
			}
	 }
}