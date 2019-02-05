package paint5;

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
        switch (shapeButtons.getSelection().getActionCommand())
        {
            case "oval":
                Oval o = new Oval(e.getX(), e.getY(), getColor());
                shapes.add(o);
                break;
            case "rect":
                Rectangle r = new Rectangle(e.getX(), e.getY(), getColor());
                shapes.add(r);
                break;
            case "line":
                Line l = new Line(e.getX(), e.getY(), getColor());
                shapes.add(l);
                break;
            default:
                return;
        }

        // Request a repaint
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
 * A class that implements what all shapes have in common
 */
class Shape
{
    // Origin and color of the Shape
    private int x;
    private int y;
    private Color color;

    /**
     * Constructs shape with specified origin and color
     */
    public Shape (int x, int y, Color c)
    {
        this.x = x;
        this.y = y;
        this.color = c;
    }

    /**
     * Paint the Shape on g. All this does is set the color.
     */
    public void paint (Graphics g)
    {
        g.setColor(color);
    }

    /**
     * Returns the x coordinate of the origin
     */
    public int getX ()
    {
        return x;
    }

    /**
     * Returns the y coordinate of the origin
     */
    public int getY ()
    {
        return y;
    }
}

/**
 * Represents the center, radii, and color of an Oval.
 */
class Oval extends Shape
{
    // Radius in both x (horizontal) and y (vertical) directions
    private int radiusX;
    private int radiusY;

    /**
     * Creates a new oval of the specified center and color.
     */
    public Oval (int x, int y, Color c)
    {
        super(x, y, c);
        radiusX = 100;
        radiusY = 100;
    }

    /**
     * Paints the oval onto g.
     */
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawOval(getX() - radiusX, getY() - radiusY, 2 * radiusX, 2 * radiusY);
    }
}

/**
 * Represents the center, dimensions, and color of a Rectangle.
 */
class Rectangle extends Shape
{
    // Information about this rectangle
    private int width;
    private int height;

    /**
     * Constructs a new Rectangle of the specified center and color.
     */
    public Rectangle (int x, int y, Color c)
    {
        super(x, y, c);
        width = 150;
        height = 150;
    }

    /**
     * Paints this rectangle
     */
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawRect(getX() - width, getY() - height, 2 * width, 2 * height);
    }
}

/**
 * Represents the endpoints and color of a Line.
 */
class Line extends Shape
{
    // Information about this line
    private int endX;
    private int endY;

    /**
     * Creates a Line of the specified starting point and color
     */
    public Line (int x, int y, Color c)
    {
        super(x, y, c);
        endX = x + 100;
        endY = y + 100;
    }

    /**
     * Paints this line
     */
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawLine(getX(), getY(), endX, endY);
    }
}
