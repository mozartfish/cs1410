package cs1410;

import java.util.Scanner;

/**
 * Illustrates searching loops for integer ranges, strings, and scanners.
 */
public class SearchingLoops
{
    /**
     * For experimentation purposes
     */
    public static void main (String[] args)
    {
        System.out.println(findIntWithLargeCosine(100));
        System.out.println(findDigit("xyz2abc"));
        System.out.println(findWordOfLength(new Scanner("one two three four"), 5));
    }

    /**
     * Returns an integer between 1 and n that has a cosine greater than 0.99. 
     * Returns 0 if there is no such integer.
     */
    public static int findIntWithLargeCosine (int n)
    {
        int i = 1;
        while (i < 1 + n)
        {
            if (Math.cos(i) > 0.99)
            {
                return i;
            }
            i = i + 1;
        }
        return 0;
    }
    
    /**
     * Returns an integer between 1 and n that has a cosine greater than 0.99. 
     * Returns 0 if there is no such integer.
     */
    public static void printIntWithLargeCosine (int n)
    {
        int answer = 0;
        int i = 1;
        while (i < 1 + n)
        {
            if (Math.cos(i) > 0.99)
            {
                answer = i;
                break;
            }
            i = i + 1;
        }
        
        System.out.println(answer);
    }

    /**
     * If the string contains a digit, returns the digit. Otherwise, returns '_'.
     */
    public static char findDigit (String s)
    {
        int i = 0;
        while (i < s.length())
        {
            char c = s.charAt(i);
            if (Character.isDigit(c))
            {
                return c;
            }
            i = i + 1;
        }
        return '_';
    }

    /**
     * This is an accumulation loop variant of the above. Returns the number of characters in s that are digits.
     */
    public static int countDigits (String s)
    {
        return 0;
    }

    /**
     * Another accumulation loop variant. Returns a string containing all of the digits from s.
     */
    public static String allDigits (String s)
    {
        return "";
    }

    /**
     * If the Scanner contains a token of the desired length, returns it. 
     * Otherwise, returns the empty string.
     */
    public static String findWordOfLength (Scanner tokens, int desiredLength)
    {
        while (tokens.hasNext())
        {
            String token = tokens.next();
            if (token.length() == desiredLength)
            {
                return token;
            }
        }
        return "";
    }
    
    /**
     * Returns the number of positive integers that are factors of n.
     */
    public static int numberOfFactors (int n)
    {
        int nFactors = 0;
        int i = 1;
        while (i <= n)
        {
            if (n%i == 0)
            {
                nFactors = nFactors + 1;
            }
            i = i + 1;
        }
        return nFactors;
    }
    
    /**
     * Returns an integer token from numbers that has at least n factors.  
     * Returns 0 if there is no such token.  The string numbers is required 
     * to contain only positive integer tokens.
     * 
     * Example: nFactors("1 2 3 4 5 6", 4) returns 6
     */
    public static int nFactors (String numbers, int n)
    {
        Scanner tokens = new Scanner(numbers);
        while (tokens.hasNextInt())
        {
            int number = tokens.nextInt();
            if (numberOfFactors(number) >= n)
            {
                return number;
            }
        }
        return 0;
    }
    
    /**
     * Reports whether all the integers in numbers are prime.  The string numbers is required to 
     * contain only positive integer tokens.
     * 
     * Example: areAllPrime("2 3 7 11") is true, but areAllPrime("2 3 7 9") is false.
     */
    public static boolean areAllPrime (String numbers)
    {
        Scanner tokens = new Scanner(numbers);
        boolean allPrime = true;
        while (tokens.hasNextInt())
        {
            int number = tokens.nextInt();
            allPrime = allPrime && (numberOfFactors(number) == 2); 
        }
        return allPrime;
    }
    
    public static boolean areAllPrimeSearch (String numbers)
    {
        Scanner tokens = new Scanner(numbers);
        while (tokens.hasNextInt())
        {
            int number = tokens.nextInt();
            if (numberOfFactors(number) != 2)
            {
                return false;
            }
        }
        return true;
    }
}
