package lists;

public class MyListTimer
{
    public static void main (String[] args)
    {
        int size = 50000;

        System.out.println("Array list");
        doTiming(new MyArrayList(), size);
        System.out.println("\nLinked list");
        doTiming(new MyLinkedList(), size);

        size *= 2;
        System.out.println("\nArray list");
        doTiming(new MyArrayList(), size);
        System.out.println("\nLinked list");
        doTiming(new MyLinkedList(), size);
    }

    public static void doTiming (MyList list, int size)
    {
        for (int pass = 0; pass < 2; pass++)
        {
            long start = System.nanoTime();
            for (int i = 0; i < size; i++)
            {
                list.addFirst(i);
            }
            long stop = System.nanoTime();
            if (pass == 1)
            {
                System.out.printf("%.6f usec average over %d addFirsts\n", (stop - start) / 1e3 / size, size);
            }

            start = System.nanoTime();
            for (int i = 0; i < size; i++)
            {
                list.get(i);
            }
            stop = System.nanoTime();
            if (pass == 1)
            {
                System.out.printf("%.6f usec average over %d gets\n", (stop - start) / 1e3 / size, size);
            }
        }
    }
}
