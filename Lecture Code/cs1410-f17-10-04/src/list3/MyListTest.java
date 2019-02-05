package list3;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyListTest
{
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testGet1 ()
    {
        MyList list = new MyList();
        list.get(0);
    }
    
    @Test
    public void testGet2 ()
    {
        MyList list = new MyList();
        list.add(7);
        assertEquals(7, list.get(0));
    }
    
    @Test
    public void testGet3 ()
    {
        MyList list = new MyList();
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i, list.get(i));
        }
        assertEquals(100, list.size());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testSet1 ()
    {
        MyList list = new MyList();
        list.set(0, 10);
    }
    
    @Test
    public void testSet2 ()
    {
        MyList list = new MyList();
        list.add(7);
        list.set(0, 8);
        assertEquals(8, list.get(0));
    }
    
    @Test
    public void testSet3 ()
    {
        MyList list = new MyList();
        for (int i = 0; i < 100; i++)
        {
            list.add(2*i);
        }
        for (int i = 0; i < 100; i++)
        {
            list.set(i, i+1);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i+1, list.get(i));
        }
    }

    @Test
    public void testSize1 ()
    {
        MyList list = new MyList();
        assertEquals(0, list.size());
    }
    
    @Test
    public void testSize2 ()
    {
        MyList list = new MyList();
        list.add(9);
        assertEquals(1, list.size());
    }
    
    @Test
    public void testSize3 ()
    {
        MyList list = new MyList();
        for (int i = 0; i < 100; i++)
        {
            list.add(i);
        }
        assertEquals(100, list.size());
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRemove1 ()
    {
        MyList list = new MyList();
        list.remove();
    }
    
    public void testRemove2 ()
    {
        MyList list = new MyList();
        list.add(17);
        list.add(18);
        list.remove();
        assertEquals(1, list.size());
        assertEquals(17, list.get(0));
    }
    
    public void testRemove3 ()
    {
        MyList list = new MyList();
        for (int i = 0; i < 100; i+=2)
        {
            list.add(i);
            list.add(i+1);
            list.remove();
        }

        assertEquals(50, list.size());
        for (int i = 0; i < 100; i+=2)
        {
            assertEquals(i*2, list.get(i));
        }
    }
}
