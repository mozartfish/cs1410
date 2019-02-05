package triangle;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Top-level window for a Sierpinski Triangle application.
 */
@SuppressWarnings("serial")
public class Triangle extends JFrame implements ActionListener
{
    /**
     * Displays a paint window.
     */
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new Triangle());
    }

    // The menu item that closes the window.
    private JMenuItem closeItem;

    /**
     * Constructs a triangle window.
     */
    public Triangle ()
    {
        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Make a PaintPanel the content pane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Make radio buttons
        JPanel foregroundPanel = new JPanel();
        ButtonGroup foregroundButtons = addColoredRadioButtons(foregroundPanel,
                new Color[] { Color.red, Color.green, Color.blue });

        JPanel levelPanel = new JPanel();
        ButtonGroup levelButtons = addLabeledRadioButtons(levelPanel,
                new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" });

        // Add the panels
        mainPanel.add(new TrianglePanel(foregroundButtons, levelButtons), "Center");
        JPanel colors = new JPanel();
        colors.add(new JLabel("Color: "));
        colors.add(foregroundPanel);
        mainPanel.add(colors, "North");
        mainPanel.add(levelPanel, "South");

        // Add a File menu. Register this object as the
        // listener for menu selection events.
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        closeItem = new JMenuItem("Close");
        closeItem.addActionListener(this);
        fileMenu.add(closeItem);
        menubar.add(fileMenu);
        setJMenuBar(menubar);

        // Ready to go
        setPreferredSize(new Dimension(600, 600));
        pack();
        setVisible(true);
    }

    /**
     * Adds to the panel JRadioButtons corresponding to each entry in the array and returns a ButtonGroup containing the
     * new buttons. The colors are used to render and set the action commands of the buttons.
     */
    private ButtonGroup addColoredRadioButtons (JPanel panel, Color[] colors)
    {
        ButtonGroup group = new ButtonGroup();

        for (Color color : colors)
        {
            JRadioButton button = new JRadioButton();
            button.setBackground(color);
            button.setForeground(color);
            button.setActionCommand("" + color.getRGB());
            panel.add(button);
            group.add(button);
            button.addActionListener(this);
        }

        ((JRadioButton) panel.getComponent(0)).setSelected(true);
        return group;
    }

    /**
     * Adds to the panel JRadioButtons corresponding to each entry in the array and returns a ButtonGroup containing the
     * new buttons. The labels are used to label and set the action commands of the buttons.
     */
    private ButtonGroup addLabeledRadioButtons (JPanel panel, String[] labels)
    {
        ButtonGroup group = new ButtonGroup();

        for (String label : labels)
        {
            JRadioButton button = new JRadioButton(label);
            button.setActionCommand(label);
            panel.add(button);
            group.add(button);
            button.addActionListener(this);
        }

        ((JRadioButton) panel.getComponent(0)).setSelected(true);
        return group;
    }

    /**
     * Deals with menu actions.
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
        // Close the window
        if (e.getSource() == closeItem)
        {
            dispose();
        }
        else if (e.getSource() instanceof JRadioButton)
        {
            repaint();
        }
    }
}
