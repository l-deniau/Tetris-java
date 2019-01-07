package com.supinfo.Tetris.GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InfoBoard extends JPanel {
	
	//Score
	int score = 0;
	JLabel label1 = new JLabel(" Score : ");
	JLabel labelScore = new JLabel (String.valueOf(score));
	
	//Image Preview
	JLabel label2 = new JLabel(" Pièce Suivante : ");
	Image myPicture;
	JLabel labelImage;
	
	//JPanel
	JPanel panel1 = new JPanel(new BorderLayout());
	JPanel panel2 = new JPanel(new BorderLayout());
	
	public InfoBoard ()
	{
		//Add JPanel
		setLayout(new BorderLayout());
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.SOUTH);
		
		//Score
		label1.setFont(new Font(Font.SERIF, Font.ITALIC, 25));
		labelScore.setFont(new Font(Font.MONOSPACED, 0, 25));
		panel1.add(label1, BorderLayout.NORTH);
		panel1.add(labelScore, BorderLayout.EAST);
		
		//Preview
		label2.setFont(new Font (Font.SERIF, 0, 13));
		panel2.add(label2, BorderLayout.NORTH);
		
		try 
		{
			myPicture = ImageIO.read(new File("images/preview1.png"));
			labelImage = new JLabel(new ImageIcon(myPicture));
		} 
		
		catch (IOException e) 
		{
			labelImage = new JLabel("- Error -");
			labelImage.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		}
		
		panel2.add(labelImage, BorderLayout.SOUTH);
	}
	
	//--Getter / Setter --//
	
	public void setImagePreview (int id)
	{
		try 
		{
			myPicture = ImageIO.read(new File("images/preview" + id + ".png"));
			labelImage.setIcon(new ImageIcon(myPicture));
		}
			
		catch (IOException e) 
		{
			labelImage = new JLabel("- Error -");
			labelImage.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		}
	}
	
	public int getScore() 
	{
		return score;
	}

	public void setScore(int score) 
	{
		this.score = score;
		labelScore.setText(String.valueOf(score));
	}

}
