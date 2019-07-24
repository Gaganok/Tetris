package View;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Model.DI;

public class TetrisCanvas extends Canvas{

	public TetrisCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		
		setBackground(DI.background);
		
		setFocusable(true);
		requestFocus();
		requestFocusInWindow();
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//renderer.refresh();
				System.out.println(e.getX() +  ", " + e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
			    switch( keyCode ) { 
			        case KeyEvent.VK_UP:
			            // handle up 
			        	DI.field.rotate();
			            break;
			        case KeyEvent.VK_DOWN:
			        	DI.field.moveDown();
			            break;
			        case KeyEvent.VK_LEFT:
			            DI.field.moveSide(-1);
			            break;
			        case KeyEvent.VK_RIGHT :
			        	//DI.field.moveSide(1);
			        	DI.field.moveSide(1);
			            break;
			     }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	
	public void init() {
		createBufferStrategy(3);
	}
	
}
