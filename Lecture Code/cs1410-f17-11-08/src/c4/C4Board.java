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
    /** The number used to indicate Player 1 */
    public static final int P1 = 1;

    /** The number used to indicate Player 2 */
    public static final int P2 = 2;

    /** The number used to indicate a tie */
    public static final int TIE = 3;

    /**
     * The state of the board in the current game, where board[c][r] is the occupant of row r and column c. The occupant
     * will be either P1 (for Player 1), P2 (for Player 2), or 0 (for empty).
     */
    private int[][] board;

    /** The player (P1 or P2) whose turn it is to move, or 0 if the game is over. */
    private int toMove;

    /** The player (P1 or P2) who moved first in the current game. */
    private int wasFirstToMove;

    /** Number of wins by P1 */
    private int p1Wins;

    /** Number of wins by P2 */
    private int p2Wins;

    /** Number of ties */
    private int ties;

    /**
     * Creates a C4Board with the specified number of rows and columns. There should be no pieces on the board and it
     * should be player 1's turn to move.
     * 
     * However, if either rows or cols is less than four, throws an IllegalArgumentException.
     */
    public C4Board (int rows, int cols)
    {
        // Make sure parameters are sensible
        if (rows < 4 || cols < 4)
        {
            throw new IllegalArgumentException();
        }

        // Create the board (it will contain all zeroes), initialize the statistics,
        // and make player 1 the first to move.
        board = new int[cols][rows];
        p1Wins = p2Wins = ties = 0;
        wasFirstToMove = toMove = P1;
    }

    /**
     * Sets up the board to play a new game, whether or not the current game is complete. All of the pieces should be
     * removed from the board. The player who had the second move in the previous game should have the first move in the
     * new game.
     */
    public void newGame ()
    {
        // Determine who moves first
        wasFirstToMove = (wasFirstToMove == P1) ? P2 : P1;
        toMove = wasFirstToMove;

        // Zero out the board
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                board[i][j] = 0;
            }
        }
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
        // Make sure the parameter is in range
        if (col < 0 || col >= board.length)
        {
            throw new IllegalArgumentException();
        }

        // Record the move, as long as it is someone's turn to move
        if (toMove != 0)
        {
            int[] column = board[col];
            for (int i = 0; i < column.length; i++)
            {
                if (board[col][i] == 0)
                {
                    board[col][i] = toMove;
                    return update();
                }
            }
        }

        // Ignore the move since the game is not active or the column is full.
        return 0;
    }

    /**
     * Updates toMove and the win/tie statistics based on the outcome of the most recent move. As long as the game is
     * not over, flips toMove from its current value. If the game is over, zeroes toMove and increments the appropriate
     * statistic. Returns P1 if the move resulted in a win for player 1, returns P2 if the move resulted in a win for
     * player 2, returns TIE of the move resulted in a tie, and returns 0 otherwise.
     */
    private int update ()
    {
        if (toMove == P1 && FourInARow.fourInRow(board))
        {
            p1Wins++;
            toMove = 0;
            return P1;
        }
        else if (FourInARow.fourInRow(board))
        {
            p2Wins++;
            toMove = 0;
            return P2;
        }
        else if (isFull())
        {
            ties++;
            toMove = 0;
            return TIE;
        }
        else
        {
            toMove = (toMove == P1) ? P2 : P1;
            return 0;
        }
    }

    /**
     * Reports whether the position on the board is full, with no more room to moves to be made.
     */
    private boolean isFull ()
    {
        for (int col = 0; col < board.length; col++)
        {
            int rows = board[col].length;
            if (board[col][rows - 1] == 0)
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Reports the occupant of the board square at the specified row and column. If the row or column doesn't exist,
     * throws an IllegalArgumentException. If the square is unoccupied, returns 0. Otherwise, returns P1 (if the square
     * is occupied by player 1) or P2 (if the square is occupied by player 2).
     */
    public int getOccupant (int row, int col)
    {
        try
        {
            return board[col][row];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Reports whose turn it is to move. Returns P1 (if it is player 1's turn to move), P2 (if it is player 2's turn to
     * move, or 0 (if it is neither player's turn to move because the current game is over because of a win or tie).
     */
    public int getToMove ()
    {
        return toMove;
    }

    /**
     * Reports how many games have been won by player 1 since this board was created.
     */
    public int getWinsForP1 ()
    {
        return p1Wins;
    }

    /**
     * Reports how many games have been won by player 2 since this board was created.
     */
    public int getWinsForP2 ()
    {
        return p2Wins;
    }

    /**
     * Reports how many ties have occurred since this board was created.
     */
    public int getTies ()
    {
        return ties;
    }
}
