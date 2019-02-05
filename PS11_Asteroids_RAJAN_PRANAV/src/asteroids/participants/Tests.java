package asteroids.participants;

import static org.junit.Assert.*;
import org.junit.Test;

public class Tests
{

    @Test
    public void testGetAngle_45degrees ()
    {
        double fromX = 0;
        double fromY = 0;
        double toX = 10;
        double toY = 10;
        double actual = AlienShip.getAngle(fromX, fromY, toX, toY);
        double correct = 1 * Math.PI / 4;
        
        assertEquals(correct, actual, 0.000000001);
    }
    
    @Test
    public void testGetAngle_135degrees ()
    {
        double fromX = 0;
        double fromY = 0;
        double toX = -10;
        double toY = 10;
        double actual = AlienShip.getAngle(fromX, fromY, toX, toY);
        double correct = 3 * Math.PI / 4;
        
        assertEquals(correct, actual, 0.000000001);
    }
    
    @Test
    public void testGetAngle_225degrees ()
    {
        double fromX = 0;
        double fromY = 0;
        double toX = -10;
        double toY = -10;
        double actual = AlienShip.getAngle(fromX, fromY, toX, toY);
        double correct = 5 * Math.PI / 4;
        
        assertEquals(correct, actual, 0.000000001);
    }
    
    @Test
    public void testGetAngle_315degrees ()
    {
        double fromX = 0;
        double fromY = 0;
        double toX = 10;
        double toY = -10;
        double actual = AlienShip.getAngle(fromX, fromY, toX, toY);
        double correct = 7 * Math.PI / 4;
        
        assertEquals(correct, actual, 0.000000001);
    }

}
