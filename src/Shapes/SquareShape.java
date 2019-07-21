package Shapes;

public class SquareShape extends Shape{
	
	public SquareShape() {super(2, 2);}
	
	@Override
	protected void initShape() {
		for(int i = 0; i < shape.length; i++) 
			for(int j = 0; j < shape[0].length; j++)
				shape[i][j] = 1;
	}
}
