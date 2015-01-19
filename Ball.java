import java.util.Random;

public class Ball implements Runnable
{
	private int x;
	private int y;
	private int xChange;
	private int yChange;
	private int diameter;
	private int maxWidth;
	private int maxHeight;
	private boolean forwards;
	private boolean downwards;
	private Random randomGenerator;

	public Ball( int windowWidth, int windowHeight, int startX, int startY, int ballDiameter )
	{
		setXCoord( startX );
		setYCoord( startY );
		diameter = ballDiameter;
		forwards = true;
		downwards = true;
		setMaxWidth( windowWidth );
		setMaxHeight( windowHeight );
		randomGenerator = new Random();
		xChange = 1 + randomGenerator.nextInt( 5 );
		yChange = 1 + randomGenerator.nextInt( 5 );
	}
	
	public void run()
	{
		while( true )
		{
			try
			{
				Thread.sleep( 20 );
			
				if( forwards )
				{
					x += xChange;
					if( x >= ( maxWidth - diameter ) )
						forwards = false;
				}
				else
				{
					x -= xChange;
					if( x <= 0 )
						forwards = true;
				}
				
				if( downwards )
				{
					y += yChange;
					if( y >= ( maxHeight - diameter ) )
						downwards = false;
				}
				else
				{
					y -= yChange;
					if( y <= 0 )
						downwards = true;
				}
			}
			catch( InterruptedException exception )
			{
				System.out.println( "Interrupted exception in Ball" );
			}
		}
	}
	
	public void setXCoord( int xCoord )
	{
		x = xCoord;
	}
	
	public void setYCoord( int yCoord )
	{
		y = yCoord;
	}
	
	public void setMaxWidth( int width )
	{
		maxWidth = width;
	}
	
	public void setMaxHeight( int height )
	{
		maxHeight = height;
	}
	
	public int getXCoord()
	{
		return x;
	}
	
	public int getYCoord()
	{
		return y;
	}
}