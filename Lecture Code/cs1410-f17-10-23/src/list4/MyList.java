package list4;

/**
 * A simple dynamic list of integers.  
 * A MyList contains zero or more integers that can 
 * be accessed and changed via a zero-based index.
 * It is possible to add new elements to the end.
 */
public class MyList
{         
    /**
     * The number of elements in this list
     */
    public int count;
        
    /** 
     * The elements in this list are stored in data[0] through
     * data[count-1].  The length of data must be >= count.
     */
    public int[] data;
}
