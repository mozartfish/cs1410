package paint7;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * Top-level window for a simple paint application. Notice how this class
 * extends JFrame (which makes it a top-level window) and implements
 * ActionListener (which allows is to register to receive action events).
 */
@SuppressWarnings("serial")
public class Paint extends JFrame implements ActionListener
{
    /**
     * Displays a paint window.
     */
    public static void main (String[] args)
    {
        SwingUtilities.invokeLater( () -> new Paint());
    }

    // The menu item that closes the window.
    private JMenuItem closeItem;

    /**
     * Constructs a paint window.
     */
    public Paint ()
    {
        // Exit on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Make a PaintPanel the content pane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);
        
        // Add shape selectors to the north
        JPanel shapePanel = new JPanel();
        JRadioButton oval, rect, line;
        shapePanel.add(oval = new JRadioButton("Oval"));
        shapePanel.add(rect = new JRadioButton("Rect"));
        shapePanel.add(line = new JRadioButton("Line"));
        ButtonGroup shapeGroup = new ButtonGroup();
        shapeGroup.add(oval);
        shapeGroup.add(rect);
        shapeGroup.add(line);
        oval.setActionCommand("oval");
        rect.setActionCommand("rect");
        line.setActionCommand("line");
        oval.setSelected(true);
        
        // Add color selectors to the north
        JPanel colorPanel = new JPanel();
        JRadioButton red, green, blue;
        colorPanel.add(red = new JRadioButton("Red"));
        colorPanel.add(green = new JRadioButton("Green"));
        colorPanel.add(blue = new JRadioButton("Blue"));
        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(red);
        colorGroup.add(green);
        colorGroup.add(blue);
        red.setActionCommand("red");
        green.setActionCommand("green");
        blue.setActionCommand("blue");
        red.setSelected(true);
        
        JPanel radioPanel = new JPanel();
        radioPanel.add(shapePanel);
        radioPanel.add(colorPanel);
        mainPanel.add(radioPanel, "North");
        
        // Add the paint panel to the center
        mainPanel.add(new PaintPanel(shapeGroup, colorGroup), "Center");

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
     * Deals with menu actions.
     */
    public void actionPerformed (ActionEvent e)
    {
        // Close the window
        if (e.getSource() == closeItem)
        {
            dispose();
        }
    }
}
