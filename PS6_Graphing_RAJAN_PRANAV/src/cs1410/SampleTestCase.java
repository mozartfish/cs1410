package cs1410;

import static org.junit.Assert.*;
import java.util.Random;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class SampleTestCase
{
    /**
     * One sample test case.  Don't conclude that your implementation is perfect simply because it passes this test!
     */
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
    
    
    
    @Rule 
    public Timeout globalTimeout = Timeout.seconds(5);
    
    private final static int MAX = 0;
    private final static int MIN = 1;
    private final static int SUM = 2;
    private final static int AVG = 3;

    @Test(expected = IllegalArgumentException.class)
    public void noKeysError ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();
        GraphingMethods.prepareGraph(categoryMap, MAX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyError ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();
        categoryMap.put("A", new ArrayList<Double>());
        categoryMap.get("A").add(17.0);
        categoryMap.put("B", new ArrayList<Double>());
        GraphingMethods.prepareGraph(categoryMap, MAX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negative ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();
        categoryMap.put("A", new ArrayList<Double>());
        categoryMap.get("A").add(17.0);
        categoryMap.put("B", new ArrayList<Double>());
        categoryMap.get("A").add(-17.0);
        GraphingMethods.prepareGraph(categoryMap, MAX);
    }

    @Test(expected = IllegalArgumentException.class)
    public void operationError ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();
        categoryMap.put("A", new ArrayList<Double>());
        categoryMap.get("A").add(17.0);
        categoryMap.put("B", new ArrayList<Double>());
        categoryMap.get("A").add(17.0);
        GraphingMethods.prepareGraph(categoryMap, 4);
    }

    @Test
    public void summariesMax ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();

        categoryMap.put("A", new ArrayList<>());
        categoryMap.put("B", new ArrayList<>());

        categoryMap.get("A").add(4.0);
        categoryMap.get("B").add(5.0);
        categoryMap.get("A").add(6.0);
        categoryMap.get("B").add(7.0);

        TreeMap<String, Double> summaryMap = GraphingMethods.prepareGraph(categoryMap, MAX);
        assertEquals(6.0, summaryMap.get("A"), 1e-6);
        assertEquals(7.0, summaryMap.get("B"), 1e-6);
        assertEquals(2, summaryMap.size());
    }

    @Test
    public void summariesMin ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();

        categoryMap.put("A", new ArrayList<>());
        categoryMap.put("B", new ArrayList<>());

        categoryMap.get("A").add(4.0);
        categoryMap.get("B").add(5.0);
        categoryMap.get("A").add(6.0);
        categoryMap.get("B").add(7.0);

        TreeMap<String, Double> summaryMap = GraphingMethods.prepareGraph(categoryMap, MIN);
        assertEquals(4.0, summaryMap.get("A"), 1e-6);
        assertEquals(5.0, summaryMap.get("B"), 1e-6);
        assertEquals(2, summaryMap.size());
    }

    @Test
    public void summariesSum ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();

        categoryMap.put("A", new ArrayList<>());
        categoryMap.put("B", new ArrayList<>());

        categoryMap.get("A").add(4.0);
        categoryMap.get("B").add(5.0);
        categoryMap.get("A").add(6.0);
        categoryMap.get("B").add(7.0);

        TreeMap<String, Double> summaryMap = GraphingMethods.prepareGraph(categoryMap, SUM);
        assertEquals(10.0, summaryMap.get("A"), 1e-6);
        assertEquals(12.0, summaryMap.get("B"), 1e-6);
        assertEquals(2, summaryMap.size());
    }

    @Test
    public void summariesAvg ()
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();

        categoryMap.put("A", new ArrayList<>());
        categoryMap.put("B", new ArrayList<>());

        categoryMap.get("A").add(4.0);
        categoryMap.get("B").add(5.0);
        categoryMap.get("A").add(6.0);
        categoryMap.get("B").add(7.0);

        TreeMap<String, Double> summaryMap = GraphingMethods.prepareGraph(categoryMap, AVG);
        assertEquals(5.0, summaryMap.get("A"), 1e-6);
        assertEquals(6.0, summaryMap.get("B"), 1e-6);
        assertEquals(2, summaryMap.size());
    }

    @Test
    public void bigMax ()
    {
        bigSummary(50, 1000, MAX);
    }

    @Test
    public void bigMin ()
    {
        bigSummary(100, 2000, MIN);
    }

    @Test
    public void bigSum ()
    {
        bigSummary(200, 4000, SUM);
    }

    @Test
    public void bigAvg ()
    {
        bigSummary(400, 8000, AVG);
    }

    /**
     * Randomly generates input for prepareGraph that conrains nKeys keys and nVal values and then tests prepareGraph
     * using the provided operation.
     */
    public void bigSummary (int nKeys, int nVals, int operation)
    {
        TreeMap<String, ArrayList<Double>> categoryMap = new TreeMap<>();
        TreeMap<String, Double> expected = new TreeMap<>();
        Random rand = new Random(nVals);

        for (int i = 0; i < nVals; i++)
        {
            String key = "K" + rand.nextInt(nKeys);
            double val = rand.nextDouble() * 1e5 + 1;
            if (categoryMap.get(key) == null)
            {
                categoryMap.put(key, new ArrayList<>());
                expected.put(key, (operation == MIN) ? Double.MAX_VALUE : 0.0);
            }
            categoryMap.get(key).add(val);
            switch (operation)
            {
                case MAX:
                    expected.put(key, Math.max(expected.get(key), val));
                    break;
                case MIN:
                    expected.put(key, Math.min(expected.get(key), val));
                    break;
                default:
                    expected.put(key, expected.get(key) + val);
            }
        }

        if (operation == AVG)
        {
            for (String key : expected.keySet())
            {
                expected.put(key, expected.get(key) / categoryMap.get(key).size());
            }
        }

        TreeMap<String, Double> actual = GraphingMethods.prepareGraph(categoryMap, operation);

        assertEquals(expected.size(), actual.size());
        for (String key : expected.keySet())
        {
            assertEquals(expected.get(key), actual.get(key), 1e-6);
        }
    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyData ()
    {
        String data = "";
        GraphingMethods.readTable(new Scanner(data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noTab ()
    {
        String data = "A45\n";
        GraphingMethods.readTable(new Scanner(data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void noNumber ()
    {
        String data = "A\tx\n";
        GraphingMethods.readTable(new Scanner(data));
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeTabs ()
    {
        String data = "A\t4.5\textra\n";
        GraphingMethods.readTable(new Scanner(data));
    }

    @Test
    public void spacesInKey ()
    {
        String data = "New York\t4.5\n";
        GraphingMethods.readTable(new Scanner(data));
    }

    @Test
    public void oneLine ()
    {
        String data = "A\t4.0\n";
        TreeMap<String, ArrayList<Double>> map = GraphingMethods.readTable(new Scanner(data));
        assertEquals(1, map.size());
        assertEquals(1, map.get("A").size());
        assertEquals(4.0, map.get("A").get(0), 1e-6);
    }

    @Test
    public void threeLines ()
    {
        String data = "A\t4.0\nB\t5.0\nC\t6.0\n";
        TreeMap<String, ArrayList<Double>> map = GraphingMethods.readTable(new Scanner(data));
        assertEquals(3, map.size());
        assertEquals(1, map.get("A").size());
        assertEquals(1, map.get("B").size());
        assertEquals(1, map.get("C").size());
        assertEquals(4.0, map.get("A").get(0), 1e-6);
        assertEquals(5.0, map.get("B").get(0), 1e-6);
        assertEquals(6.0, map.get("C").get(0), 1e-6);
    }

    @Test
    public void manyLines100 ()
    {
        manyLines(10, 100);
    }

    @Test
    public void manyLines1000 ()
    {
        manyLines(50, 1000);
    }

    @Test
    public void manyLines10000 ()
    {
        manyLines(300, 10000);
    }

    @Test
    public void manyLines50000 ()
    {
        manyLines(500, 50000);
    }

    /**
     * Randomly generates input for readTable that contains nKeys different keys and nValues values, then uses that
     * input to test readTable.
     */
    public void manyLines (int nKeys, int nValues)
    {
        Random rand = new Random(nValues);

        TreeMap<String, ArrayList<Double>> expected = new TreeMap<>();
        StringBuilder data = new StringBuilder();

        for (int i = 0; i < nValues; i++)
        {
            String key = "K" + rand.nextInt(nKeys);
            double val = rand.nextDouble() * 1e5 + 1;
            if (!expected.containsKey(key))
            {
                expected.put(key, new ArrayList<>());
            }
            expected.get(key).add(val);
            data.append(key + "\t" + val + "\n");
        }

        TreeMap<String, ArrayList<Double>> actual = GraphingMethods.readTable(new Scanner(data.toString()));
        assertEquals(expected, actual);
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyError1 ()
    {
        TreeMap<String, Double> summaryMap = new TreeMap<>();
        TreeMap<String, Color> colorMap = new TreeMap<>();
        GraphingMethods.drawGraph(null, summaryMap, colorMap, true); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void missingKeyError ()
    {
        TreeMap<String, Double> summaryMap = new TreeMap<>();
        summaryMap.put("x", 5.0);
        TreeMap<String, Color> colorMap = new TreeMap<>();
        colorMap.put("x", Color.RED);
        colorMap.put("y", Color.BLUE);
        GraphingMethods.drawGraph(null, summaryMap, colorMap, true); 
    }
 
    @Test(expected = IllegalArgumentException.class)
    public void nonPositiveError ()
    {
        TreeMap<String, Double> summaryMap = new TreeMap<>();
        summaryMap.put("x", -5.0);
        summaryMap.put("y", 5.0);
        TreeMap<String, Color> colorMap = new TreeMap<>();
        colorMap.put("x", Color.RED);
        colorMap.put("y", Color.BLUE);
        GraphingMethods.drawGraph(null, summaryMap, colorMap, true); 
    }
}



