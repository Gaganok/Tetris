package Tetris;

import java.util.List;

public class TetrisLooper implements Runnable{
	
	private List<Updatable> updatableList;
	
	public TetrisLooper(List<Updatable> updatableList) {
		this.updatableList = updatableList;
	}
	
	@Override
	public void run() {
		while(true) {
			updatableList.forEach(u -> u.update());
			try {
				Thread.sleep(DI.thread_sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
