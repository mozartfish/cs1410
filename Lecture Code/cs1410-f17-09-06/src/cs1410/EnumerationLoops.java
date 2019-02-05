package cs1410;

import java.util.Scanner;

/**
 * Lecture examples for CS 1410
 * 
 * @author Joe Zachary
 */
public class EnumerationLoops
{
    /**
     * Calls the other methods
     */
    public static void main (String[] args)
    {
        displayFourInts(10);
        displayFourChars("elephant");
        displayFourTokens(new Scanner("The rain in Spain falls mainly in the plain."));
        
        System.out.println();
        
        displayInts(10, 8);
        displayChars("elephant");
        displayTokens(new Scanner("The rain in Spain falls mainly in the plain."));
    }
    
    /**
     * Displays the next four integers beginning with start
     */
    public static void displayFourInts (int start)
    {
        System.out.println(start);
        System.out.println(start+1);
        System.out.println(start+2);
        System.out.println(start+3);
    }
    
    /**
     * Displays the first four characters of s
     */
    public static void displayFourChars (String s)
    {
        System.out.println(s.charAt(0));
        System.out.println(s.charAt(1));
        System.out.println(s.charAt(2));
        System.out.println(s.charAt(3));
    }
    
    /**
     * Displays the first four tokens of scn
     */
    public static void displayFourTokens (Scanner scn)
    {
        System.out.println(scn.next());
        System.out.println(scn.next());
        System.out.println(scn.next());
        System.out.println(scn.next());
    }
    
    /**
     * Displays the next n integers beginning with start
     */
    public static void displayInts (int start, int n)
    {
        int i = start;
        while (i < start+n)
        {
            System.out.println(i);
            i = i+1;
        }
    }
    
    /**
     * Displays the characters of s
     */
    public static void displayChars (String s)
    {
        int i = 0;
        while (i < s.length()) 
        {
            System.out.println(s.charAt(i));
            i = i+1;
        }
    }
    
    /**
     * Displays the tokens of scn
     */
    public static void displayTokens (Scanner scn)
    {
        while (scn.hasNext())
        {
            System.out.println(scn.next());
        }
    }
}
