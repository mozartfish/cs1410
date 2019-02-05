package iterators;

import java.util.*;

public class IteratorDemo
{
    public static void main (String[] args)
    {
        forEachExample();
        // iteratorExample();
    }

    /**
     * Iterates through a linked list using a foreach loop and attempts to remove an element.
     */
    public static void forEachExample ()
    {
        List<Double> list = new LinkedList<>();
        for (double d = 0; d < 1000; d++)
        {
            list.add(d);
        }
        for (double d : list)
        {
            if (d == 500)
            {
                list.remove(d);
            }
        }
    }

    /**
     * Iterates through a linked list using an iterator and attempts to remove an element.
     */
    public static void iteratorExample ()
    {
        List<Double> list = new LinkedList<>();
        for (double d = 0; d < 1000; d++)
        {
            list.add(d);
        }

        ListIterator<Double> iter = list.listIterator();
        while (iter.hasNext())
        {
            double d = iter.next();
            if (d == 500.)
            {
                iter.remove();
            }
        }
    }
}
