package animation;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Animation
{
	private static int xCoordinate;

    /**
     * This is the method that you need to rewrite to create a custom animation. This method is called repeatedly as the
     * animation proceeds. It needs to draw on g how the animation should look after time milliseconds have passed.
     * 
     * @param g Graphics object on which to draw
     * @param time Number of milliseconds that have passed since animation started
     */
    public static void paintFrame (Graphics g, int time, int height, int width)
    {
        g.drawRect(0, 0, 1000, 1000);

        if ((time > 0) && (time < 3500))
        {
            if (time % 2 == 0)
            {
                g.setColor(Color.RED);
                g.fillRect(0, 0, 1000, 1000);

            }
            else if (time % 3 == 0)
            {
                g.setColor(Color.BLUE);
                g.fillRect(0, 0, 1000, 1000);

            }
            else
            {
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, 1000, 1000);

            }
            if ((time > 0) && (time < 1000))
            {
                g.setColor(Color.BLACK);
                g.drawString("3", 500, 500);
                g.setFont(new Font("Arial", Font.BOLD, 30));
            }
            else if ((time > 1000) && (time < 2001))
            {
                g.setColor(Color.BLACK);
                g.drawString("2", 500, 500);
                g.setFont(new Font("Arial", Font.BOLD, 30));
            }

            else if ((time > 2000) && (time < 3001))
            {
                g.setColor(Color.BLACK);
                g.drawString("1", 500, 500);
                g.setFont(new Font("Arial", Font.BOLD, 30));
            }
            else
            {
                g.setColor(Color.BLACK);
                g.drawString("ACTION!", 500, 500);
                g.setFont(new Font("Arial", Font.BOLD, 30));
            }

        }
        if (time > 3500)
        {
            drawWaves(g, Color.RED);
        }
        if ((time > 3500) && (time < 4000))
        {
            if ((time > 3500) && (time < 3800))
            {
                g.setColor(Color.BLACK);
                g.drawString("This is a moving rectangle which is orange", 200, 200);
            }
            else
            {
                g.setColor(Color.BLUE);
                g.drawString("This is a moving rectangle which is orange", 200, 200);
            }
        }

        drawMovingRectangle1(g, time, 5, 200);
        drawMovingCircle1(g, time, 1000, 500);
        drawMovingCircle2(g, time, 0, 0);
        drawMovingRectangle2(g, time, 500, 500);
        drawMovingRectangle2(g, time, 300, 400);
        drawExpandingShrinkingShapes(g, time, 200, 200);

    }

    /**
     * draws a rectangle that moves sinusoidally
     * 
     * @param g
     * @param time
     * @param initialX
     * @param initialY
     */
    public static void drawMovingRectangle1 (Graphics g, int time, int initialX, int initialY)
    {
        if ((time > 3500) && (time < 5000))
        {
            drawRectangle(g, initialX + time / 10, initialY + (int) (50 * Math.sin(time * Math.PI / 5000)), 1,
                    Color.ORANGE);
        }

    }

    /**
     * draws a rectangle that moves horizontally and changes color
     * 
     * @param g
     * @param time
     * @param initialX
     * @param initialY
     */
    public static void drawMovingRectangle2 (Graphics g, int time, int initialX, int initialY)
    {

        if ((time > 5000) && (time < 7001))
        {
            xCoordinate = initialX + (time / 25);
            drawRectangle(g, (xCoordinate), (initialY), 1, Color.CYAN);
        }
        else if ((time > 7000) && (time < 9000))
        {
            xCoordinate = xCoordinate - 1;
            drawRectangle(g, (xCoordinate), (initialY), 1, Color.ORANGE);
        }

    }

    /**
     * draws a shape that expands or shrinks
     * 
     * @param g
     * @param time
     * @param initialX
     * @param initialY
     */
    public static void drawExpandingShrinkingShapes (Graphics g, int time, int initialX, int initialY)
    {
        if ((time > 5000) && (time < 7000))
        {
            drawCircle(g, (initialX + (time / 25)), (initialY + (time / 25)), 1.0 - ((time - 5000) / 200.0),
                    Color.GRAY);
        }
    }

    /**
     * draws a circle that moves and changes color
     * 
     * @param g
     * @param time
     * @param initialX
     * @param initialY
     */

    public static void drawMovingCircle1 (Graphics g, int time, int initialX, int initialY)
    {
        if ((time > 5000) && (time < 7000))
        {
            drawCircle(g, (initialX - (time / 25)), (initialY - (time / 25)), 1, Color.GREEN);
        }
        else if ((time > 7000) && (time < 9000))
        {
            drawCircle(g, (initialX - (time / 25)), (initialY - (time / 25)), 1, Color.BLUE);
        }

    }

    /**
     * draws a circle that moves and changes color
     * 
     * @param g
     * @param time
     * @param initialX
     * @param initialY
     */
    public static void drawMovingCircle2 (Graphics g, int time, int initialX, int initialY)
    {
        if ((time > 5000) && (time < 7000))
        {
            drawCircle(g, (initialX + (time / 25)), (initialY + (time / 25)), 1, Color.RED);
        }
        else if ((time > 7000) && (time < 9000))
        {
            drawCircle(g, (initialX + (time / 25)), (initialY + (time / 25)), 1, Color.ORANGE);
        }
    }

    /**
     * draws red waves
     * 
     * @param g
     * @param color
     */

    public static void drawWaves (Graphics g, Color color)
    {
        g.setColor(color);
        g.drawArc(0, 400, 150, 100, 0, -180);
        g.drawArc(150, 400, 150, 100, 0, -180);
        g.drawArc(0, 400, 150, 100, 0, -180);
        g.drawArc(150, 400, 150, 100, 0, -180);
        g.drawArc(300, 400, 150, 100, 0, -180);
        g.drawArc(450, 400, 150, 100, 0, -180);
        g.drawArc(600, 400, 150, 100, 0, -180);
        g.drawArc(750, 400, 150, 100, 0, -180);
        g.drawArc(900, 400, 100, 100, 0, -180);
    }

    /**
     * draws a rectangle
     * 
     * @param g
     * @param x
     * @param y
     * @param scale
     * @param color
     */
    public static void drawRectangle (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.drawRect(x, y, (int) (50 * scale), (int) (30 * scale));
    }

    /**
     * draws a circle
     * 
     * @param g
     * @param x
     * @param y
     * @param scale
     * @param color
     */
    public static void drawCircle (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.fillOval(x, y, (int) (50 * scale), (int) (30 * scale));

    }
	
	
    /**
     * This is the method that you need to rewrite to create a custom animation. This method is called repeatedly as the
     * animation proceeds. It needs to draw on g how the animation should look after time milliseconds have passed.
     * 
     * @param g Graphics object on which to draw
     * @param time Number of milliseconds that have passed since animation started
     */
    /*public static void paintFrame (Graphics g, int time, int height, int width)
    {
        drawHouse1(g, time, 5, 400);
        drawHouse2(g, time, 5, 200);
    }
    /*

    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house moves from left to right for two seconds, after which it moves down. The house
     * starts out invisible and grows larger as time goes on. The position of the house at the beginning of the
     * animation is given with initialX and initialY.
     */
    /*public static void drawHouse1 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 2000)
        {
            drawHouse(g, initialX + time / 10, initialY, time / 2000., Color.BLUE);
        }
        else
        {
            int intermediateX = initialX + 2000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (time - 2000) / 10, time / 2000., Color.BLUE);
        }
    }
    */

    /**
     * Draws a single frame of an animation of a house. The frame that is drawn corresponds to the point in the
     * animation time milliseconds after the animation starts.
     * 
     * During the animation, a house follows a sine wave for three seconds, after which it moves up and down. The
     * position of the house at the beginning of the animation is given with initialX and initialY.
     */
    /*public static void drawHouse2 (Graphics g, int time, int initialX, int initialY)
    {
        if (time < 3000)
        {
            drawHouse(g, initialX + time / 10, initialY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
        else
        {
            int intermediateX = initialX + 3000 / 10;
            int intermediateY = initialY;
            drawHouse(g, intermediateX, intermediateY + (int) (50 * Math.sin(time * Math.PI / 1000)), 1, Color.RED);
        }
    }
    */

    /**
     * Draws a house on g whose upper-left corner is at coordinate (x,y) using the provided scaling factor and color.
     * The larger the scaling factor, the larger the house.
     */
    /*public static void drawHouse (Graphics g, int x, int y, double scale, Color color)
    {
        g.setColor(color);
        g.drawRect(x, y, (int) (50 * scale), (int) (30 * scale));
        g.drawLine(x, y, x + (int) (25 * scale), y - (int) (15 * scale));
        g.drawLine(x + (int) (25 * scale), y - (int) (15 * scale), x + (int) (50 * scale), y);
    }
    */
}
