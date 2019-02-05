package list2;

import java.util.Arrays;

/**
 * A simple dynamic list of integers.  
 * A MyList contains zero or more integers that can 
 * be accessed and changed via a zero-based index.
 * It is possible to add new elements to the end.
 */
public class MyList
{         
    public static void main (String[] args)
    {
        MyList list1 = new MyList();
        MyList list2 = new MyList();
        list1.add(15);
        list2.add(7);
        list1.add(20);
        list2.add(9);
        list1.add(25);
        list1.get(0);
        list1.set(0, 8);
        list2.size();        
    }
       
    /**
     * The number of elements in this list
     */
    private int count;
        
    /** 
     * The elements in this list are stored in data[0] through
     * data[count-1].  The length of data must be >= count.
     */
    private int[] data;
    
    /**
     * Creates an empty list.
     */
    public MyList ()
    {
        data = new int[2];
        count = 0;
    }
    
    /**
     * Returns the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        if (index < 0 || index >= count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[index];
    }
    
    /**
     * Changes the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val)
    {
        if (index < 0 || index >= count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        data[index] = val;
    }
    
    /**
     * Appends an element to the end of this list.
     */
    public void add (int val)
    {
        if (data.length == count)
        {
            data = Arrays.copyOf(data, count*2);
        }        
        data[count] = val;
        count++;
    }
    
    /**
     * Return the length of this list.
     */
    public int size ()
    {
        return count;
    }
}
