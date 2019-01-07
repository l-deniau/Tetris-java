package com.supinfo.Tetris.Launcher;

import javax.swing.SwingUtilities;

import com.supinfo.Tetris.GUI.TetrisFrame;

public class Launcher {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				
				TetrisFrame tf = new TetrisFrame();
				tf.setVisible(true);
				
				}
		});
			
	}

}
