package step5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import cs1410lib.Dialogs;

public class LineCount
{
    /**
     * Obtains a file from the user and displays the number of lines it contains.
     */
    public static void main (String[] args)
    {
        File file = Dialogs.showOpenFileDialog("Select a text file");
        try (Scanner scn = new Scanner(file))
        {
            int count = 0;
            while (scn.hasNextLine())
            {
                scn.nextLine();
                count++;
            }

            Dialogs.showMessageDialog("The file contains " + count + " lines");
        }
        catch (FileNotFoundException e)
        {
            Dialogs.showMessageDialog("Couldn't open file");
        }
    }
}
