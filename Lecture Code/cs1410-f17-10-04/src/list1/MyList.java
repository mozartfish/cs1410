package list1;

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
     * Contains the numbers from the list appended
     * together, separated by spaces.  The numbers
     * are ordered left to right by index.
     * 
     * "1 4 7" represents {1, 4, 7}
     * "" represents { }
     */
    private String numberList;
    
    /**
     * The size of the list
     */
    private int count;
    
    /**
     * Creates an empty list.
     */
    public MyList ()
    {
        numberList = "";
        count = 0;
    }
    
    /**
     * Returns the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        return 0;
    }
    
    /**
     * Changes the element at the specified index.  
     * If the index is invalid, throws an 
     * ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val)
    {
    }
    
    /**
     * Appends an element to the end of this list.
     */
    public void add (int val)
    {
        if (numberList.length() == 0)
        {
            numberList = val + "";
        }
        else
        {
            numberList = numberList + " " + val;
        }
        count++;
    }
    
    /**
     * Return the length of this list.
     */
    public int size ()
    {
        return count;
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
        else if (size() == 1)
        {
            numberList = "";
        }
        else
        {
            numberList = 
              numberList.substring(0, numberList.lastIndexOf(' '));
        }
        count--;
    }
}
