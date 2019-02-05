package cs1410;

/**
 * Collection of methods that maniulate Pedigrees
 */
public class PedigreeDemo
{
    /**
     * Runs the methods below.
     */
    public static void main (String[] args)
    {
        // Make a sample pedigree
        Pedigree ben = makePedigree();

        // Sample pedigree manipulation
        System.out.println("Birth year of father of mother of Ben: " + ben.getMother().getFather().getBirthYear());
        System.out.println("Ben's mother: " + ben.getMother().getName());

        // Display information about pedigree
        System.out.println("Size: " + size(ben));
        System.out.println("Depth: " + depth(ben));
        System.out.println("Oldest: " + oldest(ben));
        System.out.println("Names: ");
        printNames(ben);
        System.out.println("All names: " + allNames(ben));
        System.out.println("Has 1850: " + hasBirthYear(ben, 1850));
        System.out.println("Has 1958: " + hasBirthYear(ben, 1958));
    }

    /**
     * Returns a sample Pedigree
     */
    public static Pedigree makePedigree ()
    {
        Pedigree james = new Pedigree("James", 1850, null, null);
        Pedigree larry = new Pedigree("Larry", 1890, james, null);
        Pedigree chris = new Pedigree("Chris", 1894, null, null);
        Pedigree wayne = new Pedigree("Wayne", 1892, null, null);
        Pedigree maude = new Pedigree("Maude", 1899, null, null);
        Pedigree sam = new Pedigree("Sam", 1927, larry, chris);
        Pedigree jane = new Pedigree("Jane", 1933, wayne, maude);
        Pedigree ben = new Pedigree("Ben", 1956, sam, jane);
        return ben;
    }

    /**
     * Returns twice the square root of d. Requires d >= 0.
     */
    public static double f (double d)
    {
        return 2 * Math.sqrt(d);
    }

    /**
     * Returns 0 if p is null. Otherwise, returns the number of people in p.
     */
    public static int size (Pedigree p)
    {
        if (p == null)
        {
            return 0;
        }
        else
        {
            int f = size(p.getFather());
            int m = size(p.getMother());
            return f + m + 1;
        }
    }

    /**
     * Returns 0 if p is null. Otherwise, returns the depth of p. This is the number of generations in the longest
     * branch.
     */
    public static int depth (Pedigree p)
    {
        if (p == null)
        {
            return 0;
        }
        else
        {
            int f = depth(p.getFather());
            int m = depth(p.getMother());
            return Math.max(f, m) + 1;
        }
    }

    /**
     * Returns the oldest birthyear in p, or Integer.MAX_VALUE if the pedigeree is null.
     */
    public static int oldest (Pedigree p)
    {
        if (p == null)
        {
            return Integer.MAX_VALUE;
        }
        else
        {
            int f = oldest(p.getFather());
            int m = oldest(p.getMother());
            int b = Math.min(f, m);
            return Math.min(b, p.getBirthYear());
        }
    }

    /**
     * Prints the names of all the people in p, one name per line.
     */
    public static void printNames (Pedigree p)
    {
        if (p == null)
        {
        }
        else
        {
            printNames(p.getMother());
            printNames(p.getFather());
            System.out.println(p.getName());
        }
    }
    
    /**
     * Returns the names of all the people in p appended together in
     * a String.  If p is null, returns the empty string. 
     */
    public static String allNames (Pedigree p)
    {
        return "";
    }
    
    /**
     * Returns true if there's a person in p with the given birth
     * year; returns false otherwise.
     */
    public static boolean hasBirthYear (Pedigree p, int year)
    {
        return false;
    }
}
