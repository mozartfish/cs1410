package lightsout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import static lightsout.LightsOutView.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Implements a Lights Out game with a GUI interface.
 * 
 * @author Pranav Rajan
 */
@SuppressWarnings("serial")
public class LightsOutView extends JFrame implements ActionListener
{
    // Some formatting constants
    private final static int WIDTH = 600;
    private final static int HEIGHT = 600;
    public final static int ROWS = 5;
    public final static int COLS = 5;
    public final static Color BACKGROUND_COLOR = Color.GRAY;
    public final static Color LIGHTS_ON = new Color(192, 54, 44);
    public final static String LIGHTS_ON_COLOR_NAME = "Golden Gate Red";
    public final static Color LIGHTS_OFF = new Color(54, 69, 79);
    public final static String LIGHTS_OFF_COLOR_NAME = "Charcoal";
    public final static Color BOARD_COLOR = Color.BLACK;
    public final static int BORDER = 5;
    public final static int FONT_SIZE = 20;
    public final static int ON = 1;
    public final static int OFF = 0;
    
    /** The "smarts" of the game **/
    private LightsOutModel model;

    /** The number of moves a  player has made**/
    private JLabel numMoves;

    /** The portion of the GUI that contains the playing surface **/
    private Board board;
    
    /** Instance variable to change the manual mode to off when a new game is created */
    private JButton manualMode;
    
    

    /**
     * Creates and initializes the game.
     */
    public LightsOutView ()
    {
        // Set the title that appears at the top of the window
        setTitle("Lights Out");

        // Cause the application to end when the windows is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Give the window its initial dimensions
        setSize(WIDTH, HEIGHT);

        // The root panel contains everything
        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        setContentPane(root);

        // The center portion contains the playing board
        model = new LightsOutModel(ROWS, COLS);
        board = new Board(model, this);
        root.add(board, "Center");

        // The top portion contains the moves
        JPanel moves = new JPanel();
        moves.setLayout(new BorderLayout());
        root.add(moves, "North");
       

        // Keeps track of the number of moves a player has made
        JPanel p1 = new JPanel();
        p1.setBackground(BACKGROUND_COLOR);
        numMoves = new JLabel("Move Count: " + model.getMoves());
        numMoves.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
        numMoves.setForeground(LIGHTS_ON);
        numMoves.setBorder(new EmptyBorder(0, BORDER, 0, BORDER));
        p1.add(numMoves);
        moves.add(p1, "West");
        
        // Creates a JPanel that contains buttons for manual mode and new game
        JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout());
		JButton newGame = new JButton("New Game");
		manualMode = new JButton("Manual Mode");
		newGame.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
		newGame.setBackground(BACKGROUND_COLOR);
		newGame.addActionListener(this);
		manualMode.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
		manualMode.setBackground(BACKGROUND_COLOR);
		manualMode.addActionListener(this);
		controls.add(newGame);
		controls.add(manualMode);
		root.add(controls, "South");

        // Refresh the display and we're ready
        board.refresh();
        setVisible(true);
    }

    /**
     * Sets the label that displays the number of moves
     */
    public void setMoves (int n)
    {
        numMoves.setText("Moves: " + n);
    }
    
    /**
     * Called when the New Game button is clicked. Tells the model to begin a new game, then refreshes the display.
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
    	JButton b = (JButton) e.getSource();
    	
    	if (e.getActionCommand().equals("New Game"))
    	{
    	    if (model.checkManualMode() == true)
    	    {
    	        manualMode.setText("Manual Mode");
    	        model.changeManualMode(false);
    	    }
    	    
    		model.newGame();
            board.refresh();
    	}
    	else if (((JButton) e.getSource()).getText().equals("Manual Mode"))
    	{
			b.setText("Exit Manual Mode");
			model.changeManualMode(true);
    	}
    	else if(((JButton) e.getSource()).getText().equals("Exit Manual Mode"))
    	{
    		b.setText("Manual Mode");
			model.changeManualMode(false);
    	} 
    }
}

/**
 * The playing surface of the game.
 */
@SuppressWarnings("serial")
class Board extends JPanel implements MouseListener
{
    /** The "smarts" of the game */
    private LightsOutModel model;

    /** The top level GUI for the game */
    private LightsOutView display;

    /**
     * Creates the board portion of the GUI.
     */
    public Board (LightsOutModel model, LightsOutView display)
    {
        // Record the model and the top-level display
        this.model = model;
        this.display = display;

        // Set the background color and the layout
        setBackground(BOARD_COLOR);
        setLayout(new GridLayout(ROWS, COLS));

        // Create and lay out the grid of squares that make up the game.
        for (int i = ROWS - 1; i >= 0; i--)
        {
            for (int j = 0; j < COLS; j++)
            {
                Square s = new Square(i, j);
                s.addMouseListener(this);
                add(s);
            }
        }
    }

    /**
     * Refreshes the display. This should be called whenever something changes in the model.
     */
    public void refresh ()
    {
        // Iterate through all of the squares that make up the grid
        Component[] squares = getComponents();
        for (Component c : squares)
        {
            Square s = (Square) c;

            // Set the color of the squares appropriately
            int status = model.getOccupant(s.getRow(), s.getCol());
            if (status == ON)
            {
                s.setColor(LIGHTS_ON);
            }
            else if (status == OFF)
            {
                s.setColor(LIGHTS_OFF);
            }
        }
		display.setMoves(model.getMoves());
		// Ask that this board be repainted
		repaint();
    }

    /**
     * Called whenever a Square is clicked. Notifies the model that a move has been attempted.
     */
    @Override
    public void mouseClicked (MouseEvent e)
    {
    }

    @Override
    public void mousePressed (MouseEvent e)
    {
        Square s = (Square) e.getSource();
        model.OnOff(s.getRow(), s.getCol());
        
        refresh();
        if (model.checkWin() && !model.checkManualMode())
        {
        	JOptionPane.showMessageDialog(this, "You have won!");
        }
    }

    @Override
    public void mouseReleased (MouseEvent e)
    {
    }

    @Override
    public void mouseEntered (MouseEvent e)
    {
    }

    @Override
    public void mouseExited (MouseEvent e)
    {
    }
}

/**
 * A single square on the board where a move can be made
 */
@SuppressWarnings("serial")
class Square extends JPanel
{
    /** The row within the board of this Square. Rows are numbered from zero; row zero is at the bottom of the board. */
    private int row;

    /** The column within the board of this Square. Columns are numbered from zero; column zero is at the left */
    private int col;

    /** The current Color of this Square */
    private Color color;

    /**
     * Creates a square and records its row and column
     */
    public Square (int row, int col)
    {
        this.row = row;
        this.col = col;
        this.color = BACKGROUND_COLOR;
    }

    /**
     * Returns the row of this Square
     */
    public int getRow ()
    {
        return row;
    }

    /**
     * Returns the column of this Square
     */
    public int getCol ()
    {
        return col;
    }

    /**
     * Sets the color of this square
     */
    public void setColor (Color color)
    {
        this.color = color;
    }

    /**
     * Paints this Square onto g
     */
    @Override
    public void paintComponent (Graphics g)
    {
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(BOARD_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(color);
        g.fillRect(BORDER, BORDER, getWidth() - 2 * BORDER, getHeight() - 2 * BORDER);
    }
}



