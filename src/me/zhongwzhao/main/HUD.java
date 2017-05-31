package me.zhongwzhao.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class HUD extends MouseAdapter {
	
	private int x, y;
	private Handler handler;
	private Game game;
	private int score = 0;
	private String pauseString = "Pause";
	
	public HUD(int x, int y, Handler handler, Game game) {
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.game = game;
	}
	
	public void tick() {
		if (game.getState() == State.Game) {
			score = handler.getScore();
		} else {
			score = 0;
		}
	}
	
	public void render(Graphics g) {
		if (game.getState() == State.Game || game.getState() == State.Pause) {
			g.setFont(new Font("Arial", Font.PLAIN, 44)); 
		    g.setColor(Color.BLACK);  
		    g.drawString("Score: " + score * 100, x + 185, y + 146 + 75);
		    
		    g.drawString("Next: ", x + 222, y + 500);
		    
		    g.setColor(Color.WHITE);
			g.drawRect(x + 170, y + 325, 200, 70);
			g.setColor(Color.CYAN);
			g.fillRect(x + 170, y + 325, 200, 70);
			
			g.setFont(new Font("Arial", Font.PLAIN, 36)); 
		    g.setColor(Color.BLACK);  
		    if (game.getState() == State.Game) {
		    	g.drawString(pauseString, x + 216, y + 69 + 300);
		    } else {
		    	g.drawString(pauseString, x + 200, y + 69 + 300);
		    }
		}
	}
	
	private Rectangle2D getPauseBorderBox() {
		return new Rectangle(x + 170, y + 325, 200, 70);
	}
	
	public void mouseClicked(MouseEvent e) {
		int clickX = e.getX();
		int clickY = e.getY();
		
		if (getPauseBorderBox().contains(new Point(clickX, clickY))) {
			if (game.getState() == State.Game) {
				game.setState(State.Pause);
				pauseString = "Resume";
			} else {
				game.setState(State.Game);
				pauseString = "Pause";
			}
		}
	}
	
}
