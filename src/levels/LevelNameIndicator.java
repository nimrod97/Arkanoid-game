package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;
/**
 * @author Nimrod Gabbay
 */

/**
 * implementation of "LevelNameIndicator" class.
 */
public class LevelNameIndicator implements Sprite {
    //fields
    private String levelName;

    /**
     * constructor.
     * @param levelName level name
     */
    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "" + this.levelName, 15);

    }

    @Override
    public void timePassed() {

    }
}
