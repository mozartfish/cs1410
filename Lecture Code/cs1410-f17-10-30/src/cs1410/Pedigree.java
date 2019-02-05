package cs1410;

/**
 * Represents the pedigree (ancestor tree) of an individual
 */
public class Pedigree
{
    // Name, year of birth, pedigree of father, pedigree of mother
    // If father (mother) is unknown, father (mother) is null
    private String name;
    private int year;
    private Pedigree father;
    private Pedigree mother;
    
    /**
     * Creates a Pedigree from its parts.  If a father or mother is unknown,
     * pass in null as the parameter.
     */
    public Pedigree (String name, int year, Pedigree father, Pedigree mother)
    {
        this.name = name;
        this.year = year;
        this.father = father;
        this.mother = mother;
    }
    
    /**
     * Returns name of individual at root of Pedigree
     */
    public String getName ()
    {
        return name;
    }
    
    /**
     * Returns birth year of individual at root of Pedigree
     */
    public int getBirthYear ()
    {
        return year;
    }
    
    /**
     * Returns Pedigree of father, or null if unknown
     */
    public Pedigree getFather ()
    {
        return father;
    }
    
    /**
     * Returns Pedigree of mother, or null if unknown
     */
    public Pedigree getMother ()
    {
        return mother;
    }
}
