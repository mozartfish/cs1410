package sorts;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;

public class SortTests
{
    /**
     * Creates an array containing the elements 0 through size-1 and arranges the elements into a random permutation
     */
    public static int[] makeArray (int size)
    {
        Random rand = new Random();
        int[] A = new int[size];
        for (int i = 0; i < size; i++)
        {
            A[i] = i;
        }
        for (int i = 0; i < A.length; i++)
        {
            int index = rand.nextInt(A.length - i);
            int saved = A[i];
            A[i] = A[index];
            A[index] = saved;
        }
        return A;
    }

    /**
     * Asserts that A contains the integers 0 .. A.length-1 arranged into ascending order.
     */
    public static void assertArraySorted (int[] A)
    {
        for (int i = 0; i < A.length; i++)
        {
            assertEquals(i, A[i]);
        }
    }

    @Test
    public void testSelectionSort ()
    {
        int[] A = makeArray(0);
        Selection.sort(A);
        assertArraySorted(A);

        A = makeArray(1);
        Selection.sort(A);
        assertArraySorted(A);

        A = makeArray(2);
        Selection.sort(A);
        assertArraySorted(A);

        A = makeArray(100);
        Selection.sort(A);
        assertArraySorted(A);
    }

    @Test
    public void testMergeSort ()
    {
        int[] A = makeArray(0);
        Merge.sort(A);
        assertArraySorted(A);

        A = makeArray(1);
        Merge.sort(A);
        assertArraySorted(A);

        A = makeArray(2);
        Merge.sort(A);
        assertArraySorted(A);

        A = makeArray(100);
        Merge.sort(A);
        assertArraySorted(A);
    }
}
