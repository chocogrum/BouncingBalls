// Module 23, Exercise 2
// Graham Thomas
// BallPanel.java
// panel in which a ball bounces

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

// class inherits from JPanel
public class BallPanel extends JPanel
{
	private Ball ball; // Ball object to represent the bouncing ball
	private RepaintTimer repaintTimer; // RepaintTimer object to determine how often the ball moves
	private ExecutorService threadExecutor; // ExecutorService for running threads in parallel
	private int panelWidth; // width of the panel
	private int panelHeight; // height of the panel
	
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
	} // end constructor

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g ); // call superclass method
		Graphics2D g2d = ( Graphics2D ) g; // declare Graphics2D object
		
		if( ball != null ) // if ball is not null
		{
			g2d.setPaint( Color.BLUE ); // paint the ball in colour blue
			
			// paint the ball
			g2d.fill( new Ellipse2D.Double( ball.getXCoord(), // horizontal position of the ball
										    ball.getYCoord(), // vertical position of the ball
										    ball.getDiameter(), // diameter of the ball
										    ball.getDiameter() ) // diameter of the ball
					);
		}
	}
	
	// inner class for mouse listener
	public class MouseListener extends MouseAdapter
	{
		// override method mousePressed
		public void mousePressed( MouseEvent e )
		{
			if( ball == null ) // if ball has not been initialised yet
			{
				// new Ball object
				ball = new Ball(  ( int ) getPreferredSize().getWidth(), // set max width
								  ( int ) getPreferredSize().getHeight(), // set max height
								  e.getX(), // initial horizontal position determined by mouse
								  e.getY(), // initial vertical position determined by mouse
								  10 ); // diameter of 10 pixels
								  
				threadExecutor.execute( ball ); // execute the ball in one thread
				threadExecutor.execute( repaintTimer ); // execute the repaint timer in another thread
			}
		} // end method mousePressed
	} // end inner class MouseListener
} // end class BallPanel