package Shapes;

public class TShape extends Shape {

	public TShape() {
		super(3, 2);
	}

	@Override
	protected void initShape() {
		shape[0][0] = 1;
		shape[1][0] = 1;
		shape[2][0] = 1;
		shape[1][1] = 1;
	}

}
