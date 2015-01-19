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

public class BallPanel extends JPanel
{
	private Ball ball;
	private RepaintTimer repaintTimer;
	private ExecutorService threadExecutor;
	private int width;
	private int height;
	
	public BallPanel()
	{
		setPreferredSize( new Dimension( 400, 400 ) );
		width = ( int ) getPreferredSize().getWidth();
		height = ( int ) getPreferredSize().getHeight();
		repaintTimer = new RepaintTimer( this );
		threadExecutor = Executors.newCachedThreadPool();
		setBackground( Color.WHITE );
		setOpaque( true );
		addMouseListener( new MouseListener() );
	}
	
	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		Graphics2D g2d = ( Graphics2D ) g;
		
		if( ball != null )
		{
			g2d.setPaint( Color.BLUE );
			g2d.fill( new Ellipse2D.Double( ball.getXCoord(), ball.getYCoord(), 10, 10 ) );
		}
	}
	
	public class MouseListener extends MouseAdapter
	{
		public void mousePressed( MouseEvent e )
		{
			if( ball == null )
			{
				System.out.println( "Ball is null" );
				ball = new Ball(  ( int ) getPreferredSize().getWidth(),
								  ( int ) getPreferredSize().getHeight(),
								  e.getX(),
								  e.getY(),
								  10 );
				threadExecutor.execute( ball );
				threadExecutor.execute( repaintTimer );
			}
		}
	}
}