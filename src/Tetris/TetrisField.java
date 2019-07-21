package Tetris;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Shapes.BigTShape;
import Shapes.Shape;
import Shapes.SquareShape;
import Shapes.TShape;
import Shapes.ZShape;

public class TetrisField {

	private int[][] field = new int[DI.tetrisCellCountH][DI.tetrisCellCountV];
	private Shape activeShape;

	private List<Shape> shapeList;

	public TetrisField() {


		shapeList = new ArrayList<Shape>(Arrays.asList(
				new BigTShape(), new SquareShape(), new TShape(), new ZShape()));

		newShape(new SquareShape());
	}

	@Deprecated
	private void initField() {
		for(int i = 0; i < field.length; i++) 
			for(int j = 0; j < field[0].length; j++) 
				field[i][j] = 5;
	}

	public List<Point> getShapePoints() {
		List<Point> shapeList = new ArrayList<Point>();

		for(int i = 0; i < field.length; i++) 
			for(int j = 0; j < field[0].length; j++) 
				if(field[i][j] != 0)
					shapeList.add(new Point(i, j));

		return shapeList;
	}

	private void addShape(Shape shape) {
		for (int i = 0; i < shape.height; i++) 
			for (int j = 0; j < shape.width; j++) 
				if(shape.shape[i][j] == 1)
					field[j + shape.position.x][i + shape.position.y] = shape.shape[i][j];
		activeShape = shape;
	}

	private void newShape(Shape shape) {
		//addShape(shapeList.get((int) (Math.random() * shapeList.size())));
		addShape(new ZShape());
	}

	private void removeShape(Shape shape) {
		for (int i = 0; i < shape.shape.length; i++) 
			for (int j = 0; j < shape.shape[0].length; j++) 
				field[j + shape.position.x][i + shape.position.y] = 0;
	}

	private void moveDown(){
		if(((activeShape.position.y + activeShape.height) == field[0].length) || isCollision()) {
			for (int i = 0; i < activeShape.height; i++) 
				if(isLine(i + activeShape.position.y)) 
					removeLine(i + activeShape.position.y);
			newShape(new SquareShape());
		}else {
			removeShape(activeShape);
			activeShape.position.y += 1;
			addShape(activeShape);

			System.out.println("Moved Down");
		}
	}

	private void removeLine(int line) {
		for (int i = 0; i < field.length; i++) 
			field[i][line] = 0;

		for(int i = line; i >= 0; i--) {
			for (int j = 0; j < field.length; j++) {
				if(field[j][i] == 1) {
					field[j][i + 1] = 1;
					field[j][i] = 0;
				}
			}
		}
	}

	private boolean isLine(int y) {
		for (int i = 0; i < field.length; i++) 
			if(field[i][y] == 0)
				return false;
		return true;
	}

	private boolean isCollision() {

		int[] bottom = activeShape.bottomLane();

		for(int i = 0; i < activeShape.width; i++) {
			if(field[activeShape.position.x + i][activeShape.position.y + bottom[i]] == 1) {
				System.out.println("Collision");
				return true;

			}
		}

		return false;
	}

	private void moveSide(int offset) {
		if(activeShape.position.x + offset >= 0 && 
				(activeShape.position.x + offset + activeShape.width) <= field.length &&
				isMoveSideValid(offset)) {
			removeShape(activeShape);
			activeShape.position.x += offset;
			addShape(activeShape);

			System.out.println("Moved Side");
		}
	}

	public void move(int dir, int offset) {
		synchronized(this) {
			if(dir == 0) {
				moveDown();
			} else {
				moveSide(offset);
			}
		}
	}

	public boolean isMoveSideValid(int offset) {
		int x = offset == 1 ? activeShape.width - 1 : 0; 

		for (int i = 0; i < activeShape.height; i++) 
			if(activeShape.shape[i][x] == 1 && field[activeShape.position.x + offset + x][activeShape.position.y + i] == 1)
				return false;

		return true;
	}

}
