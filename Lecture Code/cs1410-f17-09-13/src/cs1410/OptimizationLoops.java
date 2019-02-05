package cs1410;

import java.util.Scanner;
import cs1410.SearchingLoops;

/**
 * Illustrates searching loops for ranges, strings, and scanners.
 * 
 * @author Joe Zachary
 */
public class OptimizationLoops
{
    /**
     * For experimentation
     */
    public static void main (String[] args)
    {
        System.out.println(findIntWithLargestCosine(100));
        System.out.println(findLargestChar("abcABC123"));
        System.out.println(findLongestToken(new Scanner("Look in the nearest closet")));
    }

    /**
     * Returns the integer between 1 and n has the largest cosine, where n is
     * required to be at least 1.
     */
    public static int findIntWithLargestCosine(int n)
    {
        int numberWithLargestCosineSoFar = 1;
        int i = 2;
        while (i <= n)
        {
            if (Math.cos(i) > Math.cos(numberWithLargestCosineSoFar))
            {
                numberWithLargestCosineSoFar = i;
            }
            i += 1;
        }
        return numberWithLargestCosineSoFar;
    }

    /**
     * Returns the "largest" character in s, which is required to be non-empty.
     */
    public static char findLargestChar(String s)
    {
        char largestSoFar = s.charAt(0);
        int i = 1;
        while (i < s.length())
        {
            if (s.charAt(i) > largestSoFar)
            {
                largestSoFar = s.charAt(i);
            }
            i += 1;
        }
        return largestSoFar;
    }

    /**
     * Returns the longest token in s, which is required to be non-empty.
     */
    public static String findLongestToken(Scanner s)
    {
        String longestSoFar = s.next();
        int longestLength = longestSoFar.length();
        while (s.hasNext())
        {
            String token = s.next();
            int tokenLength = token.length();
            if (tokenLength > longestLength)
            {
                longestSoFar = token;
                longestLength = tokenLength;
            }
        }
        return longestSoFar;
    }
    
    /**
     * Returns the integer between 1 and n with the most factors.  If n is
     * not positive, returns 0.
     */
    public static int findIntWithMostFactors(int n)
    {
        if (n < 1)
        {
            return 0;
        }
        
        int mostFactorsSoFar = 1;
        int i = 2;
        while (i <= n)
        {
            if (SearchingLoops.numberOfFactors(i) > 
                    SearchingLoops.numberOfFactors(mostFactorsSoFar))
            {
                mostFactorsSoFar = i;
            }
            i += 1;
        }
        return mostFactorsSoFar;
    }
    
    /**
     * Returns the token from scn that contains the largest character.  If
     * scn contains no tokens, returns the empty string.
     */
    public static String findTokenWithLargestChar (Scanner scn)
    {
        return "";
    }
}
