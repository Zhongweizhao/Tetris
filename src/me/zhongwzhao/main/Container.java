package me.zhongwzhao.main;

import java.awt.Color;
import java.awt.Graphics;

public class Container {

	public static int WIDTH;
	public static int HEIGHT;
	public static int x, y;
	private Handler handler;
	
	
	
	public Container(int x, int y, int WIDTH, int HEIGHT, Handler handler) {	
		Container.WIDTH = WIDTH;
		Container.HEIGHT = HEIGHT; 
		Container.x = x;
		Container.y = y;
		this.handler = handler;
	}
	


	public void renderBackground(Graphics g) {
		
		// draw background
		g.setColor(Color.WHITE);
		g.fillRect(x, y, WIDTH, HEIGHT);
	
	}
	
	public void renderGrid(Graphics g) {
		// draw border
		g.setColor(Color.BLACK);
		g.drawRect(x, y, WIDTH, HEIGHT);
		
		// setting up the grid
		for (int i = 1; i < 10; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x + WIDTH / 10 * i - 1, y, 2, HEIGHT);
		}
		for (int i = 1; i < 20; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(x, y + HEIGHT / 20 * i - 1, WIDTH, 2);
		}	
	}	
	
	
}
