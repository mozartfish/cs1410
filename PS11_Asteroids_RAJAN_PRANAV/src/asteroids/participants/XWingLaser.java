package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Path2D;
import asteroids.destroyers.AlienShipDestroyer;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.BulletDestroyer;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;
import asteroids.game.Constants;

public class XWingLaser extends Participant implements AsteroidDestroyer, AlienShipDestroyer
{
    /** The outline of the laser */
    private Shape outline;
    
    /** The game controller */
    private Controller controller;
    
    public XWingLaser(double x, double y, double direction, Controller controller) {
        // Create the bullet
        this.controller = controller;
        setPosition(x, y);
        setVelocity(Constants.BULLET_SPEED, direction);
        setRotation(direction);
        
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(0, 1);
        poly.lineTo(15, 1);
        poly.lineTo(15, -1);
        poly.lineTo(0, -1);
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
        if (p instanceof BulletDestroyer) {
            // Expire the bullet
            Participant.expire(this);
        }
    }
    
    @Override
    public void countdownComplete(Object payload) {
        if (payload.equals("expire"))
        {
            Participant.expire(this);
        }
    }

}
