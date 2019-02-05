package coords;

/**
 * Defines a new type of object (Coord2) with two public instance variables (also known as member variables or fields)
 * and a (default) zero-argument constructor. 
 * 
 * Every Coord2 object will contain its own instances of the two variables.
 * 
 * Making instance variables public is NOT a good idea.
 */
public class Coord2
{
    /**
     * Latitude in degrees.  NEVER MAKE INSTANCE VARIABLES PUBLIC.
     */
    public double lat;
    
    /**
     * Longitude in degrees.  NEVER MAKE INSTANCE VARIABLES PUBLIC.
     */
    public double lon;
}
