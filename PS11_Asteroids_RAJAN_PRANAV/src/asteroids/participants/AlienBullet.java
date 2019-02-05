package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Random;
import asteroids.destroyers.AlienShipDestroyer;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.BulletDestroyer;
import asteroids.destroyers.ShipDestroyer;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;
import asteroids.game.Constants;

public class AlienBullet extends Participant implements AsteroidDestroyer, ShipDestroyer
{
    /** The outline of the bullet */
    private Shape outline;
    
    /** The game controller */
    private Controller controller;
    
    public AlienBullet(double x, double y, double direction, Controller controller) {
        // Create the bullet
        this.controller = controller;
        setPosition(x, y);
        setVelocity(Constants.BULLET_SPEED, direction);
        
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(1, 1);
        poly.lineTo(1, -1);
        poly.lineTo(-1, -1);
        poly.lineTo(-1, 1); 
        poly.lineTo(1, 1);
        poly.closePath();
        outline = poly;
        
        new ParticipantCountdownTimer(this, "move", Constants.BULLET_DURATION);
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
        Participant.expire(this);
    }

}
