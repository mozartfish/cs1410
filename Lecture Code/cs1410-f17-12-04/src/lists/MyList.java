package lists;

/**
 * Provides simple dynamic lists of integers. A MyList contains zero or more integers that can be accessed via a
 * zero-based index.
 */
public interface MyList
{
    /**
     * Returns the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public int get (int index);

    /**
     * Changes the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val);

    /**
     * Appends an element to the beginning of this list
     */
    public void addFirst (int val);

    /**
     * Appends an element to the end of this list.
     */
    public void addLast (int val);

    /**
     * Returns the length of this list.
     */
    public int size ();
}
