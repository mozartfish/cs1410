package sorts;

import java.util.Arrays;

public class SortTimer
{
    public static void main (String[] args)
    {
        double previous = 0;
        for (int i = 10000; i < 10000000; i *= 2)
        {
            int[] A = SortTests.makeArray(i);
            long start = System.nanoTime();
            Selection.sort(A);
            //Merge.sort(A);
            //Arrays.sort(A);
            long stop = System.nanoTime();
            System.out.printf(i + "\t%.4f seconds", (stop - start) / 1e9);
            if (previous > 0)
            {
                System.out.printf("\t%.2f", (stop-start) / previous);
            }
            System.out.println();
            previous = stop - start;
        }
    }
}
