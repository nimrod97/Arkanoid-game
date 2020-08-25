package sprites;

import biuoop.DrawSurface;

/**
 * @author Nimrod Gabbay
 */

/**
 * interface of "sprites.Sprite".
 */
public interface Sprite {

    /**
     * draw the sprite to the screen.
     * @param d board
     */
    void drawOn(DrawSurface d);
    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
