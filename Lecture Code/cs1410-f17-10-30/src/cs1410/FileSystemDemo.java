package cs1410;

import java.io.File;

/**
 * Collection of methods for examining the file system.
 */
public class FileSystemDemo
{
    /**
     * Runs the methods below.
     */
    public static void main (String[] args)
    {
        System.out.println("Count: " + countFiles(new File("c:/users/zachary/workspace")));
        System.out.println("Largest: " + largestFileSize(new File("c:/users/zachary/workspace")));
        System.out.println("Longest: " + longestFileName(new File("c:/users/zachary/workspace")));
    }
    
    /**
     * If f is a file, returns 1.  Otherwise, returns the number of files (not including
     * directories) that are contained directly or indirectly inside of f.
     */
    public static int countFiles (File f)
    {
        return 1;
    }
    
    /**
     * If f is a file, returns its size.  Otherwise, returns the size of the largest
     * file (not including directories) that is contained directly or indirectly
     * inside of f.  (If f contains no files, returns -1.)
     */
    public static long largestFileSize (File f)
    {
        return 0;
    }
    
    /**
     * If f is a file, returns its name.  Otherwise, returns the longest name of
     * all the files (not including directories) that are contained directly or indirectly
     * inside of f.  (If f contains no files, returns the empty string.)
     */
    public static String longestFileName (File f)
    {
        return "";
    }
}
