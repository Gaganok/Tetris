package Tetris;

public class TetrisUpdater implements Updatable{
	
	private TetrisField field = DI.field;
	
	public TetrisUpdater() {
		
	}

	private void updateData() {
		//field.moveDown();
		DI.field.move(0, -1);
	}
	
	@Override
	public void update() {
		updateData();
	}

	
}
