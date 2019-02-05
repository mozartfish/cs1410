package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class CollectionsDemo
{
    /**
     * Things we'll try:
     * 
     * How many different characters in the book?
     * How many different words in the book?
     * What are the most common words in the book?  How often do they occur?
     */
    public static void main (String[] args) throws IOException
        {
            // Opens a file in this project's part of the workspace
            try (InputStream book = CollectionsDemo.class.getResourceAsStream("/books/PrideAndPrejudice.txt");
                    Scanner input = new Scanner(book))
            {
                System.out.println(countWords(input));
            }
        }
    
    public static int countWords (Scanner input)
    {
        TreeSet<String> allWords = new TreeSet<>();
        while (input.hasNext())
        {
            String word = input.next();
            allWords.add(word.toLowerCase());
        }
        System.out.println(allWords);
        return allWords.size();
    }

    private static int countChars (Scanner input)
    {
        HashSet<Character> allChars = new HashSet<>();
        while (input.hasNextLine())
        {
            String line = input.nextLine();
            for (int i = 0; i < line.length(); i++)
            {
                allChars.add(Character.toLowerCase(line.charAt(i)));
            }
            allChars.add('\n');
        }
        System.out.println(allChars);
        return allChars.size();
    }
}

