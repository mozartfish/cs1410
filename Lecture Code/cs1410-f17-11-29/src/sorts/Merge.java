package sorts;

import java.util.Arrays;

public class Merge
{
    /**
     * Sorts A into ascending order
     */
    public static void sort (int[] A)
    {
        if (A.length > 1)
        {
            int[] left = Arrays.copyOfRange(A, 0, A.length / 2);
            int[] right = Arrays.copyOfRange(A, A.length / 2, A.length);
            sort(left);
            sort(right);
            merge(left, right, A);
        }
    }

    /**
     * left.length + right.length must be equal to dest.length, left and right must be sorted into ascending order.
     * Copies the elements of left and right into dest so that dest is in ascending order
     */
    public static void merge (int[] left, int[] right, int[] dest)
    {
        // Index into the left, right and dest arrays, respectively
        int indexL = 0;
        int indexR = 0;
        int indexD = 0;

        // Sweep through left and right, copying the smallest element into dest
        while (indexL < left.length && indexR < right.length)
        {
            if (left[indexL] < right[indexR])
            {
                dest[indexD] = left[indexL];
                indexD++;
                indexL++;
            }
            else
            {
                dest[indexD] = right[indexR];
                indexD++;
                indexR++;
            }
        }

        // Copy remaining elements of left into dest
        while (indexL < left.length)
        {
            dest[indexD] = left[indexL];
            indexD++;
            indexL++;
        }

        // Copy remaining elements of right into dest
        while (indexR < right.length)
        {
            dest[indexD] = right[indexR];
            indexD++;
            indexR++;
        }
    }
}
