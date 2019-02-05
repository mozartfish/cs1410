package list4;

public class ListTimer
{
    public static void main (String[] args)
    {
        // Add a lot of elements and see how long it takes
        long start = System.nanoTime();
        
        MyList list = MyListMethods.construct();
        for (int i = 0; i < 100000; i++)
        {
            MyListMethods.add(list, i);
        }
        
        long stop = System.nanoTime();
        System.out.println((stop-start) / 1e9 + " seconds");
    }
}
