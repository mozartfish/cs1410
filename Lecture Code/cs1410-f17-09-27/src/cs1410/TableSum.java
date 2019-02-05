package cs1410;

import java.util.Scanner;

/**
 * Illustrates exploitation of procedural abstraction to build a program.
 * 
 * @author Joe Zachary
 */
public class TableSum
{
    /**
     * Driver for testing if needed.
     */
    public static void main (String[] args)
    {
    }

    /**
     * The table contains data with one header row followed by zero or more value rows. The columns are separated with
     * tabs and the rows are terminated with newlines. For example:
     * 
     * <pre>
     *   Name    City     Age	Salary
     *   John    SLC      25	75000
     *   Mary    NYC      40	125000
     *   Fred    WVC      18	33000
     *   Sue     SLC      52	80000
     *   Pete    SLC      91	0
     * </pre>
     * 
     * The next two parameters must be headers from the first row. If they aren't, the method throws an
     * IllegalArgumentException.
     * <p>
     * The method locates all the rows that contain conditionValue in the column labeled with conditionHeader, and
     * returns the average of all the values from those rows that appear in the column labeled with valueHeader. If
     * there are no values to average, the method returns 0. If any of the values do not parse as doubles, the method
     * throws a NumberFormatException.
     * <p>
     * For example, conditionalAverage(t, "City", "Age", "SLC") where t is the table above would return the average of
     * 25, 52, and 91.
     * <p>
     * Also, conditionalAverage(t, "City", "Salary", "SLC") would return the average of 75000, 80000, and 0.
     */
    public static double conditionalAverage (Scanner table, String conditionHeader, String valueHeader,
            String conditionValue)
    {
        // Find column numbers of the two headers
        String headerRow = table.nextLine();
        int conditionCol = findColIndex(headerRow, conditionHeader);
        int valueCol = findColIndex(headerRow, valueHeader);

        // Accumulate values that correspond to the
        // condition header
        double sum = 0;
        int count = 0;
        while (table.hasNextLine())
        {
            String line = table.nextLine();
            String[] columns = line.split("\t");
            if (columns[conditionCol].equals(conditionValue))
            {
                count++;
                sum += Double.parseDouble(columns[valueCol]);
            }
        }

        if (count == 0)
        {
            return 0;
        }
        else
        {
            return sum / count;
        }
    }

    /**
     * The parameter headerRow is viewed as containing multiple columns, where the columns are separated with single tab
     * characters. For example, the headerRow "one\ttwo\tthree\tfour" consists of the four columns "one", "two",
     * "three", and "four". The method returns the zero-based index of the first column that equals the parameter
     * header.
     * 
     * @throws IllegalArgumentException if there is no such column
     */
    public static int findColIndex (String headerRow, String header)
    {
        String[] headers = headerRow.split("\t");
        for (int i = 0; i < headers.length; i++)
        {
            if (headers[i].equals(header))
            {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }
}
