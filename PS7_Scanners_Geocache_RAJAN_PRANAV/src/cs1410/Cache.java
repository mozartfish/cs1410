package cs1410;

/**
 * Represents a variety of information about a geocache. A geocache has a title, an owner, a difficulty rating, a
 * terrain rating, a GC code, a latitude, and a longitude.
 */
public class Cache
{
    /**
     * represents a geoCache
     */
    private String[] cache;
    /**
     * represents the title of the geoCache
     */
    private String title;
    /**
     * represents the owner of a geoCache
     */
    private String owner;
    /**
     * represents the geoCache code
     */
    private String GCCode;
    /**
     * variable used to check and see if geoCache code follows format of geoCache code specification
     */
    private String checkCode;
    /**
     * represents a latitude
     */
    private String lat;
    /**
     * represents a longitude
     */
    private String lon;
    /**
     * represents the difficulty rating
     */
    private double difficultyRating;
    /**
     * variable used to check whether the difficultyRating is of type double
     */
    private double difficultyCheck;
    /**
     * represents the terrain rating
     */
    private double terrainRating;
    /**
     * variable used to check whether terrainRating is of type double
     */
    private double terrainCheck;

    /**
     * Creates a Cache from a string that consists of these seven cache attributes: the GC code, the title, the owner,
     * the difficulty rating, the terrain rating, the latitude, and the longitude, in that order, separated by single
     * TAB ('\t') characters.
     * 
     * If any of the following problems are present, throws an IllegalArgumentException:
     * <ul>
     * <li>Fewer than seven attributes</li>
     * <li>More than seven attributes</li>
     * <li>A GC code that is anything other than "GC" followed by one or more upper-case letters and/or digits</li>
     * <li>A difficulty or terrain rating that parses to anything other than the doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5,
     * or 5.</li>
     * <li>A title, owner, latitude, or longitude that consists only of white space</li>
     */

    // Constructor
    /**
     * takes a string as a parameter and creates a new cache object which contains a GC code, the title of a cache, the
     * owner of a cache, the difficulty rating, the terrain rating, the latitude and the longitude in that order of the
     * cache
     * 
     * @param attributes
     */
    public Cache (String attributes)
    {
        // create an empty array that represents a new cache
        cache = new String[0];

        // Break the string into an array that contains the seven attributes
        String[] cacheInfo = attributes.split("\t");

        // check if there are exactly seven attributes
        if (cacheInfo.length != 7)
        {
            throw new IllegalArgumentException("String does not contain exactly seven attributes");
        }

        // check if GC code follows GC code specifications
        checkCode = cacheInfo[0];
        if ((checkCode.charAt(0) != 'G'))
        {
            throw new IllegalArgumentException("GC code does not follow GC code specifications");
        }
        if ((checkCode.charAt(1) != 'C'))
        {
            throw new IllegalArgumentException("GC code does not follow GC code specifications");
        }
        if (checkCode.length() < 3)
        {
            throw new IllegalArgumentException("GC code does not follow GC code specifications");
        }
        for (int i = 0; i < checkCode.substring(2).length(); i++)
        {
            if (Character.isDigit(checkCode.charAt(i)) == false)
            {
                if (Character.isUpperCase(checkCode.charAt(i)) == false)
                {
                    throw new IllegalArgumentException("GC code does not follow GC code specifications");
                }
            }
        }

        // check if difficulty or terrain parse to anything other than the doubles 1, 1.5, 2, 2.5, 3, 3.5, 4, 4.5, or 5
        try
        {
            difficultyCheck = Double.parseDouble(cacheInfo[3]);
            terrainCheck = Double.parseDouble(cacheInfo[4]);

        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Terrain rating and Difficulty rating are not of type double");
        }
        if ((terrainCheck < 1.0) || (terrainCheck > 5.0))
        {
            throw new IllegalArgumentException("Terrain rating is not between a rating of 1.0 and 5.0");
        }
        if ((difficultyCheck < 1.0) || (difficultyCheck > 5.0))
        {
            throw new IllegalArgumentException("Difficulty rating is not between a rating of 1.0 and 5.0");
        }

        // check if title consists of white space
        if (cacheInfo[1].equals(""))
        {
            throw new IllegalArgumentException("Title consists only of whitespace");
        }

        // check if owner consists of whitespace
        if (cacheInfo[2].equals(""))
        {
            throw new IllegalArgumentException("Owner consists only of whitespace");
        }

        // check if latitude consists only of whitespace
        if (cacheInfo[5].equals(""))
        {
            throw new IllegalArgumentException("Latitude consists only of whitespace");
        }

        // check if latitude parses to a double

        // check if longitude consists only of whitespace
        if (cacheInfo[6].equals(""))
        {
            throw new IllegalArgumentException("Longitude consists only of whitespace");
        }

        cache = cacheInfo;

    }

    /**
     * Converts this cache to a string
     */
    public String toString ()
    {
        return getTitle() + " by " + getOwner();
    }

    /**
     * Returns the owner of this cache
     */
    public String getOwner ()
    {
        owner = cache[2];
        return owner;
    }

    /**
     * Returns the title of this cache
     */
    public String getTitle ()
    {
        title = cache[1];
        return title;
    }

    /**
     * Returns the difficulty rating of this cache
     */
    public double getDifficulty ()
    {
        difficultyRating = difficultyCheck;
        return difficultyRating;
    }

    /**
     * Returns the terrain rating of this cache
     */
    public double getTerrain ()
    {
        terrainRating = terrainCheck;
        return terrainRating;
    }

    /**
     * Returns the GC code of this cache
     */
    public String getGcCode ()
    {
        GCCode = cache[0];
        return GCCode;
    }

    /**
     * Returns the latitude of this cache
     */
    public String getLatitude ()
    {
        lat = cache[5];
        return lat;
    }

    /**
     * Returns the longitude of this cache
     */
    public String getLongitude ()
    {
        lon = cache[6];
        return lon;
    }
}
