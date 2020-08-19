package sprites;
/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

import geometry.Point;

/**
 * implementation "sprites.CollisionInfo" class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor.
     * @param p  point
     * @param o object
     */
    public CollisionInfo(Point p, Collidable o) {
        this.collisionPoint = p;
        this.collisionObject = o;
    }


    /**
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }



    /**
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
