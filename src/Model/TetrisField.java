package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Shapes.BigTShape;
import Shapes.LShape;
import Shapes.Shape;
import Shapes.SquareShape;
import Shapes.TShape;
import Shapes.ZShape;

public class TetrisField {

	private int[][] field = new int[DI.tetrisCellCountH][DI.tetrisCellCountV];
	private Shape activeShape;

	private List<Class<? extends Shape>> shapeList;

	public TetrisField() {
		shapeList = new ArrayList<Class<? extends Shape>>(Arrays.asList(
				//new BigTShape(), new SquareShape(), new TShape(), new ZShape()));
				//BigTShape.class, SquareShape.class, ZShape.class, TShape.class));
				LShape.class));
		//new BigTShape(), new ZShape()));
		//TShape.class));
		newShape(new SquareShape());
	}

	public List<Point> getTakenPoints() {
		List<Point> shapeList = new ArrayList<Point>();

		for(int i = 0; i < field.length; i++) 
			for(int j = 0; j < field[0].length; j++) 
				if(field[i][j] != 0)
					shapeList.add(new Point(i, j));

		return shapeList;
	}

	private void addShape(Shape shape) {
		for (int i = 0; i < shape.width; i++) 
			for (int j = 0; j < shape.height; j++) 
				if(shape.shape[i][j] == 1)
					field[i + shape.position.x][j + shape.position.y] = shape.shape[i][j];
		activeShape = shape;
	}

	private void newShape(Shape shape) {
		try {
			addShape(shapeList.get((int) (Math.random() * shapeList.size())).newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private void removeShape(Shape shape) {
		for (int i = 0; i < shape.width; i++) 
			for (int j = 0; j < shape.height; j++) 
				if(shape.shape[i][j] == 1)
					field[i + shape.position.x][j + shape.position.y] = 0;
	}

	public void moveDown(){
		if(((activeShape.position.y + activeShape.height) == field[0].length) || isCollisionDown()) {
			//If Game Over resets the game
			if(activeShape.position.y == DI.shapeStartPoint.y)
				field = new int[DI.tetrisCellCountH][DI.tetrisCellCountV];
			else
				for (int i = 0; i < activeShape.height; i++)
					if(isLine(i + activeShape.position.y)) 
						removeLine(i + activeShape.position.y);
			
			newShape(new SquareShape());
		}else {
			removeShape(activeShape);
			activeShape.position.y += 1;
			addShape(activeShape);
		}
	}

	private void removeLine(int line) {
		//Remove Line
		for (int i = 0; i < field.length; i++) 
			field[i][line] = 0;

		//Move everything above one line down
		for(int i = line; i >= 0; i--) {
			for (int j = 0; j < field.length; j++) {
				if(field[j][i] == 1) {
					field[j][i + 1] = 1;
					field[j][i] = 0;
				}
			}
		}
	}

	public void moveSide(int offset) {
		if(activeShape.position.x + offset >= 0 && 
				(activeShape.position.x + offset + activeShape.width) <= field.length &&
				!isCollisionSide(offset)) {
			removeShape(activeShape);
			activeShape.position.x += offset;
			addShape(activeShape);
		}
	}

	private boolean isCollisionDown() {
		int[] bottom = activeShape.bottom;

		for(int i = 0; i < activeShape.width; i++) 
			if(field[activeShape.position.x + i][activeShape.position.y + bottom[i]] == 1) 
				return true;

		return false;
	}

	private boolean isCollisionSide(int offset) {
		int[] side = offset == 1 ? activeShape.rightSide : activeShape.leftSide;

		for(int i = 0; i < activeShape.height; i++)
			if(field[activeShape.position.x + side[i]][activeShape.position.y + i] == 1) 
				return true;

		return false;
	}

	private boolean isCollisionRotateBlock() {
		// Width and Height swapped assuming rotation
		int x, y;
		if(activeShape.width < activeShape.height) {
			x = activeShape.width;
			y = 0;
		} else {
			x = 0;
			y = activeShape.height;
		}

		for (; x < activeShape.height; x++) 
			for (int y1 = y; y1 < activeShape.width; y1++) {
				System.out.println("Blyat");
				if(field[activeShape.position.x + x][activeShape.position.y + y] == 1 && 
						activeShape.rotatePoint(x, y) == 1) 
					return true;
			}
		return false;
	}

	private boolean isCollisionRotateSides() {
		return (activeShape.position.x + activeShape.height > field.length ||
				(activeShape.position.y + activeShape.width) > field[0].length);
	}

	private boolean isLine(int y) {
		for (int i = 0; i < field.length; i++) 
			if(field[i][y] == 0)
				return false;
		return true;
	}

	public void rotate() {
		if(!(isCollisionRotateSides() || isCollisionRotateBlock())) {
			removeShape(activeShape);
			activeShape.rotate();
			addShape(activeShape);
		}
	}

}
