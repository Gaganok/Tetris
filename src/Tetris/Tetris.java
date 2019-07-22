package Tetris;

import Model.DI;
import View.TetrisFrame;

public class Tetris {

	public static void main(String[] args) {
		TetrisFrame frame = new TetrisFrame(DI.width, DI.height);
		frame.start();
		//test();
	}
	
	private static void test() {
		DI.field.rotate();
		DI.field.rotate();
		DI.field.rotate();
		DI.field.rotate();
	}
}
