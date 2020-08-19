package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "sprites.SpriteCollection" class.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * adding the sprite to the list.
     *
     * @param s sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * removing the sprite from the list.
     * @param s sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed();
        }

    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d board
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }

    }
}
