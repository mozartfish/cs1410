package step4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Illustrates the use of try-with-resources.
 */
public class TryWithResourcesDemo1
{
    public static void main (String[] args)
    {
        try
        {
            int n = printFirstLine1("c:\\users\\zachary\\workspace-cs1410-f17\\cs1410-f17-09-18");
            System.out.println(n);
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static int printFirstLine1 (String path) throws FileNotFoundException
    {
        int n = printFirstLine2(path);
        n = n * 4;
        return n;
    }

    public static int printFirstLine2 (String path) throws FileNotFoundException
    {
        int n = printFirstLine3(path);
        n = n * 3;
        return n;
    }

    /**
     * Prints the first line of the file stored as the specified path and returns its length. Throws an IOException if
     * there is a problem opening or reading the file.
     */
    public static int printFirstLine3 (String path) throws FileNotFoundException
    {
        // By opening the Scanner this way, it is automatically closed when control
        // leaves the try block.  This work for things other than Scanners that can
        // be opened and closed.
        File file = new File(path);
        try (Scanner scn = new Scanner(file))
        {
            String line = scn.nextLine();
            System.out.println(line);
            return line.length();
        }
    }
}
