package me.zhongwzhao.main;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class GameObject {
	
	protected int x, y;
	protected Type type;
	protected int velX, velY;
	
	public GameObject(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle2D getBorder();
	public abstract void moveDown();
	public abstract void rotate();
	public abstract void moveLeft();
	public abstract void moveRight();
	


	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}



	
	
	
}
