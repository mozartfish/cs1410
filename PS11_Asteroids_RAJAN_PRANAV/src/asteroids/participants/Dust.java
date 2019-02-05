package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Path2D;
import asteroids.game.Constants;
import asteroids.game.Controller;
import asteroids.game.Participant;

public class Dust extends Participant
{
    /** The outline of the dust */
    private Shape outline;

    /** The game controller */
    private Controller controller;

    public Dust (double x, double y, Controller controller)
    {
        this.controller = controller;

        createDustOutline(x, y);
    }

    public void createDustOutline (double x, double y)
    {
        setPosition(x, y);
        setVelocity(2, Constants.RANDOM.nextDouble() * Math.PI);

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, -1);
        poly.lineTo(0.5, 0);
        poly.lineTo(-0.5, 0);
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
