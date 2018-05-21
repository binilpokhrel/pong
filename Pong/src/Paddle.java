import java.awt.geom.*;

public class Paddle
{
    private Rectangle2D paddle = new Rectangle2D.Double();

    private static final double PADDLE_SPEED = 1, PADDLE_WIDTH = 15, PADDLE_HEIGHT = 100;
    private double DEFAULT_X;
    private double DEFAULT_Y;

    private double paddleX;
    private double paddleY;

    private int direction;

    private boolean up, down;

    private static PongGame game;

    public Paddle(PongGame g, int x, int y) {
        this.game = g;
        DEFAULT_X = x;
        DEFAULT_Y = y;
        paddleX = DEFAULT_X;
        paddleY = DEFAULT_Y;
        paddle.setRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
    }

    public void setUp(boolean b){
        up = b;
    }

    public void setDown(boolean b){
        down = b;
    }

    public void updatePos() {

            if (up && paddleY > 0) { //move up
                paddleY += -1;
            }


            if (down && paddleY + PADDLE_HEIGHT < 600) { //move down
                paddleY += 1;
            }

            paddle.setRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);

    }

    public void reset() {
        paddleX = DEFAULT_X;
        paddleY = DEFAULT_Y;
        direction = 0;
    }

    public Rectangle2D getPaddle()
    {
        return paddle;
    }

    public double getPaddleHeight(){return PADDLE_HEIGHT;}
    public double getPaddleWidth(){return PADDLE_WIDTH;}
    public double getPaddleX(){return paddleX;}
    public double getPaddleY(){return paddleY;}


}