package me.zhongwzhao.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private ArrayList<Type> types;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		types = Method.getTypes();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			
			if (types.contains(tempObject.getType())) {
				if (key == 37) {
					tempObject.moveLeft();
				} else if (key == 38) {
					tempObject.rotate();
				} else if (key == 39) {
					tempObject.moveRight();
				} else if (key == 40) {
					tempObject.moveDown();
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
		}
	}
}
