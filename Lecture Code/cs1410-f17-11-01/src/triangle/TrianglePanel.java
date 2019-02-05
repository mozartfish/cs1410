package triangle;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel that supports display Sierpinski triangles.
 */
@SuppressWarnings("serial")
public class TrianglePanel extends JPanel
{
    // Button groups for determining color and levels
    private ButtonGroup foreground;
    private ButtonGroup levels;

    /**
     * Creates a PaintPanel containing no shapes
     */
    public TrianglePanel (ButtonGroup foreground, ButtonGroup levels)
    {
        setBackground(Color.WHITE);
        this.foreground = foreground;
        this.levels = levels;
    }

    /**
     * When Swing determines that a component needs to be painted, it calls this method.
     */
    @Override
    public void paintComponent (Graphics g)
    {
        // We invoke the original, overridden version to paint the background.
        super.paintComponent(g);

        // Turn on anti-aliasing
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        int level = Integer.parseInt(levels.getSelection().getActionCommand());
        Color color = new Color(Integer.parseInt(foreground.getSelection().getActionCommand()));
        g.setColor(color);

        Sierpinski.drawSierpinskiTriangle(level, (Graphics2D) g, getWidth() / 2.0, 0, 0, getHeight(), getWidth(), getHeight());
    }
}
