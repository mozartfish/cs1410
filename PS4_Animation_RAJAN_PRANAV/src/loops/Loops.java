package loops;

import java.util.*;

/**
 * A collection of methods for the the first part of the fourth assignment of CS 1410.
 * 
 * @author Pranav Rajan
 *
 */
public class Loops
{

    public static void main (String[] args)
    {
    }

    /**
     * Reports whether or not t occurs as a token in s.
     * 
     * containsToken takes a String s and a String t as parameters, and returns a boolean. It returns true if t occurs
     * as a token within s, and returns false otherwise. (Implementation note: Use a Scanner to break s into tokens, and
     * use a searching loop to look for t.)
     * 
     * Examples: <br>
     * containsToken("", "xyz") is false <br>
     * containsToken("xyz", "xyz") is true <br>
     * 
     * @param s the first string that may be a token in t
     * @param t the second string that may contain s as a token
     * @return true or false depending on whether string t contains string s as a token
     */
    public static boolean containsToken (String s, String t)
    {
        String stringT = t;
        Scanner scnStringS = new Scanner(s);

        while (scnStringS.hasNext())
        {
            String stringSToken = scnStringS.next();
            if (stringSToken.equals(stringT))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns a line from scn that contains t as a token (if such a line exists) and returns the empty string
     * (otherwise).
     * 
     * findLineWithToken takes a Scanner scn and a String t as parameters, and returns a String. It returns a line from
     * scn that contains t as a token (if such a line exists) and returns the empty string (otherwise). (Implementation
     * note: You'll find the containsToken method specified above helpful. This method calls for a searching loop.)
     * 
     * Examples: <br>
     * findLineWithToken(new Scanner(""), "hello") returns "" <br>
     * findLineWithToken(new Scanner("hello world"), "world") returns "hello world" <br>
     * 
     * @param scn scans for a line that contains token t
     * @param t Must consist of only letters and white space
     * @return returns either an empty string or line from a scanner that contains token t
     */
    public static String findLineWithToken (Scanner scn, String t)
    {
        while (scn.hasNextLine())
        {
            String token = scn.nextLine();
            if (containsToken(token, t))
            {
                return token;
            }
        }
        return "";
    }

    /**
     * Reports whether or not s reads the same forwards and backwards
     * 
     * isPalindrome takes a String s as a parameter and returns a boolean. It returns true if s reads the same forwards
     * and backwards, and returns false otherwise. You are not allowed to use any method that reverses s.
     * (Implementation note: Use a loop that searches for a character in s that doesn't "match up" with an identical
     * character elsewhere in the string. You'll need to determine the appropriate definition of "match up".)
     * 
     * Examples: <br>
     * isPalindrome("") is true <br>
     * isPalindrome("abcddbba") is false <br>
     * 
     * @param s Must consist of only letters and white space
     * @return
     */
    public static boolean isPalindrome (String s)
    {
        int i = 0;
        int j = s.length() - 1;

        if ((s.length() == 0) || (s.length() == 1))
        {
            return true;
        }
        else
        {
            while (j > i)
            {
                if (s.charAt(i) != s.charAt(j))
                {
                    return false;
                }
                i = i + 1;
                j = j - 1;
            }
            return true;
        }
    }

    /**
     * Returns the longest token from scn that is a palindrome (if one exists) or the empty string (otherwise).
     * 
     * findLongestPalindrome takes a Scanner scn as its parameter and returns a String. It returns the longest token
     * from scn that is a palindrome (if one exists) or the empty string (otherwise). (Implementation note: You'll find
     * the isPalindrome method helpful here. This method calls for an optimization loop.)
     * 
     * Examples: <br>
     * findLongestPalindrome(new Scanner("")) returns "" <br>
     * findLongestPalindrome(new Scanner("I did something wrong")) returns "did"
     * 
     * @param scn
     * @return
     */
    public static String findLongestPalindrome (Scanner scn)
    {
        String isLongestPalindrome = "";
        int isLongestPalindromeLength = isLongestPalindrome.length();

        while (scn.hasNext())
        {
            String wordCheck = scn.next();
            int wordCheckLength = wordCheck.length();
            if (isPalindrome(wordCheck) == true)
            {
                if (wordCheckLength > isLongestPalindromeLength)
                {
                    isLongestPalindrome = wordCheck;
                    isLongestPalindromeLength = wordCheckLength;
                }
            }
        }
        return isLongestPalindrome;
    }

    /**
     * Takes a string s and calculates the number of white spaces in that string
     * 
     * @param s
     * @return
     */
    public static int numWhiteSpaces (String s)
    {
        int numWhiteSpaces = 0;
        int i = 0;

        while (i < s.length())
        {
            if (Character.isWhitespace(s.charAt(i)) == true)
            {
                numWhiteSpaces = numWhiteSpaces + 1;
            }
            i = i + 1;

        }
        return numWhiteSpaces;
    }

    /**
     * Finds the line in scn with the most whitespace characters and returns the number of whitespace characters it
     * contains (if scn contains at least one line) or -1 (otherwise)
     * 
     * findMostWhitespace takes a Scanner scn as its parameter and returns an int. It finds the line in scn with the
     * most whitespace characters and returns the number of whitespace characters it contains (if scn contains at least
     * one line) or -1 (otherwise). (Implementation note: This method calls for an optimization loop, and will be be
     * easy if you first implement a method that counts the amount of whitespace in a string. There is a method
     * Character.isWhitespace() that you'll find useful.)
     * 
     * Examples: <br>
     * findMostWhitespace(new Scanner("")) returns -1 <br>
     * findMostWhitespace(new Scanner("a b c")) returns 2 <br>
     * 
     * @param n
     * @return
     */

    public static int findMostWhitespace (Scanner n)
    {
        int greatestNumberOfWhiteSpacesSoFar = -1;

        while (n.hasNextLine())
        {
            String newLine = n.nextLine();
            if (numWhiteSpaces(newLine) > greatestNumberOfWhiteSpacesSoFar)
            {
                greatestNumberOfWhiteSpacesSoFar = numWhiteSpaces(newLine);
            }

        }
        return greatestNumberOfWhiteSpacesSoFar;
    }

}
