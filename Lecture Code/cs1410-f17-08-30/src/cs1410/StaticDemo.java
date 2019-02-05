package cs1410;

import java.util.Scanner;
import cs1410lib.Dialogs;

/**
 * Demonstration of using and writing static methods that
 * make use of other methods.
 * 
 * @author Joe Zachary
 */
public class StaticDemo
{
    /**
     * Driver for max.
     */
    public static void main1 (String[] args)
    {
        String num1 = Dialogs.showInputDialog("Enter an integer: ");
        String num2 = Dialogs.showInputDialog("Enter an integer: ");
        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int larger = Math.max(n1, n2);
        Dialogs.showMessageDialog("The larger is " + larger);
    }

    /**
     * Driver for max and showIntInputDialog.
     */
    public static void main2 (String[] args)
    {
        int n1 = showIntInputDialog();
        int n2 = showIntInputDialog("Enter an integer: ");
        int larger = Math.max(n1, n2);
        Dialogs.showMessageDialog("The larger is " + larger);
    }
    
    public static void main (String[] args)
    {
        double avg = average3(4.5, 3.2, 5.7);
        Dialogs.showMessageDialog("The average is " + avg);
        
        String ss = "45.6 82.2 14.5";
        Dialogs.showMessageDialog("The sum is " + addFirstTwoTokens(ss));
    }

    /**
     * Displays the prompt in a dialog box. Returns the integer that the user enters. If the user enters a non-integer,
     * no promises.
     */
    public static int showIntInputDialog (String prompt)
    {
        String num = Dialogs.showInputDialog(prompt);
        int n = Integer.parseInt(num);
        return n;
    }

    /**
     * Displays a dialog box. Returns the integer that the user enters. If the user enters a non-integer, no promises.
     */
    public static int showIntInputDialog ()
    {
        String num = Dialogs.showInputDialog("Enter int: ");
        int n = Integer.parseInt(num);
        return n;
    }

    /**
     * Returns the fourth root of x.
     */
    public static double fourthRoot (double x)
    {
        return Math.pow(x, 0.25);
    }

    /**
     * Returns the average of d1, d2, and d3.
     */
    public static double average3 (double d1, double d2, double d3)
    {
        return (d1 + d2 + d3) / 3.0;
    }

    /**
     * Reports whether a person with the given age is a teenager.
     */
    public static boolean isTeenager (int age)
    {
        return age >= 13 && age <= 19;
    }
    
    /**
     * Reports whether c is a lower case letter in the
     * Latin alphabet.
     */
    public static boolean isLowerCaseLetter (char c)
    {
        return c >= 'a' && c <= 'z';
    }

    /**
     * Reports whether String s contains char c.
     */
    public static boolean contains (String s, char c)
    {
        return s.toLowerCase().indexOf(c) >= 0 ||
               s.toUpperCase().indexOf(c) >= 0;
    }
    
    /**
     * Returns the sum of the first two tokens of s,
     * where s is required to contain at begin with
     * two numeric tokens.
     */
    public static double addFirstTwoTokens (String s)
    {
        Scanner scan = new Scanner(s);
        return scan.nextDouble() + scan.nextDouble();
    }
    
    /**
     * Reports whether s1 and s2 (which are required to
     * be non-empty and distinct) begin with the same token.
     */
    public static boolean startWithSameToken (Scanner s1, Scanner s2)
    {
        return false;
    }
    
    /**
     * Reports whether s1 and s2 (which are required to
     * be non-empty non-empty) end with the same character.
     */
    public static boolean endWithSameChar (String s1, String s2)
    {
        return false;
    }
}
