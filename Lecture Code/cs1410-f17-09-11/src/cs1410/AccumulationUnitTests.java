package cs1410;

import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

public class AccumulationUnitTests
{
    @Test
    public void testProduct ()
    {
        assertEquals(120, AccumulationLoops.product(1, 5));
        assertEquals(0, AccumulationLoops.product(0, 5));
        assertEquals(2, AccumulationLoops.product(-2, 2));
    }

    @Test
    public void testReverse ()
    {
        assertEquals("abcdefg", AccumulationLoops.reverse("gfedcba"));
        assertEquals("", AccumulationLoops.reverse(""));
    }

    @Test
    public void testTotalLength ()
    {
        assertEquals(0, AccumulationLoops.totalLength(new Scanner("")));
        assertEquals(1, AccumulationLoops.totalLength(new Scanner("x")));
        assertEquals(9, AccumulationLoops.totalLength(new Scanner("a bc def gh i")));
    }

    @Test
    public void testAppendInts ()
    {
        assertEquals("123456", AccumulationLoops.appendInts(1, 6));
        assertEquals("", AccumulationLoops.appendInts(1, 0));
    }

    @Test
    public void testAllLowerCase ()
    {
        assertEquals("aceg", AccumulationLoops.allLowerCase("aBcDeFg"));
        assertEquals("", AccumulationLoops.allLowerCase("ABCD%$#"));
    }

    @Test
    public void testSumOfTokens ()
    {
        assertEquals(0, AccumulationLoops.sumOfTokens(new Scanner("")));
        assertEquals(5, AccumulationLoops.sumOfTokens(new Scanner("5")));
        assertEquals(150, AccumulationLoops.sumOfTokens(new Scanner("10 20 30 40 50")));
    }
}