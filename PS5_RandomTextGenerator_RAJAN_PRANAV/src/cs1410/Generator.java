package cs1410;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import cs1410lib.Dialogs;

/**
 * A set of methods used to generate random text from a book using a scanner, file, and integer values as parameters
 * 
 * @author Pranav Rajan
 *
 */
public class Generator
{

    public static void main (String[] args) throws FileNotFoundException
    {
        try
        {
            int levelOfAnalysis = getIntFromUser("Enter a desired level of analysis greater than or equal to zero");
            int length = getIntFromUser("Enter a desired length for output text greater than or equal to zero");
            Scanner scn = getFileFromUser("Select a text file to be analyzed", levelOfAnalysis);
            String generateRandomText = PS5Library.generateText(scn, levelOfAnalysis, length);
            Dialogs.showMessageDialog(generateRandomText);

        }
        catch (Exception e)
        {
            Dialogs.showMessageDialog("There was a problem with executing this program. Please try again.");
        }

    }

    /**
     * Takes a message from a dialog box and calls a new dialog box continuously prompting the user to enter a positive
     * value greater than or equal to zero for the analysis level and length that they desire.
     * 
     * @param userEntry
     * @return
     */
    public static int getIntFromUser (String userEntry)
    {
        while (true)
        {
            try
            {
                String input = Dialogs.showInputDialog(userEntry);
                if (input == null)
                    System.exit(0);
                int x = Integer.parseInt(input);
                if (x < 0)
                {
                    throw new NumberFormatException();
                }
                return x;

            }
            catch (Exception e)
            {
                Dialogs.showMessageDialog("You have entered an invalid value. Please try again.");
            }
        }
    }

    /**
     * Takes a message from the user and continuously prompts the user for a .txt file until they enter a .txt file.
     * 
     * @param title
     * @param levelOfAnalysis
     * @return
     */
    public static Scanner getFileFromUser (String title, int levelOfAnalysis)
    {
        while (true)
        {
            try
            {
                File file = Dialogs.showOpenFileDialog(title);
                if (file == null)
                {
                    System.exit(0);
                }
                if (file.length() < (levelOfAnalysis + 1))
                {
                    Dialogs.showMessageDialog("File does not have a sufficient number of text for analysis");
                    System.exit(0);
                }
                if (!file.getName().contains(".txt"))
                {
                    throw new Exception();
                }
                Scanner scn = new Scanner(file);
                return scn;
            }
            catch (Exception e)
            {
                Dialogs.showMessageDialog("You have a problem with the file. Please try again.");
            }

        }

    }

}
