package cs1410;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import org.junit.Test;

public class PS5LibraryTests
{
    /**
     * This checks that the last line of the result ends with a newline, even if the last line in the scanner didn't.
     */
    @Test
    public void testScannerToString1 ()
    {
        assertEquals("This\nis a\ntest\n", PS5Library.scannerToString(new Scanner("This\nis a\ntest")));
        assertEquals("", PS5Library.scannerToString(new Scanner("")));
        assertEquals("  \n", PS5Library.scannerToString(new Scanner("  ")));
        assertEquals("This\nis a\ntest\n", PS5Library.scannerToString(new Scanner("This\nis a\ntest\n")));
        assertEquals("* \n\n ** \n\n\n *** \n\nis a\ntest\n", PS5Library.scannerToString(new Scanner("* \n\n ** \n\n\n *** \n\nis a\ntest")));
    }

    /**
     * This illustrates how to test that an exception is thrown when one is supposed to be thrown. If
     * pickCharThatFollowsPattern doesn't thrown the right kind of exception, the test will fail.
     */
    @Test(expected = NoSuchElementException.class)
    public void testPickCharThatFollowsPattern ()
    {
        PS5Library.pickCharThatFollowsPattern("hello", "o", new Random());
        PS5Library.pickCharThatFollowsPattern("", "  ", new Random());
        PS5Library.pickCharThatFollowsPattern("ho t Cho colate", "ho", new Random());
        PS5Library.pickCharThatFollowsPattern("  cat  dog", " ", new Random());
        PS5Library.pickCharThatFollowsPattern("asdflkasfo  gasfgkagkk  akgjasfkgan1  ", " ", new Random());
        PS5Library.pickCharThatFollowsPattern(" ****** ", "  ", new Random());
        PS5Library.pickCharThatFollowsPattern(" YoGabaBlahGaba", "Gaba", new Random());
        
    }

    /**
     * This illustrates a way to do tests of methods that have a randomized behavior. When we ask for a randomly chosen
     * substring of length 4 of "abcde", about half the time we should get "abcd" and about half the time we should get
     * "bcde". So we call chooseSubstring 1000 times and count how many times we get each possibility. (If we get
     * anything else back, we immediately fail the test case.) Then we assert that we got each "about" half the time. It
     * is possible for a correct implementation to fail this test if we get extremely unlucky, but that is extremely
     * unlikely.
     */
    @Test
    public void testChooseSubstring () throws Exception
    {
        Random rand = new Random();
        int abcd = 0;
        int bcde = 0;

        for (int i = 0; i < 1000; i++)
        {
            String substring = PS5Library.chooseSubstring("abcde", 4, rand);
            if (substring.equals("abcd"))
            {
                abcd++;
            }
            else if (substring.equals("bcde"))
            {
                bcde++;
            }
            else
            {
                fail();
            }
        }

        assertTrue(400 <= abcd && abcd <= 600 && 400 <= bcde && bcde <= 600);
    }

    @Test(expected = IllegalArgumentException.class)

    public void lengthLessThanZero() throws Exception
    {
        Random rand = new Random();
        PS5Library.chooseSubstring("YoGabaGaba", -1, rand);

    }

    public void greaterThanLengthofString() throws Exception
    {
        Random rand = new Random();
        PS5Library.chooseSubstring("YoGabaGaba", 100, rand);
    }

    /**
     * This illustrates how to make assertions about ArrayLists.
     */
    @Test
    public void testGetCharsThatFollowPattern ()
    {
        ArrayList<Character> list = new ArrayList<Character>();
        list.add('b');
        list.add('b');
        assertEquals(list, PS5Library.getCharsThatFollowPattern("abababa", "aba"));
        
        
        
        ArrayList<Character> hello = new ArrayList<Character>();
        hello.add('c');
        hello.add('d');
        hello.add('c');
        assertEquals(hello, PS5Library.getCharsThatFollowPattern("abcabdabcab", "ab"));
        
        
        ArrayList<Character> cars = new ArrayList<Character>();
        cars.add('d');
        cars.add(' ');
        assertEquals(cars, PS5Library.getCharsThatFollowPattern("a cd a c de", "a c"));
        
        
        ArrayList<Character> blah = new ArrayList<Character>();
        blah.add('1');
        blah.add('1');
        blah.add('s');
        blah.add('a');
        blah.add('h');
        
        assertEquals(blah, PS5Library.getCharsThatFollowPattern("asdf1230 asdf1230498asdfsafkjg2r4ut23asdfasdfh509", "asdf"));
        
       
    }

}