import java.awt.*;
import java.awt.event.*;
//import java.awt.geom.*;
import javax.swing.*;

public class PongGame extends JPanel{

    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String P1_MOVE_UP = "move up p1";
    private static final String P1_MOVE_DOWN = "move down p1";
    private static final String P1_STOP_UP = "stop up p1";
    private static final String P1_STOP_DOWN = "stop down p1";
    private static final String P2_MOVE_UP = "move up p2";
    private static final String P2_MOVE_DOWN = "move down p2";
    private static final String P2_STOP_UP   = "stop up p2";
    private static final String P2_STOP_DOWN = "stop down p2";

    private static final int SCORE_LIMIT = 5;

    private Timer t = new Timer(2, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }
    });

    private JFrame frame;

    private Paddle p1 = new Paddle(this, 10, 300);
    private Paddle p2 = new Paddle(this, 675, 300);
    private Ball ball = new Ball(this);

    public PongGame(JFrame f) {
        frame = f;
        setBackground(Color.BLACK);
        setKeyBindings();
        t.start();
    }



    private void setKeyBindings() {
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed W"), P1_MOVE_UP);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released W"), P1_STOP_UP);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed S"), P1_MOVE_DOWN);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released S"), P1_STOP_DOWN);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed UP"), P2_MOVE_UP);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released UP"), P2_STOP_UP);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed DOWN"), P2_MOVE_DOWN);
        this.getInputMap(IFW).put(KeyStroke.getKeyStroke("released DOWN"), P2_STOP_DOWN);

        this.getActionMap().put(P1_MOVE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setUp(true);
                p1.setDown(false);
            }
        });
        this.getActionMap().put(P1_MOVE_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setUp(false);
                p1.setDown(true);
            }
        });
        this.getActionMap().put(P1_STOP_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setUp(false);
            }
        });
        this.getActionMap().put(P1_STOP_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p1.setDown(false);
            }
        });
        this.getActionMap().put(P2_MOVE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.setUp(true);
                p2.setDown(false);
            }
        });
        this.getActionMap().put(P2_MOVE_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.setUp(false);
                p2.setDown(true);
            }
        });
        this.getActionMap().put(P2_STOP_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.setUp(false);
            }
        });
        this.getActionMap().put(P2_STOP_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                p2.setDown(false);
            }
        });
    }

    public Paddle getPlayer(int num)
    {
        return num == 1 ? p1 : p2;
    }

//    public void actionPerformed(ActionEvent e) {
//        update();
//        repaint();
//    }

    private void update() {
        ball.updatePos();
        p1.updatePos();
        p2.updatePos();
    }

    private int p1Score = 0, p2Score = 0;

    public void updateScoreForPlayer(int id) {
        if (id == 2)
            p2Score++;
        else
            p1Score++;

        if (p1Score == SCORE_LIMIT || p2Score == SCORE_LIMIT) {
            repaint();
            endGame();
        }

    }

    private void restartGame() {
        p1Score = 0;
        p2Score = 0;
        ball.reset();
        p1.reset();
        p2.reset();
    }

    public void resetGame() {
        p1.reset();
        p2.reset();

        ball.reset();
    }

    private void endGame() {
        int winnerId = p1Score == SCORE_LIMIT ? 1 : 2;

        int dialog = JOptionPane.showConfirmDialog(null, "Player " + winnerId + " has won the game! Play again?", "Pong", JOptionPane.YES_NO_OPTION);

        if (dialog == JOptionPane.YES_OPTION) {
            restartGame();
        }
        else {
            closeProgram();
        }
    }

    private void closeProgram()
    {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);

        Graphics2D g2 = (Graphics2D)g;
        g2.fill(p1.getPaddle());
        g2.fill(p2.getPaddle());
        g2.fill(ball.getBall());
        g2.drawString(p1Score + " : " + p2Score, 350, 10);
    }


}