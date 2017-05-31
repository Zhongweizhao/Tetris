package me.zhongwzhao.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6381417587358676530L;

	public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	public Menu menu;
	private HUD hud;
	private State state;

	private int containerX = 100;
	private int containerY = 30;
	private int containerWidth = 360;
	private int containerHeight = 720;
	
	
	public Game() {
		handler = new Handler(this);
		hud = new HUD(500, 30, handler, this);
		menu = new Menu(500, 30, handler, this);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseListener(hud);
		new Window(WIDTH, HEIGHT, "Tetris", this);
		
		state = State.Menu;
		
//		handler.addObject(Method.getRandomShape(5, 0, handler));
		
//		handler.addObject(new Shape(5, 2, Type.ShapeL, handler));
//		handler.addObject(Method.getShapeByType(5, 0, Type.ShapeL, handler));
//		handler.addObject(Method.getFixedSquare(4, 20, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 19, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 18, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 17, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 16, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 15, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 14, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 13, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 12, Color.BLACK));
//		handler.addObject(Method.getFixedSquare(4, 11, Color.BLACK));
	}
	
	
	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// draw background
		g.setColor(Color.WHITE);
		g.fillRect(containerX, containerY, containerWidth, containerHeight);
		
		
		// render objects
		handler.render(g);
		
		// render HUD
		if (state == State.Game || state == State.Pause) {
			hud.render(g);
		}
		
		// render menu
		if (state == State.Menu || state == State.GameOver) {
			menu.render(g);
		}
	
		// draw border
		g.setColor(Color.BLACK);
		g.drawRect(containerX, containerY, containerWidth, containerHeight);
		
		// setting up the grid
		for (int i = 1; i < 10; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(containerX + containerWidth / 10 * i - 1, containerY, 2, containerHeight);
		}
		for (int i = 1; i < 20; i++) {
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(containerX, containerY + containerHeight / 20 * i - 1, containerWidth, 2);
		}

		
		g.dispose();
		bs.show();
		
	}


	private void tick() {
		handler.tick();
		if (state == State.Menu || state == State.GameOver) {
			menu.tick();
		}
		if (state == State.Game || state == State.Pause) {
			hud.tick();
		}
	}


	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}


	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}

}
