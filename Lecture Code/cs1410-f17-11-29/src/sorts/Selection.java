package sorts;

public class Selection
{
    /**
     * Sorts A into ascending order
     */
    public static void sort (int[] A)
    {
        for (int i = 0; i < A.length - 1; i++)
        {
            int smallest = indexOfSmallest(A, i);
            swap(A, i, smallest);
        }
    }

    /**
     * Returns the index of the smallest element in the portion of A beginning at index i
     */
    public static int indexOfSmallest (int[] A, int i)
    {
        int index = i;
        while (i < A.length)
        {
            if (A[i] < A[index])
            {
                index = i;
            }
            i++;
        }
        return index;
    }

    /**
     * Swaps the elements at indexes i and j of A
     */
    public static void swap (int[] A, int i, int j)
    {
        int saved = A[i];
        A[i] = A[j];
        A[j] = saved;
    }
}
