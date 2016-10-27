package pong;
import java.applet.AudioClip;
import java.applet.Applet;

/**
 * Created by chezkah kate on 10/22/2016.
 */
public class SoundEffects {
        public static final AudioClip BallSound = Applet.newAudioClip(SoundEffects.class.getResource("ball.wav")); //sound effect of the ball in collision with the racquet
        public static final AudioClip GameOverSound = Applet.newAudioClip(SoundEffects.class.getResource("GameOver.wav")); //sound effect when the game is finally over
        public static final AudioClip Score = Applet.newAudioClip(SoundEffects.class.getResource("score!.wav")); // sound effect everytime a player gets a point/score
        public static  final AudioClip Out = Applet.newAudioClip(SoundEffects.class.getResource("out.wav")); //sound when the user choose to exit the game
        public static final AudioClip BackgroundMusic = Applet.newAudioClip(SoundEffects.class.getResource("BackgroundMusic.wav")); // background music while the game is on
}

