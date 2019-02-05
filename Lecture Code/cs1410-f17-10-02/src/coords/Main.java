  package coords;

import java.util.*;

public class Main
{
    public static void main (String[] args)
    {
    	// Create and display a String, a Scanner, and a Random object.
    	String text = "Hello world";
    	Scanner scan = new Scanner("This is a test");
    	Random rand = new Random(5);
    	display0(text, scan, rand);
    	
        // Create and display two Point1 objects
        Coord1 p1 = new Coord1();
        Coord1 p2 = new Coord1();
        display1(p1, p2);

        // Create and manipulate two Point2 objects
        Coord2 p3 = new Coord2();
        p3.lat = 17;
        p3.lon = 22;
        Coord2 p4 = new Coord2();
        p4.lat = 55;
        p4.lon = 67;
        display2(p3, p4);

        // Create and manipulate two Point3 objects
        Coord3 p5 = new Coord3(5, 8);
        Coord3 p6 = new Coord3(7, 9);
        display3(p5, p6);
        p5 = p6;

        // Create and manipulate two Point4 objects
        Coord4 p7 = new Coord4(5, 8);
        Coord4 p8 = new Coord4(7, 9);
        display4(p7, p8);

        // Create and manipulate two Point5 objects
        Coord5 p9 = new Coord5(5, 8);
        Coord5 p10 = new Coord5(7, 9);
        p9.getLongitude();
        display5(p9, p10);
    }

    private static void display0 (String t, Scanner s, Random r)
    {
        System.out.println(t);
        System.out.println(s);
        System.out.println(r);
        System.out.println();
    }
    
    private static void display1 (Coord1 pt1, Coord1 pt2)
    {
        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println();
    }
    
    private static void display2 (Coord2 pt1, Coord2 pt2)
    {
        System.out.println(pt1.lat + " " + pt1.lon + " " + pt1);
        System.out.println(pt2.lat + " " + pt2.lon + " " + pt2);
        System.out.println();
    }
    
    private static void display3 (Coord3 p5, Coord3 p6)
    {
        System.out.println(p5.lat + " " + p5.lon + " " + p5);
        System.out.println(p6.lat + " " + p6.lon + " " + p6);
        System.out.println();
    }
    
    private static void display4 (Coord4 pt1, Coord4 pt2)
    {
        System.out.println(pt1.getLatitude() + " " + pt1.getLongitude() + " " + pt1);
        System.out.println(pt2.getLatitude() + " " + pt2.getLongitude() + " " + pt2);
        System.out.println();
    }
    
    private static void display5 (Coord5 pt1, Coord5 pt2)
    {
        System.out.println(pt1);
        System.out.println(pt2);
        System.out.println();
    }
}
