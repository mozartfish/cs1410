package cs1410;

import cs1410lib.Dialogs;

public class Inches
{
    /**
     * Prompts the user for a distance in inches, then displays the
     * result of converting this into yards, feet, and inches.
     */
    public static void main (String[] args)
    {
        String input = Dialogs.showInputDialog("Enter number of inches: ");
        int inches = Integer.parseInt(input);
        int yards = inches / 36;
        inches = inches % 36;
        int feet = inches / 12;
        inches = inches % 12;
        String answer = yards + " yards " + feet + " feet "
                + inches + " inches ";
        Dialogs.showMessageDialog(answer);        
    }
}
