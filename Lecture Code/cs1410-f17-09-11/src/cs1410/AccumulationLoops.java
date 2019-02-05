package cs1410;

import java.util.Scanner;

public class AccumulationLoops
{
    /**
     * Calls the other methods.
     * 
     * @param args
     */
    public static void main (String[] args)
    {
        System.out.println(product(2, 5));
        System.out.println(reverse("abcdefg"));
        System.out.println(totalLength(new Scanner("  a bc  def   gh  i")));
    }

    /**
     * Returns the product of the next n integers beginning with start
     */
    public static int product (int start, int n)
    {
        int product = 1;
        int i = start;
        while (i < start + n)
        {
            product = product * i;
            i = i + 1;
        }
        return product;
    }

    /**
     * Returns the reverse of s
     */
    public static String reverse (String s)
    {
        String r = "";
        int i = 0;
        while (i < s.length())
        {
            r = r + s.charAt(i);
            i = i + 1;
        }
        return r;
    }

    /**
     * Returns the total length of all the tokens in scn.
     */
    public static int totalLength (Scanner scn)
    {
        int length = 0;
        while (scn.hasNext())
        {
            length = length + scn.next().length();
        }
        return length;
    }

    /**
     * Returns a string containing the n integers beginning with start all appended together
     */
    public static String appendInts (int start, int n)
    {
        String appendedInts = "";
        int i = start;
        while (i < start + n)
        {
            appendedInts = appendedInts + i;
            i = i + 1;
        }
        return appendedInts;
    }

    /**
     * Returns a string containing just the lower case letters of s
     */
    public static String allLowerCase (String s)
    {
        String lowerCaseLetters = "";
        int i = 0;
        while (i < s.length()) 
        {
            if (Character.isLowerCase(s.charAt(i)))
            {
                lowerCaseLetters = lowerCaseLetters + s.charAt(i);
            }
            i = i+1;
        }
        return lowerCaseLetters;
    }

    /**
     * Returns the sum of the integer tokens in scn. 
     * We assume that scn contains only integer tokens.
     */
    public static int sumOfTokens (Scanner scn)
    {
        int sum = 0;
        while (scn.hasNextInt())
        {
            sum = sum + scn.nextInt();
        }
        return sum;
    }
}
