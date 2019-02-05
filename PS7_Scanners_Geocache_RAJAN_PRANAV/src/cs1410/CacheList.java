package cs1410;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A CacheList is a collection of Cache objects together with these six constraints:
 * 
 * <ol>
 * <li>A title constraint</li>
 * <li>An owner constraint</li>
 * <li>A minimum difficulty constraint</li>
 * <li>A maximum difficulty constraint</li>
 * <li>A minimum terrain constraint</li>
 * <li>A maximum terrain constraint</li>
 * </ol>
 */
public class CacheList
{
    /**
     * The caches being managed by this CacheList. They are arranged in ascending order according to cache title.
     */
    private ArrayList<Cache> allCaches;
    /**
     * represents a title that allows a user to see which caches are associated with that word or character
     */
    private String titleConstraint;
    /**
     * represents an owner that allows the user to see which caches are associated with that owner
     */
    private String ownerConstraint;
    /**
     * represents user input for minimum difficulty
     */
    private double minDifficulty;
    /**
     * represents user input for maximum difficulty
     */
    private double maxDifficulty;
    /**
     * represents user input for minimum terrain difficulty
     */
    private double minTerrain;
    /**
     * represents user input for max terrain difficulty
     */
    private double maxTerrain;
    /**
     * lineCount keeps tracks of the line numbers for error checking
     */
    private int lineCount;

    /**
     * Creates a CacheList from the specified Scanner. Each line of the Scanner should contain the description of a
     * cache in a format suitable for consumption by the Cache constructor. The resulting CacheList should contain one
     * Cache object corresponding to each line of the Scanner.
     * 
     * Sets the initial value of the title and owner constraints to the empty string, sets the minimum difficulty and
     * terrain constraints to 1.0, and sets the maximum difficulty and terrain constraints to 5.0.
     * 
     * Throws an IOException if the Scanner throws an IOException, or an IllegalArgumentException if any of the
     * individual lines are not appropriate for the Cache constructor.
     * 
     * When an IllegalArgumentException e is thrown, e.getMessage() is the number of the line that was being read when
     * the error that triggered the exception was encountered. Lines are numbered beginning with 1.
     */

    // Constructor
    /**
     * constructs a new CacheList object which contains multiple cache objects
     * 
     * @param caches
     * @throws IOException
     */
    public CacheList (Scanner caches) throws IOException
    {
        allCaches = new ArrayList<Cache>();
        titleConstraint = "";
        ownerConstraint = "";
        minDifficulty = 1.0;
        minTerrain = 1.0;
        maxDifficulty = 5.0;
        maxTerrain = 5.0;
        lineCount = 1;

        // while there are multiple lines that follow cache requirements fill in CacheList
        while (caches.hasNextLine())
        {
            // creates a new string that stores the cache information as a string
            String cacheInfo = caches.nextLine();

            // checks whether a new cache satisfies cache class requirements otherwise throws an error
            // stating where an exception occurred
            try
            {
                Cache newCache = new Cache(cacheInfo);
                // System.out.println(allCaches);
                allCaches.add(newCache);
                // System.out.println(allCaches);
                lineCount++;
            }
            catch (IllegalArgumentException e)
            {
                throw new IllegalArgumentException("There is an error on line " + lineCount);
            }

        }
        // System.out.println(allCaches.size());
        // Sort the list of caches
        Collections.sort(allCaches, (c1, c2) -> c1.getTitle().compareToIgnoreCase(c2.getTitle()));
    }

    /**
     * Sets the title constraint to the specified value.
     */
    public void setTitleConstraint (String title)
    {
        titleConstraint = title.toLowerCase();
    }

    /**
     * Sets the owner constraint to the specified value.
     */
    public void setOwnerConstraint (String owner)
    {
        ownerConstraint = owner.toLowerCase();
    }

    /**
     * Sets the minimum and maximum difficulty constraints to the specified values.
     */
    public void setDifficultyConstraints (double min, double max)
    {
        minDifficulty = min;
        maxDifficulty = max;
    }

    /**
     * Sets the minimum and maximum terrain constraints to the specified values.
     */
    public void setTerrainConstraints (double min, double max)
    {
        minTerrain = min;
        maxTerrain = max;
    }

    /**
     * Returns a list that contains each cache c from the CacheList so long as c's title contains the title constraint
     * as a substring, c's owner equals the owner constraint (unless the owner constraint is empty), c's difficulty
     * rating is between the minimum and maximum difficulties (inclusive), and c's terrain rating is between the minimum
     * and maximum terrains (inclusive). Both the title constraint and the owner constraint are case insensitive.
     * 
     * The returned list is arranged in ascending order by cache title.
     */
    public ArrayList<Cache> select ()
    {
        ArrayList<Cache> caches = new ArrayList<Cache>();
        for (Cache numCaches : allCaches)
        {
            if ((numCaches.getTerrain() >= minTerrain) && (numCaches.getTerrain() <= maxTerrain)
                    && (numCaches.getDifficulty() >= minDifficulty) && (numCaches.getDifficulty() <= maxDifficulty))
            {
                // check if both titleConstraint and ownerConstraint are empty
                if ((titleConstraint.isEmpty()) && (ownerConstraint.isEmpty()))
                {
                    caches.add(numCaches);
                }
                // check if titleConstraint is not empty and ownerConstraint is empty
                else if (!(titleConstraint.isEmpty()) && (ownerConstraint.isEmpty()))
                {
                    String cacheTitle = numCaches.getTitle().toLowerCase();
                    if (cacheTitle.indexOf(titleConstraint) > -1)
                    {
                        caches.add(numCaches);
                    }
                }
                // check if titleConstraint is empty and ownerConstraint is not empty
                else if ((titleConstraint.isEmpty()) && !(ownerConstraint.isEmpty()))
                {
                    String cacheOwner = numCaches.getOwner().toLowerCase();
                    if (cacheOwner.equals(ownerConstraint))
                    {
                        caches.add(numCaches);
                    }
                }
                else if (!(titleConstraint.isEmpty()) && !(ownerConstraint.isEmpty()))
                {
                    String cacheOwner = numCaches.getOwner().toLowerCase();
                    String cacheTitle = numCaches.getTitle().toLowerCase();
                    if (((cacheTitle.indexOf(titleConstraint) > -1)) && (cacheOwner.equals(ownerConstraint)))
                    {
                        caches.add(numCaches);
                    }
                }
            }

        }
        // System.out.println(caches.size());
        return caches;
    }

    /**
     * Returns a list containing all the owners of all of the Cache objects in this CacheList. There are no duplicates.
     * The list is arranged in ascending order.
     */
    public ArrayList<String> getOwners ()
    {
        ArrayList<String> owners = new ArrayList<String>();

        for (Cache numCaches : allCaches)
        {
            String cacheOwner = numCaches.getOwner();
            if (owners.contains(cacheOwner))
            {
                continue;
            }
            else
            {
                owners.add(cacheOwner);
            }
        }

        // Sort the list of owners
        Collections.sort(owners, (s1, s2) -> s1.compareToIgnoreCase(s2));
        return owners;
    }
}
