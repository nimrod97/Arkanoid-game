package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.SpriteCollection;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "PauseScreen" class.
 */
public class PauseScreen implements Animation {
    // fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection screen;


    /**
     * constructor.
     *
     * @param k          keyboard
     * @param gameScreen screen of the game
     */
    public PauseScreen(KeyboardSensor k, SpriteCollection gameScreen) {
        this.keyboard = k;
        this.screen = gameScreen;
        this.stop = false;
    }

    /**
     * drawing the "pause" notification.
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.screen.drawAllOn(d);
        d.drawText(20, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     *
     * @return true if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
