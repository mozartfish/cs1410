package c4;

/**
 * Represents the state of a Connect Four board on which multiple games can be played. The board consists of a
 * rectangular grid of squares in which playing pieces can be placed. Squares are identified by their zero-based row and
 * column numbers, where rows are numbered starting with the bottom row, and columns are numbered by starting from the
 * leftmost column.
 * 
 * Multiple games can be played on a single board. Whenever a new game begins, the board is empty. There are two
 * players, identified by the constants P1 (Player 1) and P2 (Player 2). P1 moves first in the first game, P2 moves
 * first in the second game, and so on in alternating fashion.
 * 
 * A C4Board also keeps track of the outcomes of the games that have been played on it. It tracks the number of wins by
 * P1, the number of wins by P2, and the number of ties. It does not track games that were abandoned before being
 * completed.
 */
public class C4Board
{
    /** The number used to indicate an empty position in the board */
    public static final int EMPTY = 0;

    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;

    /** The number used to indicate a tie */
    public static final int TIE = 3;

    /** The number of rows specified by the user */
    private int boardRows;

    /** The number of columns specified by the user */
    private int boardColumns;

    /** Represents a C4 board */
    private int[][] C4Board;

    /** Represents which player's turn it is */
    private int playerTurn;

    /** Variable used to keep track of the row index */
    private int[] rowPositionIndex;

    /** Variable used to keep track of the number of rows for the checkTie method */
    private static int rowVals;

    /** Variable used to keep track of if a win or a tie has occurred */
    private boolean hasWonOrTied;

    /** Variable used to keep track of the number of ties that have occurred */
    private int numTies;

    /** Variable used to keep track of the number of times player 1 has won */
    private int numP1Wins;

    /** Variable used to keep track of the number of times player 2 has won */
    private int numP2Wins;

    /** Variable used to keep track of which player goes first */
    private int firstPlayer;

    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        if (rows < 4 || cols < 4)
        {
            throw new IllegalArgumentException();
        }

        boardRows = rows;
        boardColumns = cols;
        rowVals = rows;

        // create a new C4Board

        C4Board = new int[boardRows][boardColumns];

        // initialize the board to empty

        for (int i = 0; i < boardRows; ++i)
        {
            for (int j = 0; j < boardColumns; ++j)
            {
                C4Board[i][j] = EMPTY;
            }
        }

        // set the first player
        firstPlayer = P1;

        // set the player turn
        playerTurn = firstPlayer;

        // System.out.println(playerTurn);

        // initialize the row position index
        rowPositionIndex = new int[boardColumns];

        // initialize whether a person has won or tied
        hasWonOrTied = false;

        // initialize the number of ties
        numTies = 0;

        // initialize the number of times player 1 has won
        numP1Wins = 0;

        // initialize the number of times player 2 has won
        numP2Wins = 0;
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
        // initialize the board to empty
        for (int i = 0; i < boardRows; ++i)
        {
            for (int j = 0; j < boardColumns; ++j)
            {
                C4Board[i][j] = EMPTY;
            }
        }

        // check which player went first in the previous game and set the player who had the second move in the previous
        // game to the first player

        // check to make sure that the value of first player is P1 before changing who the first player is
        // System.out.println(firstPlayer);

        firstPlayer = changePlayerTurn(firstPlayer);
        playerTurn = firstPlayer;

        // check to make sure that the value of first player is P2 after changing who the first player is
        // System.out.println(firstPlayer);

        // initialize the row position index
        rowPositionIndex = new int[boardColumns];

        // initialize whether a person has won or tied
        hasWonOrTied = false;
    }

    /**
     * Records a move, by the player whose turn it is to move, in the first open square in the specified column. Returns
     * P1 if the move resulted in a win for player 1, returns P2 if the move resulted in a win for player 2, returns TIE
     * if the move resulted in a tie, and returns 0 otherwise.
     * 
     * If the column is full, or if the game is over because a win or a tie has previously occurred, does nothing but
     * return zero.
     * 
     * If a non-existent column is specified, throws an IllegalArgumentException.
     */
    public int moveTo (int col)
    {
        // check for non-existent column
        if (col < 0 || col >= boardColumns)
        {
            throw new IllegalArgumentException();
        }

        // create an array which can keep track of the row index
        int row = rowPositionIndex[col];

        // check if the current row is filled or if a win or tie already occurred in the previous game
        if ((row >= boardRows) || (hasWonOrTied == true))
        {
            return 0;
        }

        // record the location where the current player put their piece
        C4Board[row][col] = playerTurn;

        // increment the rowIndex
        rowPositionIndex[col]++;

        // check if the move results in a win for the current player
        if (FourInARow.fourInRow(C4Board))
        {
            hasWonOrTied = true;
            if (playerTurn == P1)
            {
                ++numP1Wins;
            }

            else
            {
                ++numP2Wins;
            }

            return playerTurn;
        }

        // Check if a tie occurred
        if (checkTie(rowPositionIndex) == true)
        {
            hasWonOrTied = true;
            ++numTies;
            return TIE;
        }

        // set playerTurn to the next player whose turn it is
        playerTurn = changePlayerTurn(playerTurn);

        return 0;
    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        // check to see if there is a non-existent column
        if ((col < 0) || (col >= boardColumns) || (row < 0) || (row >= boardRows))
        {
            throw new IllegalArgumentException();
        }

        return C4Board[row][col];
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
        return playerTurn;
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return numP1Wins;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return numP2Wins;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return numTies;
    }

    /**
     * Helper method to check which player went first in the previous game / determines which player's move it is
     */
    private static int changePlayerTurn (int currentFirstPlayer)
    {
        if (currentFirstPlayer == P1)
        {
            currentFirstPlayer = P2;
        }
        else if (currentFirstPlayer == P2)
        {
            currentFirstPlayer = P1;
        }

        return currentFirstPlayer;
    }

    /**
     * Helper method to check for a tie
     */
    private static boolean checkTie (int[] a)
    {
        boolean checkTie = true;
        for (int i : a)
        {
            if (i != rowVals)
            {
                checkTie = false;
                break;
            }
        }
        return checkTie;
    }
}
