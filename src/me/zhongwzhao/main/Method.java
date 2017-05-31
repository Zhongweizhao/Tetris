package me.zhongwzhao.main;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Method {
	
	public static int containerX = 100;
	public static int containerY = 30;
	public static int containerWidth = 360;
	public static int containerHeight = 720;
	
	public static int squareLength = 36;

	public static Square getFixedSquare(int numX, int numY, Color color) {	
		Square s = new Square(containerX + squareLength * (numX - 1), containerY + squareLength * (numY - 1), Type.FixedSquare, color);
		return s;
	}
	
	public static Square getControlledSquare(int numX, int numY, Color color) {	
		Square s = new Square(containerX + squareLength * (numX - 1), containerY + squareLength * (numY - 1), Type.ControlledSquare, color);
		return s;
	}
	
	public static ArrayList<Type> getTypes() {
		ArrayList<Type> types = new ArrayList<Type>();
		types.add(Type.ShapeI);
		types.add(Type.ShapeL);
		types.add(Type.ShapeLr);
		types.add(Type.ShapeT);
		types.add(Type.ShapeZ);
		types.add(Type.ShapeZr);
		types.add(Type.ShapeO);
		return types;
	}
	
	public static Type getRandomType() {
		ArrayList<Type> types = getTypes();
		
		Random r = new Random();
		int i = r.nextInt(types.size());
		Type type = types.get(i);
		return type;
	}
	
	public static Shape getRandomShape(int x, int y, Handler handler) {
		ArrayList<Type> types = getTypes();
		
		Random r = new Random();
		int i = r.nextInt(types.size());
		Type type = types.get(i);
		
		Shape s = new Shape(x, y, type, handler);
		
		if (type == Type.ShapeI) {
			s.setSquares(getShapeI(x, y));
		} else if (type == Type.ShapeL) {
			s.setSquares(getShapeL(x, y));
		} else if (type == Type.ShapeLr) {
			s.setSquares(getShapeLr(x, y));
		} else if (type == Type.ShapeT) {
			s.setSquares(getShapeT(x, y));
		} else if (type == Type.ShapeZ) {
			s.setSquares(getShapeZ(x, y));
		} else if (type == Type.ShapeZr) {
			s.setSquares(getShapeZr(x, y));
		} else if (type == Type.ShapeO) {
			s.setShapeX(s.getShapeX() - 0.5);
			s.setShapeY(s.getShapeY() - 0.5);
			s.setSquares(getShapeO(x, y));
		}

		return s;
	}
	
	public static Shape getShapeByType(int x, int y, Type type, Handler handler) {
		Shape s = new Shape(x, y, type, handler);
		
		if (type == Type.ShapeI) {
			s.setSquares(getShapeI(x, y));
		} else if (type == Type.ShapeL) {
			s.setSquares(getShapeL(x, y));
		} else if (type == Type.ShapeLr) {
			s.setSquares(getShapeLr(x, y));
		} else if (type == Type.ShapeT) {
			s.setSquares(getShapeT(x, y));
		} else if (type == Type.ShapeZ) {
			s.setSquares(getShapeZ(x, y));
		} else if (type == Type.ShapeZr) {
			s.setSquares(getShapeZr(x, y));
		} else if (type == Type.ShapeO) {
			s.setShapeX(s.getShapeX() - 0.5);
			s.setShapeY(s.getShapeY() - 0.5);
			s.setSquares(getShapeO(x, y));
		}
		return s;
	}
	
	public static NextShape getNextShapeByType(int x, int y, Type type, Handler handler) {
		NextShape s = new NextShape(x, y, type, handler);
		
		if (type == Type.ShapeI) {
			s.setSquares(getShapeI(x, y));
		} else if (type == Type.ShapeL) {
			s.setSquares(getShapeL(x, y));
		} else if (type == Type.ShapeLr) {
			s.setSquares(getShapeLr(x, y));
		} else if (type == Type.ShapeT) {
			s.setSquares(getShapeT(x, y));
		} else if (type == Type.ShapeZ) {
			s.setSquares(getShapeZ(x, y));
		} else if (type == Type.ShapeZr) {
			s.setSquares(getShapeZr(x, y));
		} else if (type == Type.ShapeO) {
			s.setShapeX(s.getShapeX() - 0.5);
			s.setShapeY(s.getShapeY() - 0.5);
			s.setSquares(getShapeO(x, y));
		}
		return s;
	}

	public static ArrayList<Square> getShapeL(int x, int y) {
		Color color = Color.BLUE;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x + 1, y, color);
		Square s3 = getControlledSquare(x - 1, y, color);
		Square s4 = getControlledSquare(x - 1, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeLr(int x, int y) {
		Color color = Color.ORANGE;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x + 1, y, color);
		Square s3 = getControlledSquare(x - 1, y, color);
		Square s4 = getControlledSquare(x + 1, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeT(int x, int y) {
		Color color = Color.MAGENTA;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x + 1, y, color);
		Square s3 = getControlledSquare(x - 1, y, color);
		Square s4 = getControlledSquare(x, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeZ(int x, int y) {
		Color color = Color.PINK;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x + 1, y, color);
		Square s3 = getControlledSquare(x - 1, y - 1, color);
		Square s4 = getControlledSquare(x, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeZr(int x, int y) {
		Color color = Color.RED;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x - 1, y, color);
		Square s3 = getControlledSquare(x + 1, y - 1, color);
		Square s4 = getControlledSquare(x, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeI(int x, int y) {
		Color color = Color.GREEN;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x - 1, y, color);
		Square s3 = getControlledSquare(x + 1, y, color);
		Square s4 = getControlledSquare(x - 2, y, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeO(int x, int y) {
		Color color = Color.YELLOW;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getControlledSquare(x, y, color);
		Square s2 = getControlledSquare(x - 1, y, color);
		Square s3 = getControlledSquare(x, y - 1, color);
		Square s4 = getControlledSquare(x - 1, y - 1, color);
		res.add(s1);
		res.add(s2);
		res.add(s3);
		res.add(s4);
		return res;
	}
	
	public static ArrayList<Square> getShapeDotFixed(int x, int y) {
		Color color = Color.BLACK;
		ArrayList<Square> res = new ArrayList<Square>();
		Square s1 = getFixedSquare(x, y, color);
		res.add(s1);
		return res;
	}
	
	public static int toRelativeX(int absolute){
		return (absolute - containerX) / squareLength + 1;
	}
	
	public static int toAbsoluteX(int relative){
		return (relative - 1) * squareLength + containerX;
	}
	
	public static int toRelativeY(int absolute){
		return (absolute - containerY) / squareLength + 1;
	}
	
	public static int toAbsoluteY(int relative){
		return (relative - 1) * squareLength + containerY;
	}

	public static double toRelativeX(double absolute){
		return (absolute - containerX) / squareLength + 1;
	}
	
	public static double toAbsoluteX(double relative){
		return (relative - 1) * squareLength + containerX;
	}
	
	public static double toRelativeY(double absolute){
		return (absolute - containerY) / squareLength + 1;
	}
	
	public static double toAbsoluteY(double relative){
		return (relative - 1) * squareLength + containerY;
	}
	
	// x and y are relative length
	// clockwise rotate
	public static Square rotate(int x, int y, Square s) {
		int shapeCenterX = Method.toAbsoluteX(x);
		int shapeCenterY = Method.toAbsoluteY(y);
		
		int oldCenterX = s.x;
		int oldCenterY = s.y;
		
		int newSquareRx = -(oldCenterY - shapeCenterY) + shapeCenterX;
		int newSquareRy = (oldCenterX - shapeCenterX) + shapeCenterY;
		
		Square sn = new Square(newSquareRx, newSquareRy, s.getType(), s.getColor());
		return sn;
	}
	
	public static Square rotate(double x, double y, Square s) {
		int shapeCenterX =(int) Method.toAbsoluteX(x);
		int shapeCenterY =(int) Method.toAbsoluteY(y);
		
		int oldCenterX = s.x;
		int oldCenterY = s.y;
		
		int newSquareRx = -(oldCenterY - shapeCenterY) + shapeCenterX;
		int newSquareRy = (oldCenterX - shapeCenterX) + shapeCenterY;

		Square sn = new Square(newSquareRx, newSquareRy, s.getType(), s.getColor());
		return sn;
	}
	
}
