package cs1410;

import java.util.*;

/**
 * A collection of methods for the third assignment of CS 1410.
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
     * Returns the life stage of a person, given his or her age. The possible return values are "baby" (age is less than
     * 2), "child" (age is between 2 and 12 inclusive), "teen" (age is between 13 and 17 inclusive), "adult" (age is
     * between 18 and 64 inclusive), and "senior" (age is greater than 64).
     * 
     * Examples: getLifeStage(25) is "adult" and getLifeStage(0) is "baby".
     * 
     * IMPLEMENTATION NOTE: Use a 5-way conditional
     * 
     * @param age Must be non-negative
     */
    public static String getLifeStage (int age)
    {
        int personAge = age;
        String lifeStage = "";

        if (personAge < 2)
        {
            lifeStage = "baby";
        }
        else if (personAge <= 12)
        {
            lifeStage = "child";
        }
        else if (personAge <= 17)
        {
            lifeStage = "teen";
        }
        else if (personAge <= 64)
        {
            lifeStage = "adult";
        }
        else
        {
            lifeStage = "senior";
        }

        return lifeStage;
    }

    /**
     * Returns the index within s of the first vowel ('a', 'e', 'i', 'o', 'u' or an upper-case version) that occurs in
     * s. If there is no vowel in s, returns -1.
     * 
     * Examples: firstVowelIndex("Apple") is 0, firstVowelIndex("hello") is 1, firstVowelIndex("slope") is 2,
     * firstVowelIndex("strength") is 4, and firstVowelIndex("xyzzy") is -1.
     * 
     * IMPLEMENTATION NOTE: This method is already completely implemented. There is no need for you to change anything.
     */
    public static int firstVowelIndex (String s)
    {
        int i = 0;
        while (i < s.length())
        {
            if ("aeiouAEIOU".indexOf(s.charAt(i)) >= 0)
            {
                return i;
            }

            i = i + 1;
        }

        return -1;
    }

    /**
     * Returns the result of converting s to "Pig Latin". Convert a string s to Pig Latin by using the following rules:
     * 
     * (1) If s contains no vowels, do nothing to it.
     * 
     * (2) Otherwise, if s starts with a vowel, append "way" to the end.
     * 
     * (3) Otherwise, move everything up to (but not including) the first vowel to the end and add "ay".
     * 
     * For example, "hello" converts to "ellohay", "small" converts to "allsmay", "eat" converts to "eatway", and "nth"
     * converts to "nth".
     * 
     * IMPLEMENTATION NOTE: This will require a three-way conditional that extracts pieces of Strings and recombines
     * them into new Strings. For full credit (and an easier implementation), use the firstVowelIndex method provided
     * above in your method's implementation. You will also find the methods s.substring() and s.charAt() (where s is a
     * String), as well as the + operator that concatenates Strings, very useful.
     */
    public static String toPigLatin (String s)
    {
        if (firstVowelIndex(s) == -1)
        {
            return s;
        }
        else if (firstVowelIndex(s) == 0)
        {
            return s + "way";
        }
        else
        {
            String newString1 = s.substring(firstVowelIndex(s), s.length());
            String newString2 = s.substring(0, firstVowelIndex(s)) + "ay";
            s = newString1 + newString2;
        }

        return s;
    }

    /**
     * Returns the result of converting each token from words into Pig Latin and then appending the results, with each
     * converted word followed by a single space character. A token is a sequence of letters separated from other tokens
     * by white space. If there are no tokens, returns the empty string.
     * 
     * Examples: "" converts to "" and "This is a test" converts to "isThay isway away esttay ".
     * 
     * IMPLEMENTATION NOTE: Use a Scanner to split the string into individual tokens and use an accumulation loop to
     * consume the tokens. Make use of your toPigLatin() method.
     * 
     * @param words Must consist of only letters and white space
     */
    public static String convertToPigLatin (String words)
    {
        Scanner scnWords = new Scanner(words);
        String pigLatinString = "";

        while (scnWords.hasNext())
        {
            pigLatinString = pigLatinString + toPigLatin(scnWords.next()) + " ";
        }

        return pigLatinString;
    }

    /**
     * Returns the sum of the nth roots of each double x in numbers, where numbers consists of zero or more double
     * tokens (separated by white space) and n is positive. A small around of roundoff error is to be expected.
     * 
     * Examples: sumOfRoots("1 4 9 16", 2) is 10 and sumOfRoots("") is 0.
     * 
     * @param numbers Must consist of only double literals and white space
     * @param n must be positive
     */
    public static double sumOfRoots (String numbers, int n)
    {
        Scanner scnNumbers = new Scanner(numbers);
        double sumOfRoots = 0.0;

        while (scnNumbers.hasNextDouble())
        {
            int rootExp = n;
            double nthRoot = Math.pow(scnNumbers.nextDouble(), 1.0 / (double) rootExp);
            sumOfRoots = sumOfRoots + nthRoot;
        }
        scnNumbers.close();

        return sumOfRoots;
    }

    /**
     * Reports whether or not every character in source occurs at least once in target.
     * 
     * Examples: containsAll("abc", "abracadabra") is true, and containsAll("def", "Defect") is false.
     * 
     * IMPLEMENTATION NOTE: Write this as an accumulation loop. Don't try to write a doubly-nested loop!
     */
    public static boolean containsAll (String source, String target)
    {
        int i = 0;

        while (i < source.length())
        {
            String containsChar = Character.toString(source.charAt(i));

            if (target.contains(containsChar) == false)
            {
                return false;
            }
            else
            {
                i = i + 1;
            }
        }

        return true;
    }

    /**
     * Returns a String of length width that begins and ends with the character edge and contains width-2 copies of the
     * character inner in between. The method does not print anything.
     * 
     * Example makeLine('+', '-', 8) is "+------+".
     * 
     * IMPLEMENTATION NOTE: Use an accumulation loop
     * 
     * @params width must be >= 2
     */
    public static String makeLine (char edge, char inner, int width)
    {
        int i = 0;
        String makeLine = "";

        String lineInner = Character.toString(inner);
        String lineEdge = Character.toString(edge);

        while (i < (width - 2))
        {
            makeLine = makeLine + lineInner;
            i = i + 1;
        }

        return lineEdge + makeLine + lineEdge;
    }

    /**
     * Returns a string which, when printed out, will be a rectangle shaped something like this:
     * 
     * <pre>
     * +----------+
     * |          |
     * |          |
     * |          |
     * |          |
     * |          |
     * +----------+
     * </pre>
     * 
     * The returned string should consist of height lines, each ending with a newline. In addition to the newline, the
     * first and last lines should begin and end with '+' and should contain width-2 '-' symbols. In addition to the
     * newline, the other lines should begin and end with '|' and should contain width-2 spaces. The method does not
     * print anything.
     * 
     * IMPLEMENTATION NOTE: For full credit (and for easier implementation), make use of the makeLine method in your
     * implementation of makeRectangle. Use an accumulation loop.
     * 
     * @param height must be >= 2
     * @param width must be >= 2
     */
    public static String makeRectangle (int height, int width)
    {
        int i = 0;
        String drawMiddleLines = "";
        String drawMiddle = makeLine('|', ' ', width) + "\n";

        String drawTopLine = makeLine('+', '-', width) + "\n";

        while (i < (height - 2))
        {
            drawMiddleLines = drawMiddleLines + drawMiddle;
            i = i + 1;
        }

        String drawBottomLine = makeLine('+', '-', width) + "\n";

        return drawTopLine + drawMiddleLines + drawBottomLine;
    }

    /**
     * Returns the integer that follows n in a Hailstone sequence. If n is 1, returns 1. Otherwise, returns either n/2
     * (if n is even) or 3n+1 (if n is odd).
     * 
     * Examples: nextHailstone(1) is 1, nextHailstone(7) is 22, and nextHailstone(6) is 3,
     * 
     * IMPLEMENTATION NOTE: This one will require a three-way conditional
     * 
     * @param n must be positive
     */
    public static int nextHailstone (int n)
    {
        if (n == 1)
        {
            return n;
        }
        else if (n % 2 == 0)
        {
            return (n / 2);
        }
        else
        {
            return (3 * n) + 1;
        }
    }

    /**
     * Returns a string consisting of a Hailstone sequence beginning with the positive integer n and ending with 1. The
     * string should consist of a sequence of numerals, with each numeral followed by a single space. When a numeral m
     * (other than 1) appears in the sequence, it should be followed by nextHailstone(m).
     * 
     * Examples: nextHailstone(1) is "1 " and nextHailstone(5) is "5 16 8 4 2 1 ".
     * 
     * IMPLEMENTATION NOTE: Make use of your nextHailstone method. Use an accumulation loop, but with a bit of a twist.
     * 
     * @param n must be positive
     */
    public static String hailstones (int n)
    {
        int nextHail = n;
        String hailstones = Integer.toString(nextHail) + " ";

        if (n == 1)
        {
            return hailstones;
        }
        else
        {
            while (nextHailstone(nextHail) != 1)
            {
                hailstones = hailstones + Integer.toString(nextHailstone(nextHail)) + " ";
                nextHail = nextHailstone(nextHail);
            }
        }

        return hailstones + Integer.toString(nextHailstone(1)) + " ";
    }

    /**
     * Reports whether or not s1 and s2 contain exactly the same tokens in the same order.
     * 
     * Examples: <br>
     * sameTokens("this is a test", " this is a test ") is true <br>
     * sameTokens("", "") is true <br>
     * sameTokens("hello there", "hello there Joe") is false <br>
     * sameTokens("abc def", "def abc") is false <br>
     * sameTokens("a", "A") is false
     *
     * IMPLEMENTATION NOTE: You can write this as an accumulation loop with a twist. Be aware of the implications of the
     * short-circuited evaluation of &&.
     */
    public static boolean sameTokens (String s1, String s2)
    {
        Scanner scnString1 = new Scanner(s1);
        Scanner scnString2 = new Scanner(s2);
        boolean sameTokens = true;

        while (scnString1.hasNext() && scnString2.hasNext())
        {
            String String1 = scnString1.next();
            String String2 = scnString2.next();
            sameTokens = sameTokens && String1.equals(String2);
        }

        return sameTokens && (!scnString1.hasNext() && !scnString2.hasNext());

    }

}






