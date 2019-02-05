package mobiles2;

import javax.swing.SwingUtilities;

public class MobileExamples
{
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new MobileViewer(makeMobile1(), "Mobile 1"));
    }

    public static Mobile makeMobile1 ()
    {
        Rod m6 = new Rod(28, 7, new Bob(10), new Bob(40));
        Mobile m5 = new Rod(12, 18, new Bob(3), new Bob(2));
        Mobile m4 = new Rod(5, 20, new Bob(20), m5);
        Mobile m3 = new Rod(24, 12, m4, new Bob(50));
        Mobile m2 = new Rod(30, 10, new Bob(25), m3);
        Mobile m1 = new Rod(20, 40, m2, m6);
        return m1;
    }

    public static Mobile makeMobile2 ()
    {
        Mobile m6 = new Rod(28, 7, new Bob(10), new Bob(40));
        Mobile m5 = new Rod(10, 20, new Bob(3), new Bob(2));
        Mobile m4 = new Rod(5, 20, new Bob(20), m5);
        Mobile m3 = new Rod(24, 12, m4, new Bob(50));
        Mobile m2 = new Rod(30, 10, new Bob(25), m3);
        Mobile m1 = new Rod(20, 40, m2, m6);
        return m1;
    }

    public static Mobile makeMobile3 ()
    {
        Mobile m6 = new Rod(28, 7, new Bob(10), new Bob(40));
        Mobile m5 = new Rod(12, 18, new Bob(3), new Bob(2));
        Mobile m4 = new Rod(5, 20, new Bob(20), m5);
        Mobile m3 = new Rod(24, 12, m4, new Bob(50));
        Mobile m2 = new Rod(29, 10, new Bob(25), m3);
        Mobile m1 = new Rod(20, 40, m2, m6);
        return m1;
    }
}
