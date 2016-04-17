package engine;
import java.awt.Color;
import java.awt.Graphics;

import enums.Direction;
import interfaces.Destroyable;
import interfaces.Drowable;

public class Bullet implements Drowable, Destroyable{
	private int x = -100;
	private int y = -100;
	private int speed = 5;
	private Direction direction;
	
	public Bullet(int x, int y, Direction direction){
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Direction getDirection() {
		return direction;
	}
	public int getSpeed() {
		return speed;
	}
	
	public void updateX(int x){
		this.x += x;
	}
	public void updateY(int y){
		this.y += y;
	}
	public void destroy(){
		x = -100;
		y = -100;
	}
	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255, 255, 0));
		g.fillRect(this.x, this.y, 14, 14);
		
	}
	
}