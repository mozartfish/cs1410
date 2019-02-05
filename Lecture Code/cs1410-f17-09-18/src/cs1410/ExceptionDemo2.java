package cs1410;

import java.io.File;
import java.util.Scanner;

public class ExceptionDemo2
{
    public static void main (String[] args)
    {
        int n = printFirstLine1("c:\\users\\zachary\\workspace-cs1410-f17\\cs1410-f17-09-18");
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
    
    public static int printFirstLine3 (String path)
    {
        File file = new File(path);
        Scanner scn = new Scanner(file);
        String line = scn.nextLine();
        System.out.println(line);
        return line.length();
    }
}
