package v3;

import javax.swing.Box;
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
        frame.setTitle("Tip Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // A JPanel is a Component on which other Components can be placed.
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));

        // Create the controls
        JLabel amountLabel = new JLabel("Check Amount: ");
        JTextField amount = new JTextField(22);

        JLabel tipLabel = new JLabel("Tip %: ");
        JTextField tip = new JTextField(22);

        JButton calculate = new JButton("Calculate Total");

        JLabel totalLabel = new JLabel("Total: ");
        JTextField total = new JTextField(22);
        total.setEditable(false);

        // Lay out the controls with some vertical space added in
        controlPanel.add(Box.createVerticalStrut(50));

        controlPanel.add(amountLabel);
        amount.setMaximumSize(amount.getPreferredSize());
        controlPanel.add(amount);

        controlPanel.add(Box.createVerticalStrut(25));

        controlPanel.add(tipLabel);
        tip.setMaximumSize(tip.getPreferredSize());
        controlPanel.add(tip);

        controlPanel.add(Box.createVerticalStrut(25));

        controlPanel.add(calculate);

        controlPanel.add(Box.createVerticalStrut(25));

        controlPanel.add(totalLabel);
        total.setMaximumSize(total.getPreferredSize());
        controlPanel.add(total);

        controlPanel.add(Box.createVerticalStrut(50));
        controlPanel.add(Box.createVerticalGlue());

        // To display something in a JFrame, set its content pane
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.LINE_AXIS));
        rootPanel.add(Box.createHorizontalStrut(50));
        rootPanel.add(controlPanel);
        rootPanel.add(Box.createHorizontalGlue());
        frame.setContentPane(rootPanel);

        // Complete layout and make visible
        frame.pack();
        frame.setVisible(true);
    }
}
