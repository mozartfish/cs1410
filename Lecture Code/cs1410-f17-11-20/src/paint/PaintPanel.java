package paint;

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

        // Turn on anti-aliasting
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
        Shape shape;
        switch (shapeButtons.getSelection().getActionCommand())
        {
        case "oval":
            shape = new Oval(e.getX(), e.getY(), getColor());
            break;
        case "rect":
            shape = new Rectangle(e.getX(), e.getY(), getColor());
            break;
        case "line":
            shape = new Line(e.getX(), e.getY(), getColor());
            break;
        default:
            return;
        }

        // Add to list of shape and request a repaint
        shapes.add(shape);
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

    /**
     * This is called whenever the mouse is dragged inside this PaintPanel.
     * Changes the size of the most recently created shape and asks Swing to
     * repaint.
     */
    @Override
    public void mouseDragged (MouseEvent e)
    {
        Shape s = shapes.get(shapes.size() - 1);
        s.setExtent(e.getX(), e.getY());
        repaint();
    }

    @Override
    public void mouseMoved (MouseEvent e)
    {
    }
}

/**
 * An abstract class that implements what all shapes have in common and provides
 * abstract methods to override.
 */
abstract class Shape
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
        color = c;
    }

    /**
     * Set the extent (radius, length, etc.) of the Shape by passing in a point
     * to which the mouse was dragged.
     */
    abstract public void setExtent (int x, int y);

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
     * Sets the radius of this oval to the difference between the center and the
     * point (x,y)
     */
    public void setExtent (int x, int y)
    {
        radiusX = x - getX();
        radiusY = y - getY();
    }

    /**
     * Paints the oval onto g.
     */
    public void paint (Graphics g)
    {
        super.paint(g);
        int centerX = getX();
        int centerY = getY();
        if (radiusX >= 0 && radiusY >= 0)
        {
            g.drawOval(centerX - radiusX, centerY - radiusY, 2 * radiusX,
                    2 * radiusY);
        }
        else if (radiusX <= 0 && radiusY <= 0)
        {
            g.drawOval(centerX + radiusX, centerY + radiusY, -2 * radiusX, -2
                    * radiusY);
        }
        else if (radiusX <= 0 && radiusY >= 0)
        {
            g.drawOval(centerX + radiusX, centerY - radiusY, -2 * radiusX,
                    2 * radiusY);
        }
        else
        {
            g.drawOval(centerX - radiusX, centerY + radiusY, 2 * radiusX, -2
                    * radiusY);
        }
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
     * Sets the width and height of this rectangle to the difference between the
     * center and x,y.
     */
    public void setExtent (int x, int y)
    {
        width = x - getX();
        height = y - getY();
    }

    /**
     * Paints this rectangle
     */
    public void paint (Graphics g)
    {
        super.paint(g);
        int centerX = getX();
        int centerY = getY();
        if (width >= 0 && height >= 0)
        {
            g.drawRect(centerX - width, centerY - height, 2 * width, 2 * height);
        }
        else if (width <= 0 && height <= 0)
        {
            g.drawRect(centerX + width, centerY + height, -2 * width, -2
                    * height);
        }
        else if (width <= 0 && height >= 0)
        {
            g.drawRect(centerX + width, centerY - height, -2 * width,
                    2 * height);
        }
        else
        {
            g.drawRect(centerX - width, centerY + height, 2 * width, -2
                    * height);
        }
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
     * Sets the endpoint of this line
     */
    public void setExtent (int x, int y)
    {
        endX = x;
        endY = y;
    }

    public void paint (Graphics g)
    {
        super.paint(g);
        g.drawLine(getX(), getY(), endX, endY);
    }
}
