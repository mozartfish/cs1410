package cs1410;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.Test;

public class CacheListTests
{
    /**
     * An example test for CacheList objects
     */
    @Test
    public void test () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void errorOnLineOne() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void fewerThanSevenAttributes() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCRQWK\\tOld Three Tooth\\tgeocade\\t\\3.5\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Turns");
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void greaterThanSevenAttributes() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCRQWK\\tOld Three Tooth\\tgeocadet\t3.5\\t3\tN40 45.850\tW111 48.045\\t123\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("Turns");
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test(expected = IllegalArgumentException.class)
    public void GCCodeDoesNotStartWithG() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "ACRQWK\\tOld Three Tooth\\tgeocadet\\t3.5\\t3\\tN40 45.850\\tW111 48.045\\t123\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
       
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void testForTitleCheck() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("T");
        ArrayList<Cache> selected = clist.select();
        assertEquals(2, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
        assertEquals("GCRQWK", selected.get(1).getGcCode());
    }
    @Test
    public void testOwnerCheck() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("bunny");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void testForTitleandOwnerCheck() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tbunny\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("bunny");
        clist.setTitleConstraint("Turns");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }

    @Test
    public void testForCapitalTitle () throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tBUNNY\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("TURNS");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void testForCapitalOwner() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tBUNNY\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("BUNNY");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void testForCapitalOwnerandCapitalTitle() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tBUNNY\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeocadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setOwnerConstraint("BUNNY");
        clist.setTitleConstraint("TURNS");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void repeatedOwners() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tBUNNY\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tBUNNY\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("TURNS");
        ArrayList<Cache> selected = clist.select();
        assertEquals(1, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
    }
    @Test
    public void twoDifferentOwners() throws IOException
    {
        CacheList clist = new CacheList(new Scanner(
                "GCABCD\tAs The World Turns\tBUNNY\t1\t1\tN40 45.875\tW111 48.986\nGCRQWK\tOld Three Tooth\tgeoCadet\t3.5\t3\tN40 45.850\tW111 48.045\n"));
        clist.setTitleConstraint("T");
        ArrayList<Cache> selected = clist.select();
        assertEquals(2, selected.size());
        assertEquals("GCABCD", selected.get(0).getGcCode());
        assertEquals("GCRQWK", selected.get(1).getGcCode());
    }
}
