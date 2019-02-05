package coords;

/**
 * Here we have made the instance variables private and added
 * two methods.
 */
public class Coord4
{
    /**
     * Latitude in degrees.
     */
    private double lat;
    
    /**
     * Longitude in degrees.
     */
    private double lon;
    
    /**
     * Creates a point with the specified lat and lon (in degrees).  Note
     * that a constructor always has the same name as the class and has
     * no return type.
     */
    public Coord4(double latCoord, double lonCoord)
    {
        // Make sure the latitude is valid
        if (latCoord < -90 || latCoord > 90)
        {
            throw new IllegalArgumentException("Invalid latitude: " + latCoord);
        }
        
        // Make sure the longiude is valid
        if (lonCoord < -180 || lonCoord > 180)
        {
            throw new IllegalArgumentException("Invalid longitude: " + lonCoord);
        }
        
        // Initialize the instance variables
        lat = latCoord;
        lon = lonCoord;
    }
    
    /**
     * Returns the latitude of this coordinate.
     */
    public double getLatitude ()
    {
        return lat;
    }
    
    /**
     * Returns the longitude of this coordinate.
     */
    public double getLongitude ()
    {
        return lon;
    }
}
