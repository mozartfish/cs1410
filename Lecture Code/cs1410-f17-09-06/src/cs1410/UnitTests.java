package cs1410;

import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

public class UnitTests
{
    @Test
    public void testMax2 ()
    {
        assertEquals(5, Conditionals.max(4, 5));
        assertEquals(-8, Conditionals.max(-20, -8));
        assertEquals(0, Conditionals.max(0, 0));
    }

    @Test
    public void testMax3 ()
    {
        assertEquals(22, Conditionals.max(4, 5, 22));
        assertEquals(7, Conditionals.max(7, -2, 6));
        assertEquals(5, Conditionals.max(1, 5, -29));
    }

    @Test
    public void squareRootOfAbsoluteValue ()
    {
        assertEquals(3, Conditionals.squareRootOfAbsoluteValue(9), .000000001);
        assertEquals(4, Conditionals.squareRootOfAbsoluteValue(-16), .000000001);
        assertEquals(0, Conditionals.squareRootOfAbsoluteValue(0), .000000001);

    }

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
        assertEquals("12345", AccumulationLoops.appendInts(1, 6));
        assertEquals("", AccumulationLoops.appendInts(1, 0));
    }

    @Test
    public void testAllLowerCase ()
    {
        assertEquals("aBcDeFg", AccumulationLoops.allLowerCase("aceg"));
        assertEquals("", AccumulationLoops.allLowerCase("ABCD%$#"));
    }

    @Test
    public void testSumOfTokens ()
    {
        assertEquals(0, AccumulationLoops.sumOfTokens(new Scanner("")));
        assertEquals(5, AccumulationLoops.sumOfTokens(new Scanner("5")));
        assertEquals(15, AccumulationLoops.sumOfTokens(new Scanner("1 2 3 4 5")));
    }
}