package cs1410;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Methods in support of PS6.
 * 
 * @author Pranav Rajan and Joe Zachary
 */
public class GraphingMethods
{
    /**
     * Constant used to request a max operation
     */
    public final static int MAX = 0;

    /**
     * Constant used to request a min operation
     */
    public final static int MIN = 1;

    /**
     * Constant used to request a sum operation
     */
    public final static int SUM = 2;

    /**
     * Constant used to request an average operation
     */
    public final static int AVG = 3;

    /**
     * The dataSource must consist of one or more lines. If there is not at least one line, the method throws an
     * IllegalArgumentException whose message explains what is wrong.
     * 
     * Each line must consist of some text (a key), followed by a tab character, followed by a double literal (a value),
     * followed by a newline.
     * 
     * If any lines are encountered that don't meet this criteria, the method throws an IllegalArgumentException whose
     * message explains what is wrong.
     * 
     * Otherwise, the map returned by the method (here called categoryMap) must have all of these properties:
     * 
     * (1) The set of keys contained by categoryMap must be the same as the set of keys that occur in the Scanner
     * 
     * (2) The list valueMap.get(key) must contain exactly the same numbers that appear as values on lines in the
     * Scanner that begin with key. The values must occur in the list in the same order as they appear in the Scanner.
     * 
     * For example, if the Scanner contains
     * 
     * <pre>
     * Utah        10
     * Nevada       3
     * Utah         2
     * California  14
     * Arizona     21
     * Utah         2
     * California   7
     * California   6
     * Nevada      11
     * California   1
     * </pre>
     * 
     * (where the spaces in each line are intended to be a single tab), then this map should be returned:
     * 
     * <pre>
     *  Arizona    {21}
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     */
    public static TreeMap<String, ArrayList<Double>> readTable (Scanner dataSource)
    {
        TreeMap<String, ArrayList<Double>> valueMap = new TreeMap<>();

        while (dataSource.hasNextLine())
        {   // Split the line into tokens using tab as the delimiter. Check if there are exactly two tokens.
            String[] tokens = dataSource.nextLine().split("\t");
            if (tokens.length != 2)
            {
                throw new IllegalArgumentException("File contains a line without two tokens");
            }

            // Check if each value is a double
            String key = tokens[0];
            double value;
            try
            {
                value = Double.parseDouble(tokens[1]);
            }
            catch (NumberFormatException e)
            {
                throw new IllegalArgumentException("File contains an invalid double literal: " + tokens[1]);
            }

            // Update the map
            ArrayList<Double> values = valueMap.get(key);
            if (values == null)
            {
                values = new ArrayList<>();
                valueMap.put(key, values);
            }
            values.add(value);
        }
        // If the final map is empty throw an exception. Otherwise return the map
        if (valueMap.size() == 0)
        {
            throw new IllegalArgumentException("File contains no lines");
        }

        else
        {
            return valueMap;
        }
    }

    /**
     * If categoryMap is of size zero, throws an IllegalArgumentException whose message explains what is wrong.
     * 
     * Else if any of the values in the category map is an empty set, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Else if any of the numbers in the categoryMap is not positive, throws an IllegalAgumentException whose message
     * explains what is wrong.
     * 
     * Else if operation is anything other than SUM, AVG, MAX, or MIN, throws an IllegalArgumentException whose message
     * explains what is wrong.
     *
     * Else, returns a TreeMap<String, Double> (here called summaryMap) such that:
     * 
     * (1) The sets of keys contained by categoryMap and summaryMap are the same
     * 
     * (2) For all keys, summaryMap.get(key) is the result of combining the numbers contained in the set
     * categoryMap.get(key) using the specified operation. If the operation is MAX, "combining" means finding the
     * largest of the numbers. If the operation is MIN, "combining" means finding the smallest of the numbers. If the
     * operation is SUM, combining means summing the numbers. If the operation is AVG, combining means averaging the
     * numbers.
     * 
     * For example, suppose the categoryMap maps like this:
     * 
     * <pre>
     *  Arizona    {21
     *  California {14, 7, 6, 1} 
     *  Nevada     {3, 11}
     *  Utah       {10, 2, 2}
     * </pre>
     * 
     * and the operation is SUM. The map that is returned must map like this:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     */
    public static TreeMap<String, Double> prepareGraph (TreeMap<String, ArrayList<Double>> categoryMap, int operation)
    {
        // categoryMap must not be empty
        if (categoryMap.size() == 0)
        {
            throw new IllegalArgumentException("categoryMap is size zero");
        }

        // All sets must be non-empty and all values must be positive numbers
        for (ArrayList<Double> values : categoryMap.values())
        {
            if (values.size() == 0)
            {
                throw new IllegalArgumentException("Category maps to an empty list of values");
            }

            for (Double d : values)
            {
                if (d <= 0)
                {
                    throw new IllegalArgumentException("Category maps to a list containing a non-positive double");
                }
            }
        }

        // Check to see if an operation is valid
        if ((operation < MAX) || (operation > AVG))
        {
            throw new IllegalArgumentException("Invalid operation specified: " + operation);
        }

        // Obtain the summary value for each category
        TreeMap<String, Double> summaryMap = new TreeMap<>();

        for (String category : categoryMap.keySet())
        {

            double operationVal;
            if (operation == MAX)
            {
                operationVal = MAX(categoryMap.get(category));

            }
            else if (operation == MIN)
            {
                operationVal = MIN(categoryMap.get(category));

            }
            else if (operation == SUM)
            {
                operationVal = SUM(categoryMap.get(category));

            }
            else
            {
                operationVal = AVG(categoryMap.get(category));

            }

            summaryMap.put(category, operationVal);

        }

        // Return result
        return summaryMap;

    }

    /**
     * MAX takes an ArrayList for a parameter and then returns the greatest value in the ArrayList
     * 
     * @param Val
     * @return
     */
    public static double MAX (ArrayList<Double> Val)
    {
        double greatestValSoFar = 0.0;
        for (int i = 0; i < Val.size(); ++i)
        {
            greatestValSoFar = Math.max(Val.get(i), greatestValSoFar);
        }
        return greatestValSoFar;
    }

    /**
     * MIN takes an ArrayList for a parameter and then returns the least value in the ArrayList
     * 
     * @param Val
     * @return
     */
    public static double MIN (ArrayList<Double> Val)
    {
        double leastValSoFar = Val.get(0);
        for (int i = 0; i < Val.size(); ++i)
        {
            leastValSoFar = Math.min(Val.get(i), leastValSoFar);
        }
        return leastValSoFar;
    }

    /**
     * SUM takes an ArrayList for a parameter and returns the total sum of the all the elements in the ArrayList
     * 
     * @param Val
     * @return
     */
    public static double SUM (ArrayList<Double> Val)
    {
        double sumSoFar = 0.0;
        for (int i = 0; i < Val.size(); ++i)
        {
            sumSoFar = sumSoFar + Val.get(i);
        }
        return sumSoFar;
    }

    /**
     * Uses the SUM method above and take an ArrayList as a parameter and returns the average of all the elements in the
     * ArrayList
     * 
     * @param Val
     * @return
     */
    public static double AVG (ArrayList<Double> Val)
    {
        double averageOfSum = (SUM(Val) / Val.size());
        return averageOfSum;
    }

    /**
     * If colorMap is empty, throws an IllegalArgumentException.
     * 
     * If there is a key in colorMap that does not occur in summaryMap, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * If any of the numbers in the summaryMap is non-positive, throws an IllegalArgumentException whose message
     * explains what is wrong.
     * 
     * Otherwise, displays on g the subset of the data contained in summaryMap that has a key that appears in colorMap
     * with either a pie chart (if usePieChart is true) or a bar graph (otherwise), using the colors in colorMap.
     * 
     * Let SUM be the sum of all the values in summaryMap whose keys also appear in colorMap, let KEY be a key in
     * colorMap, let VALUE be the value to which KEY maps in summaryMap, and let COLOR be the color to which KEY maps in
     * colorMap. The area of KEY's slice (in a pie chart) and the length of KEY's bar (in a bar graph) must be
     * proportional to VALUE/SUM. The slice/bar should be labeled with both KEY and VALUE, and it should be colored with
     * COLOR.
     * 
     * For example, suppose summaryMap has this mapping:
     * 
     * <pre>
     *  Arizona    21
     *  California 28 
     *  Nevada     14
     *  Utah       14
     * </pre>
     * 
     * and colorMap has this mapping:
     * 
     * <pre>
     *  California Color.GREEN
     *  Nevada     Color.BLUE
     *  Utah       Color.RED
     * </pre>
     * 
     * Since Arizona does not appear as a key in colorMap, Arizona's entry in summaryMap is ignored.
     * 
     * In a pie chart Utah and Nevada should each have a quarter of the pie and California should have half. In a bar
     * graph, California's line should be twice as long as Utah's and Nevada's. Utah's slice/bar should be red, Nevada's
     * blue, and California's green.
     * 
     * The method should display the pie chart or bar graph by drawing on the g parameter. The example code below draws
     * both a pie chart and a bar graph for the situation described above.
     */
    public static void drawGraph (Graphics g, TreeMap<String, Double> summaryMap, TreeMap<String, Color> colorMap,
            boolean usePieChart)
    {
        // This implementation ignores its parameters (except for usePieChart) and draws a pie chart or a bar graph
        // for the data described in the Javadoc.

        final int TOP = 10;        // Offset of graph from top edge
        final int LEFT = 10;       // Offset of graph from left edge
        final int DIAM = 300;      // Diameter of pie chart
        final int WIDTH = 10;      // Width of bar in bar chart
        
        // The colorMap must be non-empty
        if (colorMap.size() == 0)
        {
            throw new IllegalArgumentException("colorMap is empty");
        }
        
        // The keys in colorMap must be a subset of the ones in summaryMap
        for (String key : colorMap.keySet())
        {
            if (!summaryMap.containsKey(key))
            {
                throw new IllegalArgumentException("colorMap contains an unknown key: " + key);
            }
        }
        
        // Add up all the numbers that are involved, making sure that none of the numbers is negative
         double sum = 0.0;
         
         for (String key : colorMap.keySet())
         {
             if (summaryMap.containsKey(key))
             {
                 double d = summaryMap.get(key);
                 if (d <= 0)
                 {
                     throw new IllegalArgumentException("summaryMap contains non-positive value: " + d);
                 }
                 sum += d;
             }
         }
         
        // Draw a pie chart if requested
        if (usePieChart)
        {
            int countPie = 0;
            double startAngle = 0;

            for (String category : colorMap.keySet())
            {

                double intialArcSize = (summaryMap.get(category));
                double arcAngle = (intialArcSize / sum * 360);
                double arcAngleDisplay = 0;
                if (countPie == 0)
                {
                    arcAngleDisplay = 360;
                }
                else
                {
                    arcAngleDisplay = arcAngle;
                }

                g.setColor(colorMap.get(category));
                g.fillArc(LEFT, TOP, DIAM, DIAM, (int) startAngle, (int) arcAngleDisplay);
                g.fillRect(LEFT + DIAM + 2 * WIDTH, TOP + countPie * WIDTH, WIDTH, WIDTH);
                g.setColor(Color.black);
                g.drawString(category + " " + String.format("%.2f", summaryMap.get(category)), LEFT + DIAM + 4 * WIDTH,
                        TOP + (countPie + 1) * WIDTH);

                countPie += 2;
                startAngle = startAngle + arcAngle;

            }

        }

        // Draw a bar chart if requested
        else
        {
            int topOffset = TOP;
            for (String category : colorMap.keySet())
            {
                g.setColor(colorMap.get(category));
                int length = (int) Math.rint(DIAM * summaryMap.get(category) / sum);
                g.fillRect(LEFT + DIAM - length, topOffset, length, 2 * WIDTH);
                g.setColor(Color.black);
                g.drawString(category + " " + String.format("%.2f", summaryMap.get(category)), LEFT + DIAM + 2 * WIDTH,
                        topOffset + WIDTH + WIDTH / 2);
                topOffset += 3 * WIDTH;
            }

        }
    }
}
