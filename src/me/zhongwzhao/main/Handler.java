package me.zhongwzhao.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	private boolean controlRelease = false;
	private Game game;
	private boolean gameOver = false;
	private Type nextType;
	private Shape nextShape;
	
	private int score = 0;
	
	public Handler(Game game) {
		this.game = game;
		nextType = Method.getRandomType();
		nextShape = Method.getShapeByType(20, 17, nextType, this);
	}
	
	public void tick() {
		if (game.getState() == State.Game) {
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).tick();	
			}
			
			if (controlRelease) {
				controlRelease = false;
				// check game over
				GameOverHandler.tick(this);
				
				if (gameOver) {
					return;
				}
				
				// when controlRelease, check if some line is full, then add another control shape
				
				FullLineHandler.removeFullLine(this);
				addObject(Method.getShapeByType(6, 0, nextType, this));
				removeObject(nextShape);
				nextType = Method.getRandomType();
				nextShape = Method.getShapeByType(20, 17, nextType, this);
			}
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			objects.get(i).render(g);
		}
		
		if (game.getState() == State.Game || game.getState() == State.Pause) {
			nextShape.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.objects.add(object);
	}

	public void removeObject(GameObject object) {
		this.objects.remove(object);
	}
	
	public boolean isControlRelease() {
		return controlRelease;
	}

	public void setControlRelease(boolean controlRelease) {
		this.controlRelease = controlRelease;
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore() {
		score++;
	}
	
	public void addScore(int i) {
		score += i;
	}
	
	public void endGame() {
		game.menu.setLastScore(score);
		game.setState(State.GameOver);
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
