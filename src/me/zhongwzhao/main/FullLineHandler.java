package me.zhongwzhao.main;

import java.util.ArrayList;

public class FullLineHandler {
	public static void removeFullLine(Handler handler) {
		int[][] table = new int[20][10];
		int[] fullRow = new int[10];
		int x, y;
		
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = 0;
			}
		}
		
		for (int i = 0; i < fullRow.length; i++) {
			fullRow[i] = 1;
		}
		
		
		for (int i = 0; i < handler.objects.size(); i++) {
			GameObject tempObject = handler.objects.get(i);
			x = Method.toRelativeX(tempObject.getX());
			y = Method.toRelativeY(tempObject.getY());
			table[y-1][x-1] = 1;
		}
		
		for (int i = table.length - 1; i >= 0; i--) {
			if (arrayEquals(table[i], fullRow)) {
				// remove all at line i + 1 (y = i + 1)
				ArrayList<GameObject> removeList = new ArrayList<GameObject>();
				for (GameObject tempObject : handler.objects) {
					x = Method.toRelativeX(tempObject.getX());
					y = Method.toRelativeY(tempObject.getY());
					if (y == i + 1) {
						removeList.add(tempObject);
					} 
				}
				for (GameObject object : removeList) {
					handler.removeObject(object);
				}
				for (GameObject tempObject : handler.objects) {
					x = Method.toRelativeX(tempObject.getX());
					y = Method.toRelativeY(tempObject.getY());
					if (y < i + 1) {
						tempObject.moveDown();
					} 
				}
				handler.addScore();
				removeFullLine(handler);
				return;
			}
			
		}
		
	}
	
	public static boolean arrayEquals(int[] a1, int[] a2) {
		if (a1.length != a2.length) {
			return false;
		}
		
		boolean result = true;
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != a2[i]) {
				result = false;
				break;
			}
		}	
		return result;		
	}
}
