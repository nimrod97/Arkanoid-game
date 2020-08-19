package game;

import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Collidable;
import sprites.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "game.GameEnvironment" class.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }


    /**
     * add the given collidable to the environment.
     *
     * @param c collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);

    }

    /**
     * remove the given collidable from the list.
     *
     * @param c collidable object
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * .
     *
     * @param trajectory trajectory of object
     * @return If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //storing the collidables that collide with the object
        List<Collidable> interPoints = new ArrayList<Collidable>();
        Rectangle rec = null;
        Point p = null;
        // storing closestIntersectionToStartOfLine point of each collidable in the list
        for (int i = 0; i < this.collidables.size(); i++) {
            rec = this.collidables.get(i).getCollisionRectangle();
            p = trajectory.closestIntersectionToStartOfLine(rec);
            if (p != null) {
                interPoints.add(this.collidables.get(i));
            }

        }
        // there are no intersection points
        if (interPoints.isEmpty()) {
            return null;
        }
        // there are many collision points with many collidables and we will find the closest one
        // to the start of the line and find the exact collidable
        int i = 0;
        Point returnP = trajectory.closestIntersectionToStartOfLine(interPoints.get(i).getCollisionRectangle());
        Collidable returnObject = interPoints.get(i);
        for (i = 1; i < interPoints.size(); i++) { // finding the closest collision
            p = trajectory.closestIntersectionToStartOfLine(interPoints.get(i).getCollisionRectangle());
            if (trajectory.start().distance(p) < trajectory.start().distance(returnP)) {
                returnP = p;
                returnObject = interPoints.get(i);

            }
        }
        return new CollisionInfo(returnP, returnObject);

    }

    /**
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        if (this.collidables.isEmpty()) {
            return true;
        }
        return false;
    }

}
