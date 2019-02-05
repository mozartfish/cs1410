package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

/**
 * A library of methods for implementing the random text generation algorithm described in PS5, Fall 2017.
 * 
 * @author Pranav Rajan and Joe Zachary
 */
public class PS5Library
{
    /**
     * Demonstrates the use of the generateText method.
     */
    public static void main (String[] args) throws IOException
    {
        // You won't need to use this feature in PS5, but this shows how to include a resource (in this
        // case a text file) as part of a project. I created a package called "books", then put two
        // text files into the package. I was then able to open one of those files as shown below. When
        // I export the project, the resources go along with it.
        try (InputStream book = PS5Library.class.getResourceAsStream("/books/PrideAndPrejudice.txt");
                Scanner input = new Scanner(book))
        {
            System.out.println(generateText(input, 6, 100));
        }
    }

    /**
     * Uses all the text in the input to generate and return random text with the specified level and length, using the
     * algorithm described in PS5, CS 1410, Fall 2017.
     * 
     * @throws IllegalArgumentException if level < 0, or length < 0, or there are less than level+1 chars in the input.
     */
    public static String generateText (Scanner input, int level, int length)
    {
        // Validate the parameters
        if (level < 0 || length < 0)
        {
            throw new IllegalArgumentException();
        }

        // Grab all the text from the Scanner and make sure it is long enough.
        String text = scannerToString(input);
        if (level >= text.length())
        {
            throw new IllegalArgumentException();
        }

        // Create a random number generator to pass to the methods that make random choices
        Random rand = new Random();

        // Get the initial pattern.
        String pattern = chooseSubstring(text, level, rand);

        // Build up the final result one character at a time. We use a StringBuilder because
        // it is more efficient than using a String when doing long sequences of appends.
        StringBuilder result = new StringBuilder();
        while (result.length() < length)
        {
            try
            {
                // Pick at random a character that follows the pattern in the text and append it
                // to the result. If there is no such character (which can happen if the pattern
                // occurs only once, at the very end of text), the method we're calling will throw
                // a NoSuchElementException, which is caught below.
                char newChar = pickCharThatFollowsPattern(text, pattern, rand);
                result.append(newChar);

                // Update the pattern by removing its first character and adding on the new
                // character. The length of the pattern remains the same. If the pattern is
                // the empty string, though, it never changes.)
                if (pattern.length() > 0)
                {
                    pattern = pattern.substring(1) + newChar;
                }
            }
            catch (NoSuchElementException e)
            {
                // It is possible to get stuck if the pattern occurs only once in the text and
                // that occurrence is at the very end of the text. In this case, we pick a new
                // seed and keep going.
                pattern = chooseSubstring(text, level, rand);
            }
        }

        // Return the string we've accumulated.
        return result.toString();
    }

    /**
     * scannerToString, which takes a Scanner as its parameter and returns a String. The returned string consists of all
     * the characters in the scanner in their original order, including the newlines. The last line (assuming there are
     * any lines) should always end with a newline, even if it didn't in the original text. For example,
     * scannerToString("This\nis a\ntest") should return "This\nis a\ntest\n".
     * 
     * A convenient way to implement this method is to build up the result by reading one line at a time from the
     * Scanner. For much improved efficiency when dealing with Scanners containing complete books, consider using a
     * StringBuilder object to construct the result. You can look at my generateText method for an example of its use.
     * 
     * @param scn
     * @return
     */
    public static String scannerToString (Scanner scn)
    {
        StringBuilder scanToString = new StringBuilder();
        while (scn.hasNextLine())
        {
            String token = scn.nextLine();
            scanToString.append(token).append('\n');
        }

        String s = scanToString.toString();
        return s;
    }

    /**
     * chooseSubstring, which takes a String text, an int length, and a random number generator as its parameters. It
     * should use the random number generator to return a randomly chosen substring of text that has the specified
     * length. If length is either negative or greater than the length of text, the method should throw an
     * IllegalArgumentException. For example, chooseSubstring("abcde", 4, new Random()) should return "abcd" about half
     * the time and "bcde" about half the time.
     * 
     * A string of length n will contain n+1-m different substrings of length m, assuming n >= m. These substrings start
     * at indexes ranging from 0 to n-m. There is a convenient method provided by the Random parameter that you can use
     * to choose a random index.
     * 
     * @param text
     * @param length
     * @param rand
     * @return
     */
    public static String chooseSubstring (String text, int length, Random rand)
    {

        int n = text.length();
        int m = length;
        int a = rand.nextInt((n - m) + 1);

        if (m < 0)
        {
            throw new IllegalArgumentException("The length given is less than 0. Please try again.");
        }
        if (m > n)
        {
            throw new IllegalArgumentException(
                    "The length given is greater than the length of the string. Please try again.");
        }
        if (m == n)
            return text;

        String s = text.substring(a, (a + m));

        return s;

    }

    /**
     * getCharsThatFollowPattern, which takes a String text and a String pattern as parameters, and returns an
     * ArrayList<Character>. The returned list should contain the character that follows each non-tail occurrence of the
     * pattern in the text. (A non-tail occurrence of the pattern is one that is not at the very end of the text.) The
     * length of the list must be the same as the number of non-tail occurrences of the pattern. The character stored at
     * index n of the list must be the character that followed the nth non-tail occurrence of the pattern. For example,
     * getCharsThatFollowPattern("abcabdabcab", "ab") should return the ArrayList ['c', 'd', 'c'].
     * 
     * @param text
     * @param pattern
     * @return
     */

    public static ArrayList<Character> getCharsThatFollowPattern (String text, String pattern)
    {
        ArrayList<Character> charVals = new ArrayList<Character>();

        int n = text.indexOf(pattern);
        while (n != -1)
        {
            if ((n >= text.length() - pattern.length()))
            {
                return charVals;
            }
            char c = text.charAt(n + pattern.length());
            charVals.add(c);
            n = text.indexOf(pattern, n + 1);
        }
        return charVals;
    }

    /**
     * 
     * pickCharThatFollowsPattern, which takes a String text, a String pattern, and a random number generator as
     * parameters. It should randomly choose a non-tail occurrence of the pattern in the text, returning the character
     * that immediately follows that occurrence of the pattern. If there are no non-tail occurrences of the pattern in
     * the text, the method should throw a NoSuchElementException. For example, pickCharThatFollowsPattern("They are
     * here", "he") should return 'y' or 'r' with equal probability.
     * 
     * @param text
     * @param pattern
     * @param rand
     * @return
     */
    public static char pickCharThatFollowsPattern (String text, String pattern, Random rand)
    {
        try
        {
            ArrayList<Character> getChars = getCharsThatFollowPattern(text, pattern);
            int randInt = rand.nextInt(getChars.size());
            return getChars.get(randInt);
        }
        catch (Exception e)
        {
            throw new NoSuchElementException();
        }

    }
}
