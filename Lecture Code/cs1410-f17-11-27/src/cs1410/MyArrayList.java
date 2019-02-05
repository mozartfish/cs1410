    package cs1410;

import java.util.Arrays;

/**
 * Provides objects that represent dynamic arrays of integers. These objects are
 * mutable.
 */
public class MyArrayList
{
    /**
     * Test driver for MyArrayList
     */
    public static void main (String[] args)
    {
        MyArrayList list = new MyArrayList();
        for (int i = 0; i < 5; i++)
        {
            list.addLast(i);
        }
        list.set(3, 8);
        list.removeLast();
    }

    // The numbers in this dynamic array are the ones stored
    // in the first "count" positions of "elements"
    private int[] elts;
    private int count;

    /**
     * Constructs an empty dynamic array
     */
    public MyArrayList()
    {
        this.elts = new int[4];
        this.count = 0;
    }

    /**
     * Returns the length of this dynamic array
     */
    public int size ()
    {
        return this.count;
    }

    /**
     * Extends the size of this dynamic array by one location, then stores e in
     * the new location.
     */
    public void addLast (int e)
    {
        if (this.count == this.elts.length)
        {
            this.elts = Arrays.copyOf(this.elts, this.count * 2);
        }
        this.elts[this.count] = e;
        this.count++;
    }

    /**
     * Shrinks the size of this dynamic array by one location, removing the last
     * element in the process. If this dynamic array is already empty, throws an
     * ArrayIndexOutOfBoundsException.
     */
    public void removeLast ()
    {
        if (count == 0)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        count--;
    }

    /**
     * Returns the element stored at the specified index of this dynamic array,
     * if 0 <= index < size(). Otherwise, throws an
     * ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        if (index >= count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        return elts[index];
    }

    /**
     * Changes the element stored at the specified index of this dynamic array
     * to value, if 0 <= index < size(). Otherwise, throws an
     * ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int value)
    {
        if (index >= count)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        elts[index] = value;
    }

    /**
     * Returns a string version of this dynamic array.
     */
    public String toString ()
    {
        String result = "[";
        if (count > 0)
        {
            result += elts[0];
        }
        for (int i = 1; i < count; i++)
        {
            result += ", " + elts[i];
        }
        return result + "]";
    }

}
