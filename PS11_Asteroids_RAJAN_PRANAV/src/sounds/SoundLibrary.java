package sounds;

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import asteroids.game.Controller;
import static asteroids.game.Constants.*;

public class SoundLibrary
{
    /** A Clip that, when played, sounds like a weapon being fired */
    private Clip fireClip;
    
    /** A Clip that, when played, sounds when a ship is destroyed */
    private Clip destroyShip;
    
    /** A Clip that, when played, sounds when a ship is destroyed */
    private Clip destroyAlien;
    
    /** A Clip that, when played, sounds when a large asteroid is destroyed */
    private Clip largeAsteroid;
    
    /** A Clip that, when played, sounds when a medium asteroid is destroyed */
    private Clip mediumAsteroid;
    
    /** A Clip that, when played, sounds when a small asteroid is destroyed */
    private Clip smallAsteroid;
    
    /** A Clip that, when played, sounds when a space ship is thrusting in space */
    private Clip shipThrust;
    
    /** A Clip that, when played, sounds when a Big Alien ship appears */
    private Clip bigAlien;
    
    /** A Clip that, when played, sounds when a Small Alien appears */
    private Clip smallAlien;
    
    /** A Clip that, when played, sounds like a low beat */
    private Clip beat1;
    
    /** A Clip that, when played, sounds like a high beat */
    private Clip beat2;
    
    /** A Clip that, when played, sounds like an X-Wing being fired  */
    private Clip xwingFire;
    
    /** A Clip that, when played, sounds like an X-Wing being fired  */
    private Clip tieFire;
    
    public SoundLibrary()
    {
        // We create the clips in advance so that there will be no delay
        // when we need to play them back. Note that the actual wav
        // files are stored in the "sounds" project.
        fireClip = createClip("/sounds/fire.wav");
        destroyShip = createClip("/sounds/bangShip.wav");
        largeAsteroid = createClip("/sounds/bangLarge.wav");
        mediumAsteroid = createClip("/sounds/bangMedium.wav");
        smallAsteroid = createClip("/sounds/bangSmall.wav");
        shipThrust = createClip("/sounds/thrust.wav");
        destroyAlien = createClip("/sounds/bangAlienShip.wav");
        bigAlien = createClip("/sounds/saucerBig.wav");
        smallAlien = createClip("/sounds/saucerSmall.wav");
        beat1 = createClip("/sounds/beat1.wav");
        beat2 = createClip("/sounds/beat2.wav");
        xwingFire = createClip("/sounds/xwingFire.wav");
        tieFire = createClip("/sounds/tieFire.wav");
    }
    
    /**
     * Creates an audio clip from a sound file.
     */
    public Clip createClip (String soundFile)
    {
        // Opening the sound file this way will work no matter how the
        // project is exported. The only restriction is that the
        // sound files must be stored in a package.
        try (BufferedInputStream sound = new BufferedInputStream(getClass().getResourceAsStream(soundFile)))
        {
            // Create and return a Clip that will play a sound file. There are
            // various reasons that the creation attempt could fail. If it
            // fails, return null.
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            return clip;
        }
        catch (LineUnavailableException e)
        {
            return null;
        }
        catch (IOException e)
        {
            return null;
        }
        catch (UnsupportedAudioFileException e)
        {
            return null;
        }
    }
    
    public void playClip(String s)
    {
        if (s.equals("fire"))
        {
            if (fireClip.isRunning())
            {
                fireClip.stop();
            }
            fireClip.setFramePosition(0);
            fireClip.start();
        }
        if (s.equals("bangShip"))
        {
            if (destroyShip.isRunning())
            {
                destroyShip.stop();
            }
            destroyShip.setFramePosition(0);
            destroyShip.start();
        }
        if (s.equals("bangAlienShip"))
        {
            if (destroyAlien.isRunning())
            {
                destroyAlien.stop();
            }
            destroyAlien.setFramePosition(0);
            destroyAlien.start();
        }
        if (s.equals("bangLarge"))
        {
            if (largeAsteroid.isRunning())
            {
                largeAsteroid.stop();
            }
            largeAsteroid.setFramePosition(0);
            largeAsteroid.start();
        }
        if (s.equals("bangMedium"))
        {
            if (mediumAsteroid.isRunning())
            {
                mediumAsteroid.stop();
            }
            mediumAsteroid.setFramePosition(0);
            mediumAsteroid.start();
        }
        if (s.equals("bangSmall"))
        {
            if (smallAsteroid.isRunning())
            {
                smallAsteroid.stop();
            }
            smallAsteroid.setFramePosition(0);
            smallAsteroid.start();
        }
        if (s.equals("thrust"))
        {
            if (shipThrust.isRunning())
            {
                shipThrust.stop();
            }
            shipThrust.setFramePosition(0);
            shipThrust.start();
        }
        if (s.equals("bigAlien"))
        {
            if (bigAlien.isRunning())
            {
                bigAlien.stop();
            }
            bigAlien.setFramePosition(0);
            bigAlien.loop(bigAlien.LOOP_CONTINUOUSLY);
        }
        if (s.equals("smallAlien"))
        {
            if (smallAlien.isRunning())
            {
                smallAlien.stop();
            }
            smallAlien.setFramePosition(0);
            smallAlien.loop(smallAlien.LOOP_CONTINUOUSLY); 
        }
        if (s.equals("background"))
        {
            if (beat1.isRunning())
            {
                beat1.stop();
            }
            beat1.setFramePosition(0);
            beat1.start();
            if (beat2.isRunning())
            {
                beat2.stop();
            }
            beat2.setFramePosition(0);
            beat2.start();
        }
        if (s.equals("xwingFire"))
        {
            if (xwingFire.isRunning())
            {
                xwingFire.stop();
            }
            xwingFire.setFramePosition(0);
            xwingFire.start();
        }
        if (s.equals("tieFire"))
        {
            if (tieFire.isRunning())
            {
                tieFire.stop();
            }
            tieFire.setFramePosition(0);
            tieFire.start();
        }
    }
    
    public Clip getbigAlien()
    {
        return bigAlien;
    }
    
    public Clip getsmallAlien()
    {
        return smallAlien;
    }

    public Clip getBeat1 ()
    {
        return beat1;
    }
    
    public Clip getBeat2 ()
    {
        return beat2;
    }
}
