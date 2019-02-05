package lists;

/**
 * Provides simple linked lists of integers. A MyLinkedList contains zero or more integers that can be accessed via a
 * zero-based index.
 */
public class MyLinkedList implements MyList
{
    /** The size of this MyLinkedList */
    private int size;

    /** The linked list containing the elements of this MyLinkedList */
    private Link root;

    /**
     * Creates an empty list.
     */
    public MyLinkedList ()
    {
        root = null;
        size = 0;
    }

    /**
     * Returns the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public int get (int index)
    {
        return getLink(index).getValue();
    }

    /**
     * Changes the element at the specified index. If the index is invalid, throws an ArrayIndexOutOfBoundsException.
     */
    public void set (int index, int val)
    {
        getLink(index).setValue(val);
    }

    /**
     * Appends an element to the beginning of this list
     */
    public void addFirst (int val)
    {
        root = new Link(val, root);
        size++;
    }

    /**
     * Appends an element to the end of this list.
     */
    public void addLast (int val)
    {
        if (root == null)
        {
            addFirst(val);
        }
        else
        {
            getLink(size - 1).setNext(new Link(val, null));
            size++;
        }
    }

    private Link getLink (int index)
    {
        if (index < 0 || index >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        }
        Link node = root;
        while (index > 0)
        {
            node = node.getNext();
            index--;
        }
        return node;
    }

    /**
     * Returns the length of this list.
     */
    public int size ()
    {
        return size;
    }
}

/**
 * A single link node in a linked list
 */
class Link
{
    /** The value stored in this link */
    private int value;

    /** The next link, or null */
    private Link next;

    /**
     * Creates a link with the specified value and next
     */
    public Link (int value, Link next)
    {
        this.value = value;
        this.next = next;
    }

    /**
     * Returns the value of this Link
     */
    public int getValue ()
    {
        return value;
    }

    /**
     * Sets the value of this Link
     */
    public void setValue (int val)
    {
        value = val;
    }

    /**
     * Returns the next Link
     */
    public Link getNext ()
    {
        return next;
    }

    /**
     * Sets the next Link
     */
    public void setNext (Link link)
    {
        next = link;
    }
}
