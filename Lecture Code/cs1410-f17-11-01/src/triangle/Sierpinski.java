package triangle;

import java.awt.Graphics2D;
import java.awt.geom.Path2D;

public class Sierpinski
{
    /**
     * Draws a Sierpinski triangle of the specified level >= 0 on the provided Graphics object. The three vertices of
     * the triangle are (x1,y1), (x2,y2), and (x3,y3).
     */
    public static void drawSierpinskiTriangle (int level, Graphics2D g, double x1, double y1, double x2, double y2,
            double x3, double y3)
    {       
        if (level <= 0)
        {
            Path2D.Double triangle = new Path2D.Double();
            triangle.moveTo(x1, y1);
            triangle.lineTo(x2, y2);
            triangle.lineTo(x3, y3);
            g.fill(triangle);
        }
        else
        {
            drawSierpinskiTriangle(level - 1, g, x1, y1, (x1 + x2) / 2, (y1 + y2) / 2, (x1 + x3) / 2, (y1 + y3) / 2);
            drawSierpinskiTriangle(level - 1, g, x2, y2, (x2 + x1) / 2, (y2 + y1) / 2, (x2 + x3) / 2, (y2 + y3) / 2);
            drawSierpinskiTriangle(level - 1, g, x3, y3, (x3 + x1) / 2, (y3 + y1) / 2, (x3 + x2) / 2, (y3 + y2) / 2);
        }
    }
}
