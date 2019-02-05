package step1;

/**
 * Illustrates the use of a finally block.
 */
public class ExceptionDemo2
{
    public static void main (String[] args)
    {
        try
        {
            int n = convert1("5673");
            System.out.println(n);
            return;
        }
        catch (NumberFormatException e)
        {
            // When the exception occurs in convert3, control will transfer here
            // because there are no intervening exception handlers.
            System.out.println("Bad integer");
        }
        finally
        {
            System.out.println("All done!");
        }
    }

    public static int convert1 (String s)
    {
        int n = convert2(s);
        n = n * 4;
        return n;
    }

    public static int convert2 (String s)
    {
        int n = convert3(s);
        n = n * 3;
        return n;
    }

    /**
     * Returns the result of converting s into an int. If s can't be converted, 
     * throws a NumberFormatException.
     */
    public static int convert3 (String s)
    {
        int n = Integer.parseInt(s);
        n = n * 2;
        return n;
    }
}
