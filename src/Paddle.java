import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Paddle
{
	private Rectangle2D paddle = new Rectangle2D.Double();
	
	private static final double PADDLE_SPEED = 7, PADDLE_WIDTH = 15, PADDLE_HEIGHT = 100;
	private double DEFAULT_X;
	private double DEFAULT_Y;
	
	private double paddleX;
	private double paddleY;
	
	private int direction;
	
	private static Pong game;	
		
	public Paddle(Pong g, int x, int y)
	{
		this.game = g;
		DEFAULT_X = x;
		DEFAULT_Y = y;
		paddleX = DEFAULT_X;
		paddleY = DEFAULT_Y;
		paddle.setRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
	}	
	
	public void update()
	{
		movePaddle(direction);
		paddle.setRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
	}	
		
	public void movePaddle(int d)
	{
		direction = d;
		
		for (int i = 0; i <= PADDLE_SPEED; i++)
		{
				if (direction == -1 && paddleY>0) //move up
					paddleY += direction;
				if (direction == 1 && paddleY+PADDLE_HEIGHT<600) //move down
					paddleY += direction;	
		}			
	}
	
	public void reset()
	{
		paddleX = DEFAULT_X;
		paddleY = DEFAULT_Y;
		direction = 0;
		//paddle.setRect(PADDLE_START_X, PADDLE_START_Y, PADDLE_WIDTH, PADDLE_HEIGHT);
	}		
	
	public Rectangle2D getPaddle()
	{
		return paddle;
	}	 	
}
