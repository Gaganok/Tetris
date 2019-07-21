package Shapes;

public class BigTShape extends Shape {
	
	public BigTShape() {
		super(3, 3);
	}

	@Override
	protected void initShape() {
		shape[0][0] = 1;
		shape[1][0] = 1;
		shape[2][0] = 1;
		
		shape[1][1] = 1;
		shape[1][2] = 1;
	}
}
