package step7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import cs1410lib.Dialogs;

public class MedianWithFile
{
    /**
     * Obtains a file from the user that contains only ints, computes the median and the mean, then writes those two
     * values to a file chosen by the user.
     */
    public static void main (String[] args)
    {
        // The list of numbers will eventually go here
        ArrayList<Integer> numbers;

        // Obtain the list of numbers, report any errors encountered
        try
        {
            File inputFile = Dialogs.showOpenFileDialog("Select a text file containing only ints");
            if (inputFile == null) return;
            Scanner scn = new Scanner(inputFile);
            numbers = getInts(scn);
        }
        catch (FileNotFoundException e)
        {
            Dialogs.showMessageDialog("Input file not found");
            return;
        }
        catch (InputMismatchException e)
        {
            Dialogs.showMessageDialog("Non-int found in input file");
            return;
        }
        catch (NoSuchElementException e)
        {
            Dialogs.showMessageDialog("Unable to read ints from input file");
            return;
        }

        // Write the output, report any errors encountered
        try
        {
            File outputFile = Dialogs.showSaveFileDialog("Select a text file to receive the output");
            if (outputFile == null) return;
            saveResults(numbers, outputFile);
        }
        catch (NoSuchElementException e)
        {
            Dialogs.showMessageDialog("Unable to read ints from input file");
        }
        catch (FileNotFoundException e)
        {
            Dialogs.showMessageDialog("Unable to open output file");
        }
        catch (IOException e)
        {
            Dialogs.showMessageDialog("Unable to write to output file");
        }
    }

    /**
     * Reads ints from the Scanner and returns a list containing them. If the Scanner is empty or there is a problem
     * reading it, throws a NoSuchElementException. If a non-int is encountered, throws an InputMismatchException.
     */
    public static ArrayList<Integer> getInts (Scanner scn)
    {
        ArrayList<Integer> lines = new ArrayList<Integer>();
        while (scn.hasNext())
        {
            lines.add(scn.nextInt());
        }
        return lines;
    }

    /**
     * Writes the mean and median of the numbers to the outputFile.  Throws NoSuchElementException
     * if numbers is empty; FileNotFoundException if the outputFile can't be opened; IOException if
     * there are any problems writing outputFile.
     */
    public static void saveResults (ArrayList<Integer> numbers, File outputFile) throws FileNotFoundException, IOException
    {
        // Make sure there is at least one number
        if (numbers.size() == 0) 
        {
            throw new NoSuchElementException();
        }
                
        // Compute the mean
        double sum = 0;
        for (int n: numbers)
        {
            sum += n;
        }       
        double mean = sum / numbers.size();
        
        // Compute the median
        Collections.sort(numbers);
        int median = numbers.get(numbers.size()/2);
        
        // Save the results
        try (PrintStream output = new PrintStream(outputFile))
        {
            output.println("Mean: " + mean);
            output.println("Median: " + median);
        }     
    }
}
