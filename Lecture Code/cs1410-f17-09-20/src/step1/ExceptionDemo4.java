package step1;

/**
 * Illustrates the throw statement.
 */
public class ExceptionDemo4
{
    public static void main (String[] args)
    {
        try
        {
            int n = convert1("x");
            System.out.println(n);
        }
        catch (NumberFormatException e)
        {
            // When the exception occurs in convert3, control will transfer here
            // because there are no intervening exception handlers.
            System.out.println(e.getMessage());
        }
        catch (RuntimeException e)
        {
            System.out.println(e.getMessage());
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
     * Returns the result of converting s into an int. If s is empty, throws a Runtime
     * Exception.  If s can't be converted, throws a NumberFormatException.
     */
    public static int convert3 (String s)
    {
        if (s.equals(""))
        {
            throw new RuntimeException("Empty string");
        }
        else
        {
            int n = Integer.parseInt(s);
            n = n * 2;
            return n;
        }
    }
}
