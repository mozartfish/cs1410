package map2;

/**
 * Provides key/value pairs.
 */
class Pair
{
    /**
     * The key portion of this pair
     */
    private String key;
    
    /**
     * The value portion of this pair
     */
    private String value;
    
    /**
     * Constructs a pair from the given key and value
     */
    public Pair (String key, String value)
    {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Returns the key from this pair
     */
    public String getKey ()
    {
        return key;
    }
    
    /**
     * Returns the value from this pair
     */
    public String getValue ()
    {
        return value;
    }
    
    /**
     * Sets the value of this pair
     */
    public void setValue (String value)
    {
        this.value = value;
    }
}
