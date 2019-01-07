package com.supinfo.Tetris.Engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import com.supinfo.Tetris.GUI.GameBoard;
import com.supinfo.Tetris.GUI.InfoBoard;
import com.supinfo.Tetris.GUI.TetrisFrame;
import com.supinfo.Tetris.Gfx.Square;
import com.supinfo.Tetris.Gfx.TFive;
import com.supinfo.Tetris.Gfx.TFour;
import com.supinfo.Tetris.Gfx.TOne;
import com.supinfo.Tetris.Gfx.TSeven;
import com.supinfo.Tetris.Gfx.TSix;
import com.supinfo.Tetris.Gfx.TThree;
import com.supinfo.Tetris.Gfx.TTwo;
import com.supinfo.Tetris.Gfx.Tetriminos;

public class TetrisCore extends Thread {

	private TetrisFrame tf;
	private GameBoard gb;
	private InfoBoard ib;
	
	private long speed = 200;
	private Random random = new Random();
	private int r = (random.nextInt(7)+1), i=(-1);
	
	private List <Tetriminos> tetriminos = new ArrayList<Tetriminos>();
	private boolean [][] collision = new boolean [10][19];
	
	private boolean lockControl = true;

	public TetrisCore(TetrisFrame tf, GameBoard gb, InfoBoard ib)
	{
		this.tf=tf;
		this.gb=gb;
		this.ib=ib;
		
		//set collision
		for (int y=0; y<19; y++)
		{
			for (int x=0; x<10; x++)
			{
				collision [x][y] = false;
			}
		}
	}
	
	public void run ()
	{
		while (true)
		{
			//Create new Tetrominos
			newTetriminos(r);
			lockControl = false;
			gb.repaint();
			
			//Game Over Detection
			if (!tetriminos.get(i).canFall(collision))
			{
				JOptionPane.showMessageDialog(tf, "Vous avez perdu !!!  Score : "+ib.getScore(), "- Tetris -", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
			
			//set ImagePreview
			r = (random.nextInt(7)+1);
			ib.setImagePreview(r);
			
			//Loop of the fall
			while(tetriminos.get(i).canFall(collision))
			{
				tetriminos.get(i).fall();
				gb.repaint();
				
				try {sleep(this.speed);} 
				catch (InterruptedException e) {e.printStackTrace();}
			}
			
			lockControl = true;
			
			//Update Collision tab
			tetriminos.get(i).setCollision(collision);
			
			//Check and Erase Complete Line
			eraseLine();
		}
	}

	private void newTetriminos (int r)
	{
		switch (r)
		{
			case 1 : tetriminos.add(new TOne()); break;
			case 2 : tetriminos.add(new TTwo()); break;
			case 3 : tetriminos.add(new TThree()); break;
			case 4 : tetriminos.add(new TFour()); break;
			case 5 : tetriminos.add(new TFive()); break;
			case 6 : tetriminos.add(new TSix()); break;
			case 7 : tetriminos.add(new TSeven()); break;
		}
		
		//Update the current Tetrominos
		i++;
		
		//Export Square of the Tetrominos to the SquareList
		for(Square s : tetriminos.get(i).getSquareList())
		{
			gb.getSquareList().add(s);
		}
	}
	
	private void eraseLine() 
	{
		int countSquare, countLine = 0, lastY = 0;
		
		//Loop to check each Line
		for (int y=0; y<19; y++)
		{
			countSquare = 0;
			
			//Loop to count the square in one Line
			for (int x=0; x<10; x++)
			{
				if (collision [x][y]) countSquare ++;
			}

			//Erase the Complete Line
			if (countSquare == 10)
			{
				for (int i = 0; i<gb.getSquareList().size(); i++)
				{
					if (gb.getSquareList().get(i).getPosY() == y*30)
					{
						gb.getSquareList().remove(i);
						gb.repaint();
						i--;
					}
				}
				
				//Update the collision tab
				for (int x=0; x<10; x++)
				{
					collision[x][y] = false;
				}
				
				lastY = y;
				
				countLine++;
			}
		}
		
		
		if(countLine > 0)
		{
			//Fall the square above the complete line
			for (Square s : gb.getSquareList())
			{
				if (s.getPosY() < lastY*30)
				{
					s.setPosY(s.getPosY()+(30*countLine));
				}
			}
			
			//Update (hard) the collision tab
			for (int j=0;j<19;j++)
			{
				for (int i=0;i<10;i++)
				{
					collision[i][j] = false;
				}
			}
			
			for (Square s : gb.getSquareList())
			{
				if (s.getPosX() < 300 && s.getPosY()<570)
				{
					try
					{
						collision[s.getPosX()/30][s.getPosY()/30] = true;
					}
					catch (ArrayIndexOutOfBoundsException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		//Set the score
		switch (countLine)
		{
			case 1: ib.setScore(ib.getScore()+10); break;
			case 2: ib.setScore(ib.getScore()+30); break;	
			case 3: ib.setScore(ib.getScore()+50); break;
			case 4: ib.setScore(ib.getScore()+80); break;
		}
	}
	
	//--Getter / Setter --//
	
	public void setSpeed (long speed)
	{
		this.speed = speed;
	}
	
	public long getSpeed (long speed)
	{
		return this.speed;
	}
	
	public int getI()
	{
		return this.i;
	}
	
	public boolean[][] getCollision() 
	{
		return collision;
	}

	public void setCollision(boolean[][] collision) 
	{
		this.collision = collision;
	}
	
	public boolean isLockControl() 
	{
		return lockControl;
	}

	public void setLockControl(boolean lockControl) 
	{
		this.lockControl = lockControl;
	}
	
	public List<Tetriminos> getTetriminos() 
	{
		return tetriminos;
	}
	
}