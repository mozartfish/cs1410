package v4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Provides a TipCalculator application
 * 
 * @author Joe Zachary
 */
public class TipCalculator implements ActionListener
{
    /**
     * Launches the tip calculator on the GUI event thread.
     */
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new TipCalculator());
    }
    
    private JTextField amount;
    private JTextField tip;
    private JTextField total;
    private JLabel error;

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
        amount = new JTextField(22);

        JLabel tipLabel = new JLabel("Tip %: ");
        tip = new JTextField(22);

        JButton calculate = new JButton("Calculate Total");
        calculate.addActionListener(this);

        JLabel totalLabel = new JLabel("Total: ");
        total = new JTextField(22);
        total.setEditable(false);
        
        error = new JLabel();

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
        controlPanel.add(error);

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

    @Override
    public void actionPerformed (ActionEvent e)
    {
        try
        {
            double amnt = Double.parseDouble(amount.getText());
            double pct = Double.parseDouble(tip.getText());
            double pay = amnt + amnt*pct/100;
            total.setText(pay + "");
        }
        catch (NumberFormatException ex)
        {
            //JOptionPane.showMessageDialog(null, "Bad!");
            error.setText("Bad!");
        }
    }
}