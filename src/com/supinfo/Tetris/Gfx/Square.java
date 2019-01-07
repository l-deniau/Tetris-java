package com.supinfo.Tetris.Gfx;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Square {
	
	private Image square;
	private int posX;
	private int posY;
	
	public Square (String color)
	{
		try
		{
			if (color.equals("blue")) square = ImageIO.read(new File("images/blue.png"));
			if (color.equals("cyan")) square = ImageIO.read(new File("images/cyan.png"));
			if (color.equals("green")) square = ImageIO.read(new File("images/green.png"));
			if (color.equals("orange")) square = ImageIO.read(new File("images/orange.png"));
			if (color.equals("purple")) square = ImageIO.read(new File("images/purple.png"));
			if (color.equals("red")) square = ImageIO.read(new File("images/red.png"));
			if (color.equals("yellow")) square = ImageIO.read(new File("images/yellow.png"));
		}
		
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}

	//--Getter / Setter--//
	
	public Image getSquare() 
	{
		return square;
	}

	public void setSquare(Image square) 
	{
		this.square = square;
	}

	public int getPosX() 
	{
		return posX;
	}

	public void setPosX(int posX) 
	{
		this.posX = posX;
	}

	public int getPosY() 
	{
		return posY;
	}

	public void setPosY(int posY) 
	{
		this.posY = posY;
	}

}
