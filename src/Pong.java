import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Pong extends JPanel implements ActionListener
{
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String P1_MOVE_UP = "move up p1";
	private static final String P1_MOVE_DOWN = "move down p1";
	private static final String P1_STOP = "stop p1";
	private static final String P2_MOVE_UP = "move up p2";
	private static final String P2_MOVE_DOWN = "move down p2";
	private static final String P2_STOP = "stop p2";
	
	private static final int UP = -1;
	private static final int DOWN = 1;
	private static final int STOP = 0;
	
	private static final int SCORE_LIMIT = 5;
	
	private Timer t = new Timer(5, this);
	
	private JFrame frame;
	
	private Paddle p1 = new Paddle(this, 10, 300);
	private Paddle p2 = new Paddle(this, 675, 300);
	private Ball ball = new Ball(this);
	
	public Pong(JFrame f)
	{
		frame = f;
		setBackground(Color.BLACK);
		setKeyBindings();
		t.start();
	}
	

	
	private void setKeyBindings()
	{
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed W"), P1_MOVE_UP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), P1_STOP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed S"), P1_MOVE_DOWN);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released S"), P1_STOP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed UP"), P2_MOVE_UP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released UP"), P2_STOP);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed DOWN"), P2_MOVE_DOWN);
		this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released DOWN"), P2_STOP);
		
		this.getActionMap().put(P1_MOVE_UP, new MoveAction(1, UP));
		this.getActionMap().put(P1_MOVE_DOWN, new MoveAction(1, DOWN));
		this.getActionMap().put(P1_STOP, new MoveAction(1, STOP));
		this.getActionMap().put(P2_MOVE_UP, new MoveAction(2, UP));
		this.getActionMap().put(P2_MOVE_DOWN, new MoveAction(2, DOWN));
		this.getActionMap().put(P2_STOP, new MoveAction(2, STOP));
	}	
	
	public Paddle getPlayer(int num)
	{
		if(num == 1)
		{
			return p1;
		}
		else
			return p2;	
	}	
	
	public void actionPerformed(ActionEvent e)
	{
		update();
	}
			
	private void update()
	{
		ball.update();
		p1.update();
		p2.update();
		repaint();
	}		

	private class MoveAction extends AbstractAction
	{
		int player;
		int direction;
		
		public MoveAction(int p, int d)
		{
			player = p;
			direction = d;
		}
		
		public void actionPerformed(ActionEvent e)
		{
			if (player == 1)
				p1.movePaddle(direction);
			else
				p2.movePaddle(direction);	
			repaint();
		}		
	}
	
	private int p1Score = 0, p2Score = 0;
	private String score = "Player 1 Score: " + p1Score + "\t" + "Player 2 Score: " + p2Score;
	
	public void updateScoreForPlayer(int id)
	{
		if (id == 2)
			p2Score++;
		else
			p1Score++;
		if (p1Score == SCORE_LIMIT || p2Score == SCORE_LIMIT)
		{
			repaint();
			endGame();
		}	
		
	}
	
	private void resetGame()
	{
		p1Score = 0;
		p2Score = 0;
		ball.reset();
		p1.reset();
		p2.reset();
	}	
	
	private void endGame()
	{	int id;
	
		if (p1Score == SCORE_LIMIT)
			id = 1;
		else 
			id = 2;	
		
		int dialog = JOptionPane.showConfirmDialog(null, "Player " + id + " has won the game! Play again?", "Pong", JOptionPane.YES_NO_OPTION);
		
		if (dialog == JOptionPane.YES_OPTION)
		{
			resetGame();
		}
		else
		{
			closeProgram();
		}				
	}
	
	private void closeProgram()
	{
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}		
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.fill(p1.getPaddle());
		g2.fill(p2.getPaddle());
		g2.fill(ball.getBall());
		g2.drawString(p1Score + " : " + p2Score, 350, 10);
	}
	
	
	
	
}
