package ps3;

import static org.junit.Assert.*;
import static ps3.MethodLibrary.*;
import org.junit.Test;

public class PS3GradingTestCases
{
    @Test
    public void testLifeStage ()
    {
        assertEquals("baby", getLifeStage(0));
        assertEquals("baby", getLifeStage(1));
        assertEquals("child", getLifeStage(2));
        assertEquals("child", getLifeStage(10));
        assertEquals("teen", getLifeStage(13));
        assertEquals("teen", getLifeStage(16));
        assertEquals("adult", getLifeStage(21));
        assertEquals("adult", getLifeStage(60));
        assertEquals("senior", getLifeStage(70));
        assertEquals("senior", getLifeStage(80));
        
        assertEquals("child", getLifeStage(2));
        assertEquals("child", getLifeStage(12));
        assertEquals("teen", getLifeStage(17));
        assertEquals("adult", getLifeStage(18));
        assertEquals("adult", getLifeStage(64));
        assertEquals("senior", getLifeStage(65));
    }

    @Test
    public void testToPigLatin ()
    {
        assertEquals("ickslay", toPigLatin("slick"));
        assertEquals("ICKSLay", toPigLatin("SLICK"));
        assertEquals("ongstray", toPigLatin("strong"));
        assertEquals("ONGSTRay", toPigLatin("STRONG"));
        assertEquals("xyzzy", toPigLatin("xyzzy"));
        assertEquals("orangeway", toPigLatin("orange"));
        
        assertEquals("psst", toPigLatin("psst"));
        assertEquals("eachway", toPigLatin("each"));
        assertEquals("implesay", toPigLatin("simple"));
        assertEquals("ingthay", toPigLatin("thing"));
        assertEquals("engthstray", toPigLatin("strength"));
    }

    @Test
    public void testConvertToPigLatin ()
    {
        assertEquals("isthay isway away esttay ", convertToPigLatin("this is a test"));
        assertEquals("isthay isway away esttay ", convertToPigLatin("    this     is     a         test"));
        assertEquals("", convertToPigLatin(""));
        assertEquals("", convertToPigLatin("           "));
        assertEquals("eThay ainray inway ainSpay allsfay ainlymay inway ethay ainplay ",
                convertToPigLatin("The rain in Spain falls mainly in the plain"));
        
        assertEquals("ownay isway ethay imetay ", convertToPigLatin("now is the time"));
        assertEquals("oneway otway eethray ourfay ivefay sx ", convertToPigLatin("one two three four five sx"));
    }
    
    @Test
    public void testSumOfRoots ()
    {
        assertEquals(0.0, sumOfRoots("", 2), .000000001);
        assertEquals(10.0, sumOfRoots("1.0 4.0 9.0 16.0", 2), .000000001);
        assertEquals(7.524635948, sumOfRoots("1.0 2.0 3.0 4.0 5.0 6.0", 5), .000000001);
        
        assertEquals(18.9949358457, sumOfRoots("10 20 30 40 50 60", 3), .000000001);
        assertEquals(3.26287145929, sumOfRoots("1 .5 .05 .005", 10), .000000001);
    }
    
    @Test
    public void testContainsAll ()
    {
        assertTrue(containsAll("", ""));
        assertTrue(containsAll("abc", "abracadabra"));
        assertFalse(containsAll("def", "Defect"));
        assertFalse(containsAll("x", ""));
        
        assertTrue(containsAll("xyz", "zzzzyyyyxxxx"));
        assertFalse(containsAll("xyz", "zzzzxxxx"));
        assertFalse(containsAll("xyz", ""));
    }
    
    @Test
    public void testMakeLine ()
    {
        assertEquals("+------+", makeLine('+', '-', 8));
        assertEquals("$$$$", makeLine('$', '$', 4));
        assertEquals("**", makeLine('*', '=', 2));
        
        assertEquals("aa", makeLine('a', 'b', 2));
        assertEquals("abbba", makeLine('a', 'b', 5));
    }

    @Test
    public void testMakeRectangle ()
    {
        assertEquals("++\n++\n", makeRectangle(2, 2));
        assertEquals("+-+\n| |\n+-+\n", makeRectangle(3, 3));
        assertEquals("+--+\n|  |\n|  |\n|  |\n+--+\n", makeRectangle(5, 4));
        
        assertEquals("+---+\n|   |\n|   |\n+---+\n", makeRectangle(4, 5));
    }

    @Test
    public void testNextHailstone ()
    {
        assertEquals(1, nextHailstone(1));
        assertEquals(16, nextHailstone(5));
        assertEquals(100, nextHailstone(33));
        assertEquals(1, nextHailstone(2));
        assertEquals(8, nextHailstone(16));
        assertEquals(1000000, nextHailstone(2000000));
        
        assertEquals(2998, nextHailstone(999));
        assertEquals(11111, nextHailstone(22222));
    }

    @Test
    public void testHailstones ()
    {
        assertEquals("1 ", hailstones(1));
        assertEquals("16 8 4 2 1 ", hailstones(16));
        assertEquals("7 22 11 34 17 52 26 13 40 20 10 5 16 8 4 2 1 ", hailstones(7));
        
        assertEquals("3 10 5 16 8 4 2 1 ", hailstones(3));
        assertEquals("2 1 ", hailstones(2));
    }
    
    @Test
    public void testSameTokens ()
    {
        assertTrue(sameTokens("this is a test", " this  is  a  test "));
        assertTrue(sameTokens("", ""));
        assertFalse(sameTokens("hello there", "hello there Joe"));
        assertFalse(sameTokens("abc def", "def abc"));
        assertFalse(sameTokens("a", "A"));
        assertFalse(sameTokens("a b c", "abc"));
        
        assertFalse(sameTokens("a b c", ""));
        assertFalse(sameTokens("ab cd", "abc d"));
        assertTrue(sameTokens("x y z", "x  y  z"));
    }
}
