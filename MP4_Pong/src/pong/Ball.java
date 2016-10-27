package pong;

import java.awt.*;

/**
 * Created by chezkah kate on 10/22/2016.
 */
public class Ball {
    private static final  int DIAMETER = 15;
    private Game gameOn;
    double x = 300;
    double y = 100;
    double xa = 2;
    double ya = 2;

    public Ball(Game game){this.gameOn = game;}

    void motion() throws InterruptedException{
        boolean changeDirection = true;
        if(xa + x <= 0){
            x = 300;
            y = 100;
            xa = 2;
            ya = 2;
            gameOn.score2++;
            gameOn.gameOver();
        }
        //for player1
        else if(collision(gameOn.rac1)){
            xa = gameOn.speedUp1;
            x = gameOn.rac1.getTopY() + DIAMETER;
            gameOn.speedUp1 += 0.5; //the ball speeds up after the ball collides with the racquet
        }
        //for player2
        else if(collision(gameOn.rac2)){
            xa =- gameOn.speedUp2;
            x = gameOn.rac2.getTopY() - DIAMETER;
            gameOn.speedUp2 +=0.5; //the ball speeds up after the ball collides with the racquet
        }
        else if (y + ya < 0)
            ya = gameOn.speedUp1;
        else if (y + ya > gameOn.getHeight() - DIAMETER)
            ya = -gameOn.speedUp1;
        else if (x + xa < 0)
            xa = gameOn.speedUp2;
        else if (x + xa > gameOn.getWidth() - DIAMETER){ //checks if the ball is beyond the right boundary
            x = 300;    //reset everything to normal
            y = 100;
            ya = 2;
            xa = 2;
            gameOn.score1++;
            gameOn.gameOver();
        }
        else
            changeDirection = false;
        if (changeDirection) {
            SoundEffects.BallSound.play();
        }
            x = x + xa;
            y = y + ya;
    }

    //checks collision of the ball and the racquet
    private boolean collision(Racquet rac){
        return rac.getBounds().intersects(getBounds());
    }

    //gets the boundaries of the rectangle/frame
    private Rectangle getBounds() {
        return new Rectangle((int)x, (int) y, DIAMETER, DIAMETER);
    }

    public void paintSprite(Graphics2D graph2d) {
        graph2d.setColor(Color.WHITE);
        graph2d.fillOval((int)x, (int) y, DIAMETER, DIAMETER);
    }

}
