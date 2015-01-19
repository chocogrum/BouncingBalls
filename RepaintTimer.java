// Module 23, Exercise 2
// Graham Thomas
// RepaintTimer.java
// decides how often a panel is repainted

/* Modify the program in Exercise 1 to add a new ball each time the user clicks the mouse. Provide for a minimum of 20 balls. Randomly choose the color for each new ball. */

// import required classes
import javax.swing.JPanel;

public class RepaintTimer implements Runnable
{
	private JPanel repaintPanel; // panel to repaint
	private int sleepTime; // time to wait between repaints

	// constructor
	public RepaintTimer( JPanel panel, int time )
	{
		repaintPanel = panel; // set the panel to be repainted
		sleepTime = time; // set the sleep time
	} // end constructor
	
	// main code to be run
	public void run()
	{
		// loop continuously until user exits the program
		while( true )
		{
			try
			{
				Thread.sleep( sleepTime ); // sleep for a while
				repaintPanel.repaint(); // repaint the panel
			}
			catch( InterruptedException exception )
			{
				// InterruptedException caught
				System.out.println( "Interrupted exception in RepaintTimer" );
			} // end catch block
		} // end while loop
	} // end method run
} // end class RepaintTimer