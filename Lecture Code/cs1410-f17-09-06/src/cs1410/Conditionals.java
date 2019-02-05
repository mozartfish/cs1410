package cs1410;

/**
 * Lecture examples for CS 1410
 * 
 * @author Joe Zachary
 */
public class Conditionals
{
    /**
     * Some manual tests of the methods in this class
     */
    public static void main (String[] args)
    {
        System.out.println(max(8, 12));
        System.out.println(max(4, 2, 7));
        System.out.println(squareRootOfAbsoluteValue(64.0));
        System.out.println(squareRootOfAbsoluteValue(-16.0));
    }

    /**
     * Returns the larger of x and y
     */
    public static int max (int x, int y)
    {
        if (x > y)
        {
            return x;
        }
        else
        {
            return y;
        }
    }

    /**
     * Returns the largest of x, y, and z
     */
    public static int max (int x, int y, int z)
    {
        if (x > y && x > z)
        {
            return x;
        }
        else if (y > z)
        {
            return y;
        }
        else
        {
            return z;
        }
    }
    
    /**
     * Returns the square root of the absolute value of x.
     */
    public static double squareRootOfAbsoluteValue (double x)
    {
        if (x < 0)
        {
            x = -x;
        }
        return Math.sqrt(x);
    }
}
