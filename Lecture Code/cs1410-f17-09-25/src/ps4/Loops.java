package ps4;

import java.util.Scanner;

public class Loops
{
    /**
     * Reports whether t appears as a token in s.  (This is not the same as saying that
     * t appears as a substring in s.)
     */
    public static boolean containsToken (String s, String t)
    {
        Scanner scn = new Scanner(s);
        while (scn.hasNext())
        {
            if (scn.next().equals(t)) return true;
        }
        return false;
    }

    /**
     * If there are one or more lines in scn that contain the token t, returns one of those
     * lines.  Otherwise, returns the empty string.
     */
    public static String findLineWithToken (Scanner scn, String t)
    {
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            if (containsToken(line, t))
            {
                return line;
            }
        }
        return "";
    }

    /**
     * Reports whether s is a palindrome.
     */
    public static boolean isPalindrome (String s)
    {
        int i = 0;
        while (i < s.length())
        {
            if (s.charAt(i) != s.charAt(s.length() - i - 1))
            {
                return false;
            }
            i += 1;
        }
        return true;
    }

    /**
     * Returns the line from scn that is the longest palindrome.  If there is no line
     * that is a palindrome, returns "".
     */
    public static String findLongestPalindrome (Scanner scn)
    {
        String longest = "";
        while (scn.hasNext())
        {
            String s = scn.next();
            if (isPalindrome(s) && s.length() > longest.length())
            {
                longest = s;
            }
        }
        return longest;
    }

    /**
     * Finds the line in scn with the most whitespace characters,
     * and returns the number of whitespace characters it contains.
     * If there are no lines, returns -1.
     */
    public static int findMostWhitespace (Scanner scn)
    {
        int mostSoFar = -1;
        while (scn.hasNextLine())
        {
            String line = scn.nextLine();
            int wsCount = countWhitespace(line);
            mostSoFar = Math.max(wsCount, mostSoFar);
        }
        return mostSoFar;
    }
    
    /**
     * Returns the number of whitespace characters in s.
     */
    public static int countWhitespace (String s)
    {
        int count = 0;
        int i = 0;
        while (i < s.length())
        {
            if (Character.isWhitespace(s.charAt(i)))
            {
                count += 1;
            }
            i += 1;
        }
        return count;
    }

}
