package cs1410;

import static org.junit.Assert.*;
import static cs1410.TableSum.*;
import java.util.Scanner;
import org.junit.Test;

public class TableSumTest
{
    private final static String TABLE = 
            "Name\tCity\tAge\tSalary\nJohn\tSLC\t25\t75000\nMary\tNYC\t40\t125000\nFred\tWVC\t18\t33000\nSue\tSLC\t52\t80000\nPete\tSLC\t91\t0\n";
    
	@Test
	public void testConditionalAverage1 ()
	{
		assertEquals((25+52+91)/3.0, conditionalAverage(new Scanner(TABLE), "City", "Age", "SLC"), 1e-6);
	}
	
	@Test
	public void testConditionalAverage2 ()
	{
		assertEquals((75000+80000+0)/3.0, conditionalAverage(new Scanner(TABLE), "City", "Salary", "SLC"), 1e-6);
	}
	
	@Test
	public void testConditionalAverage3 ()
	{
		assertEquals(125000, conditionalAverage(new Scanner(TABLE), "City", "Salary", "NYC"), 1e-6);
	}
	
	@Test
	public void testConditionalAverage4 ()
	{
		assertEquals(0, conditionalAverage(new Scanner(TABLE), "City", "Age", "Boise"), 1e-6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConditionalAverage5 ()
	{
		String table = "Name\tCity\tAge\nJohn\tSLC\t25\nMary\tNYC\40\nFred\tWVC\t18\nSue\tSLC\t52\nPete\tSLC\t91\n";
		conditionalAverage(new Scanner(table), "Cities", "Age", "SLC");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConditionalAverage6 ()
	{
		String table = "Name\tCity\tAge\nJohn\tSLC\t25\nMary\tNYC\40\nFred\tWVC\t18\nSue\tSLC\t52\nPete\tSLC\t91\n";
		conditionalAverage(new Scanner(table), "City", "Ages", "SLC");
	}
	
	@Test(expected=NumberFormatException.class)
	public void testConditionalAverage7 ()
	{
		String table = "Name\tCity\tAge\nJohn\tSLC\t25\nMary\tNYC\40\nFred\tWVC\t18\nSue\tSLC\t52\nPete\tSLC\t91x\n";
		conditionalAverage(new Scanner(table), "City", "Age", "SLC");
	}
	
	@Test
    public void testFindColIndex1 ()
    {
        assertEquals(0, findColIndex("a\tb\tc\td", "a"));
        assertEquals(2, findColIndex("a\tb\tc\td", "c"));
        assertEquals(0, findColIndex("abc", "abc"));
        assertEquals(2, findColIndex("a\t\tb", "b"));
        assertEquals(1, findColIndex("a\t\tb", ""));
        assertEquals(0, findColIndex("a\ta", "a"));
    }
    
	@Test(expected=IllegalArgumentException.class)
    public void testFindColIndex2 ()
    {
        findColIndex("a\tb\tc\td", "e");
    }
	
}
