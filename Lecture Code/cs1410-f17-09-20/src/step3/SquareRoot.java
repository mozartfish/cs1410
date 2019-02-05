package step3;

import cs1410lib.Dialogs;

public class SquareRoot
{
    /**
     * Obtains a number from the user and displays its square root.
     */
    public static void main (String[] args)
    {
        while (true)
        {
            try
            {
                String input = Dialogs.showInputDialog("Enter a non-negative number");
                if (input == null) return;
                double x = Double.parseDouble(input);
                if (x < 0) 
                {
                    throw new NumberFormatException();
                }
                Dialogs.showMessageDialog("The square root is " + Math.sqrt(x));
            }
            catch (NumberFormatException e)
            {
                Dialogs.showMessageDialog("Bad input");
            }
        }
    }
}
