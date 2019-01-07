package com.supinfo.Tetris.Gfx;


public class TTwo extends Tetriminos {

	public TTwo() 
	{
		super("green");
		squareList.get(0).setPosY(-30); squareList.get(0).setPosX(150);
		squareList.get(1).setPosY(0); squareList.get(1).setPosX(120);
		squareList.get(2).setPosY(-30); squareList.get(2).setPosX(120);
		squareList.get(3).setPosY(0); squareList.get(3).setPosX(90);
	}

	public void pivoter(boolean[][] collision) 
	{
		pNbr++;
		int posXr = squareList.get(2).getPosX();
		int posYr = squareList.get(2).getPosY();
		
		try
		{
			switch (pNbr%2)
			{
				case 0:
					if (squareList.get(0).getPosX() >= 30
						&& !collision [(posXr)/30][(posYr+30)/30]
						&& !collision [(posXr-30)/30][(posYr+30)/30])
					{
						squareList.get(0).setPosY(posYr); squareList.get(0).setPosX(posXr+30);
						squareList.get(1).setPosY(posYr+30); squareList.get(1).setPosX(posXr);
						squareList.get(2).setPosY(posYr); squareList.get(2).setPosX(posXr);
						squareList.get(3).setPosY(posYr+30); squareList.get(3).setPosX(posXr-30);
						break;
					}
					else
					{
						pNbr--; break;
					}
					
				case 1:
					if (squareList.get(3).getPosY() <= 510
						&& !collision [(posXr)/30][(posYr-30)/30]
						&& !collision [(posXr+30)/30][(posYr+30)/30])
					{
						squareList.get(0).setPosY(posYr-30); squareList.get(0).setPosX(posXr);
						squareList.get(1).setPosY(posYr); squareList.get(1).setPosX(posXr+30);
						squareList.get(2).setPosY(posYr); squareList.get(2).setPosX(posXr);
						squareList.get(3).setPosY(posYr+30); squareList.get(3).setPosX(posXr+30);
						break;
					}
					else
					{
						pNbr--; break;
					}
			}
		}
		
		catch (ArrayIndexOutOfBoundsException e)
		{
			pNbr--;
		}
	}

	public boolean canFall(boolean [][] collision) 
	{
		switch (pNbr%2)
		{
		case 0 : 
			if (squareList.get(1).getPosY() < 540
				&& !collision [squareList.get(0).getPosX()/30][(squareList.get(0).getPosY()+30)/30]
				&& !collision [squareList.get(3).getPosX()/30][(squareList.get(3).getPosY()+30)/30]
				&& !collision [squareList.get(1).getPosX()/30][(squareList.get(1).getPosY()+30)/30]) 
				
				return true;
			
			else return false;
			
		case 1 : 
			if (squareList.get(3).getPosY() < 540
				&& !collision [squareList.get(2).getPosX()/30][(squareList.get(2).getPosY()+30)/30]
				&& !collision [squareList.get(3).getPosX()/30][(squareList.get(3).getPosY()+30)/30]) 
				
				return true; 
			
			else return false;
			
			default : return false;
		}
	}

	public void moveLeft(boolean[][] collision) 
	{
		switch (pNbr%2)
		{
			case 0 :
				if (squareList.get(3).getPosX() >= 30
					&& !collision [(squareList.get(3).getPosX()-30)/30][(squareList.get(3).getPosY())/30]
					&& !collision [(squareList.get(2).getPosX()-30)/30][(squareList.get(2).getPosY())/30])
				{
					for (Square s : squareList)
					{
						s.setPosX(s.getPosX()-30);
					}
				} break;
				
			case 1 : 
				if (squareList.get(0).getPosX() >= 30
					&& !collision [(squareList.get(0).getPosX()-30)/30][(squareList.get(0).getPosY())/30]
					&& !collision [(squareList.get(2).getPosX()-30)/30][(squareList.get(2).getPosY())/30]
					&& !collision [(squareList.get(3).getPosX()-30)/30][(squareList.get(3).getPosY())/30])
				{
					for (Square s : squareList)
					{
						s.setPosX(s.getPosX()-30);
					}
				} break;
		}
	}

	public void moveRight(boolean[][] collision) 
	{
		switch (pNbr%2)
		{
			case 0 :
				if (squareList.get(0).getPosX() <= 240
					&& !collision [(squareList.get(0).getPosX()+30)/30][(squareList.get(0).getPosY())/30]
					&& !collision [(squareList.get(1).getPosX()+30)/30][(squareList.get(1).getPosY())/30])
				{
					for (Square s : squareList)
					{
						s.setPosX(s.getPosX()+30);
					}
				} break;
				
			case 1 : 
				if (squareList.get(1).getPosX() <= 240
					&& !collision [(squareList.get(1).getPosX()+30)/30][(squareList.get(1).getPosY())/30]
					&& !collision [(squareList.get(0).getPosX()+30)/30][(squareList.get(0).getPosY())/30]
					&& !collision [(squareList.get(3).getPosX()+30)/30][(squareList.get(3).getPosY())/30])
				{
					for (Square s : squareList)
					{
						s.setPosX(s.getPosX()+30);
					}
				} break;
		}
	}

}
