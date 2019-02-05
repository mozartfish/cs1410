package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Search
{
    public static void main (String[] args) throws FileNotFoundException
    {
        System.out.println("No caching vs. caching");
        timeDirectLookups(1000);
        System.out.printf("  Read file every time: %.3f msec\n", timeDirectLookups(10));
        timeCachedLookupsLinear(1000, true);
        System.out.printf("  Read file only once: %.3f msec\n", timeCachedLookupsLinear(10, true));

        System.out.println("\nLinear search vs. binary search, not counting time to read dictionary");
        timeCachedLookupsLinear(1000, false);
        System.out.printf("  Linear search: %.3f msec\n", timeCachedLookupsLinear(1000, false));
        timeCachedLookupsBinary(1000, false);
        System.out.printf("  Binary search: %.6f msec\n", timeCachedLookupsBinary(1000, false));
    }

    /**
     * Returns the average number of seconds required to look up a non-existent word when the words are read directly
     * from a file. The number of repetitions to average over is given.
     */
    public static double timeDirectLookups (int repts) throws FileNotFoundException
    {
        long start = System.nanoTime();
        for (int i = 0; i < repts; i++)
        {
            try (Scanner dict = new Scanner(new File("dictionary.txt")))
            {
                findWordInScanner(dict, "lkjsdkf");
            }
        }
        long stop = System.nanoTime();
        return (stop - start) / 1e6 / repts;
    }

    /**
     * Reports whether or not w appears as a token in s. Does a linear sequence of direct reads from s.
     */
    public static boolean findWordInScanner (Scanner s, String w)
    {
        while (s.hasNext())
        {
            if (s.next().equals(w))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the average number of seconds required to look up a non-existent word when the words are first read into
     * an array. A linear search is used. The number of repetitions to average over is given. The words are read into a
     * file only prior to the first repetition. Whether or not the time required to read the dictionary is timed can be
     * controlled.
     */
    public static double timeCachedLookupsLinear (int repts, boolean timeDictionaryRead) throws FileNotFoundException
    {
        long start = System.nanoTime();
        ArrayList<String> dictionary = new ArrayList<String>();
        try (Scanner dict = new Scanner(new File("dictionary.txt")))
        {
            while (dict.hasNext())
            {
                dictionary.add(dict.next());
            }
        }

        if (!timeDictionaryRead)
        {
            start = System.nanoTime();
        }

        for (int i = 0; i < repts; i++)
        {
            findWordInArrayLinear(dictionary, "lkjsdkf");
        }

        long stop = System.nanoTime();
        return (stop - start) / 1e6 / repts;
    }

    /**
     * Returns the average number of seconds required to look up a non-existent word when the words are first read into
     * an array. A binary search is used. The number of repetitions to average over is given. The words are read into a
     * file only prior to the first repetition. Whether or not the time required to read the dictionary is timed can be
     * controlled.
     */
    public static double timeCachedLookupsBinary (int repts, boolean timeDictionaryRead) throws FileNotFoundException
    {
        long start = System.nanoTime();
        ArrayList<String> dictionary = new ArrayList<String>();
        try (Scanner dict = new Scanner(new File("dictionary.txt")))
        {
            while (dict.hasNext())
            {
                dictionary.add(dict.next());
            }
        }

        if (!timeDictionaryRead)
        {
            start = System.nanoTime();
        }

        for (int i = 0; i < repts; i++)
        {
            findWordInArrayBinary(dictionary, "lkjsdkf");
        }
        long stop = System.nanoTime();
        return (stop - start) / 1e6 / repts;
    }

    /**
     * Reports whether or not w appears in the ArrayList a. Does a linear search.
     */
    public static boolean findWordInArrayLinear (ArrayList<String> a, String w)
    {
        for (String word : a)
        {
            if (word.equals(w))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Reports whether or not w appears in the ArrayList a. Does a binary search.
     */
    public static boolean findWordInArrayBinary (ArrayList<String> a, String w)
    {
        int lo = 0;
        int hi = a.size() - 1;
        while (lo < hi)
        {
            int mid = (lo + hi) / 2;
            int comp = a.get(mid).compareTo(w);
            if (comp == 0)
            {
                return true;
            }
            else if (comp < 0)
            {
                lo = mid + 1;
            }
            else
            {
                hi = mid - 1;
            }
        }
        return false;
    }
}
