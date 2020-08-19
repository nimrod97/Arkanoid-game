package sprites;

import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import biuoop.DrawSurface;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Nimrod Gabbay
 * ID: 318322484
 */

/**
 * implementaiton of "sprites.Block" class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private List<HitListener> hitListeners;
    private Rectangle rec;
    private Color color;


    /**
     * constructor.
     *
     * @param upperLeft point of the block
     * @param width     block width
     * @param height    block height
     * @param color     block color
     */
    public Block(Point upperLeft, double width, double height, java.awt.Color color) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * @return collision rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;

    }

    /**
     * @param collisionPoint  point of collision between the ball and the block
     * @param currentVelocity velocity of the ball
     * @param hitter          ball
     * @return the new velocity after the collision and notifying the block about hitting
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx, dy;
        dx = currentVelocity.getDx();
        dy = currentVelocity.getDy();
        Line[] recLines = this.rec.getRecLines();
        // hits top/bottom lines of the rectangle
        if (recLines[0].isOnLine(collisionPoint) || recLines[2].isOnLine(collisionPoint)) {
            dy = dy * -1;
        }
        // hits left/right lines of the rectangle
        if ((recLines[1].isOnLine(collisionPoint) || recLines[3].isOnLine(collisionPoint))) {
            dx = dx * -1;
        }
        this.notifyHit(hitter);
        return new Velocity(dx, dy);


    }

    /**
     * drawing the block.
     *
     * @param drawSurface board
     */
    public void drawOn(DrawSurface drawSurface) {
        drawSurface.setColor(this.color);
        drawSurface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY()
                , (int) this.rec.getWidth(), (int) this.rec.getHeight());
        drawSurface.setColor(Color.black);
        drawSurface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY()
                , (int) this.rec.getWidth(), (int) this.rec.getHeight());

    }

    /**
     *
     */
    @Override
    public void timePassed() {
        //not now..

    }

    /**
     * adding the block to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * removing the block from the game.
     *
     * @param game game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);

    }

    /**
     * in case of collision with the ball, the function notifies all listeners
     * about a hit event.
     *
     * @param hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }
}