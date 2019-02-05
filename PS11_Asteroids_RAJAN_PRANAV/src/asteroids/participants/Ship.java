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
public class Ship extends Participant implements AsteroidDestroyer, AlienShipDestroyer
{
    /** The number that represents the flame off */
    private static final int OFF = 0;
    
    /** The number that represents the flame on */
    private static final int ON = 1;
    
    /** The outline of the ship */
    private Shape outline;

    /** The outline of the flame */
    private Shape outlineFlame;
    
    /** Game controller */
    private Controller controller;
    
    /** The flame */
    private int flame;

    /**
     * Constructs a ship at the specified coordinates that is pointed in the given direction.
     */
    public Ship (int x, int y, double direction, Controller controller)
    {
        this.controller = controller;
        setPosition(x, y);
        setRotation(direction);

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(21, 0);
        poly.lineTo(-21, 12);
        poly.lineTo(-14, 10);
        poly.lineTo(-14, -10);
        poly.lineTo(-21, -12);
        poly.closePath();
        outline = poly;
        
        Path2D.Double poly2 = new Path2D.Double();
        poly2.moveTo(21, 0);
        poly2.lineTo(-21, 12);
        poly2.lineTo(-14, 10);
        poly2.lineTo(-14, -10);
        poly2.lineTo(-21, -12);
        poly2.closePath();
        poly2.moveTo(-25, 0);
        poly2.lineTo(-14, -5);
        poly2.lineTo(-14, 5);
        poly2.closePath();
        outlineFlame = poly2;
    }

    /**
     * Returns the x-coordinate of the point on the screen where the ship's nose is located.
     */
    public double getXNose ()
    {
        Point2D.Double point = new Point2D.Double(20, 0);
        transformPoint(point);
        return point.getX();
    }

    /**
     * Returns the x-coordinate of the point on the screen where the ship's nose is located.
     */
    public double getYNose ()
    {
        Point2D.Double point = new Point2D.Double(20, 0);
        transformPoint(point);
        return point.getY();
    }
    
    public void toggleFlame()
    {
        if (flame == OFF) {
            flame = ON;
        }
        else {
            flame = OFF;
        }
    }

    @Override
    protected Shape getOutline ()
    {
        if (controller.getAcceleration()) 
        {
            toggleFlame();
            if (flame == OFF) {
                return outline;
            }
            else {
                return outlineFlame;
            }
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
     * fire bullets
     */
    public void fireBullets()
    {
        Bullet b = new Bullet(getXNose(), getYNose(), getRotation(), controller);
        controller.addParticipant(b);
        new ParticipantCountdownTimer(b, "expire", BULLET_DURATION);
        
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
            controller.shipDestroyed();
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
        //Use for Alien Ship
        //else if (payload.equals("shoot")) {
            //shoot();
            //new ParticipantCountdownTimer(this, "shoot", 2000);
        //}
        
    }
    
}
