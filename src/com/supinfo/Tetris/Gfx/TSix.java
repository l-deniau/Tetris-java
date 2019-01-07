package com.supinfo.Tetris.Gfx;


public class TSix extends Tetriminos {

	public TSix() 
	{
		super("yellow");
		squareList.get(0).setPosY(-30); squareList.get(0).setPosX(150);
		squareList.get(1).setPosY(0); squareList.get(1).setPosX(120);
		squareList.get(2).setPosY(-30); squareList.get(2).setPosX(120);
		squareList.get(3).setPosY(0); squareList.get(3).setPosX(150);
	}

	public void pivoter(boolean[][] collision) {}

	public boolean canFall(boolean [][] collision) 
	{
		if (squareList.get(1).getPosY() < 540
			&& !collision [squareList.get(1).getPosX()/30][(squareList.get(1).getPosY()+30)/30]
			&& !collision [squareList.get(3).getPosX()/30][(squareList.get(3).getPosY()+30)/30]) 
			
			return true;
		
		else return false;
	}
	
	public void moveLeft(boolean[][] collision) 
	{
		if (squareList.get(2).getPosX() >= 30
			&& !collision [(squareList.get(2).getPosX()-30)/30][(squareList.get(2).getPosY())/30]
			&& !collision [(squareList.get(1).getPosX()-30)/30][(squareList.get(1).getPosY())/30])
		{
			for (Square s : squareList)
			{
				s.setPosX(s.getPosX()-30);
			}
		}
	}

	public void moveRight(boolean[][] collision) 
	{
		if (squareList.get(3).getPosX() <= 240
			&& !collision [(squareList.get(0).getPosX()+30)/30][(squareList.get(0).getPosY())/30]
			&& !collision [(squareList.get(3).getPosX()+30)/30][(squareList.get(3).getPosY())/30])
		{
			for (Square s : squareList)
			{
				s.setPosX(s.getPosX()+30);
			}
		}
	}

}
