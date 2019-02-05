package asteroids.participants;

import static asteroids.game.Constants.*;
import java.awt.Shape;
import java.awt.geom.*;
import asteroids.destroyers.*;
import asteroids.game.Controller;
import asteroids.game.Participant;
import asteroids.game.ParticipantCountdownTimer;
import sounds.SoundLibrary;

/**
 * Represents ships
 */
public class XWing extends Participant implements AsteroidDestroyer, AlienShipDestroyer
{
    /** The outline of the ship */
    private Shape outline;

    /** The outline of the flame */
    private Shape outlineFlame;
    
    /** Game controller */
    private Controller controller;
    
    private SoundLibrary sound;
    
    /**
     * Constructs a ship at the specified coordinates that is pointed in the given direction.
     */
    public XWing (int x, int y, double direction, Controller controller)
    {
        this.controller = controller;
        setPosition(x, y);
        setRotation(direction);

        createXWingOutline();
    }
    
    public void createXWingOutline() {
        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(23, -1);
        poly.lineTo(-9, -5);
        poly.lineTo(-7, -5);
        poly.lineTo(-7, -9);
        poly.lineTo(-9, -9);
        poly.lineTo(-9, -18);
        poly.lineTo(6, -18);
        poly.lineTo(-14, -18);
        poly.lineTo(-17, -8);
        poly.lineTo(-22, -8);
        poly.lineTo(-22, -6);
        poly.lineTo(-17, -6);
        poly.lineTo(-18, -4);
        
        poly.lineTo(-18, 4);
        poly.lineTo(-17, 6);
        poly.lineTo(-22, 6);
        poly.lineTo(-22, 8);
        poly.lineTo(-17, 8);
        poly.lineTo(-14, 18);
        poly.lineTo(6, 18);
        poly.lineTo(-9, 18);
        poly.lineTo(-9, 9);
        poly.lineTo(-7, 9);
        poly.lineTo(-7, 5);
        poly.lineTo(-9, 5);
        poly.lineTo(23, 1);
        poly.closePath();
        outline = poly;
        
        poly.transform(AffineTransform.getScaleInstance(1.5, 1.5));
        
        Path2D.Double poly2 = new Path2D.Double();
        poly2.moveTo(23, -1);
        poly2.lineTo(-9, -5);
        poly2.lineTo(-7, -5);
        poly2.lineTo(-7, -9);
        poly2.lineTo(-9, -9);
        poly2.lineTo(-9, -18);
        poly2.lineTo(6, -18);
        poly2.lineTo(-14, -18);
        poly2.lineTo(-17, -8);
        poly2.lineTo(-22, -8);
        poly2.lineTo(-29, -7);
        poly2.lineTo(-22, -6);
        poly2.lineTo(-17, -6);
        poly2.lineTo(-18, -4);
        
        poly2.lineTo(-18, 4);
        poly2.lineTo(-17, 6);
        poly2.lineTo(-22, 6);
        poly2.lineTo(-29, 7);
        poly2.lineTo(-22, 8);
        poly2.lineTo(-17, 8);
        poly2.lineTo(-14, 18);
        poly2.lineTo(6, 18);
        poly2.lineTo(-9, 18);
        poly2.lineTo(-9, 9);
        poly2.lineTo(-7, 9);
        poly2.lineTo(-7, 5);
        poly2.lineTo(-9, 5);
        poly2.lineTo(23, 1);
        poly2.closePath();
        outlineFlame = poly2;

        poly2.transform(AffineTransform.getScaleInstance(1.5, 1.5));
    }

    /**
     * Returns the x-coordinate of the point on the screen where the X-Wing's guns are located.
     */
    public double getXGun1 ()
    {
        Point2D.Double point = new Point2D.Double(15, -27);
        transformPoint(point);
        return point.getX();
    }
    
    /**
     * Returns the x-coordinate of the point on the screen where the X-Wing's guns are located.
     */
    public double getXGun2 ()
    {
        Point2D.Double point = new Point2D.Double(15, 27);
        transformPoint(point);
        return point.getX();
    }

    /**
     * Returns the x-coordinate of the point on the screen where the X-Wing's 1st gun is located.
     */
    public double getYGun1 ()
    {
        Point2D.Double point = new Point2D.Double(15, -27);
        transformPoint(point);
        return point.getY();
    }
    
    /**
     * Returns the x-coordinate of the point on the screen where the X-Wing's 2nd gun is located.
     */
    public double getYGun2 ()
    {
        Point2D.Double point = new Point2D.Double(15, 27);
        transformPoint(point);
        return point.getY();
    }

    @Override
    protected Shape getOutline ()
    {
        if (controller.getAcceleration()) 
        {
            return outlineFlame;
        }
        else 
        {
            return outline;
        }
    }

    /**
     * Customizes the base move method by imposing friction
     */
    @Override
    public void move ()
    {
        applyFriction(SHIP_FRICTION);
        super.move();
    }
   
    /**
     * Turns right by Pi/16 radians
     */
    public void turnRight ()
    {
        rotate(Math.PI / 16);
    }

    /**
     * Turns left by Pi/16 radians
     */
    public void turnLeft ()
    {
        rotate(-Math.PI / 16);
    }

    /**
     * Accelerates by SHIP_ACCELERATION
     */
    public void accelerate ()
    {
        accelerate(SHIP_ACCELERATION);
        
    }
    
    /**
     * Fire bullets
     */
    public void fireBullets()
    {
        sound = new SoundLibrary();
        sound.playClip("xwingFire");
        XWingLaser xwl = new XWingLaser(getXGun1(), getYGun1(), getRotation(), controller);
        controller.addParticipant(xwl);
        XWingLaser xwl2 = new XWingLaser(getXGun2(), getYGun2(), getRotation(), controller);
        controller.addParticipant(xwl2);
        new ParticipantCountdownTimer(xwl, "expire", BULLET_DURATION);
        new ParticipantCountdownTimer(xwl2, "expire", BULLET_DURATION);
    }

    /**
     * When a Ship collides with a ShipDestroyer, it expires
     */
    @Override
    public void collidedWith (Participant p)
    {
        if (p instanceof ShipDestroyer)
        {
            // Turn the ship into debris
            Debris d = new Debris(getX(), getY(), 1, controller);
            Debris d2 = new Debris(getX(), getY(), 2, controller);
            Debris d3 = new Debris(getX(), getY(), 2, controller);
            controller.addParticipant(d);
            new ParticipantCountdownTimer(d, "expire", 1500);
            controller.addParticipant(d2);
            new ParticipantCountdownTimer(d2, "expire", 1500);
            controller.addParticipant(d3);
            new ParticipantCountdownTimer(d3, "expire", 1500);
            
            // Expire the ship from the game
            Participant.expire(this);

            // Tell the controller the ship was destroyed
            controller.xwingDestroyed();
        }
    }

    /**
     * This method is invoked when a ParticipantCountdownTimer completes its countdown.
     */
    @Override
    public void countdownComplete (Object payload)
    {
        // Give a burst of acceleration, then schedule another
        // burst for 200 msecs from now.
        if (payload.equals("move"))
        {
            accelerate();
            new ParticipantCountdownTimer(this, "move", 200);
        }
    }
}
