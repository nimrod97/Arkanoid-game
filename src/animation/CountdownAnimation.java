package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.Counter;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "CountdownAnimation" class.
 */
public class CountdownAnimation implements Animation {
    // fields
    private double seconds;
    private Counter counter;
    private SpriteCollection screen;
    private boolean running;
    private Sleeper sleeper;

    /**
     * constructor.
     *
     * @param numOfSeconds seconds
     * @param countFrom    number to count down from
     * @param gameScreen   screen of the specific level
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.counter = new Counter(countFrom);
        this.screen = gameScreen;
        this.running = false;
        this.sleeper = new Sleeper();

    }

    /**
     * drawing the "count down" frame according the count value.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.counter.getValue() == -1) {
            this.running = true;
        } else if (this.counter.getValue() == 0) {
            this.screen.drawAllOn(d);
            d.setColor(Color.BLACK);
            d.drawText(330, 300, "Go!", 100);
            this.counter.decrease(1);

        } else {
            this.screen.drawAllOn(d);
            d.setColor(Color.BLACK);
            d.drawText(370, 300, "" + this.counter.getValue(), 100);
            this.counter.decrease(1);
        }
        sleeper.sleepFor((long) (this.seconds * 1000) / 3);


    }

    /**
     * @return true if the frame should stop
     */
    @Override
    public boolean shouldStop() {
        return this.running;
    }
}
