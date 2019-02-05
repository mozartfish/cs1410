package cs1410;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class MyTestCases
{

    // readTable test cases
    @Test
    public void testReadTable ()
    {
        try (Scanner scn = new Scanner(
                "Utah\t10\nNevada\t3\nUtah\t2\nCalifornia\t14\nArizona\t21\nUtah\t2\nCalifornia\t7\nCalifornia\t6\nNevada\t11\nCalifornia\t1\n"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);

            TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
            ArrayList<Double> azList = new ArrayList<>();
            azList.add(21.0);
            expected.put("Arizona", azList);

            ArrayList<Double> caList = new ArrayList<>();
            caList.add(14.0);
            caList.add(7.0);
            caList.add(6.0);
            caList.add(1.0);
            expected.put("California", caList);

            ArrayList<Double> nvList = new ArrayList<>();
            nvList.add(3.0);
            nvList.add(11.0);
            expected.put("Nevada", nvList);

            ArrayList<Double> utList = new ArrayList<>();
            utList.add(10.0);
            utList.add(2.0);
            utList.add(2.0);
            expected.put("Utah", utList);

            assertEquals(expected, actual);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noTab ()
    {
        try (Scanner scn = new Scanner("Hellomoto10\n"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);

            TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
            ArrayList<Double> hellmoto = new ArrayList<>();
            hellmoto.add(10.0);
            expected.put("Hellomoto", hellmoto);
            assertEquals(expected, actual);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noKey ()
    {
        try (Scanner scn = new Scanner("\t\t10"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);

            TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
            ArrayList<Double> tabOnly = new ArrayList<>();
            tabOnly.add(10.0);
            expected.put("\t\t10", tabOnly);
            assertEquals(expected, actual);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void noNewLine ()
    {
        try (Scanner scn = new Scanner(""))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);

            TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
            ArrayList<Double> noNewLine = new ArrayList<>();
            noNewLine.add(0.0);
            expected.put("", noNewLine);
            assertEquals(expected, actual);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void notADouble ()
    {
        try (Scanner scn = new Scanner("Hello\ta"))
        {
            TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(scn);

            TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
            ArrayList<Double> notADouble = new ArrayList<>();
            notADouble.add(0.0);
            expected.put("Hello", notADouble);
            assertEquals(expected, actual);
        }
    }

    // test cases for the prepareGraph method
    @Test
    public void testAverageMethod ()
    {
        TreeMap<String, ArrayList<Double>> actual = new TreeMap<String, ArrayList<Double>>();

        ArrayList<Double> azList = new ArrayList<>();
        azList.add(21.0);
        actual.put("Arizona", azList);

        ArrayList<Double> caList = new ArrayList<>();
        caList.add(14.0);
        caList.add(7.0);
        caList.add(6.0);
        caList.add(1.0);
        actual.put("California", caList);

        ArrayList<Double> nvList = new ArrayList<>();
        nvList.add(3.0);
        nvList.add(11.0);
        actual.put("Nevada", nvList);

        ArrayList<Double> utList = new ArrayList<>();
        utList.add(10.0);
        utList.add(3.0);
        utList.add(5.0);
        actual.put("Utah", utList);

        TreeMap<String, Double> expected = new TreeMap<>();
        TreeMap<String, Double> averageMethod = GraphingMethods.prepareGraph(actual, 3);
        expected.put("Arizona", 21.0);
        expected.put("California", 7.0);
        expected.put("Nevada", 7.0);
        expected.put("Utah", 6.0);

        assertEquals(expected, averageMethod);
    }

    @Test
    public void testSumMethod ()
    {
        TreeMap<String, ArrayList<Double>> actual = new TreeMap<String, ArrayList<Double>>();

        ArrayList<Double> azList = new ArrayList<>();
        azList.add(21.0);
        actual.put("Arizona", azList);

        ArrayList<Double> caList = new ArrayList<>();
        caList.add(14.0);
        caList.add(7.0);
        caList.add(6.0);
        caList.add(1.0);
        actual.put("California", caList);

        ArrayList<Double> nvList = new ArrayList<>();
        nvList.add(3.0);
        nvList.add(11.0);
        actual.put("Nevada", nvList);

        ArrayList<Double> utList = new ArrayList<>();
        utList.add(10.0);
        utList.add(3.0);
        utList.add(5.0);
        actual.put("Utah", utList);

        TreeMap<String, Double> expected = new TreeMap<>();
        TreeMap<String, Double> averageMethod = GraphingMethods.prepareGraph(actual, 2);
        expected.put("Arizona", 21.0);
        expected.put("California", 28.0);
        expected.put("Nevada", 14.0);
        expected.put("Utah", 18.0);

        assertEquals(expected, averageMethod);
    }

    @Test
    public void testMinMethod ()
    {
        TreeMap<String, ArrayList<Double>> actual = new TreeMap<String, ArrayList<Double>>();

        ArrayList<Double> azList = new ArrayList<>();
        azList.add(21.0);
        actual.put("Arizona", azList);

        ArrayList<Double> caList = new ArrayList<>();
        caList.add(14.0);
        caList.add(7.0);
        caList.add(6.0);
        caList.add(1.0);
        actual.put("California", caList);

        ArrayList<Double> nvList = new ArrayList<>();
        nvList.add(3.0);
        nvList.add(11.0);
        actual.put("Nevada", nvList);

        ArrayList<Double> utList = new ArrayList<>();
        utList.add(10.0);
        utList.add(3.0);
        utList.add(5.0);
        actual.put("Utah", utList);

        TreeMap<String, Double> expected = new TreeMap<>();
        TreeMap<String, Double> averageMethod = GraphingMethods.prepareGraph(actual, 1);
        expected.put("Arizona", 21.0);
        expected.put("California", 1.0);
        expected.put("Nevada", 3.0);
        expected.put("Utah", 3.0);

        assertEquals(expected, averageMethod);
    }

    @Test
    public void testMaxMethod ()
    {
        TreeMap<String, ArrayList<Double>> actual = new TreeMap<String, ArrayList<Double>>();

        ArrayList<Double> azList = new ArrayList<>();
        azList.add(21.0);
        actual.put("Arizona", azList);

        ArrayList<Double> caList = new ArrayList<>();
        caList.add(14.0);
        caList.add(7.0);
        caList.add(6.0);
        caList.add(1.0);
        actual.put("California", caList);

        ArrayList<Double> nvList = new ArrayList<>();
        nvList.add(3.0);
        nvList.add(11.0);
        actual.put("Nevada", nvList);

        ArrayList<Double> utList = new ArrayList<>();
        utList.add(10.0);
        utList.add(3.0);
        utList.add(5.0);
        actual.put("Utah", utList);

        TreeMap<String, Double> expected = new TreeMap<>();
        TreeMap<String, Double> averageMethod = GraphingMethods.prepareGraph(actual, 0);
        expected.put("Arizona", 21.0);
        expected.put("California", 14.0);
        expected.put("Nevada", 11.0);
        expected.put("Utah", 10.0);

        assertEquals(expected, averageMethod);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptySet ()
    {
        TreeMap<String, ArrayList<Double>> emptyMap = new TreeMap<String, ArrayList<Double>>();
        ArrayList<Double> emptyList = new ArrayList<>();
        emptyMap.put("California", emptyList);
        GraphingMethods.prepareGraph(emptyMap, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeNumber ()
    {
        TreeMap<String, ArrayList<Double>> actual = new TreeMap<String, ArrayList<Double>>();

        ArrayList<Double> azList = new ArrayList<>();
        azList.add(21.0);
        actual.put("Arizona", azList);

        ArrayList<Double> caList = new ArrayList<>();
        caList.add(-14.0);
        caList.add(-7.0);
        caList.add(-6.0);
        caList.add(-1.0);
        actual.put("California", caList);

        ArrayList<Double> nvList = new ArrayList<>();
        nvList.add(-3.0);
        nvList.add(11.0);
        actual.put("Nevada", nvList);

        ArrayList<Double> utList = new ArrayList<>();
        utList.add(-10.0);
        utList.add(3.0);
        utList.add(5.0);
        actual.put("Utah", utList);

        TreeMap<String, Double> expected = new TreeMap<>();
        TreeMap<String, Double> averageMethod = GraphingMethods.prepareGraph(actual, 3);
        expected.put("Arizona", 21.0);
        expected.put("California", -7.0);
        expected.put("Nevada", 11.0);
        expected.put("Utah", 10.0);

        assertEquals(expected, averageMethod);
    }

}
