package cs1410;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LectureProblems
{

    public static void main (String[] args) throws IOException
    {
        try (InputStream book = CollectionsDemo.class.getResourceAsStream("/books/PrideAndPrejudice.txt");
                Scanner input = new Scanner(book))
        {
            input.useDelimiter("[^a-zA-Z]+");
            System.out.println(problem4(input));
        }

    }

    /**
     * See Problem 1 on today's slides.
     */
    public static HashSet<String> problem1 (Scanner scn)
    {
        HashSet<String> words = new HashSet<>();
        if (scn.hasNext())
        {
            String word1 = scn.next().toLowerCase();
            while (scn.hasNext())
            {
                String word2 = scn.next().toLowerCase();
                // What goes here?
            }
        }
        return words;
    }

    /**
     * See Problem 2 on today's slides.
     */
    public static HashMap<String, Integer> problem2 (Scanner scn)
    {
        HashMap<String, Integer> wordCounts = new HashMap<>();
        if (scn.hasNext())
        {
            String word1 = scn.next().toLowerCase();
            while (scn.hasNext())
            {
                String word2 = scn.next().toLowerCase();
                if (word1.equals("elizabeth"))
                {
                    // What goes here?
                }
                word1 = word2;
            }
        }
        return wordCounts;
    }

    /**
     * See Problem 3 on today's slides.
     */
    public static HashMap<String, HashSet<String>> problem3 (Scanner scn)
    {
        HashMap<String, HashSet<String>> allFollowingWords = new HashMap<>();
        if (scn.hasNext())
        {
            String word1 = scn.next().toLowerCase();
            while (scn.hasNext())
            {
                String word2 = scn.next().toLowerCase();
                HashSet<String> words = allFollowingWords.get(word1);
                
                // What goes here?
                
                word1 = word2;
            }
        }
        return allFollowingWords;
    }
    
    /**
     * See Problem 4 on today's slides.
     */
    public static HashMap<String, HashMap<String, Integer>> problem4 (Scanner scn)
    {
        HashMap<String, HashMap<String, Integer>> allFollowingWordCounts = new HashMap<>();
        if (scn.hasNext())
        {
            String word1 = scn.next().toLowerCase();
            while (scn.hasNext())
            {
                String word2 = scn.next().toLowerCase();
                HashMap<String, Integer> wordCounts = allFollowingWordCounts.get(word1);
                
                // What goes here?
                
                word1 = word2;
            }
        }
        return allFollowingWordCounts;
    }

}
