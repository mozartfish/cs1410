package ps4;

import static org.junit.Assert.*;
import java.util.Scanner;
import org.junit.Test;

public class PS4GradingTests
{
    @Test
    public void testContainsToken ()
    {
        assertFalse(Loops.containsToken("", "xyz"));
        assertTrue(Loops.containsToken("xyz", "xyz"));
        assertTrue(Loops.containsToken("xyx xyy xyz xya", "xyz"));
        assertFalse(Loops.containsToken("xyx xyy xyZ xya", "xyz"));
        
        assertFalse(Loops.containsToken("a b c", "abc"));
        assertTrue(Loops.containsToken("a b c", "c"));
        assertTrue(Loops.containsToken("ab cd ef fe", "ef"));
        assertFalse(Loops.containsToken("abc def ghi", "de"));
    }

    @Test
    public void testFindLineWithToken ()
    {
        assertEquals("", Loops.findLineWithToken(new Scanner(""), "hello"));
        assertEquals("hello world", Loops.findLineWithToken(new Scanner("hello world"), "world"));
        assertEquals("", Loops.findLineWithToken(new Scanner("hello world"), "abc"));
        assertEquals("hello world", Loops.findLineWithToken(new Scanner("hello world"), "world"));
        assertEquals("this is a test",
                Loops.findLineWithToken(new Scanner("hello world\nthis is a test\nthis is another test"), "a"));
        
        assertEquals("", Loops.findLineWithToken(new Scanner(""), ""));
        assertEquals("b", Loops.findLineWithToken(new Scanner("a\nb\nc\nd/ne/nf"), "b"));
        assertEquals("", Loops.findLineWithToken(new Scanner("hello world"), "worl"));
        assertEquals("", Loops.findLineWithToken(new Scanner("hello world"), "hello world"));
    }

    @Test
    public void testIsPalindrome ()
    {
        assertTrue(Loops.isPalindrome(""));
        assertTrue(Loops.isPalindrome("z"));
        assertFalse(Loops.isPalindrome("xy"));
        assertTrue(Loops.isPalindrome("abcddcba"));
        assertFalse(Loops.isPalindrome("abcddbba"));
        assertTrue(Loops.isPalindrome("abcdedcba"));
        assertFalse(Loops.isPalindrome("abcdedbba"));
        
        assertTrue(Loops.isPalindrome("  *  "));
        assertFalse(Loops.isPalindrome("  * "));
        assertTrue(Loops.isPalindrome("1234567890987654321"));
        assertFalse(Loops.isPalindrome("1234567890987654321 "));
    }

    @Test
    public void testFindLongestPalindrome ()
    {
        assertEquals("", Loops.findLongestPalindrome(new Scanner("")));
        assertEquals("did", Loops.findLongestPalindrome(new Scanner("I did something wrong")));
        assertEquals("", Loops.findLongestPalindrome(new Scanner("This is an apple")));
        assertEquals("peep", Loops.findLongestPalindrome(new Scanner("a bb xyz\nI heard a peep sis")));
        
        assertEquals("amanaplanacanalpanama", Loops.findLongestPalindrome(new Scanner("amanaplanacanalpanama")));
        assertEquals("abcdefgfedcba", Loops.findLongestPalindrome(new Scanner("aaba abba abcdefgfedcba abcdefghijklmnopqrstuvwxyz")));
        assertEquals("", Loops.findLongestPalindrome(new Scanner("ab abb abcb abcdedcb")));
    }

    @Test
    public void testFindMostWhitespace ()
    {
        assertEquals(-1, Loops.findMostWhitespace(new Scanner("")));
        assertEquals(2, Loops.findMostWhitespace(new Scanner("a b c")));
        assertEquals(3, Loops.findMostWhitespace(new Scanner("a bb\na bb\t\t\nxyz")));
        
        assertEquals(6, Loops.findMostWhitespace(new Scanner("a     z\na\t \t \t z\na    z\na\t\t\t\t")));
        assertEquals(11, Loops.findMostWhitespace(new Scanner("\t \t \t \t \t \t")));
        assertEquals(0, Loops.findMostWhitespace(new Scanner("elephant\ngiraffe\ncat")));
        assertEquals(0, Loops.findMostWhitespace(new Scanner("\n\n\n\n")));
    }
}
