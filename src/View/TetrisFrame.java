package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;

import Service.TetrisLooper;
import Service.TetrisUpdater;
import Service.Updatable;

public class TetrisFrame extends JFrame{

	private final int width, height;
	private TetrisCanvas canvas;

	public TetrisFrame(int width, int height) {
		setResizable(false);
		Dimension size = new Dimension(width, height);
		//setPreferredSize(size);
		//setMinimumSize(size);
		//setSize(size);
		getContentPane().setPreferredSize(size);

		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//setLocationByPlatform(true);
		this.setLocation(500, 10);
		setLayout(new FlowLayout());

		this.width = width;
		this.height = height;

		canvas = new TetrisCanvas(width, height);	
	
		add(canvas);
		pack();

		setVisible(true);
	}


	public void start() {
		canvas.init();

		List<Updatable> updatableList = new ArrayList<Updatable>(Arrays.asList(
				new TetrisRenderer(canvas.getBufferStrategy(), width, height),
				new TetrisUpdater()));

		new Thread(new TetrisLooper(updatableList)).start();
	}
}
