package Service;

import Model.DI;
import Model.TetrisField;

public class TetrisUpdater implements Updatable{
	
	private TetrisField field = DI.field;
	
	public TetrisUpdater() {
		
	}

	private void updateData() {
		//field.moveDown();
		DI.field.moveDown();
	}
	
	@Override
	public void update() {
		updateData();
	}

	
}
