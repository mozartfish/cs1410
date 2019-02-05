package scan;

import static org.junit.Assert.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import org.junit.Test;

public class MyScannerTests
{

    @Test
    public void testhasNext ()
    {
        MyScanner scan = new MyScanner("Hello 123");
        assertEquals(true, scan.hasNext());
    }

    @Test
    public void testhasNextInt ()
    {
        MyScanner scan = new MyScanner("Hello 123");
        assertEquals(false, scan.hasNextInt());
    }

    @Test
    public void testnextMethod ()
    {
        MyScanner scan = new MyScanner("Hello 123");
        assertEquals("Hello", scan.next());
    }

    @Test
    public void testnextIntMethod ()
    {
        MyScanner scan = new MyScanner("Hello 123");
        scan.next();
        assertEquals(123, scan.nextInt());
    }

    @Test(expected = InputMismatchException.class)
    public void testhasNextEmptyString ()
    {
        MyScanner scan = new MyScanner("");
        scan.hasNext();
        assertEquals("", scan.nextInt());
    }

    @Test(expected = InputMismatchException.class)
    public void testhasNextIntEmptyString ()
    {
        MyScanner scan = new MyScanner("");
        scan.hasNextInt();
        assertEquals("", scan.nextInt());
    }

    @Test(expected = InputMismatchException.class)
    public void testnextIntEmptyString ()
    {
        MyScanner scan = new MyScanner("");
        scan.nextInt();
        assertEquals("", scan.nextInt());
    }

    @Test(expected = NoSuchElementException.class)
    public void testnextEmptyString ()
    {
        MyScanner scan = new MyScanner("");
        scan.next();
        assertEquals("", scan.nextInt());
    }

    @Test
    public void testTwoStringhasNext ()
    {
        MyScanner scan = new MyScanner("Hello World");

        assertEquals(true, scan.hasNext());
    }

    @Test
    public void testTwoStringhasNextInt ()
    {
        MyScanner scan = new MyScanner("Hello World");

        assertEquals(false, scan.hasNextInt());
    }

    @Test
    public void testTwoStringsnext ()
    {
        MyScanner scan = new MyScanner("Hello \n World");
        assertEquals("Hello", scan.next());
        assertEquals("World", scan.next());
    }

    @Test(expected = InputMismatchException.class)
    public void testTwoStringsnextInt ()
    {
        MyScanner scan = new MyScanner("Hello World");
        assertEquals("Hello", scan.nextInt());
        scan.next();
        assertEquals("World", scan.nextInt());
    }

    @Test
    public void testTwoIntshasNext ()
    {
        MyScanner scan = new MyScanner("123 456");

        assertEquals(true, scan.hasNext());
    }

    @Test
    public void testTwoIntshasNextInt ()
    {
        MyScanner scan = new MyScanner("123 456");

        assertEquals(true, scan.hasNextInt());
    }

    @Test
    public void testTwoIntsnext ()
    {
        MyScanner scan = new MyScanner("123 456");
        assertEquals(true, scan.hasNext());
        assertEquals("123", scan.next());
        assertEquals(true, scan.hasNext());
        assertEquals("456", scan.next());
        assertEquals(false, scan.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testTwoIntsnextInt ()
    {
        MyScanner scan = new MyScanner("123 456");
        assertEquals(123, scan.nextInt());
        assertEquals(456, scan.nextInt());
        assertEquals(false, scan.hasNextInt());
        assertEquals("", scan.nextInt());
    }

}
