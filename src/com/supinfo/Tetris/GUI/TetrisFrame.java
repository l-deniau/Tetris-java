package com.supinfo.Tetris.GUI;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.supinfo.Tetris.Engine.*;

@SuppressWarnings("serial")
public class TetrisFrame extends JFrame implements KeyListener {

	//JPanel
	TetrisCore tc;
	
	//Thread
	GameBoard gb = new GameBoard();
	InfoBoard ib = new InfoBoard();
	
	public TetrisFrame() 
	{
		//Set the parameters of the JFrame
		setTitle("- Tetris -");
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setFocusable(true);
		
		setBoundsCenter();
		buildContentPane();
		
		addKeyListener(this);
		
		//Create Threads
		tc = new TetrisCore (this, gb, ib);
		
		//Start Threads
		tc.start();
	}
	
	//Center the JFrame with the screen resolution
	private void setBoundsCenter ()
	{
		//Get the Screen Resolution
		int h = Toolkit.getDefaultToolkit().getScreenSize().height;
		int w = Toolkit.getDefaultToolkit().getScreenSize().width;

		//Set the Bounds of the JFrame
		getContentPane().setSize(400, 570);
		setBounds(w/2-203, h/2-299, 407, 599);
	}
	
	//Build JPanels
	private void buildContentPane()
	{
		//Absolute Positioning
		getContentPane().setLayout(null);
		
		//Set the Bounds of the JPanels
		gb.setBounds(0, 0, 300, 570);
		ib.setBounds(300, 0, 100, 570);
		
		//Add Panels to the JFrame
		getContentPane().add(gb);
		getContentPane().add(ib);
	}
	
	//--- KeyEvent ---
	
	public void keyPressed(KeyEvent e) 
	{
		//Move Left
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			tc.getTetriminos().get(tc.getI()).moveLeft(tc.getCollision());
			gb.repaint();
		}
		
		//Move Right
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			tc.getTetriminos().get(tc.getI()).moveRight(tc.getCollision());
			gb.repaint();
		}
		
		//Up Speed
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			if (tc.getTetriminos().get(tc.getI()).canFall(tc.getCollision()) && !tc.isLockControl())
			{
				tc.getTetriminos().get(tc.getI()).fall();
				tc.setSpeed(100);
				gb.repaint();
			}
		}
		
		//Rotation
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			tc.getTetriminos().get(tc.getI()).pivoter(tc.getCollision());
			gb.repaint();
		}
		
		//Hard Drop
		if (e.getKeyCode() == KeyEvent.VK_SHIFT)
		{
			while (tc.getTetriminos().get(tc.getI()).canFall(tc.getCollision()) && !tc.isLockControl())
			{
				tc.getTetriminos().get(tc.getI()).fall();
				gb.repaint();
			}
		}
	}
	
	public void keyReleased(KeyEvent e) 
	{
		//Normal Speed
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			tc.setSpeed(200);
		}
	}

	public void keyTyped(KeyEvent e) {}

}
