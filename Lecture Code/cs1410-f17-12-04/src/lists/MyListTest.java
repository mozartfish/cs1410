package lists;

import static org.junit.Assert.*;
import org.junit.Test;

public class MyListTest
{
    private MyList makeList ()
    {
        return new MyLinkedList();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGet1 ()
    {
        MyList list = makeList();
        list.get(0);
    }

    @Test
    public void testGet2 ()
    {
        MyList list = makeList();
        list.addLast(7);
        assertEquals(7, list.get(0));
    }

    @Test
    public void testGet3 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i++)
        {
            list.addLast(i);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i, list.get(i));
        }
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSet1 ()
    {
        MyList list = makeList();
        list.get(0);
    }

    @Test
    public void testSet2 ()
    {
        MyList list = makeList();
        list.addLast(7);
        list.set(0, 8);
        assertEquals(8, list.get(0));
    }

    @Test
    public void testSet3 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i++)
        {
            list.addLast(2 * i);
        }
        for (int i = 0; i < 100; i++)
        {
            list.set(i, i + 1);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i + 1, list.get(i));
        }
    }

    @Test
    public void testSet4 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i++)
        {
            list.addFirst(2 * i);
        }
        for (int i = 0; i < 100; i++)
        {
            list.set(i, i + 1);
        }
        for (int i = 0; i < 100; i++)
        {
            assertEquals(i + 1, list.get(i));
        }
    }

    @Test
    public void testSize1 ()
    {
        MyList list = makeList();
        assertEquals(0, list.size());
    }

    @Test
    public void testSize2 ()
    {
        MyList list = makeList();
        list.addLast(9);
        assertEquals(1, list.size());
    }

    @Test
    public void testSize3 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i++)
        {
            list.addLast(i);
        }
        assertEquals(100, list.size());
    }

    @Test
    public void testSize4 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i++)
        {
            list.addFirst(i);
        }
        assertEquals(100, list.size());
    }

    @Test
    public void testSize5 ()
    {
        MyList list = makeList();
        for (int i = 0; i < 100; i += 2)
        {
            list.addLast(i);
            list.addFirst(i + 1);
        }
        assertEquals(100, list.size());

        for (int i = 0; i < 50; i++)
        {
            assertEquals(99 - 2 * i, list.get(i));
        }

        for (int i = 50; i < 100; i++)
        {
            assertEquals(2 * (i - 50), list.get(i));
        }
    }
}
