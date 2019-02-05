package step6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import cs1410lib.Dialogs;

public class MedianLine
{
    /**
     * Prompts the user for a file, sorts the lines it contains, displays the line in the middle of the sort.
     */
    public static void main (String[] args)
    {
        File file = Dialogs.showOpenFileDialog("Select a text file");
        if (file == null) return;
        try (Scanner scn = new Scanner(file))
        {           
            String median = findMedianLine(scn);
            Dialogs.showMessageDialog(median);
        }
        catch (FileNotFoundException e)
        {
            Dialogs.showMessageDialog("No such file");
        }
        catch (NoSuchElementException e)
        {
            Dialogs.showMessageDialog("Unable to read lines");
        }
    }

    /**
     * Reads lines from the Scanner, sorts them, and returns the middle line of the sort. If the Scanner is empty or
     * there is a problem reading lines, throws a NoSuchElementException.
     */
    public static String findMedianLine (Scanner scn)
    {
        ArrayList<String> lines = new ArrayList<String>();
        while (scn.hasNextLine())
        {
            lines.add(scn.nextLine());
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
