package Shapes;

import Tetris.DI;
import Tetris.Point;

public abstract class Shape {
	
	public int width, height;
	public int[][] shape;
	public Point position;
	
	
	public Shape(int width, int height, Point position) {
		this(width, height);
		this.position = new Point(position);
	}
	
	public Shape(int width, int height) {
		this.position = new Point(DI.shapeStartPoint);
		this.width = width;
		this.height = height;
		shape = new int[width][height];		
		initShape();
	}

	protected abstract void initShape();
	
	private void print() {
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[0].length; j++) {
				System.out.print(shape[i][j]);
			}
			System.out.println();
		}
	}
	
	public int[] bottomLane() {
		int[] bottom = new int[width]; 
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if(shape[y][x] == 1) {
					if((y + 1) == height)
						bottom[x] = height;
					for(int k = y + 1; k < height; k++) {
						if(shape[k][x] == 0) {
							bottom[x] = k;
							break;
						} 
					}
				}
			}
		}
		
		return bottom;
	}
}
