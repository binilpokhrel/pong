import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Ball
{
	private Rectangle2D ball = new Rectangle2D.Double();
	
	private static final double BALL_WIDTH = 10, BALL_HEIGHT = 10, BALL_START_X = 345, BALL_START_Y = 295;
	
	private double ballX = BALL_START_X;
	private double ballY = BALL_START_Y;
	
	private double velX = 6, velY = 6;
	
	private static Pong game;
	
	public Ball(Pong g)
	{
		this.game = g;
		ball.setRect(BALL_START_X, BALL_START_Y, BALL_WIDTH, BALL_HEIGHT);
	}	
	
	public void update()
	{
		checkCollisions();
		ballX += velX;
		ballY += velY;
		ball.setRect(ballX, ballY, BALL_WIDTH, BALL_HEIGHT);
	}
	
	private void checkCollisions()
	{
		if (goalHit())
		{
			if (ballX<=0)
				game.updateScoreForPlayer(2);
			else
				game.updateScoreForPlayer(1);	
			resetBall();
		}	
		if(paddleHit())
		{
			velX = -velX;
		}
		if (ceilingFloorHit())
		{
			velY = -velY;
		}
	}
	
	private boolean goalHit()
	{
		return (ballX<=0 || ballX>=690);
	}	
	
	private boolean paddleHit()
	{
		return (ball.intersects(game.getPlayer(1).getPaddle()) || ball.intersects(game.getPlayer(2).getPaddle()));
	}
	
	private boolean ceilingFloorHit()
	{
		return (ballY<=0 || ballY>=590);
	}
	
	private void reset()
	{
		ballX = BALL_START_X;
		ballY = BALL_START_Y;
		//ball.setRect(ballX, ballY, BALL_WIDTH, BALL_HEIGHT);
	}			
	
	public Rectangle2D getBall()
	{
		return ball;
	}
}
