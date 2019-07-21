package Shapes;

import Model.Point;

public class ZShape extends Shape{
	
	public ZShape() {super(3, 2);}

	@Override
	protected void initShape() {
		shape[0][0] = 1;
		shape[1][0] = 1;
		shape[1][1] = 1;
		shape[2][1] = 1;
	}

}
