package cs1410;

import static org.junit.Assert.*;
import static cs1410.SearchingLoops.*;

import java.util.Scanner;

import org.junit.Test;

public class SearchingUnitTests
{
    @Test
    public void testFindIntWithLargeCosine ()
    {
        assertEquals(0, findIntWithLargeCosine(1));
        assertEquals(25, findIntWithLargeCosine(100));
    }
    
    @Test
    public void testFindDigit ()
    {
        assertEquals('_', findDigit("abc"));
        assertEquals('2', findDigit("ab2x"));
    }
    

    @Test
    public void testCountDigits ()
    {
        assertEquals(3, countDigits("a1b2c3d"));
        assertEquals(0, countDigits("abcd"));
    }

    @Test
    public void testAllDigits ()
    {
        assertEquals("123", allDigits("a1b2c3d"));
        assertEquals("", allDigits("abcd"));
    }

    @Test
    public void testFindWordOfLength ()
    {
        assertEquals("", findWordOfLength(new Scanner(""), 5));
        assertEquals("", findWordOfLength(new Scanner("This is a test"), 6));
        assertEquals("is", findWordOfLength(new Scanner("This is a test"), 2));
    }
    
    @Test
    public void testNumberOfFactors ()
    {
        assertEquals(1, numberOfFactors(1));
        assertEquals(4, numberOfFactors(6));
        assertEquals(6, numberOfFactors(12));
    }
    
    @Test
    public void testNFactors ()
    {
        assertEquals(6, nFactors("1 2 3 4 5 6", 4));
        assertEquals(12, nFactors("10 11 12 13", 6));
    }
}
