package Shapes;

import java.util.Arrays;

import Model.DI;
import Model.Point;

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

		for (int x = 0; x < width; x++)
			for (int y = height - 1; y >= 0; y--) 
				if(shape[x][y] == 1) {
					bottom[x] = y + 1;
					break;
				}

		return bottom;
	}

	public int[] leftLine() {
		int[] left = new int[height]; 

		for (int y = 0; y < height; y++) 
			for (int x = 0; x < width; x++)
				if(shape[x][y] == 1) {
					left[y] = x - 1;
					break;
				}

		return left;
	}
	
	public int[] rightLine() {
		int[] right = new int[height]; 

		for (int y = 0; y < height; y++) 
			for (int x = width - 1; x >= 0; x--)
				if(shape[x][y] == 1) {
					right[y] = x + 1;
					break;
				}

		return right;
	}

	public void rotate() {
		int[][] rotatedShape = new int[height][width];
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				rotatedShape[y][x] = shape[x][y]; 
			}
		}
		
		int temp = height;
		height = width;
		width = temp;
		
		print();
		shape = rotatedShape;
		System.out.println();
		
	}
	
}
