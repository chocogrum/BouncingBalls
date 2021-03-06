// Module 23, Exercise 2
// Graham Thomas
// BallPanel.java
// panel in which balls can bounce

/* Modify the program in Exercise 1 to add a new ball each time the user clicks the mouse. Provide for a minimum of 20 balls. Randomly choose the color for each new ball. */

// import required classes
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

// class inherits from JPanel
public class BallPanel extends JPanel
{
	private RepaintTimer repaintTimer; // RepaintTimer object to determine how often the ball moves
	private ExecutorService threadExecutor; // ExecutorService for running threads in parallel
	private int panelWidth; // width of the panel
	private int panelHeight; // height of the panel
	private int ballCounter; // track number of balls created
	private final int MAX_NUMBER_OF_BALLS = 20; // max number of balls that can be created
	private Ball[] ballArray = new Ball[ MAX_NUMBER_OF_BALLS ]; // declare array of Ball objects
	private Random randomGenerator; // initialise random number generator
	
	// constructor accepts parameters for setting preferred size
	public BallPanel( int width, int height )
	{
		setPreferredSize( new Dimension( width, height ) ); // set preferred size
	 	panelWidth = ( int ) getPreferredSize().getWidth(); // get the preferred width
		panelHeight = ( int ) getPreferredSize().getHeight(); // get the preferred height
		
		// initialise repaintTimer with this panel and a sleep time of 20 milliseconds
		repaintTimer = new RepaintTimer( this, 20 );
		
		threadExecutor = Executors.newCachedThreadPool(); // initialise threadExecutor
		setBackground( Color.WHITE ); // set panel background colour to white
		setOpaque( true ); // opaque is true so panel background colour can be seen
		addMouseListener( new MouseListener() ); // add mouse listener to panel
		threadExecutor.execute( repaintTimer ); // execute the repaint timer in one thread
		ballCounter = 0; // initialise ball counter to zero
		randomGenerator = new Random(); // initialise random number generator
	} // end constructor

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g ); // call superclass method
		Graphics2D g2d = ( Graphics2D ) g; // declare Graphics2D object
		
		for( Ball ball : ballArray ) // loop through array of balls
		{		
			if( ball != null ) // if ball is not null
			{
				// set paint to the colour of the ball
				g2d.setPaint( ball.getBallColour() );
			
				// paint the ball
				g2d.fill( new Ellipse2D.Double( ball.getXCoord(), // horizontal position of the ball
												ball.getYCoord(), // vertical position of the ball
												ball.getDiameter(), // diameter of the ball
												ball.getDiameter() ) // diameter of the ball
						);
			} // end if
		} // end for loop
	} // end method paintComponent
	
	// inner class for mouse listener
	public class MouseListener extends MouseAdapter
	{
		// override method mousePressed
		public void mousePressed( MouseEvent e )
		{
			if( ballCounter < MAX_NUMBER_OF_BALLS ) // if max number of balls hasn't been reached
			{
				// add a new ball to the array
				ballArray[ ballCounter ] = new Ball(  ( int ) getPreferredSize().getWidth(), // set max width
								                        ( int ) getPreferredSize().getHeight(), // set max height
								                        e.getX(), // initial horizontal position determined by mouse
								                        e.getY(), // initial vertical position determined by mouse
								                        10 ); // diameter of 10 pixels
				
				// set the ball colour to a random colour
				ballArray[ ballCounter ].setBallColour( new Color( randomGenerator.nextInt( 256 ),
									     					       randomGenerator.nextInt( 256 ),
									     					       randomGenerator.nextInt( 256 )
									     					      )
									     		      );
								  
				threadExecutor.execute( ballArray[ ballCounter ] ); // execute the ball in its own thread
				
				ballCounter++;
			}
		} // end method mousePressed
	} // end inner class MouseListener
} // end class BallPanel