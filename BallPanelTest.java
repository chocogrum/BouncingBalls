// Module 23, Exercise 2
// Graham Thomas
// BallPanelTest.java
// class for testing panel with bouncing ball

/* Modify the program in Exercise 1 to add a new ball each time the user clicks the mouse. Provide for a minimum of 20 balls. Randomly choose the color for each new ball. */

// import required classes
import javax.swing.JFrame;

// class inherits from JFrame
public class BallPanelTest extends JFrame
{

	BallPanel ballPanel; // BallPanel object
	
	// constructor
	public BallPanelTest()
	{
		super( "Bouncing Ball" ); // call superclass constructor with window title
		ballPanel = new BallPanel( 600, 600 ); // initialise ballPanel with a width and height of 600 pixels
		add( ballPanel ); // add ballPanel to this frame
	} // end constructor

	// main method
	public static void main( String args[] )
	{
		BallPanelTest bpt = new BallPanelTest(); // declare and initialise new BallPanelTest object
		bpt.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); // set window to exit on close
		bpt.pack(); // resize window to fit contents
		bpt.setVisible( true ); // set window to be visible
	} // end method main
} // end class BallPanelTest