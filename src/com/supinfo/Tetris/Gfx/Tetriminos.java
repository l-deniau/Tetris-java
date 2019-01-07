package com.supinfo.Tetris.Gfx;

import java.util.ArrayList;
import java.util.List;

public abstract class Tetriminos {
	
	protected List<Square> squareList = new ArrayList <Square>();

	//The Position number
	protected int pNbr = 0;
	
	public Tetriminos(String color)
	{
		for (int i = 0; i < 4; i++) squareList.add(new Square(color));
	}
	
	public abstract void pivoter(boolean[][] collision);
	
	public abstract void moveLeft(boolean[][] collision);
	
	public abstract void moveRight(boolean[][] collision);

	public abstract boolean canFall(boolean[][] collision);
	
	public void fall()
	{
		for (Square s : squareList)
		{
			s.setPosY(s.getPosY()+30);
		}
	}
	
	public void setCollision (boolean [][] collision)
	{
		for (Square s : squareList)
		{
			collision[s.getPosX()/30][s.getPosY()/30] = true;
		}
	}

	//--Getter / Setter--//
	
	public List<Square> getSquareList() 
	{
		return squareList;
	}

	public void setSquareList(List<Square> squareList) 
	{
		this.squareList = squareList;
	}

}
