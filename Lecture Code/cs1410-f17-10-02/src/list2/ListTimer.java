package list2;

import java.util.ArrayList;

public class ListTimer
{
    public static void main (String[] args)
    {
        // Add a lot of elements and see how long it takes
        long start = System.nanoTime();
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++)
        {
            list.add(i);
        }
        
        long stop = System.nanoTime();
        System.out.println((stop-start) / 1e9 + " seconds");
    }
}
