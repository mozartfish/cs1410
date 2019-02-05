package cs1410;

import static org.junit.Assert.*;
import static cs1410.OptimizationLoops.*;

import java.util.Scanner;

import org.junit.Test;

public class OptimizationUnitTests
{
    @Test
    public void testFindIntWithLargestCosine ()
    {
        assertEquals(1, findIntWithLargestCosine(1));
        assertEquals(44, findIntWithLargestCosine(100));
    }

    @Test
    public void testFindLargestChar ()
    {
        assertEquals('$', findLargestChar("$"));
        assertEquals('C', findLargestChar("ACB"));
        assertEquals('b', findLargestChar("ABab12"));
    }

    @Test
    public void testFindLongestWord ()
    {
        assertEquals("hello", findLongestToken(new Scanner("hello")));
        assertEquals("mainly", findLongestToken(new Scanner("The rain in Spain falls mainly in")));
    }
}
