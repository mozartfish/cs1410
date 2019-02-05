package list4;

import java.util.Arrays;

/**
 * Methods for interacting with MyList objects.
 */
public class MyListMethods
{         
    public static void main (String[] args)
    {
        MyList list1 = construct();
        MyList list2 = construct();
        add(list1, 15);
        add(list2, 7);
        add(list1, 20);
        add(list2, 9);
        add(list1, 25);
        get(list1, 0);
        set(list1, 0, 8);
        size(list2);        
    }
    
    /**
     * Returns an empty list.
     */
    public static MyList construct ()
    {
        MyList list = new MyList();
        list.count = 0;
        list.data = new int[2];
        return list;
    }
    
    /**
     * Returns the element at the specified index of the list.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public static int get (MyList list, int index)
    {
        if (index < 0 || index >= list.count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return list.data[index];
    }
    
    /**
     * Changes the element at the specified index of the list.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public static void set (MyList list, int index, int val)
    {
        if (index < 0 || index >= list.count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        list.data[index] = val;
    }
    
    /**
     * Appends an element to the end of the list.
     */
    public static void add (MyList list, int val)
    {
        if (list.count == list.data.length)
        {
            list.data = Arrays.copyOf(list.data, 2*list.count);
        }
        list.data[list.count] = val;
        list.count++;
    }
    
    /**
     * Return the length of the list.
     */
    public static int size (MyList list)
    {
        return list.count;
    }
    
    /**
     * Removes the last element of the list.
     * Throws an ArrayIndexOutOfBoundsException
     * if this list is empty.
     */
    public static void remove (MyList list)
    {
        if (list.count == 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        list.count--;
    }
}
