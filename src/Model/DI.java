package Model;

import java.awt.Color;

public class DI {
	
	public static final int 
		width = 400, 
		height = 500,
		
		frameLocationX = 500,
		frameLocationY = 25,
		
		tetrisCellCountV = 20,
		tetrisCellCountH = 10,
				
		gridCellHeight = (int) (height * 0.05),
		gridCellWidth = (int) (width * 0.1),
	
		thread_sleep =  100;//1000/60;
	
	
	public static final Color 
		background = Color.BLACK,
		gridLine = Color.orange,
		shape = Color.red;
	
	public static final Point shapeStartPoint = new Point(tetrisCellCountH / 2, 0);
	public static final TetrisField field = new TetrisField();	
}
