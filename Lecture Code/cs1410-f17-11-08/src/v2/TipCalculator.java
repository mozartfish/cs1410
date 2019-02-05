package v2;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Provides a TipCalculator application
 * 
 * @author Joe Zachary
 */
public class TipCalculator
{
    /**
     * Launches the tip calculator on the GUI event thread.
     */
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new TipCalculator());
    }

    /**
     * Creates and launches a TipCalculator GUI
     */
    public TipCalculator ()
    {
        // A JFrame is a top-level GUI window.
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(300, 400));
        frame.setTitle("Tip Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // A JPanel is a Component on which other Components can be placed.
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));

        // Put the check amount label and field on a panel
        JPanel amountPanel = new JPanel();
        amountPanel.add(new JLabel("Check Amount: "));
        JTextField amount = new JTextField(20);
        amountPanel.add(amount);

        // Put the tip label and field on a panel
        JPanel tipPanel = new JPanel();
        tipPanel.add(new JLabel("Tip %: "));
        JTextField tip = new JTextField(20);
        tipPanel.add(tip);

        // Put the total label and field on a panel
        JPanel totalPanel = new JPanel();
        totalPanel.add(new JLabel("Total: "));
        JTextField total = new JTextField(20);
        total.setEditable(false);
        totalPanel.add(total);

        // Compose all the panels and the button
        rootPanel.add(amountPanel);
        rootPanel.add(tipPanel);
        JButton calculate = new JButton("Calculate Total Including Tip");
        rootPanel.add(calculate);
        rootPanel.add(totalPanel);

        // To display something in a JFrame, set its content pane
        frame.setContentPane(rootPanel);

        // Complete layout and make visible
        frame.pack();
        frame.setVisible(true);
    }
}
