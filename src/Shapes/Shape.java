package Shapes;

import java.util.Arrays;

import Model.DI;
import Model.Point;

public abstract class Shape {

	public int width, height;
	public int[][] shape;
	public int[] bottom, leftSide, rightSide;

	public Point position;

	private int[][] nextRotate;


	public Shape(int width, int height, String shapeString) {
		this.position = new Point(DI.shapeStartPoint);
		this.width = width;
		this.height = height;
		shape = new int[width][height];	
		initShape(shapeString);
		sidesInit();
	}

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
		sidesInit();
	}

	private void initShape(String shapeString) {
		for (int x = 0; x < width; x++) 
			for (int y = 0; y < height; y++) 
				if(shapeString.charAt(x + y * width) == '1')
					this.shape[x][y] = 1;
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

	private void bottomInit() {
		bottom = new int[width]; 

		for (int x = 0; x < width; x++)
			for (int y = height - 1; y >= 0; y--) 
				if(shape[x][y] == 1) {
					bottom[x] = y + 1;
					break;
				}
	}

	private void leftSideInit() {
		leftSide = new int[height]; 

		for (int y = 0; y < height; y++) 
			for (int x = 0; x < width; x++)
				if(shape[x][y] == 1) {
					leftSide[y] = x - 1;
					break;
				}
	}

	private void rightSideInit() {
		rightSide = new int[height]; 

		for (int y = 0; y < height; y++) 
			for (int x = width - 1; x >= 0; x--)
				if(shape[x][y] == 1) {
					rightSide[y] = x + 1;
					break;
				}
	}

	private void sidesInit() {
		bottomInit();
		leftSideInit();
		rightSideInit();
	}

	public void rotate() {
		rotateInit();
		shape = nextRotate.clone();
		int temp = height;
		height = width;
		width = temp;
		sidesInit();
		nextRotate = null;
	}

	private void rotateInit() {
		if(nextRotate == null) {
			nextRotate = new int[height][width];

			for (int x = 0; x < width; x++)
				for (int y = 0; y < height; y++)
					nextRotate[y][x] = shape[x][y]; 

			int temp;

			for (int y = 0; y < width; y++) {
				for (int x = 0; x < height / 2; x++) {
					temp = nextRotate[x][y];
					nextRotate[x][y] = nextRotate[height - 1 - x][y];
					nextRotate[height - 1 - x][y] = temp;
				}
			}
		}
	}

	public int rotatePoint(int x1, int y1) {
		rotateInit();
		return nextRotate[x1][y1];
	}

}
