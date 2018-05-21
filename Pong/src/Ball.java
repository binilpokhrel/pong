import java.awt.geom.*;

public class Ball
{
    private Rectangle2D ball = new Rectangle2D.Double();

    private static final double BALL_WIDTH = 10, BALL_HEIGHT = 10, BALL_START_X = 345, BALL_START_Y = 295, MAX_ANGLE = 70, MAX_SPEED = 0.9;

    private double velX = 0.5, velY = 0.5;

    private static PongGame game;

    public Ball(PongGame g) {
        this.game = g;
        ball.setRect(BALL_START_X, BALL_START_Y, BALL_WIDTH, BALL_HEIGHT);
    }

    public void updatePos() {
        checkCollisions();
        ball.setRect(ball.getX() + velX, ball.getY() + velY, BALL_WIDTH, BALL_HEIGHT);
    }

    private void checkCollisions() {

        if (goalHit()) {
            if (ball.getX()<=0)
                game.updateScoreForPlayer(2);
            else
                game.updateScoreForPlayer(1);
            game.resetGame();
        }

        if(paddleHit()) {
            int player = ball.getX() < this.game.getWidth()/2 ? 1 : 2;

            //changes velocity of ball based on angle of impact
            double relY = (this.game.getPlayer(player).getPaddleY()+(this.game.getPlayer(player).getPaddleHeight()/2)) - ball.getY();
            double normalizedY = (relY/(this.game.getPlayer(player).getPaddleHeight()/2));
            double bounceAngle = normalizedY * MAX_ANGLE;
            velX = MAX_SPEED*Math.cos(bounceAngle);
            velY = MAX_SPEED*-Math.sin(bounceAngle);


            ball.setRect (velX > 0 ? ball.getX() - 1 : ball.getX() + 1, ball.getY(), BALL_WIDTH, BALL_HEIGHT);
            velX = -velX;
        }

        if (ceilingFloorHit()) {
            velY = -velY;
        }
    }

    private boolean goalHit() {
        return (ball.getX()<=0 || ball.getX()>=690);
    }

    private boolean paddleHit() {
        return (ball.intersects(game.getPlayer(1).getPaddle()) || ball.intersects(game.getPlayer(2).getPaddle()));
    }

    private boolean ceilingFloorHit()
    {
        return (ball.getY()<=0 || ball.getY()>=590);
    }

    public void reset() {
        ball.setRect(BALL_START_X, BALL_START_Y, BALL_WIDTH, BALL_HEIGHT);
    }

    public Rectangle2D getBall()
    {
        return ball;
    }
}