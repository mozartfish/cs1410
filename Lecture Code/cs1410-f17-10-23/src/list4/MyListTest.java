package list4;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyListTest
{
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testGet1 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.get(list, 0);
    }
    
    @Test
    public void testGet2 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.add(list, 7);
        assertEquals(7, MyListMethods.get(list, 0));
    }
    
    @Test
    public void testGet3 ()
    {
        MyList list = MyListMethods.construct();
        for (int i = 0; i < 100; i++)
        {
            MyListMethods.add(list, i);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i, MyListMethods.get(list, i));
        }
        assertEquals(100, MyListMethods.size(list));
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSet1 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.set(list, 0, 10);
    }
    
    @Test
    public void testSet2 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.add(list, 7);
        MyListMethods.set(list, 0, 8);
        assertEquals(8, MyListMethods.get(list, 0));
    }
    
    @Test
    public void testSet3 ()
    {
        MyList list = MyListMethods.construct();
        for (int i = 0; i < 100; i++)
        {
            MyListMethods.add(list, 2*i);
        }
        for (int i = 0; i < 100; i++)
        {
            MyListMethods.set(list, i, i+1);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i+1, MyListMethods.get(list, i));
        }
    }

    @Test
    public void testSize1 ()
    {
        MyList list = MyListMethods.construct();
        assertEquals(0, MyListMethods.size(list));
    }
    
    @Test
    public void testSize2 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.add(list, 9);
        assertEquals(1, MyListMethods.size(list));
    }
    
    @Test
    public void testSize3 ()
    {
        MyList list = MyListMethods.construct();
        for (int i = 0; i < 100; i++)
        {
            MyListMethods.add(list, i);
        }
        assertEquals(100, MyListMethods.size(list));
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemove1 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.remove(list);
    }
    
    public void testRemove2 ()
    {
        MyList list = MyListMethods.construct();
        MyListMethods.add(list, 17);
        MyListMethods.add(list, 18);
        MyListMethods.remove(list);
        assertEquals(1, MyListMethods.size(list));
        assertEquals(17, MyListMethods.get(list, 0));
    }
    
    public void testRemove3 ()
    {
        MyList list = MyListMethods.construct();
        for (int i = 0; i < 100; i+=2)
        {
            MyListMethods.add(list, i);
            MyListMethods.add(list, i+1);
            MyListMethods.remove(list);
        }

        assertEquals(50, MyListMethods.size(list));
        for (int i = 0; i < 100; i+=2)
        {
            assertEquals(i*2, MyListMethods.get(list, i));
        }
    }
}
