import javax.swing.JFrame;

public class BallPanelTest extends JFrame
{

	BallPanel ballPanel;
	
	public BallPanelTest()
	{
		super( "Bouncing Ball" );
		ballPanel = new BallPanel();
		add( ballPanel );
	}

	public static void main( String args[] )
	{
		BallPanelTest bpt = new BallPanelTest();
		bpt.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		bpt.pack();
		bpt.setVisible( true );
	}
}