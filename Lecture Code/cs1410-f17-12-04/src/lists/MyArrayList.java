package lists;

import java.util.Arrays;

/**
 * Provides simple dynamic lists of integers. A MyList contains zero or more integers that can be accessed via a
 * zero-based index.
 */
public class MyArrayList implements MyList
{
    /** The first size elements of this array contaon the numbers on this MyArrayList */
    private int[] elements;

    /** The size of this MyArrayList */
    private int size;

    /**
     * Creates an empty list.
     */
    public MyArrayList ()
    {
        elements = new int[2];
        size = 0;
    }

    /**
     * Returns the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        if (index >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        else
        {
            return elements[index];
        }
    }

    /**
     * Changes the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val)
    {
        if (index >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        else
        {
            elements[index] = val;
        }
    }

    /**
     * Appends an element to the beginning of this list
     */
    public void addFirst (int val)
    {
        if (elements.length == size)
        {
            int[] newElements = new int[size * 2];
            System.arraycopy(elements, 0, newElements, 1, size);
            elements = newElements;
        }
        else
        {
            System.arraycopy(elements, 0, elements, 1, size);
        }
        elements[0] = val;
        size++;
    }

    /**
     * Appends an element to the end of this list.
     */
    public void addLast (int val)
    {
        if (elements.length == size)
        {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
        elements[size] = val;
        size++;
    }

    /**
     * Return the length of this list.
     */
    public int size ()
    {
        return size;
    }
}
