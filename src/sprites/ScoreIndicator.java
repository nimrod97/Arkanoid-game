package sprites;

import biuoop.DrawSurface;

import java.awt.Color;
/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "ScoreIndicator" class.
 */
public class ScoreIndicator implements Sprite {
    //field
    private Counter counter;

    /**
     * constructor.
     * @param c counter
     */
    public ScoreIndicator(Counter c) {
        this.counter = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(280, 15, "Score:" + this.counter.getValue(), 15);

    }

    @Override
    public void timePassed() {

    }
}
