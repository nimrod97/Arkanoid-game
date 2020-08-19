package sprites;

import game.GameLevel;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import biuoop.DrawSurface;
import geometry.Velocity;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */


/**
 * setting the class of "ball".
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment env;
    private int boardWidth; // will be useful when we want to move the ball
    private int boardHeight; //will be useful when we want to move the ball

    /**
     * constructor- creating a ball with point, radius and color.
     * setting the velocity to 0 as a default also.
     *
     * @param center point
     * @param r      radius
     * @param color  color
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);

    }

    /**
     * another constructor- creating a point from coordinates,
     * and getting radius and color for creating a ball afterwards.
     * setting the velocity to 0 as a default also.
     *
     * @param x     value
     * @param y     value
     * @param r     radius
     * @param color color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
    }

    /**
     * ball constructor- including setting the game environment.
     *
     * @param x      value
     * @param y      value
     * @param r      radius
     * @param color  color
     * @param env    game environment
     * @param width  board width
     * @param height board height
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment env, int width, int height) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.env = env;
        this.boardWidth = width;
        this.boardHeight = height;
    }

    /**
     * constructor.
     *
     * @param x      value
     * @param y      value
     * @param r      radius
     * @param color  color
     * @param width  board width
     * @param height board height
     */
    public Ball(double x, double y, int r, java.awt.Color color, int width, int height) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
        this.v = new Velocity(0, 0);
        this.boardWidth = width;
        this.boardHeight = height;
    }


    /**
     * getX.
     *
     * @return x value of the point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * getY.
     *
     * @return y value of the point
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * getSize.
     *
     * @return size of the ball (radius)
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getColor.
     *
     * @return color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawOn-draw the ball on the given DrawSurface.
     *
     * @param surface surface of drawing
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * calling the ball to move.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * setting the game environment.
     *
     * @param environment reference to the game environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.env = environment;
    }

    /**
     * @return game environment reference
     */
    public GameEnvironment getGameEnvironment() {
        return this.env;
    }


    /**
     * setVelocity.
     *
     * @param velocity changes in x and y axes
     */
    public void setVelocity(Velocity velocity) {

        this.v = velocity;
    }

    /**
     * setVelocity- getting changes in position in x and y axes
     * and setting the velocity.
     *
     * @param dx change in x
     * @param dy change in y
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * getVelocity.
     *
     * @return velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }


    /**
     * moveOneStep
     * check if the will not collide with the edges of the board and with blocks,
     * and then moving the ball according to the velocity it has.
     * if the ball collides with block/ edges of board the velocity will change
     * accordingly.
     * We assume that the ball will be in the frame at the start point.
     */
    public void moveOneStep() {
        // the flag will determine if there is a collision with block or
        // if the ball approaches the boundaries
        int flag = 0;
        double dx = this.getVelocity().getDx();
        double dy = this.getVelocity().getDy();
        // cases of ball approaches boundaries
        if (this.getX() + this.getSize() + dx > this.boardWidth || this.getX() - this.getSize() + dx < 0) {
            this.setVelocity(-1 * dx, dy);
            flag++;
        }
        if (this.getY() + this.getSize() + dy > boardHeight || this.getY() - this.getSize() + dy < 0) {
            this.setVelocity(dx, -1 * dy);
            flag++;
        }
        if (flag != 0) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            // case of blocks in the game environment
            if (this.env != null && !this.env.isEmpty()) {
                // setting the ball trajectory
                Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
                // getting the collision info (if there is)
                CollisionInfo collInfo = this.env.getClosestCollision(trajectory);
                // there is no collision with blocks in this trajectory
                if (collInfo == null) {
                    this.center = this.getVelocity().applyToPoint(this.center);
                } else {
                    // there is a collision with block
                    Point collpoint = collInfo.collisionPoint();
                    // notify 'hit' function that there is a collision
                    Velocity velocity = collInfo.collisionObject().hit(this, collpoint, this.v);
                    // setting the new velocity after the collision
                    this.setVelocity(velocity);
                    // check if the new trajectory of the ball after the collision
                    // will also include collision point maybe with other blocks
                    Line tempTrajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
                    collInfo = this.env.getClosestCollision(tempTrajectory);
                    // there is another collision
                    if (collInfo != null) {
                        collpoint = collInfo.collisionPoint();
                        double x = collpoint.getX();
                        double y = collpoint.getY();
                        // moving the ball "almost" to the second collision
                        // point in order to prevent the ball to enter the block
                        if (this.getVelocity().getDx() > 0) {
                            x = x - 0.01;
                        } else {
                            x = x + 0.01;
                        }
                        if (this.getVelocity().getDy() > 0) {
                            y = y - 0.01;
                        } else {
                            y = y + 0.01;
                        }
                        this.center = new Point(x, y);
                    } else {
                        this.center = this.getVelocity().applyToPoint(this.center);
                    }
                }
            }


        }
    }

    /**
     * adding the ball to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing the ball from the game.
     * @param g game
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}


