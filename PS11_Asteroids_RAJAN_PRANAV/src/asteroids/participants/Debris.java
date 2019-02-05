package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Path2D;
import asteroids.game.Constants;
import asteroids.game.Controller;
import asteroids.game.Participant;

public class Debris extends Participant
{
    /** The outline of the debris */
    private Shape outline;
    
    /** The game controller */
    private Controller controller;

    public Debris(double x, double y, int size, Controller controller) {
        this.controller = controller;
        
        if (size == 1) {
            createSmallDebrisOutline(x, y);
        }
        else if (size == 2) {
            createLargeDebrisOutline(x, y);
        }
    }
    
    public void createSmallDebrisOutline(double x, double y) {
        setPosition(x, y);
        setVelocity(2, Constants.RANDOM.nextDouble() * Math.PI);
        rotate(Constants.RANDOM.nextDouble() * Math.PI);
        
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, 0);
        poly.lineTo(0, 7);
        poly.closePath();
        outline = poly;
    }
    
    public void createLargeDebrisOutline(double x, double y) {
        setPosition(x, y);
        setVelocity(2, Constants.RANDOM.nextDouble() * Math.PI);
        rotate(Constants.RANDOM.nextDouble() * Math.PI);
        
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, 0);
        poly.lineTo(0, 20);
        poly.closePath();
        outline = poly;
    }

    @Override
    protected Shape getOutline ()
    {
        return outline;
    }
    
    @Override
    public void collidedWith (Participant p)
    {
    }
    
    @Override
    public void countdownComplete (Object payload)
    {
        if (payload.equals("expire"))
        {
            Participant.expire(this);
        }
    }

}
