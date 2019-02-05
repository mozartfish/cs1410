package paint7;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * A JPanel that supports painting shapes via click and drag operations. Any
 * number of shapes can be drawn. Notice that PaintPanel extends JPanel and
 * implements two interfaces, which allows a PaintPanel object to deal with the
 * mouse events that occur over it.
 */
@SuppressWarnings("serial")
public class PaintPanel extends JPanel implements MouseListener,
        MouseMotionListener
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
     * When Swing determines that a component needs to be painted, it calls this
     * method. The version that comes with JPanel fills in the background color.
     * This overrides that version by also painting the shapes.
     */
    @Override
    public void paintComponent (Graphics g)
    {
        // We invoke the original, overridden version to paint the background.
        super.paintComponent(g);

        // Turn on anti-aliasing
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

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
     * This is called whenever a mouse button is pressed when the mouse is
     * inside this PaintPanel. Creates a new (single-point) Shape, adds it to
     * the list of shapes, and asks Swing to repaint this PaintPanel.
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
        int x = e.getX();
        int y = e.getY();
        Shape current = shapes.get(shapes.size()-1);
        current.setExtent(x, y);
        repaint();
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
    }
}

/**
 * Shapes that can be drawn on a PaintPanel.  Every shape has
 * an origin and a color.
 */
abstract class Shape
{
    // The origin and color of the shape
    private int originX;
    private int originY;
    private Color color;
    
    public Shape (int x, int y, Color c)
    {
        originX = x;
        originY = y;
        color = c;
    }
       
    /**
     * Sets the extent (height, width, length, whatever) of
     * this shape
     */
    public abstract void setExtent (int x, int y);

    /**
     * Returns the x coordinate of the origin
     */
    protected int getX ()
    {
        return originX;
    }

    /**
     * Returns the y coordinate of the origin
     */
    protected int getY ()
    {
        return originY;
    }

    /**
     * Draws this shape
     */
    public void paint (Graphics g)
    {
        g.setColor(color);
    }
}

/**
 * Represents the center, radii, and color of an Oval.
 */
class Oval extends Shape
{
    // Center, radius in both x (horizontal) and y (vertical) dimensions, and
    // color.    
    private int radiusX;
    private int radiusY;
    
    /**
     * Creates a new oval of the specified center and color.
     */
    public Oval (int x, int y, Color c)
    {
        super(x, y, c);
        radiusX = 0;
        radiusY = 0;
    }
    
    /**
     * Sets the radii of this Oval
     */
    @Override
    public void setExtent (int x, int y)
    {
        radiusX = Math.abs(getX() - x);
        radiusY = Math.abs(getY() - y);
    }

    /**
     * Paints the oval onto g.
     */
    @Override
    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawOval(getX() - radiusX, getY() - radiusY, 2 * radiusX,
                2 * radiusY);
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
        width = 0;
        height = 0;
    }
    
    /**
     * Sets the dimensions of this Rectangle
     */
    @Override
    public void setExtent (int x, int y)
    {
        width = Math.abs(getX() - x);
        height = Math.abs(getY() - y);
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
        endX = x;
        endY = y;
    }
    
    /**
     * Sets the endpoint of this Line
     */
    @Override
    public void setExtent (int x, int y)
    {
        endX = x;
        endY = y;
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
