package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.Path2D;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.game.Participant;

public class Mine extends Participant implements AsteroidDestroyer
{
    /** The outline of the ship */
    private Shape outline;
    
    public Mine(int posX, int posY, int rotation, int velX, int velY) {
        setPosition(posX, posY);
        setRotation(rotation);
        setVelocity(velX, velY);
        
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(2, 2);
        poly.lineTo(2, -2);
        poly.lineTo(-2, -2);
        poly.lineTo(-2, 2);
        poly.lineTo(2, 2);
        poly.closePath();
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
        // TODO Auto-generated method stub
        
    }

}
