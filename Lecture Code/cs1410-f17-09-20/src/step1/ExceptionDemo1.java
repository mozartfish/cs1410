package step1;

/**
 * Illustrates exception handling close to the source.
 */
public class ExceptionDemo1
{
    public static void main (String[] args)
    {
        int n = convert1("5673x");
        System.out.println(n);
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
     * Returns the result of converting s into an int. If s can't be converted, returns -1.
     */
    public static int convert3 (String s)
    {
        try
        {
            int n = Integer.parseInt(s);
            n = n * 2;
            return n;
        }
        catch (NumberFormatException e)
        {
            // When the exception is thrown by Integer.parseInt, control transfers here.
            return -1;
        }
    }
}
