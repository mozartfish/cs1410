package cs1410;

import static org.junit.Assert.*;
import org.junit.Test;

public class CacheTests
{
    /**
     * An example test for Cache objects
     */
    @Test
    public void test ()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCRQWK", c.getGcCode());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void FewerThanSevenAttributes()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocade\t\3.5");
        assertEquals("GCRQWK", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GreaterThanSevenAttributes()
    {
        Cache c = new Cache("GCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\t123");
        assertEquals("GCRQWK", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeDoesNotStartWithG()
    {
        Cache c = new Cache("ACRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\t123");
        assertEquals("ACRQWK", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeDoesNotHaveACThatFollowsTheGInTheCode()
    {
        Cache c = new Cache("GPRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\t123");
        assertEquals("GPRQWK", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeDoesNotHaveCorrectCapitalization()
    {
        Cache c = new Cache("GCacb\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\t123");
        assertEquals("GCacb", c.getGcCode());
    }
    @Test()
    public void GCCodeMixOfNumbersAndLetters()
    {
        Cache c = new Cache("GCR12W34K\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCR12W34K", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeWithIllegalCharacters()
    {
        Cache c = new Cache("GCRWK!@*&\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GCRWK!@*&", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeWithNoFollowingCharacters()
    {
        Cache c = new Cache("GC\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("GC", c.getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void negativeTerrain()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t-1.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("-1.5", c.getTerrain());
    }
    @Test(expected = IllegalArgumentException.class)
    public void LargeTerrain()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t-1.5\t3\tN40 45.850\tW111 48.045");
        assertEquals("7.5", c.getTerrain());
    }
    @Test(expected = IllegalArgumentException.class)
    public void negativeDifficulty()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t1.5\t-3.4\tN40 45.850\tW111 48.045");
        assertEquals("-3.4", c.getDifficulty());
    }
    @Test(expected = IllegalArgumentException.class)
    public void LargeDifficulty()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t1.5\t7.10\tN40 45.850\tW111 48.045");
        assertEquals("7.10", c.getDifficulty());
    }
    @Test()
    public void TerrainThatWorks()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t1.5\t3.1\tN40 45.850\tW111 48.045");
        assertEquals(3.1, c.getTerrain(),0.1);
    }
    @Test()
    public void DifficultyThatWorks()
    {
        Cache c = new Cache("GCRWK\tOld Three Tooth\tgeocadet\t1.5\t3.425\tN40 45.850\tW111 48.045");
        assertEquals(1.5, c.getDifficulty(),0.1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void noTitle()
    {
        Cache c = new Cache("GCRWK\t\tgeocadet\t1.5\t7.10\tN40 45.850\tW111 48.045");
        assertEquals("", c.getTitle());
    }
    @Test(expected = IllegalArgumentException.class)
    public void noOwner()
    {
        Cache c = new Cache("GCRWK\t\t\t1.5\t7.10\tN40 45.850\tW111 48.045");
        assertEquals("", c.getOwner());
    }
    @Test(expected = IllegalArgumentException.class)
    public void noLat()
    {
        Cache c = new Cache("GCRWK\t\t\t1.5\t7.10\t\tW111 48.045");
        assertEquals("", c.getLatitude());
    }
    @Test(expected = IllegalArgumentException.class)
    public void noLong()
    {
        Cache c = new Cache("GCRWK\t\t\t1.5\t7.10\t\t");
        assertEquals("", c.getLongitude());
    }
    
}
