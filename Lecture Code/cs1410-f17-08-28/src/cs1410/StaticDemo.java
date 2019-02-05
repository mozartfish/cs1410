package cs1410;

import cs1410lib.Dialogs;

/**
 * Demonstration of using and writing static methods
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
    public static void main (String[] args)
    {
        int n1 = showIntInputDialog();
        int n2 = showIntInputDialog("Enter an integer: ");
        int larger = Math.max(n1, n2);
        Dialogs.showMessageDialog("The larger is " + larger);
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
    public static double average (double d1, double d2, double d3)
    {
        return (d1 + d2 + d3) / 3.0;
    }

    // Other methods we could have done.
    // isTeenager
    // inchesToMeters 0.0254
    // poundsToKilograms 0.453592
    // bmi
    // isLowerCaseLetter
    // sumToN
}
