package com.supinfo.Tetris.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.supinfo.Tetris.Gfx.Square;

@SuppressWarnings("serial")
public class GameBoard extends JPanel {
	
	private List<Square> squareList = new ArrayList<Square>();
	
	public void paintComponent(Graphics g)
	{
		//Draw Background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 300, 570);
		
		//Draw Square
		for (Square s : squareList)
		{
			g.drawImage(s.getSquare(), s.getPosX(), s.getPosY(), this);
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
