import javax.swing.JPanel;

public class RepaintTimer implements Runnable
{
	private JPanel repaintPanel;

	public RepaintTimer( JPanel panel )
	{
		repaintPanel = panel;
	}
	
	public void run()
	{
		while( true )
		{
			try
			{
				Thread.sleep( 20 );
				repaintPanel.repaint();
			}
			catch( InterruptedException exception )
			{
				System.out.println( "Interrupted exception in RepaintTimer" );
			}
		}
	}
}