package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * Created by chezkah kate on 10/22/2016.
 */
public class Game extends JPanel{

    Ball ball = new Ball(this);
    Racquet rac1 = new Racquet(this, 5, 0);
    Racquet rac2 = new Racquet(this,560,0);
    int score1 = 0;
    int score2 = 0;
    double speed, speedUp1, speedUp2;
    double powerSpeed1 = 10, powerSpeed2 = 10;

    private int getScore1(){
        return score1;
    }
    private int getScore2(){return score2;}

    public Game(){
        speed = speedUp1 = speedUp2 = 3;

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
                    rac2.keyReleased(e);
                }
                if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S){
                    rac1.keyReleased(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN){
                    rac2.moveDown();
                }
                if (e.getKeyCode() == KeyEvent.VK_UP){
                    rac2.moveUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_W){
                    rac1.moveUp();
                }
                if (e.getKeyCode() == KeyEvent.VK_S){
                    rac1.moveDown();
                } //sets the speed of the powerUp
                if (e.getKeyCode() == KeyEvent.VK_SPACE){
                    powerSpeed1+=3;
                } //sets the speed of the powerUp
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    powerSpeed2+=3;
                }
            }
        });
        setFocusable(true);
        SoundEffects.BackgroundMusic.loop();
    }

    private void motion() throws InterruptedException{
        ball.motion();
        rac1.motion();
        rac2.motion();
    }

    //drawing the sprites
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        //sets the backgrounf color to black
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,getWidth(),getHeight());
        Graphics2D graph2d;
        graph2d = (Graphics2D) graphics;
        graph2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paintSprite(graph2d);
        graph2d.setColor(Color.CYAN);
        rac1.paintSprite(graph2d);
        graph2d.setColor(Color.PINK);
        rac2.paintSprite(graph2d);

        //for player1
        graph2d.setColor(Color.CYAN);	//setting the score color to cyan for player 1
        graph2d.setFont(new Font("Impact", Font.PLAIN, 30));	//setting the font to Impact
        graph2d.drawString(String.valueOf(getScore1()), 270, 30);	//printing it on 270 by 30 canvass
        graph2d.setFont(new Font("Calibri", Font.PLAIN, 12));    //setting the font to Calibri
        graph2d.drawString("Player 1: Press Space to PowerUp!",100,300);

        //for player2
        graph2d.setColor(Color.PINK); //setting the score color to pink for player2
        graph2d.setFont(new Font("Impact", Font.PLAIN, 30)); // setting the font to Impact
        graph2d.drawString(String.valueOf(getScore2()), 300, 30);	//printing it on 300 by 30 canvass
        graph2d.setFont(new Font("Calibri", Font.PLAIN, 12));    //setting the font to Calibri
        graph2d.drawString("Player 2: Press Enter to PowerUp!",350,300);
    }

    //the game is considered over when one player already reached 3 points
    public void gameOver() throws InterruptedException {
        SoundEffects.BackgroundMusic.stop();
        SoundEffects.Score.play();
        speed = powerSpeed1 = powerSpeed2 = 2; //resets all the speed to normal
        SoundEffects.BackgroundMusic.loop();
        if(score1 == 3 || score2 == 3 ) {
            SoundEffects.GameOverSound.play();
            //displays the FINAL SCORE
            JOptionPane.showMessageDialog(this, "Player One <Cyan>: " + getScore1() + "\nPlayer Two <Pink>: " + getScore2(), "CONGRATULATIONS! ", JOptionPane.YES_NO_OPTION);
            //prompts a dialog asking whether the player wants to continue playing after the game is over
            if (JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                instance();
                inMotion();
            } else {
                SoundEffects.Out.play(); //Sound effect before exiting or closing the frame
                System.exit(ABORT);
            }
        }
    }

    private void instance() {
        speed = 3;
        score1 = 0;
        score2 = 0;
    }

    private void inMotion() throws InterruptedException{
        while (true){
            if(score1 == 3 || score2 == 3){
                gameOver(); // calls the game over function since a player already reached 3 pts
            }
            motion();
            repaint();
            Thread.sleep(20);
        }
    }

    public static  void main(String args[]) throws InterruptedException{
        JFrame fr = new JFrame("Pong_v1.0");
        Game g = new Game();
        fr.add(g);
        fr.setSize(590,350);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g.inMotion();
    }
}




