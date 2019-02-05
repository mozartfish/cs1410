package step2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Illustrates handling a checked exception.
 */
public class CheckedExceptionDemo1
{
    public static void main (String[] args)
    {
        //int n = printFirstLine1("c:\\users\\zachary\\workspace-cs1410-f17\\cs1410-f17-09-18");
        int n = printFirstLine1("c:\\users\\zachary\\documents\\animals.txt");
        
        System.out.println(n);
    }

    public static int printFirstLine1 (String path)
    {
        int n = printFirstLine2(path);
        n = n * 4;
        return n;
    }

    public static int printFirstLine2 (String path)
    {
        int n = printFirstLine3(path);
        n = n * 3;
        return n;
    }

    /**
     * Prints the first line of the file stored as the specified path and returns its length. 
     * Returns 0 if there is a problem opening the file.
     */
    public static int printFirstLine3 (String path)
    {
        try
        {
            File file = new File(path);
            Scanner scn = new Scanner(file);
            String line = scn.nextLine();
            System.out.println(line);
            return line.length();
        }
        catch (FileNotFoundException e)
        {
            return 0;
        }
    }
}
