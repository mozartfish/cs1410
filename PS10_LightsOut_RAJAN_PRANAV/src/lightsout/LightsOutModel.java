package lightsout;

import java.util.Random;

public class LightsOutModel 
{
	/**
	 * Representation of a Lights Out board
	 */
	private int[][] board;
	
	/**
	 * Instance variable to represent Lights On
	 */
	private final int ON = 1;
	
	/**
	 * Instance variable to represent Lights Off
	 */
	private final int OFF = 0;
	
	/**
	 * Instance variable to check whether a person has selected manual mode
	 */
	private boolean manualMode;
	
	/**
	 * Instance variable to keep track of the number times a person has moved
	 */
	private int numMoves;

	public LightsOutModel(int rows, int cols) 
	{
		// Make sure parameters are sensible
        if (rows < 5 || cols < 5)
        {
            throw new IllegalArgumentException();
        }
        
        board = new int[rows][cols];
        manualMode = false;      
        
        newGame();
	}
	
	/**
	 * create a new game
	 */
	public void newGame()
	{
		// set all the lights off
		for (int i = 0; i < 5; i++) 
		{
			for (int j = 0; j < 5; j++) 
			{
				board[i][j] = OFF;
			}
		}

		// turn on various lights randomly and determine difficulty
		Random rand = new Random();

		for (int k = 0; k < 20; k++) 
		{
			OnOff(rand.nextInt(5), rand.nextInt(5));
		}
		numMoves = 0;

	}

    /**
     * Method that implements the rules of lights out. Changes the color of the square selected and the surrounding
     * squares
     * 
     * @param rows
     * @param cols
     */
	public void OnOff(int rows, int cols) 
	{
		if (manualMode)
		{
			changeLight(rows, cols);
		}
		else
		{
			changeLight(rows, cols);
			if (cols + 1 < 5)
			{
				changeLight(rows, cols + 1);
			}
			if (cols - 1 >=0 )
			{
				changeLight(rows, cols - 1);
			}
			if (rows + 1 < 5)
			{
				changeLight(rows + 1, cols);
			}
			if (rows - 1 >= 0)
			{
				changeLight(rows - 1, cols);
			}
			
			numMoves ++;
		}
		
		
	}
	
	/**
	 * Changes the color of the square based on the previous status of the square (on or off)
	 * @param row
	 * @param cols
	 */
	public void changeLight(int row, int cols) 
	{
		if (board[row][cols] == OFF) 
		{
			board[row][cols] = ON;
		} 
		else 
		{
			board[row][cols] = OFF;
		}

	}
	
	/**
	 * returns the occupant of a square
	 * @param row
	 * @param col
	 * @return
	 */
	public int getOccupant (int row, int col)
    {
        try
        {
            return board[row][col];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException();
        }
    }
	
	/**
	 * helper method to check whether the user has selected manual mode
	 * @return
	 */
	public boolean checkManualMode()
	{
		return manualMode;
	}
	
	/**
	 * Helper method to get the number of moves a player has made
	 * @return
	 */
	public int getMoves()
	{
		return numMoves;
	}
	
	/**
	 * Helper method to change manual mode
	 * @param b
	 */
	public void changeManualMode(boolean b)
	{
		manualMode = b;
	}
	

	/**
	 * Boolean method that checks whether all the lights have been turned off
	 * @return
	 */
	public boolean checkWin()
	{
		for (int i= 0; i < 5; i++)
		{
			for (int j = 0; j < 5; j++)
			{
				if (board[i][j] == ON)
				{
					return false;
				}
			}
		}
		return true;
	}

	
}
