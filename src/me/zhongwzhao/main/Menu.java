package me.zhongwzhao.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Menu extends MouseAdapter {
	
	private int x, y;
	private Handler handler;
	private Game game;
	private int lastScore = 0;
	
	public int getLastScore() {
		return lastScore;
	}

	public void setLastScore(int lastScore) {
		this.lastScore = lastScore;
	}

	public Menu(int x, int y, Handler handler, Game game) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.game = game;
	}
	
	public void tick() {}
	
	public void render(Graphics g) {
		if (game.getState() == State.Menu) {
			
			g.setColor(Color.WHITE);
			g.drawRect(x + 170, y + 100, 200, 70);
			g.setColor(Color.CYAN);
			g.fillRect(x + 170, y + 100, 200, 70);
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.BLACK);  
		    g.drawString("Start", x + 228, y + 146);
		    
		    g.setColor(Color.WHITE);
			g.drawRect(x + 170, y + 250, 200, 70);
			g.setColor(Color.CYAN);
			g.fillRect(x + 170, y + 250, 200, 70);
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.BLACK);  
		    g.drawString("Exit", x + 236, y + 146 + 150);
		}
		
		if (game.getState() == State.GameOver) {
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.GREEN);  
		    g.drawString("Your Score: " + lastScore * 100, x + 148, y + 146);
			
			g.setColor(Color.WHITE);
			g.drawRect(x + 170, y + 250, 200, 70);
			g.setColor(Color.CYAN);
			g.fillRect(x + 170, y + 250, 200, 70);
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.BLACK);  
		    g.drawString("Start again!", x + 178, y + 146 + 150);
		    
		    g.setColor(Color.WHITE);
			g.drawRect(x + 170, y + 400, 200, 70);
			g.setColor(Color.CYAN);
			g.fillRect(x + 170, y + 400, 200, 70);
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.BLACK);  
		    g.drawString("Exit", x + 236, y + 146 + 300);
		}
	}
	
	private Rectangle2D getStartBorderBox() {
		return new Rectangle(x + 170, y + 100, 200, 70);
	}
	
	private Rectangle2D getExitBorderBox() {
		return new Rectangle(x + 170, y + 250, 200, 70);
	}
	
	private Rectangle2D getGameOverRestartBorderBox() {
		return new Rectangle(x + 170, y + 250, 200, 70);
	}
	
	private Rectangle2D getGameOverExitBorderBox() {
		return new Rectangle(x + 170, y + 400, 200, 70);
	}
	
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		
		if (game.getState() == State.Menu && getStartBorderBox().contains(new Point(clickX, clickY))) {
			handler.addObject(Method.getRandomShape(6, 0, handler));
			game.setState(State.Game);
		} else if (game.getState() == State.Menu && getExitBorderBox().contains(new Point(clickX, clickY))) {
			System.exit(1);
		} else if (game.getState() == State.GameOver && getGameOverRestartBorderBox().contains(new Point(clickX, clickY))) {
			game.setState(State.Game);
			handler.objects.clear();
			handler.setGameOver(false);
			handler.addObject(Method.getRandomShape(6, 0, handler));
		} else if (game.getState() == State.GameOver && getGameOverExitBorderBox().contains(new Point(clickX, clickY))) {
			System.exit(1);
		}
	}
	
}
