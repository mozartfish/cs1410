package paint4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A JPanel that supports painting shapes via click and drag operations. Any number of shapes can be drawn. Notice that
 * PaintPanel extends JPanel and implements two interfaces, which allows a PaintPanel object to deal with the mouse
 * events that occur over it.
 */
@SuppressWarnings("serial")
public class PaintPanel extends JPanel implements MouseListener, MouseMotionListener
{
    // Shapes being displayed
    private ArrayList<Shape> shapes;

    // Button groups for selecting shape and color
    private ButtonGroup shapeButtons;
    private ButtonGroup colorButtons;

    /**
     * Creates a PaintPanel containing no shapes
     */
    public PaintPanel (ButtonGroup shapeButtons, ButtonGroup colorButtons)
    {
        shapes = new ArrayList<Shape>();
        setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.shapeButtons = shapeButtons;
        this.colorButtons = colorButtons;
    }

    /**
     * When Swing determines that a component needs to be painted, it calls this method. The version that comes with
     * JPanel fills in the background color. This overrides that version by also painting the shapes.
     */
    @Override
    public void paintComponent (Graphics g)
    {
        // We invoke the original, overridden version to paint the background.
        super.paintComponent(g);

        // Turn on anti-aliasing
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Paint all the shapes.
        for (Shape s : shapes)
        {
            s.paint(g);
        }
    }

    @Override
    public void mouseClicked (MouseEvent e)
    {
    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }

    /**
     * This is called whenever a mouse button is pressed when the mouse is inside this PaintPanel. Creates a new
     * (single-point) Shape, adds it to the list of shapes, and asks Swing to repaint this PaintPanel.
     */
    @Override
    public void mousePressed (MouseEvent e)
    {
        Shape s;
        switch (shapeButtons.getSelection().getActionCommand())
        {
            case "oval":
                s = new Oval(e.getX(), e.getY(), getColor());
                break;
            case "rect":
                s = new Rectangle(e.getX(), e.getY(), getColor());
                break;
            case "line":
                s = new Line(e.getX(), e.getY(), getColor());
                break;
            default:
                return;
        }

        // Request a repaint
        shapes.add(s);
        repaint();
    }

    /**
     * Returns the selected color
     */
    public Color getColor ()
    {
        switch (colorButtons.getSelection().getActionCommand())
        {
            case "red":
                return Color.red;
            case "blue":
                return Color.blue;
            case "green":
                return Color.green;
            default:
                return Color.black;
        }
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
    }

    @Override
    public void mouseDragged (MouseEvent e)
    {
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
    }
}

/**
 * Shapes that can be drawn on a PaintPanel
 */
interface Shape
{
    /**
     * Draws this shape
     */
    public void paint (Graphics g);
}

/**
 * Represents the center, radii, and color of an Oval.
 */
class Oval implements Shape
{
    // Center, radius in both x (horizontal) and y (vertical) dimensions, and
    // color.
    private int centerX;
    private int centerY;
    private int radiusX;
    private int radiusY;
    private Color color;

    /**
     * Creates a new oval of the specified center and color.
     */
    public Oval (int x, int y, Color c)
    {
        centerX = x;
        centerY = y;
        color = c;
        radiusX = 100;
        radiusY = 100;
    }

    /**
     * Paints the oval onto g.
     */
    @Override
    public void paint (Graphics g)
    {
        g.setColor(color);
        g.drawOval(centerX - radiusX, centerY - radiusY, 2 * radiusX, 2 * radiusY);
    }
}

/**
 * Represents the center, dimensions, and color of a Rectangle.
 */
class Rectangle implements Shape
{

    // Information about this rectangle
    private int centerX;
    private int centerY;
    private Color color;
    private int width;
    private int height;

    /**
     * Constructs a new Rectangle of the specified center and color.
     */
    public Rectangle (int x, int y, Color c)
    {
        centerX = x;
        centerY = y;
        color = c;
        width = 150;
        height = 150;
    }

    /**
     * Paints this rectangle
     */
    @Override
    public void paint (Graphics g)
    {
        g.setColor(color);
        g.drawRect(centerX - width, centerY - height, 2 * width, 2 * height);
    }
}

/**
 * Represents the endpoints and color of a Line.
 */
class Line implements Shape
{
    // Information about this line
    private int startX;
    private int startY;
    private Color color;
    private int endX;
    private int endY;

    /**
     * Creates a Line of the specified starting point and color
     */
    public Line (int x, int y, Color c)
    {
        startX = x;
        startY = y;
        color = c;
        endX = x + 100;
        endY = y + 100;
    }

    /**
     * Paints this line
     */
    @Override
    public void paint (Graphics g)
    {
        g.setColor(color);
        g.drawLine(startX, startY, endX, endY);
    }
}
