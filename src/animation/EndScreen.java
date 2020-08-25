package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import listeners.ScoreTrackingListener;

import java.awt.Color;

/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "EndScreen" class.
 */
public class EndScreen implements Animation {
    // fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private ScoreTrackingListener score;
    private int remainedBlocks;

    /**
     * constructor.
     *
     * @param k         keyboard
     * @param s         current score
     * @param remainedB remained blocks in the game
     */
    public EndScreen(KeyboardSensor k, ScoreTrackingListener s, int remainedB) {
        this.keyboard = k;
        this.score = s;
        this.stop = false;
        this.remainedBlocks = remainedB;
    }

    /**
     * drawing the end screen of the game according to the result.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.remainedBlocks == 0) {
            d.setColor(Color.BLUE);
            d.drawText(100, d.getHeight() / 2, "You Win! Your score is " + this.score.getCurrentScore().getValue()
                    , 40);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        } else {
            d.setColor(Color.red);
            d.drawText(100, d.getHeight() / 2, "Game Over. Your score is " + this.score.getCurrentScore().getValue()
                    , 40);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }

        }
    }

    /**
     * @return true if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
