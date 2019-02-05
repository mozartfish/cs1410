package cs1410;


import java.io.FileNotFoundException;
import javax.swing.SwingUtilities;
import graphics.GraphWindow;

public class Main
{
    /**
     * Displays a GUI interface for viewing data
     * 
     * @throws FileNotFoundException
     */
    public static void main (String[] args)
    {

        SwingUtilities.invokeLater( () -> new GraphWindow().setVisible(true));

    }
   
}
