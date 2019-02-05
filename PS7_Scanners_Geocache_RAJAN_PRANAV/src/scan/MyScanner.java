package scan;

import java.util.NoSuchElementException;
import java.util.InputMismatchException;

public class MyScanner
{
    /**
     * represents all the tokens that comprise the user input
     */
    private String[] tokens;
    /**
     * represents the current token
     */
    private int index;

    // Constructor
    /**
     * takes a string as a parameter and creates a new object which contains the tokens that comprise the input
     * @param userInput
     */

    public MyScanner (String userInput)
    {
        index = 0;
        tokens = userInput.trim().split("\\s+");
    }

    /**
     * this method checks whether there is still a token in the string
     */
    public boolean hasNext ()
    {
        return (tokens.length != 0 && tokens.length != index);
    }

    /**
     * this method returns the next token in the string if there is a next token
     */
    public String next ()
    {
        if (hasNext())
        {
            // return the token at index and then increment index by 1
            return tokens[index++];
        }
        throw new NoSuchElementException();
    }

    /**
     * this method checks whether the next token is an int
     * 
     * @return
     */
    public boolean hasNextInt ()
    {
        // check to make sure that there is still a token in string
        if (hasNext())
        {
            try
            {
                // convert the token to an int to see if the next token is an integer
                Integer.parseInt(tokens[index]);
            }
            // if an exception is thrown return false
            catch (NumberFormatException e)
            {
                return false;
            }
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * this method returns the next token in the string if it is an int
     */
    public int nextInt ()
    {
        if (hasNextInt())
        {
            // return the token at index and then increment index by 1
            return Integer.parseInt(tokens[index++]);
        }
        else if (!hasNext())
        {
            throw new NoSuchElementException();
        }
        else
        {
            throw new InputMismatchException();
        }

    }

}
