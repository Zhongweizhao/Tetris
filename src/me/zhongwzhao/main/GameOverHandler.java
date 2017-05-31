package me.zhongwzhao.main;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class GameOverHandler {
	
	private static Rectangle2D cover = new Rectangle(Method.containerX + 3* Method.squareLength, 
			Method.containerY,
			4 * Method.squareLength, 
			Method.squareLength);
	
	public static void tick(Handler handler) {
		boolean collide = false;
		
		for (int i = 0; i < handler.objects.size(); i++) {
			if (handler.objects.get(i).getBorder().intersects(cover)) {
				collide = true;
			}
		}
		
		if (collide) {
			handler.setGameOver(true);
			handler.endGame();
		}
	}
	
}
