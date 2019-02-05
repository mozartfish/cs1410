package asteroids.game;

import static asteroids.game.Constants.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.sound.sampled.Clip;
import javax.swing.*;
import asteroids.participants.AlienShip;
import asteroids.participants.Asteroid;
import asteroids.participants.Bullet;
import asteroids.participants.Debris;
import asteroids.participants.Mine;
import asteroids.participants.Ship;
import asteroids.participants.TieFighter;
import asteroids.participants.XWing;
import sounds.SoundLibrary;

/**
 * Controls a game of Asteroids.
 */
public class Controller implements KeyListener, ActionListener
{
    /** The enhanced game */
    public static boolean enhanced;
    
    /** The state of all the Participants */
    private ParticipantState pstate;

    /** The ship (if one is active) or null (otherwise) */
    private Ship ship;
    
    /** The X-Wing (if one is active) or null (otherwise) */
    private XWing xwing;

    /** When this timer goes off, it is time to refresh the animation */
    private Timer refreshTimer;

    /**
     * The time at which a transition to a new stage of the game should be made. A transition is scheduled a few seconds
     * in the future to give the user time to see what has happened before doing something like going to a new level or
     * resetting the current level.
     */
    private long transitionTime;

    /** Number of lives left */
    private int lives;
   
    /** The current level */
    private int level;

    /** The game display */
    private Display display;

    /** The game score */
    private int score;
    
    private boolean beginGame;
    
    /** Turns the ship right */
    private boolean turnRight;
    
    /** Turns the ship left */
    private boolean turnLeft;
    
    /** Accelerates the ship */
    private boolean accelerate;

    /** Fires a bullet */
    private boolean fireBullet;

    /** Alien ship */
    private AlienShip alienship;
    
    /** Tie fighter */
    private TieFighter tiefighter;
    
    /** Alien spawn time */
    private long alienSpawn;
    
    /** The Sound Library */
     private SoundLibrary sound;
     
     private Timer beat1Timer;
     
     private Timer beat2Timer;
     
     private int timer1Start;
     
     private int timer2Start;
     
     private Clip beat1;
     
     private Clip beat2;
     
    /**
     * Constructs a controller to coordinate the game and screen
     */
    public Controller ()
    {
        // Initialize the ParticipantState
        pstate = new ParticipantState();

        // Set up the refresh timer.
        refreshTimer = new Timer(FRAME_INTERVAL, this);
        
        timer1Start = INITIAL_BEAT;
        timer2Start = INITIAL_BEAT;

        beat1Timer = new Timer(timer1Start, this);
        beat2Timer = new Timer(timer2Start, this);
        
        // Clear the transitionTime
        transitionTime = Long.MAX_VALUE;

        // Record the display object
        display = new Display(this);

        score = 0;
        lives = 3;
        level = 1;
        
        // Bring up the splash screen and start the refresh timer
        splashScreen();
        display.setVisible(true);
        refreshTimer.start();
        
        turnLeft = false;
        turnRight = false;
        accelerate = false;
        fireBullet = false;
        sound = new SoundLibrary();
        
        beat1 = sound.getBeat1();
        beat2 = sound.getBeat2();
        
        alienSpawn = System.currentTimeMillis() + ALIEN_DELAY + RANDOM.nextInt(6);
    }

    /**
     * Returns the ship, or null if there isn't one
     */
    public Ship getShip ()
    {
        return ship;
    }
    
    /**
     * Returns the ship, or null if there isn't one
     */
    public XWing getXWing ()
    {
        return xwing;
    }

    /**
     * Configures the game screen to display the splash screen
     */
    private void splashScreen ()
    {
        // Clear the screen, reset the level, and display the legend
        clear();
        display.setLegend("Asteroids");

        // Place four asteroids near the corners of the screen.
        placeAsteroids();
    }

    /**
     * The game is over. Displays a message to that effect.
     */
    private void finalScreen ()
    {
        display.setLegend(GAME_OVER);
        display.removeKeyListener(this);
    }

    /**
     * Place a new ship in the center of the screen. Remove any existing ship first.
     */
    private void placeShip ()
    {
        // Place a new ship
        Participant.expire(ship);
        ship = new Ship(SIZE / 2, SIZE / 2, -Math.PI / 2, this);
        addParticipant(ship);
        display.setLegend("");
    }
    
    /**
     * Place an X-Wing in the center of the screen. Remove any existing ship first.
     */
    private void placeXWing ()
    {
        // Place a new xwing
        Participant.expire(xwing);
        xwing = new XWing(SIZE / 2, SIZE / 2, -Math.PI / 2, this);
        addParticipant(xwing);
        display.setLegend("");
    }
    
    /**
     * Place a new alien ship
     */
    public void placeAlienShip()
    {
        alienship = new AlienShip(this);
        addParticipant(alienship);
        
        if (level >= 3)
        {
           alienship.setSize(0); 
        }
    }
    
    /**
     * Place a new alien ship
     */
    public void placeTieFighter()
    {
        tiefighter = new TieFighter(this);
        addParticipant(tiefighter);
    }
    
    public void destroyedAlien()
    {
        //increaseScore(ALIENSHIP_SCORE[size]);
        alienship = null;
        sound.playClip("bangAlienShip");
        alienSpawn = System.currentTimeMillis() + ALIEN_DELAY + RANDOM.nextInt(6);
    }
    
    public void destroyedTieFighter()
    {
        //increaseScore(ALIENSHIP_SCORE[size]);
        tiefighter = null;
        sound.playClip("bangAlienShip");
        alienSpawn = System.currentTimeMillis() + ALIEN_DELAY + RANDOM.nextInt(6);
    }

    /**
     * Places an asteroid near one corner of the screen. Gives it a random velocity and rotation.
     */
    private void placeAsteroids ()
    {
        Asteroid[] asteroids = new Asteroid[11];
        asteroids[0] = new Asteroid(0, 2, EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[1] = new Asteroid(1, 2, -EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[2] = new Asteroid(2, 2, EDGE_OFFSET, -EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[3] = new Asteroid(3, 2, -EDGE_OFFSET, -EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[4] = new Asteroid(0, 2, EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[5] = new Asteroid(1, 2, -EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[6] = new Asteroid(2, 2, EDGE_OFFSET, -EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[7] = new Asteroid(3, 2, -EDGE_OFFSET, -EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[8] = new Asteroid(0, 2, EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[9] = new Asteroid(1, 2, -EDGE_OFFSET, EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);
        asteroids[10] = new Asteroid(2, 2, EDGE_OFFSET, -EDGE_OFFSET, RANDOM.nextInt(MAXIMUM_LARGE_ASTEROID_SPEED) + 1, this);

        for (int i = 1; i <= level; i++) {
            if (level == i) {
                addParticipant(asteroids[0]);
                addParticipant(asteroids[1]);
                addParticipant(asteroids[2]);
                addParticipant(asteroids[3]);
                for (int j = 1; j < i; j++) {
                    addParticipant(asteroids[j + 3]);
                }
            }
        }
    }

    /**
     * Clears the screen so that nothing is displayed
     */
    private void clear ()
    {
        pstate.clear();
        display.setLegend("");
        ship = null;
    }

    /**
     * Sets things up and begins a new game.
     */
    private void initialScreen ()
    {
        // Clear the screen
        clear();

        beat1Timer.start();
        
        // Place asteroids
        placeAsteroids();

        if (enhanced) {
            // Place the X-Wing
            placeXWing();
        }
        else {
            // Place the ship
            placeShip();
        }

        // Place Alienship
        //placeAlienShip();
        
        // Place tie fighter
        //placeTieFighter();
        
        // Reset statistics
        lives = 3;
        level = 1;
        score = 0;
        
        //backgroundMusic();
        
        // Start listening to events (but don't listen twice)
        display.removeKeyListener(this);
        display.addKeyListener(this);

        // Give focus to the game screen
        display.requestFocusInWindow();
    }

    /**
     * Adds a new Participant
     */
    public void addParticipant (Participant p)
    {
        pstate.addParticipant(p);
    }

    /**
     * The ship has been destroyed
     */
    public void shipDestroyed ()
    {
     // Play sound
        sound.playClip("bangShip");
        
        // Null out the ship
        ship = null;

        // Display a legend
        display.setLegend("Ouch!");

        // Decrement lives
        lives--;
        
        turnRight = false;
        turnLeft = false;
        accelerate = false;
        fireBullet = false;

        // Since the ship was destroyed, schedule a transition
        scheduleTransition(END_DELAY);
    }
    
    /**
     * The X-Wing has been destroyed
     */
    public void xwingDestroyed ()
    {
     // Play sound
        sound.playClip("bangShip");
        
        // Null out the ship
        xwing = null;
        
        // Display a legend
        display.setLegend("I'm hit!");
        
        // Decrement lives
        lives--;
        
        turnRight = false;
        turnLeft = false;
        accelerate = false;
        fireBullet = false;
        
        // Since the ship was destroyed, schedule a transition
        scheduleTransition(END_DELAY);
    }

    /**
     * An asteroid has been destroyed
     */
    public void asteroidDestroyed (int size)
    {
        if (size == 2)
        {
            sound.playClip("bangLarge");
        }
        if (size == 1)
        {
            sound.playClip("bangMedium");
        }
        if (size == 0)
        {
            sound.playClip("bangSmall");
        }
        
        // If all the asteroids are gone, schedule a transition
        if (pstate.countAsteroids() == 0)
        {
            scheduleTransition(END_DELAY);
        }
    }

    /**
     * Schedules a transition m msecs in the future
     */
    private void scheduleTransition (int m)
    {
        transitionTime = System.currentTimeMillis() + m;
    }

    /**
     * This method will be invoked because of button presses and timer events.
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
        if (e.getSource() == beat1Timer) {
            beat1Timer.stop();
            if (timer1Start > FASTEST_BEAT) {
                timer1Start -= BEAT_DELTA;
            }
            beat1.setFramePosition(0);
            beat1.start();
            beat2Timer = new Timer(timer2Start, this);
            beat2Timer.start();
        }
        
        if (e.getSource() == beat2Timer) {
            beat2Timer.stop();
            if (timer2Start > FASTEST_BEAT) {
                timer2Start -= BEAT_DELTA;
            }
            beat2.setFramePosition(0);
            beat2.start();
            beat1Timer = new Timer(timer1Start, this);
            beat1Timer.start();
        }
        
        // The start button has been pressed. Stop whatever we're doing
        // and bring up the initial screen
        if (e.getSource() instanceof JButton)
        {
            beginGame = true;
            initialScreen();
        }

        // Time to refresh the screen and deal with keyboard input
        else if (e.getSource() == refreshTimer)
        {
            // ship controls and features
            if (turnRight && (ship != null || xwing != null))
            {
                if (enhanced) {
                    xwing.turnRight();
                }
                else {
                    ship.turnRight();   
                }
            }
            if (turnLeft && (ship != null || xwing != null))
            {
                if (enhanced) {
                    xwing.turnLeft();
                }
                else {
                    ship.turnLeft();   
                }
            }
            if (accelerate && (ship != null || xwing != null))
            {
                if (enhanced) {
                    xwing.accelerate();
                    sound.playClip("thrust");
                }
                else {
                    ship.accelerate();
                    sound.playClip("thrust");
                }
            }
            if (fireBullet && (ship != null || xwing != null))
            {
                if (enhanced) {
                    if (pstate.countBullets() <= BULLET_LIMIT) 
                    {
                        xwing.fireBullets();
                    }
                }
                else {
                    if (pstate.countBullets() <= BULLET_LIMIT) 
                    {
                        ship.fireBullets();
                        sound.playClip("fire");
                    }
                }
            }
            
            if (alienSpawn <= System.currentTimeMillis())
            {
                alienSpawn = Long.MAX_VALUE;
                if (level > 1)
                {
                    if (enhanced) {
                        placeTieFighter();
                    }
                    else {
                        placeAlienShip();   
                    }
                }
            }
            
            // It may be time to make a game transition
            performTransition();

            // Move the participants to their new locations
            pstate.moveParticipants();
            
            // Refresh screen
            display.refresh();
        }
    }

    /**
     * Returns an iterator over the active participants
     */
    public Iterator<Participant> getParticipants ()
    {
        return pstate.getParticipants();
    }

    /**
     * If the transition time has been reached, transition to a new state
     */
    private void performTransition ()
    {
        // Do something only if the time has been reached
        if (transitionTime <= System.currentTimeMillis())
        {
            // Clear the transition time
            transitionTime = Long.MAX_VALUE;

            // If there are no lives left, the game is over. Show the final screen.
            if (lives <= 0)
            {
                finalScreen();
            }
            else if (pstate.countAsteroids() == 0){
                beat1Timer.stop();
                beat2Timer.stop();
                beat1Timer.restart();
                beat2Timer.restart();
                
                level++;
                if (enhanced) {
                    placeXWing();
                    placeTieFighter();
                }
                else {
                    placeShip();
                    placeAlienShip();
                }
                placeAsteroids();
                
                beat1Timer.stop();
                beat2Timer.stop();
                
                beat1Timer.start();
                beat2Timer.start();
            }
            else {
                if (enhanced) {
                    placeXWing();
                }
                else {
                    placeShip();
                }
            }
        }
    }

    /**
     * If a key of interest is pressed, record that it is down.
     */
    @Override
    public void keyPressed (KeyEvent e)
    {
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && (ship != null || xwing != null))
        {
            turnRight = true;
        }
        if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && (ship != null || xwing != null))
        {
            turnLeft = true;
        }
        if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && (ship != null || xwing != null))
        {
            accelerate = true;
        }
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && (ship != null || xwing != null))
        {
            fireBullet = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_P)
        {
            refreshTimer.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_O)
        {
            refreshTimer.start();
        }
    }

    /**
     * These events are ignored.
     */
    @Override
    public void keyTyped (KeyEvent e)
    {
    }

    /**
     * These events are ignored.
     */
    @Override
    public void keyReleased (KeyEvent e)
    {
        if ((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) && (ship != null || xwing != null))
        {
            turnRight = false;
        }
        if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) && (ship != null || xwing != null))
        {
            turnLeft = false;
        }
        if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && (ship != null || xwing != null))
        {
            accelerate = false;
        }
        if ((e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) && (ship != null || xwing != null))
        {
            fireBullet = false;
        }
    }
    
    public void backgroundMusic() {
        sound.playClip("background");
    }

    public boolean getBeginGame() {
        return beginGame;
    }
    
    public int getLevel ()
    {
        return level;
    }

    public void increaseScore (int points)
    {
        this.score += points;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getLives()
    {
        return lives;
    }
    
    public boolean getAcceleration () 
    {
        return accelerate;
    }
}
