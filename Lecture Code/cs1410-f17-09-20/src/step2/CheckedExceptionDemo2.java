package step2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Illustrates letting a checked exception propagate.
 */
public class CheckedExceptionDemo2
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
            System.out.println("Couldn't read file");
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
     * Prints the first line of the file stored as the specified path and returns its length. 
     * Throws a FileNotFoundException if there is a problem opening or reading the file.
     */
    public static int printFirstLine3 (String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner scn = new Scanner(file);
        String line = scn.nextLine();
        System.out.println(line);
        return line.length();
    }
}
