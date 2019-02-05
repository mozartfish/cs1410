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
 * Represents alien ships
 */
public class AlienShip extends Participant implements AsteroidDestroyer, BulletDestroyer, ShipDestroyer
{
    /** The outline of the alien ship */
    private Shape outline;

    /** Game controller */
    private Controller controller;
    
    /** Ship initial direction */
    private double initialDirection;
    
    /** Ship direction */
    private double direction;
    
    /** Ship size */
    private int size;
    
    /** Alien sounds */
    private SoundLibrary alienSound;

    /**
     * Constructs a ship at the specified coordinates that is pointed in the given direction.
     */
    public AlienShip (Controller controller)
    {
        alienSound = new SoundLibrary();
        this.controller = controller;
        
        if(controller.getLevel() < 3) {
            alienSound.playClip("bigAlien");
            size = 1; // Medium ship
        }
        else {
           size = RANDOM.nextInt(2);
        }
        if (size == 0) {
            alienSound.playClip("smallAlien");
        }
        initialDirection = (RANDOM.nextInt(2) * Math.PI);
        this.setVelocity(ALIENSHIP_SPEED[size], RANDOM.nextInt(2) * Math.PI);
        setDirection(initialDirection);
        setPosition(0, RANDOM.nextDouble() * SIZE);

        Path2D.Double poly = new Path2D.Double();
        poly.moveTo(20, 0);
        poly.lineTo(10, 8);
        poly.lineTo(-10, 8);
        poly.lineTo(-20, 0);
        poly.lineTo(20, 0);
        poly.lineTo(-20, 0);
        poly.lineTo(-10, -8);
        poly.lineTo(10, -8);
        poly.lineTo(-8, -8);
        poly.lineTo(-6, -15);
        poly.lineTo(6, -15);
        poly.lineTo(8, -8);
        poly.lineTo(10, -8);
        poly.closePath();

        // Scale to the desired size
        double scale = ALIENSHIP_SCALE[size];
        poly.transform(AffineTransform.getScaleInstance(scale, scale));
        
        outline = poly;
        
        // Schedule a turn in 2 seconds
        new ParticipantCountdownTimer(this, "turn", 2000);
        // Schedule a shoot in 1.5 seconds
        new ParticipantCountdownTimer(this, "shoot", 1500);
    }
    
    private void turn ()
    {
       if (initialDirection > Math.PI / 2 && initialDirection < 3 * Math.PI / 2) 
       {
           direction = (RANDOM.nextInt(3) - 1);
           setDirection(direction * Math.PI/4 + Math.PI);
       }
       else {
           direction = (RANDOM.nextInt(3) - 1);
           setDirection(direction * Math.PI/4);
       }
    }
    
    @Override
    protected Shape getOutline ()
    {
        return outline;
    }

    /**
     * Customizes the base move method by imposing friction
     */
    @Override
    public void move ()
    {
        super.move();
    }

    /**
     * When an alien ship collides with a ShipDestroyer, it expires
     */
    @Override
    public void collidedWith (Participant p)
    {
        if (p instanceof AlienShipDestroyer)
        {
            if (size == 1)
            {
                alienSound.getbigAlien().stop();
            }
            if (size == 0)
            {
                alienSound.getsmallAlien().stop();
            }
            // Turn the alien ship into debris
            Debris d = new Debris(getX(), getY(), 1, controller);
            Debris d2 = new Debris(getX(), getY(), 1, controller);
            Debris d3 = new Debris(getX(), getY(), 2, controller);
            Debris d4 = new Debris(getX(), getY(), 2, controller);
            Debris d5 = new Debris(getX(), getY(), 2, controller);
            Debris d6 = new Debris(getX(), getY(), 2, controller);
            controller.addParticipant(d);
            new ParticipantCountdownTimer(d, "expire", 1500);
            controller.addParticipant(d2);
            new ParticipantCountdownTimer(d2, "expire", 1500);
            controller.addParticipant(d3);
            new ParticipantCountdownTimer(d3, "expire", 1500);
            controller.addParticipant(d4);
            new ParticipantCountdownTimer(d4, "expire", 1500);
            controller.addParticipant(d5);
            new ParticipantCountdownTimer(d5, "expire", 1500);
            controller.addParticipant(d6);
            new ParticipantCountdownTimer(d6, "expire", 1500);
            
            //new ParticipantCountdownTimer(this, "spawn", 2000);
            controller.destroyedAlien();
            
            // Expire the alien ship from the game
            Participant.expire(this);
            
            if (p instanceof Bullet) {
            controller.increaseScore(ALIENSHIP_SCORE[size]);
            }
        }
    }

    /**
     * This method is invoked when a ParticipantCountdownTimer completes its countdown.
     */
    @Override
    public void countdownComplete (Object payload)
    {
        if (payload.equals("turn"))
        {
            turn();
            new ParticipantCountdownTimer(this, "turn", 2000);
        }
        
        if (payload.equals("shoot"))
        {
            fireAlienBullets();
            new ParticipantCountdownTimer(this, "shoot", 2000);
        }

        if (payload.equals("spawn"))
        {
            controller.placeAlienShip();
        }
    }
    
    public void fireAlienBullets()
    {
        double radian = 0;
        double randomRadian = 0;
        double range = 10;
        
        if (size == 0 && !this.isExpired())
        {
             if (controller.getShip() != null) {
                 radian = getAngle(this.getX(), this.getY(), controller.getShip().getX(), controller.getShip().getY());
                 double degree = Math.toDegrees(radian);
                 double minRangeDegree = degree - (range/2);
                 double randomDegree = minRangeDegree + RANDOM.nextDouble() * range;
                 randomRadian = Math.toRadians(randomDegree);
             }

        }
        else if (size == 1 && !this.isExpired())
        {
            randomRadian = RANDOM.nextDouble() * 2 * Math.PI;
        }
        
        AlienBullet b = new AlienBullet(this.getX(), this.getY(), randomRadian, controller);
        controller.addParticipant(b);
        new ParticipantCountdownTimer(b, "expire", BULLET_DURATION);
    }
    
    public static double getAngle(double fromX, double fromY, double toX, double toY) {
        double angle;
        double standardAngle;
        double fraction;
        double x = toX - fromX;
        double y = toY - fromY;
        double hypo = Math.sqrt(x*x + y*y);
        
        fraction = x / hypo;
        angle = Math.acos(fraction);
        angle = y > 0 ? angle: -angle;
        standardAngle = normalize(angle);
        
        return standardAngle;
    }

    public void setSize (int size)
    {
        this.size = size;
        
    }
}
