package step6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import cs1410lib.Dialogs;

public class MedianInt
{
    /**
     * Prompts the user for a file that contains only int literals, sorts the ints it contains, 
     * and displays the median integer.
     */
    public static void main (String[] args)
    {
        try
        {
            File file = Dialogs.showOpenFileDialog("Select a text file containing only ints");
            if (file == null) return;
            Scanner scn = new Scanner(file);
            int median = findMedianInt(scn);
            Dialogs.showMessageDialog(median + "");
        }
        catch (FileNotFoundException e)
        {
            Dialogs.showMessageDialog("No such file");
        }
        catch (InputMismatchException e)
        {
            Dialogs.showMessageDialog("Non-int found");
        }
        catch (NoSuchElementException e)
        {
            Dialogs.showMessageDialog("Unable to read ints");
        }
    }

    /**
     * Reads ints from the Scanner and returns the median int. If the Scanner is empty or 
     * there is a problem reading it, throws a NoSuchElementException.  If a non-int is
     * encountered, throws an InputMismatchException.
     */
    public static int findMedianInt (Scanner scn)
    {
        ArrayList<Integer> lines = new ArrayList<Integer>();
        while (scn.hasNext())
        {
            lines.add(scn.nextInt());
        }
        if (lines.size() > 0)
        {
            Collections.sort(lines);
            return lines.get(lines.size() / 2);
        }
        else
        {
            throw new NoSuchElementException();
        }
    }
}
