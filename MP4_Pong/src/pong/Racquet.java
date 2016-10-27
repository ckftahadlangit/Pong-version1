package pong;

import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Created by chezkah kate on 10/22/2016.
 */

public class Racquet {
    private Game gameOn;
    private int pos;
    private static final int HEIGHT = 50;
    private  static final int WIDTH = 10;
    double x = 0;
    double xa = 0;

    public Racquet(Game g, int pos, int x){
        gameOn = g;
        this.pos = pos;
        this.x = x;
    }
    
    //motion of the racquet
    public void motion(){
        if((xa + x < gameOn.getHeight()  - HEIGHT) && (xa + x > 0)){
            x = x + xa;
        }
    }
    
    //paints the sprites 
    public void paintSprite(Graphics2D graph2d) {
        graph2d.fillRect(pos,(int) x,WIDTH, HEIGHT );
    }

    public void keyReleased(KeyEvent e){
        xa = 0;
    }

    //upward move of the ball
    public void moveUp(){ xa = -gameOn.speed;}
    //downward move of the ball
    public void moveDown(){ xa = gameOn.speed;}
    //gets the boundary of the rectangle
    public Rectangle getBounds(){
        return new Rectangle(pos,(int)x, WIDTH, HEIGHT);
    }

    public int getTopY(){return pos - WIDTH;}
}
