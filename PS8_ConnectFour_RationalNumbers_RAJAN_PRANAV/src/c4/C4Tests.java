package c4;

import static org.junit.Assert.*;
import org.junit.Test;

public class C4Tests
{
    // Test for invalid row argument
    @Test(expected = IllegalArgumentException.class)
    public void testForInvalidRowArgument ()
    {
        C4Board C = new C4Board(3, 5);
        assertEquals(5, C.getWinsForP2());
    }

    // Test for invalid column argument
    @Test(expected = IllegalArgumentException.class)
    public void testForInvalidColumnArgument ()
    {
        C4Board C = new C4Board(5, 3);
        assertEquals(5, C.getWinsForP2());
    }

    // Test for valid row and column arguments
    @Test
    public void testForValidRowAndColumnArguments ()
    {
        C4Board C = new C4Board(6, 7);
        assertEquals(0, C.getOccupant(0, 0));
    }

    // Test a 5x5 board that satisfies constructor requirements
    @Test
    public void TestConstructor ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Check that all the positions in the board are set to empty
        assertEquals(0, c.getOccupant(0, 0));
        assertEquals(0, c.getOccupant(0, 1));
        assertEquals(0, c.getOccupant(0, 2));
        assertEquals(0, c.getOccupant(0, 3));
        assertEquals(0, c.getOccupant(0, 4));
        assertEquals(0, c.getOccupant(1, 0));
        assertEquals(0, c.getOccupant(1, 1));
        assertEquals(0, c.getOccupant(1, 2));
        assertEquals(0, c.getOccupant(1, 3));
        assertEquals(0, c.getOccupant(1, 4));
        assertEquals(0, c.getOccupant(2, 0));
        assertEquals(0, c.getOccupant(2, 1));
        assertEquals(0, c.getOccupant(2, 2));
        assertEquals(0, c.getOccupant(2, 3));
        assertEquals(0, c.getOccupant(2, 4));
        assertEquals(0, c.getOccupant(3, 0));
        assertEquals(0, c.getOccupant(3, 1));
        assertEquals(0, c.getOccupant(3, 2));
        assertEquals(0, c.getOccupant(3, 3));
        assertEquals(0, c.getOccupant(3, 4));
        assertEquals(0, c.getOccupant(4, 0));
        assertEquals(0, c.getOccupant(4, 1));
        assertEquals(0, c.getOccupant(4, 2));
        assertEquals(0, c.getOccupant(4, 3));
        assertEquals(0, c.getOccupant(4, 4));

        // Check that the first player to move is Player 1
        assertEquals(1, c.getToMove());

        // Check that the number of ties is 0
        assertEquals(0, c.getTies());

        // Check that the number of wins for player 1 is 0
        assertEquals(0, c.getWinsForP1());

        // Check that the number of wins for player 2 is 0
        assertEquals(0, c.getWinsForP2());
    }

    // Test newGame() to make sure it satisfies the specifications of newGame()
    @Test
    public void TestnewGame ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Player 1 will move first and add a piece to the column
        assertEquals(0, c.moveTo(0));

        // Check that player one has added a piece to the position (0,0)
        assertEquals(1, c.getOccupant(0, 0));

        // Call the newGame() method
        c.newGame();

        // Check that all the positions in the board are set to empty
        assertEquals(0, c.getOccupant(0, 0));
        assertEquals(0, c.getOccupant(0, 1));
        assertEquals(0, c.getOccupant(0, 2));
        assertEquals(0, c.getOccupant(0, 3));
        assertEquals(0, c.getOccupant(0, 4));
        assertEquals(0, c.getOccupant(1, 0));
        assertEquals(0, c.getOccupant(1, 1));
        assertEquals(0, c.getOccupant(1, 2));
        assertEquals(0, c.getOccupant(1, 3));
        assertEquals(0, c.getOccupant(1, 4));
        assertEquals(0, c.getOccupant(2, 0));
        assertEquals(0, c.getOccupant(2, 1));
        assertEquals(0, c.getOccupant(2, 2));
        assertEquals(0, c.getOccupant(2, 3));
        assertEquals(0, c.getOccupant(2, 4));
        assertEquals(0, c.getOccupant(3, 0));
        assertEquals(0, c.getOccupant(3, 1));
        assertEquals(0, c.getOccupant(3, 2));
        assertEquals(0, c.getOccupant(3, 3));
        assertEquals(0, c.getOccupant(3, 4));
        assertEquals(0, c.getOccupant(4, 0));
        assertEquals(0, c.getOccupant(4, 1));
        assertEquals(0, c.getOccupant(4, 2));
        assertEquals(0, c.getOccupant(4, 3));
        assertEquals(0, c.getOccupant(4, 4));

        // Check that the first player to move is Player 2
        assertEquals(2, c.getToMove());

        // Check that the number of ties is 0
        assertEquals(0, c.getTies());

        // Check that the number of wins for player 1 is 0
        assertEquals(0, c.getWinsForP1());

        // Check that the number of wins for player 2 is 0
        assertEquals(0, c.getWinsForP2());
    }

    // Test a 5x5 board where Player 1 wins first and Player 1's wins increases to 1. Create a new game test that player
    // 2 wins and its number of wins increases to 1. Create a new game test that results in a tie and the number of ties
    // increases to 1
    @Test
    public void TestWinAndTie ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Player 1 will move first and adds a piece to the position(0,0)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves second and adds a piece to position (1,0)
        assertEquals(0, c.moveTo(1));

        // Player 1 moves next and adds a piece to position (0,1)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to position (2,0)
        assertEquals(0, c.moveTo(2));

        // Player 1 moves next and adds a piece to position (0,2)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to position (3,0)
        assertEquals(0, c.moveTo(3));

        // Player 1 moves next and adds a piece to position (0,3) and wins the game
        assertEquals(1, c.moveTo(0));

        // Check that the number of wins for Player 1 has increased to 1
        assertEquals(1, c.getWinsForP1());

        // Test whether or not column is filled or a tie or win has previously occurred is working properly
        assertEquals(0, c.moveTo(4));

        // Create a new game
        c.newGame();

        // Check that the first player to move is Player 2
        assertEquals(2, c.getToMove());

        // Player 2 will move first and adds a piece to the position(0,0)
        assertEquals(0, c.moveTo(0));

        // Player 1 moves second and adds a piece to position (0,1)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to position (1,0)
        assertEquals(0, c.moveTo(1));

        // Player 1 moves next and adds a piece to position (0,2)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to position (2,0)
        assertEquals(0, c.moveTo(2));

        // Player 1 moves next and adds a piece to position (0,3)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to position (3,0) and wins the game
        assertEquals(2, c.moveTo(3));

        // Check that the number of wins for Player 2 has increased to 1
        assertEquals(1, c.getWinsForP2());

        // Test whether or not column is filled or a tie or win has previously occurred is working properly
        assertEquals(0, c.moveTo(4));

        // Create a new game
        c.newGame();

        // Check that the first player to move is Player 1
        assertEquals(1, c.getToMove());

        // Player 1 moves first and adds a piece to (0,0)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to (0,1)
        assertEquals(0, c.moveTo(0));

        // Player 1 moves next and adds a piece to (0,2)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to (2,0)
        assertEquals(0, c.moveTo(2));

        // Player 1 moves next and adds a piece to (0,3)
        assertEquals(0, c.moveTo(0));

        // Player 2 moves next and adds a piece to (0,4)
        assertEquals(0, c.moveTo(0));

        // Player 1 moves next and adds a piece to (1,0)
        assertEquals(0, c.moveTo(1));

        // Player 2 moves next and adds a piece to (1,1)
        assertEquals(0, c.moveTo(1));

        // Player 1 moves next and adds a piece to (2,1)
        assertEquals(0, c.moveTo(2));

        // Player 2 moves next and adds a piece to (1,2)
        assertEquals(0, c.moveTo(1));

        // Player 1 moves next and adds a piece to (3,0)
        assertEquals(0, c.moveTo(3));

        // Player 2 moves next and adds a piece to (3,1)
        assertEquals(0, c.moveTo(3));

        // Player 1 moves next and adds a piece to (4,0)
        assertEquals(0, c.moveTo(4));

        // Player 2 moves next and adds a piece to (4,1)
        assertEquals(0, c.moveTo(4));

        // Player 1 moves next and adds a piece to (2,2)
        assertEquals(0, c.moveTo(2));

        // Player 2 moves next and adds a piece to (4,2)
        assertEquals(0, c.moveTo(4));

        // Player 1 moves next and adds a piece to (3,2)
        assertEquals(0, c.moveTo(3));

        // Player 2 moves next and adds a piece to (1,3)
        assertEquals(0, c.moveTo(1));

        // Player 1 moves next and adds a piece to (2,3)
        assertEquals(0, c.moveTo(2));

        // Player 2 moves next and adds a piece to (4,3)
        assertEquals(0, c.moveTo(4));

        // Player 1 moves next and adds a piece to (3,3)
        assertEquals(0, c.moveTo(3));

        // Player 2 moves next and adds a piece to (2,4)
        assertEquals(0, c.moveTo(2));

        // Player 1 moves next and adds a piece to (1,4)
        assertEquals(0, c.moveTo(1));

        // Player 2 moves next and adds a piece to (3,4)
        assertEquals(0, c.moveTo(3));

        // Player 1 moves next and adds a piece to (4,4) which results in a tie
        assertEquals(3, c.moveTo(4));

        // Test whether or not column is filled or a tie or win has previously occurred is working properly
        assertEquals(0, c.moveTo(4));

        // Check if the number of ties increases by 1.
        assertEquals(1, c.getTies());

        // Check that the number of times player 1 has won is the same
        assertEquals(1, c.getWinsForP1());

        // Check that the number of times player 2 has won is the same
        assertEquals(1, c.getWinsForP2());
    }

    // Test a 5x5 board that throws an IllegalArgumentException due to an incorrect column entry
    @Test(expected = IllegalArgumentException.class)
    public void TestIncorrectColumnArgument ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Player 1 moves first and adds a piece to (-1,0)
        assertEquals(0, c.moveTo(-1));

        // Player 2 moves next and adds a piece to (6,0)
        assertEquals(0, c.moveTo(6));
    }

    // Test a 5x5 board that throws an IllegalArgumentException for the getOccupant() method
    @Test(expected = IllegalArgumentException.class)
    public void TestGetOccupantExceptions ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Test Column Exceptions
        assertEquals(1, c.getOccupant(-1, 3));
        assertEquals(1, c.getOccupant(6, 3));

        // Test Row Exceptions
        assertEquals(2, c.getOccupant(2, -5));
        assertEquals(2, c.getOccupant(2, 6));
    }

    // Test a 5x5 board that uses getOccupant() method correctly
    @Test
    public void TestGetOccupant ()
    {
        // Create a new 5x5 board
        C4Board c = new C4Board(5, 5);

        // Test position (4,0) which should be empty
        assertEquals(0, c.getOccupant(0, 4));
    }

}
