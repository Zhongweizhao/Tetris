package me.zhongwzhao.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Square extends GameObject {
	
	private static int squareLength = 36;
	private static Rectangle2D bottom = new Rectangle(Method.containerX, Method.containerY + Method.containerHeight, Method.containerWidth, squareLength);
	private static Rectangle2D leftBorder = new Rectangle(Method.containerX - 2* squareLength, 
															Method.containerY - 2 * squareLength, 
															2 * squareLength, 
															Method.containerHeight + 2 * squareLength);
	private static Rectangle2D leftBorderFirstLayer = new Rectangle(Method.containerX - 2* squareLength, 
			Method.containerY - 2 * squareLength, 
			2 * squareLength, 
			Method.containerHeight + 2 * squareLength);
	private static Rectangle2D leftBorderSecondLayer = new Rectangle(Method.containerX - 2* squareLength, 
			Method.containerY - 2 * squareLength, 
			2 * squareLength, 
			Method.containerHeight + 2 * squareLength);
	private static Rectangle2D rightBorder = new Rectangle(Method.containerX + Method.containerWidth, 
															Method.containerY - 2 * squareLength, 
															2 * squareLength, 
															Method.containerHeight + 2 * squareLength);
	private static Rectangle2D rightBorderFirstLayer = new Rectangle(Method.containerX + Method.containerWidth, 
			Method.containerY - 2 * squareLength, 
			2 * squareLength, 
			Method.containerHeight + 2 * squareLength);
	private Color color;
	
	public Square(int x, int y, Type type, Color color) {
		super(x, y, type);
		this.color = color;
	}
	
	public void moveDown() {
		setY(getY() + squareLength);
	}
	
	
	public void rotate() {}
	
	public void rotate(int x, int y) {
		int shapeCenterX = Method.toAbsoluteX(x);
		int shapeCenterY = Method.toAbsoluteY(y);
		
		int oldCenterX = this.x;
		int oldCenterY = this.y;
		
		int newSquareRx = -(oldCenterY - shapeCenterY) + shapeCenterX;
		int newSquareRy = (oldCenterX - shapeCenterX) + shapeCenterY;

		this.setX(newSquareRx);
		this.setY(newSquareRy);
	}
	
	public void rotate(double x, double y) {
		int shapeCenterX =(int) Method.toAbsoluteX(x);
		int shapeCenterY =(int) Method.toAbsoluteY(y);
		
		int oldCenterX = this.x;
		int oldCenterY = this.y;
		
		int newSquareRx = -(oldCenterY - shapeCenterY) + shapeCenterX;
		int newSquareRy = (oldCenterX - shapeCenterX) + shapeCenterY;

		this.setX(newSquareRx);
		this.setY(newSquareRy);
	}
	
	public void moveLeft() {
		setX(getX() - squareLength);
	}
	
	public void moveRight() {
		setX(getX() + squareLength);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, squareLength, squareLength);
		
		g.setColor(Color.GRAY);
		g.drawRect(x, y, squareLength, squareLength);
	}
	
	public Rectangle2D getBorder(){
		return new Rectangle(x, y, squareLength, squareLength);
	}
	
	public Square squareBelow() {
		Square s = new Square(getX(), getY() + squareLength, getType(), Color.WHITE);
		return s;
	}
	
	public Square squareLeft() {
		Square s = new Square(getX() - squareLength, getY(), getType(), Color.WHITE);
		return s;
	}
	
	public Square squareRight() {
		Square s = new Square(getX() + squareLength, getY(), getType(), Color.WHITE);
		return s;
	}
	
	public boolean hasReachEnd() {
		Square below = squareBelow();
		return below.getBorder().intersects(bottom);
	}
	
	public boolean hasReachLeft() {
		Square left = squareLeft();
		return left.getBorder().intersects(leftBorder);
	}
	
	public boolean hasReachRight() {
		Square right = squareRight();
		return right.getBorder().intersects(rightBorder);
	}
	
	public boolean isOutsideButtom() {
		return getBorder().intersects(bottom);
	}
	
	public boolean isOutsideLeft() {
		return getBorder().intersects(leftBorder);
	}
	
	public boolean isOutsideRight() {
		return getBorder().intersects(rightBorder);
	}

	public static Rectangle2D getRightBorder() {
		return rightBorder;
	}

	public static void setRightBorder(Rectangle2D rightBorder) {
		Square.rightBorder = rightBorder;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
