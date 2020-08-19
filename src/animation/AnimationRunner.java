package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "AnimationRunner" class.
 */
public class AnimationRunner {
    // fields
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     *
     * @param g            gui
     * @param framesPerSec frames per second
     */
    public AnimationRunner(GUI g, int framesPerSec) {
        this.gui = g;
        this.framesPerSecond = framesPerSec;
        this.sleeper = new Sleeper();

    }

    /**
     * running the game.
     *
     * @param animation animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * @return gui of the game
     */
    public GUI getGui() {
        return this.gui;
    }
}
