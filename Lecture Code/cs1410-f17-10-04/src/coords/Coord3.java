package coords;

/**
 * Here we have provided a constructor that initializes the instance variables
 * when an object is created.
 */
public class Coord3
{
    /**
     * Latitude in degrees.  NEVER MAKE INSTANCE VARIABLES PUBLIC.
     */
    public double lat;
    
    /**
     * Longitude in degrees.  NEVER MAKE INSTANCE VARIABLES PUBLIC.
     */
    public double lon;
    
    /**
     * Creates a point with the specified lat and lon (in degrees).  Note
     * that a constructor always has the same name as the class and has
     * no return type.
     */
    public Coord3(double latCoord, double lonCoord)
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
}
