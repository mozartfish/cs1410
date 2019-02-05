package vars;

public class StaticVariables
{
    /**
     * A public static variable (just like a public static method) is visible from any part of the program that can
     * access the class that contains the variable's declaration.
     * 
     * A private static variable (just like a private static method) is visible only in the class that contains the
     * variable's declaration.
     * 
     * If a static variable is declared "final", then its value cannot be changed via assignment.
     */

    /**
     * Static final variables (i.e. constants) are commonly used. This method uses two constants from the Java library.
     */
    public static void demo1 ()
    {
        System.out.println(Math.PI);
        //System.out = null;
        //Math.PI = 3.0;
    }

    /**
     * A static constant should be public if it is generally useful, and should be private if it is meaningful only
     * within its class.
     */
    
    /**
     * Here are two examples of public constants.  By convention, constants are written in all caps.
     */
    public final static String MY_SCHOOL = "University of Utah";
    public final static int MINIMUM_VOTING_AGE = 18;
    
    /**
     * Here are two examples of private constants.  Use private constants to void putting "magic numbers" into code.
     * By giving constants a name and a comment, they are much easier to understand and to change.
     */
    private final static int OFFSET_FROM_TOP = 65;
    private final static String TITLE = "My Game";

    /**
     * It is also possible to declare static variables that are not constants.  Such variables will be visible
     * throughout the program (if they are public) or throughout the class (if they are private).  They are
     * almost always a BAD IDEA!  What would happen if we declared a variable i in one place and then used it
     * in every method in a program?
     */
    public static int i; 
}
