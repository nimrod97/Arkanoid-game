package sprites; /**
 * @author Nimrod Gabbay
 * ID: 318322484
 */

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * interface of "sprites.Collidable".
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * @param collisionPoint  point of collision
     * @param currentVelocity current velocity of the object (ball).
     * @param hitter          ball
     * @return the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
