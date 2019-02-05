package list2;

import java.util.Arrays;

/**
 * A simple dynamic list of integers.  
 * A MyList contains zero or more integers that can 
 * be accessed and changed via a zero-based index.
 * It is possible to add new elements to the end
 * and to remove elements from the end.
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
        list2.remove();
    }
    
    /** 
     * Holds the elements of the list.  Is
     * exactly the same length as the list.
     * Element i of the list is stored in data[i].
     */
    private int[] data;
    
    /**
     * Creates an empty list.
     */
    public MyList ()
    {
        data = new int[0];
    }
    
    /**
     * Returns the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        return data[index];
    }
    
    /**
     * Changes the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val)
    {
        data[index] = val;
    }
    
    /**
     * Appends an element to the end of this list.
     */
    public void add (int val)
    {
        data = Arrays.copyOf(data, data.length + 1);
        data[data.length - 1] = val;
    }
    
    /**
     * Return the length of this list.
     */
    public int size ()
    {
        return data.length;
    }
    
    /**
     * Removes the last element of this list.
     * Throws an ArrayIndexOutOfBoundsException
     * if this list is empty.
     */
    public void remove ()
    {
        if (size() == 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        data = Arrays.copyOf(data, data.length - 1);
    }
}
