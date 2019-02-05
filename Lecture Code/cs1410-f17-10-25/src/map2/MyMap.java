package map2;

import java.util.ArrayList;

/**
 * A MyMap object maps strings to strings.
 * 
 * @author Joe Zachary
 */
public class MyMap
{
    /**
     * Illustrates how MyMap objects work.
     */
    public static void main (String[] args)
    {
        MyMap m = new MyMap();
        m.put("Utah", "4");
        System.out.println(m.size());
        m.put("Ohio State", "1");
        m.put("Utah", "3");
        System.out.println(m.size());
        System.out.println(m.get("Ohio State"));
        System.out.println(m.get("Utah"));
        m.remove("Utah");
    }

    /**
     * For each item in the MyMap there is a Pair in pairs that contains the item's
     * key and value.  No key can occur more than once in pairs.
     */
    private ArrayList<Pair> pairs;

    /**
     * Creates an empty MyMap.
     */
    public MyMap ()
    {
        pairs = new ArrayList<>();
    }

    /**
     * If key already maps to something, replaces its mapping with key. Otherwise, adds a mapping from key to val.
     */
    public void put (String key, String val)
    {
        // If the key is already in Pairs, change its value
        for (Pair kvp: pairs)
        {
            if (kvp.getKey().equals(key))
            {
                kvp.setValue(val);
                return;
            }
        }
        
        // The key wasn't found it.  Add a new Pair.
        pairs.add(new Pair(key, val));
        
    }

    /**
     * Removes the mapping involving key (if one exists) or does nothing (otherwise).
     */
    public void remove (String key)
    {
        // Remove the Pair if it exists
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).getKey().equals(key))
            {
                pairs.remove(i);
                return;
            }
        }
    }

    /**
     * Returns the value to which key maps (if one exists) or null (otherwise).
     */
    public String get (String key)
    {
        // Return the value if the Pair exists
        for (Pair pair: pairs)
        {
            if (pair.getKey().equals(key))
            {
                return pair.getValue();
            }
        }
        
        // The key is unknown.
        return null;
    }

    /**
     * Returns the number of elements in this map.
     */
    public int size ()
    {
        return pairs.size();
    }
}
