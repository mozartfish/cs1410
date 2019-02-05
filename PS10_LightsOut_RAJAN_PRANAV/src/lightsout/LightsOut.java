package lightsout;

import javax.swing.SwingUtilities;

/**
 * Launches a game of LightsOut.
 * 
 * @author Pranav Rajan
 */
public class LightsOut 
{
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(() -> new LightsOutView());
	}
}
