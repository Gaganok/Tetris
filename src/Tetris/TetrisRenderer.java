package Tetris;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.List;

public class TetrisRenderer implements Updatable {
	
	private final int width, height;
	private BufferStrategy bs;
	private TetrisField field = DI.field;

	public TetrisRenderer(BufferStrategy bs, int width, int height) {
		this.bs = bs;
		
		this.width = width;
		this.height = height;
	}

	private void render() {
		Graphics g = bs.getDrawGraphics();
		
		fillBackground(g);
		
		drawField(g);
		drawGrid(g);
		
		
		g.dispose();
		bs.show();
	}
	
	private void fillBackground(Graphics g) {
		g.setColor(DI.background);
		g.fillRect(0, 0, width, height);
	}
	
	public void refresh() {
		
	}
	
	private void drawGrid(Graphics g) {
		g.setColor(DI.gridLine);
		
		for(int i = 1; i <= 10; i++ ) 
			g.drawLine(i * DI.gridCellWidth, 0, i * DI.gridCellWidth, height);
		
		for(int i = 1; i <= 20; i++) 
			g.drawLine(0, i * DI.gridCellHeight, width, i * DI.gridCellHeight);
	}
	
	private void drawField(Graphics g) {
		g.setColor(DI.shape);
		field.getShapePoints().forEach(p -> 
			g.fillRect(p.x * DI.gridCellWidth, p.y * DI.gridCellHeight, DI.gridCellWidth, DI.gridCellHeight)
		);
	}

	@Override
	public void update() {
		render();
	}
}
