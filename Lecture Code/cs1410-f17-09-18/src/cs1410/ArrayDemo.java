package cs1410;

import java.io.File;
import java.util.Arrays;
import java.util.Random;

public class ArrayDemo
{
    /**
     * Demonstrates aspects of arrays
     */
    public static void main (String[] args)
    {
        // The args parameter contains any command-line arguments that were
        // provided when the program was launched.
        printArray(args);
        System.out.println();

        // Notice how average takes a variable number of parameters
        System.out.println(average(1, 2, 3, 4, 5, 6, 7, 8, 9));
        System.out.println();

        // Here is a method that returns an array
        int[] integers = makeRandomArray(10);
        
        // Sort the array using a library method
        Arrays.sort(integers);
        
        // Print the sorted array
        System.out.println(Arrays.toString(integers));
        System.out.println();
        
        // Display the contents of a folder
        displayFolderContents("c:\\users\\zachary\\workspace-cs1410-f17\\cs1410-f17-09-18");
    }

    /**
     * Prints the elements of args
     */
    public static void printArray (String[] strings)
    {
        int i = 0;
        while (i < strings.length)
        {
            System.out.println(strings[i]);
            i++;
        }
    }

    /**
     * Returns the average of its parameters
     */
    public static double average (double first, double... rest)
    {
        // Note the use of a for loop
        double total = first;       
        for (int i = 0; i < rest.length; i++)
        {
            total += rest[i];
        }
        
        // Here is the equivalent while loop
        total = first;
        {
            int i = 0;
            while (i < rest.length)
            {
                total += rest[i];
                i++;
            }
        }
               
        return total / (rest.length + 1);
    }

    /**
     * Returns an array of length n containing randomly chosen integers between 0 and 9999, inclusive.
     */
    public static int[] makeRandomArray (int n)
    {
        Random rand = new Random();
        int[] randomArray = new int[n];
        for (int i = 0; i < n; i++)
        {
            randomArray[i] = rand.nextInt(1000);
        }
        return randomArray;
    }
    
    /**
     * Displays the contents of the named folder, twice.
     */
    public static void displayFolderContents (String folderPath)
    {
        File folder = new File(folderPath);
        File[] contents = folder.listFiles();
        
        // Note the user of a for each loop
        for (File f: contents)
        {
            System.out.println(f);
        }
        
        System.out.println();
        
        // Here is the equivalent for loop
        for (int i = 0; i < contents.length; i++)
        {
            File f = contents[i];
            System.out.println(f);
        }
    }
}
