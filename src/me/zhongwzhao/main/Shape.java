package me.zhongwzhao.main;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Shape extends GameObject{
	
	private double shapeX, shapeY;
	private int orientation = 0;

	public double getShapeX() {
		return shapeX;
	}

	public void setShapeX(double shapeX) {
		this.shapeX = shapeX;
	}

	public double getShapeY() {
		return shapeY;
	}

	public void setShapeY(double shapeY) {
		this.shapeY = shapeY;
	}

	private ArrayList<Square> squares = new ArrayList<Square>();
	public ArrayList<Square> getSquares() {
		return squares;
	}

	public void setSquares(ArrayList<Square> squares) {
		this.squares = squares;
	}

	Handler handler;
	int buffer = 0;
	
	public Shape(int x, int y, Type type, Handler handler) {
		super(x, y, type);
		this.shapeX = x;
		this.shapeY = y;
		this.handler = handler;
		this.type = type;
	}
	
	public void destroy() {
		handler.removeObject(this);
		for (int i = 0; i < squares.size(); i++) {
			Square s = squares.get(i);
			s.setType(Type.FixedSquare);
			handler.addObject(s);
		}
		handler.setControlRelease(true);
	}

	@Override
	public void tick() {
		if (buffer == 30) {
			boolean collide = false;
			for (int i = 0; i < squares.size(); i++) {
				if (squares.get(i).getType() == Type.ControlledSquare) {
					for (int j = 0; j < handler.objects.size(); j++) {
						if (handler.objects.get(j).getType() == Type.FixedSquare) {
							if (squares.get(i).squareBelow().getBorder().intersects(handler.objects.get(j).getBorder())) {
								collide = true;
							}
						}
					}
					if (squares.get(i).hasReachEnd()) {
						collide = true;
					}
				}
			}
			
			if (!collide) {
				for (int i = 0; i < squares.size(); i++) {
					if (squares.get(i).getType() == Type.ControlledSquare) {
						squares.get(i).moveDown();
					}
				}
				y++;
				shapeY++;
			} else {
				this.destroy();
			}
			
			buffer = 0;
		}
		buffer++;
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < squares.size(); i++) {
			squares.get(i).render(g);
		}
	}
	
	public Rectangle2D getBorder() {
		Rectangle2D res;
		if (squares.size() == 1) {
			res = squares.get(0).getBorder();
		} else {
			res = squares.get(0).getBorder();
			for (int i = 1; i < squares.size(); i++) {
				res.add(squares.get(i).getBorder());
			}
		}
		return res;
	}
	
	
	public void moveDown() {
		boolean collide = false;
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < handler.objects.size(); j++) {
				if (handler.objects.get(j).getType() == Type.FixedSquare) {
					if (squares.get(i).squareBelow().getBorder().intersects(handler.objects.get(j).getBorder())) {
						collide = true;
					}
				}
			}
			if (squares.get(i).hasReachEnd()) {
				collide = true;
			}
		}
		
		if (!collide) {
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).moveDown();
			}
			this.y += 1;
			this.shapeY += 1;
		}
	}
	
	public void rotate() {
		boolean collide = false;
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < handler.objects.size(); j++) {
				if (handler.objects.get(j).getType() == Type.FixedSquare) {
					if (Method.rotate(shapeX, shapeY, squares.get(i)).getBorder().intersects(handler.objects.get(j).getBorder())) {
						collide = true;
					}
				}
			}
			if (Method.rotate(x, y, squares.get(i)).isOutsideLeft() || 
					Method.rotate(x, y, squares.get(i)).isOutsideButtom() || 
					Method.rotate(x, y, squares.get(i)).isOutsideRight()) {
				collide = true;
			}
		}
		if (!collide) {
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).rotate(shapeX, shapeY);
			}
			// special case for SHAPES
			if (type == Type.ShapeI) {
				if ((orientation % 2) == 0) {
					shapeX -= 0.5;
					shapeY -= 0.5;
				} else {
					shapeX += 0.5;
					shapeY += 0.5;
				}
			}
			if (type == Type.ShapeZ || type == Type.ShapeZr) {
				if ((orientation % 2) == 0) {
					shapeX += 0.5;
					shapeY -= 0.5;
				} else {
					shapeX -= 0.5;
					shapeY += 0.5;
				}
			}
			orientation = (orientation + 1) % 4;
		}
	}
	
	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public void moveLeft() {
		boolean collide = false;
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < handler.objects.size(); j++) {
				if (handler.objects.get(j).getType() == Type.FixedSquare) {
					if (squares.get(i).squareLeft().getBorder().intersects(handler.objects.get(j).getBorder())) {
						collide = true;
					}
				}
			}
			if (squares.get(i).hasReachLeft()) {
				collide = true;
			}
		}
		
		if (!collide) {
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).moveLeft();
			}
			this.x -= 1;
			this.shapeX -= 1;
		}
	}
	
	public void moveRight() {
		boolean collide = false;
		for (int i = 0; i < squares.size(); i++) {
			for (int j = 0; j < handler.objects.size(); j++) {
				if (handler.objects.get(j).getType() == Type.FixedSquare) {
					if (squares.get(i).squareRight().getBorder().intersects(handler.objects.get(j).getBorder())) {
						collide = true;
					}
				}
			}
			if (squares.get(i).hasReachRight()) {
				collide = true;
			}
		}
		
		if (!collide) {
			for (int i = 0; i < squares.size(); i++) {
				squares.get(i).moveRight();
			}
			this.x += 1;
			this.shapeX += 1;
		}
	}
	
}
