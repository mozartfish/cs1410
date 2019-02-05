package cs1410;

import java.util.Scanner;
import java.lang.Math;
import java.math.BigInteger;

/**
 * A collection of methods for the second assignment of CS 1410.
 * 
 * @author Pranav Rajan
 */
public class MethodLibrary
{
    /**
     * You can use this main method for experimenting with your methods if you like, but it is not part of the
     * assignment.
     */
    public static void main (String[] args)
    {

    }

    /**
     * Returns the nth root of x, where n is positive. For example, nthRoot(27.0, 3) is 3.0 (the cube root of 27), and
     * nthRoot(64.0, 6) is 2.0. NOTE: A small amount of roundoff error is acceptable.
     * 
     * The number x is required to have a real-valued nth root, and n is required to be positive. If this requirement is
     * violated, the behavior of the method is undefined (it does not matter what it does).
     */
    public static double nthRoot (double x, int n)
    {
        int rootExp = n;
        return Math.pow(x, 1.0 / (double) rootExp);
    }

    /**
     * Reports whether or not c is a vowel ('a', 'e', 'i', 'o', 'u' or the upper-case version). For example,
     * isVowel('a') and isVowel('U') are true; isVowel('x') and isVowel('H') are false.
     */
    public static boolean isVowel (char c)
    {
        return (c == 'a') || (c == 'A') || (c == 'e') || (c == 'E') || (c == 'i') || (c == 'I') || (c == 'o')
                || (c == 'O') || (c == 'u') || (c == 'U');
    }

    /**
     * Reports whether or not number is a multiple of both factor1 and factor2. For example, isMultipleOf(15, 3, 5) is
     * true and isMutipleOf(27, 3, 4) is false.
     * 
     * Both factor1 and factor2 are required to be non-zero. If this requirement is violated, the behavior of the method
     * is undefined (it does not matter what it does).
     */
    public static boolean isMultipleOf (int number, int factor1, int factor2)
    {
        int multiple = number;
        int multipleFactor1 = factor1;
        int multipleFactor2 = factor2;
        return (multiple % multipleFactor1 == 0) && (multiple % multipleFactor2 == 0);
    }

    /**
     * Returns the string that results from capitalizing the first character of s, which is required to have at least
     * one character. For example, capitalize("hello") is "Hello" and capitalize("Jack") is "Jack".
     * 
     * The string s is required to be non-empty. If this requirement is violated, the behavior of the method is
     * undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The Character.toUpperCase() method will be helpful. The method s.substring() [where s is a
     * String] will also be helpful. Learn more about them before starting!
     */
    public static String capitalize (String s)
    {
        String originalString = s;
        char capital = Character.toUpperCase(originalString.charAt(0));
        String newString = originalString.replace(originalString.charAt(0), capital);
        return newString;
    }

    /**
     * Returns a new string that (1) begins with all the characters of original that come after the first occurrence of
     * target and (2) ends with all the characters of target that come before the first occurrence of pattern. For
     * example, flip("abcdefg", 'd') is "efgabc", flip("ababad", 'b') is "abada", and flip("x", 'x') is "".
     * 
     * The string original is required to contain the character target. If this requirement is violated, the behavior of
     * the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The methods s.indexOf() and s.substring() [where s is a String] will be helpful.
     */
    public static String flip (String original, char pattern)
    {
        String originalString = original;
        char target = pattern;

        int targetLocation = originalString.indexOf(target);

        String newString1 = originalString.substring(targetLocation + 1, originalString.length());
        String newString2 = originalString.substring(0, targetLocation);

        return newString1 + newString2;
    }

    /**
     * Returns a new string that is just like s except all of the lower-case vowels ('a', 'e', 'i', 'o', 'u') have been
     * capitalized. For example, capitalizeVowels("hello") is "hEllO", capitalizeVowels("String") is "StrIng", and
     * capitalizeVowels("nth") is "nth".
     * 
     * IMPLEMENTATION HINT: The method s.replace() [where s is a String] will be helpful.
     */
    public static String capitalizeVowels (String s)
    {
        String originalString = s;
        String capitalizeA = originalString.replace('a', 'A');
        String capitalizeE = capitalizeA.replace('e', 'E');
        String capitalizeI = capitalizeE.replace('i', 'I');
        String capitalizeO = capitalizeI.replace('o', 'O');
        String capitalizeVowels = capitalizeO.replace('u', 'U');

        return capitalizeVowels;
    }

    /**
     * Reports whether s1 and s2 end with the same n characters. For example, sameEnding("abcde" "xde", 2) is true and
     * sameEnding("abcde", "xde", 3) is false.
     * 
     * The value of n is required to be non-negative, and the two strings must each contain at least n characters. If
     * this requirement is violated, the behavior of the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The methods s.length() and s.substring() [where s is a String] will be helpful.
     */
    public static boolean sameEnding (String s1, String s2, int n)
    {
        String string1 = s1;
        String string2 = s2;
        int nChar = n;

        String nChar1 = string1.substring((string1.length() - nChar), string1.length());
        String nChar2 = string2.substring((string2.length() - nChar), string2.length());

        return nChar1.equals(nChar2);

    }

    /**
     * Returns the value of the largest of the five int literals, separated by white space, that make up the integerList
     * parameter. For example, largestOfFive(" 15 28 -99 62 44 ") is 62.
     * 
     * The string integerList is required to consist of exactly five int literals surrounded by white space. If this
     * requirement is violated, the behavior of the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The class java.util.Scanner will be helpful. Use the one-argument constructor that takes a
     * String as a parameter and the nextInt() method.
     */
    public static int largestOfFive (String integerList)
    {
        String input = integerList;

        Scanner scnr = new Scanner(input);
        int num1 = scnr.nextInt();
        int num2 = scnr.nextInt();
        int num3 = scnr.nextInt();
        int num4 = scnr.nextInt();
        int num5 = scnr.nextInt();

        int largestOfFirstTwo = Math.max(num1, num2);
        int largestOfSecondTwo = Math.max(num3, num4);
        int largestOfThirdTwo = Math.max(largestOfFirstTwo, largestOfSecondTwo);
        int largestOfFive = Math.max(largestOfThirdTwo, num5);

        return largestOfFive;
    }

    /**
     * Reports whether or not date1 comes earlier in time than date2. For example, isEarlierThan("12-01-2015",
     * "02-15-2017") is true but isEarlierThan("10-11-2016", "10-11-2016") and isEarlierThan("09-09-1967", "02-15-1933")
     * is false.
     * 
     * The two parameters must be of the form MM-DD-YYYY where YYYY is a year, MM is the two-digit number of a month, DD
     * is a two-digit number of a day, and the entire date is valid. If this requirement is violated, the behavior of
     * the method is undefined (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: Turn this into a String comparison problem.
     */
    public static boolean isEarlierThan (String date1, String date2)
    {
        String mmYYDD1 = date1;
        String mmYYDD2 = date2;

        String year1 = mmYYDD1.substring(6, 10);
        String year2 = mmYYDD2.substring(6, 10);
        String month1 = mmYYDD1.substring(0, 2);
        String month2 = mmYYDD2.substring(0, 2);
        String day1 = mmYYDD1.substring(3, 5);
        String day2 = mmYYDD2.substring(3, 5);

        int month1Int = Integer.parseInt(month1);
        int month2Int = Integer.parseInt(month2);
        int day1Int = Integer.parseInt(day1);
        int day2Int = Integer.parseInt(day2);
        int year1Int = Integer.parseInt(year1);
        int year2Int = Integer.parseInt(year2);

        return (year2Int - year1Int > 0) || (year2Int - year1Int == 0 && month2Int - month1Int > 0)
                || (year2Int - year1Int == 0 && month2Int - month1Int == 0 && day2Int - day1Int > 0);

    }

    /**
     * Returns the integer numeral that represents the sum of the integer numerals integer1 and integer2. For example,
     * addNumerals("125", "64") is "189". The method works for numerals of any length, including numerals that consist
     * of hundreds or thousands of digits and are far too big to parse as ints or longs.
     * 
     * The two parameters must both be valid integer numerals. That is, both must consist of one or more digits
     * optionally preceded by a + and - sign. If this requirement is violated, the behavior of the method is undefined
     * (it does not matter what it does).
     * 
     * IMPLEMENTATION HINT: The class java.math.BigInteger will be helpful. Use the one-argument constructor that takes
     * a String as a parameter, the add method(), and the toString() method.
     */
    public static String addNumerals (String integer1, String integer2)
    {
        BigInteger bigInt1 = new BigInteger(integer1);
        BigInteger bigInt2 = new BigInteger(integer2);

        BigInteger bigInt3 = bigInt1.add(bigInt2);

        String sum = bigInt3.toString();
        return sum;
    }
}
