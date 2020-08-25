package sprites;
/**
 * @author Nimrod Gabbay
 */

import game.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Velocity;

import java.awt.Color;

/**
 * implementation of "sprites.Paddle" class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Color color;
    private double speed;

    /**
     * constructor.
     *
     * @param upperLeft point of the paddle
     * @param width     paddle width
     * @param height    paddle height
     * @param color     paddle color
     * @param k         keysensor for moving the paddle
     * @param speed     speed of the paddle
     */
    public Paddle(Point upperLeft, double width, double height, java.awt.Color color, KeyboardSensor k, double speed) {
        this.rec = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.keyboard = k;
        this.speed = speed;

    }

    /**
     * moving the paddle to the left.
     */
    public void moveLeft() {
        double x = this.rec.getUpperLeft().getX();
        double y = this.rec.getUpperLeft().getY();
        if (x - this.speed >= 0) {
            this.rec = new Rectangle(new Point(x - this.speed, y), this.rec.getWidth(), this.rec.getHeight());
        } else {
            this.rec = rec;
        }

    }

    /**
     * moving the paddle to the right.
     */
    public void moveRight() {
        double x = this.rec.getUpperLeft().getX();
        double y = this.rec.getUpperLeft().getY();
        if (x + this.rec.getWidth() + this.speed <= 800) {
            this.rec = new Rectangle(new Point(x + this.speed, y), this.rec.getWidth(), this.rec.getHeight());
        } else {
            this.rec = rec;
        }

    }

    // sprites.Sprite

    /**
     * calling the paddle to move.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * drawing the paddle.
     *
     * @param d board
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY()
                , (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY()
                , (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    // sprites.Collidable

    /**
     * @return collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;

    }


    /**
     * check if there is a collision with the ball and if so,
     * the direction of the ball will be changed according to the
     * location of the collision on the paddle.
     *
     * @param collisionPoint  collison point of the ball and the paddle
     * @param currentVelocity current velocity of the ball
     * @param hitter          ball
     * @return the velocity of the ball after the colliding
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx, dy;
        dx = currentVelocity.getDx();
        dy = currentVelocity.getDy();
        Line[] recLines = this.rec.getRecLines();
        // hits top/bottom lines of the rectangle
        Line upperLine = recLines[0];
        double lineLength = this.rec.getWidth();
        double y = upperLine.start().getY();
        // setting the 5 regions on the upper line of the paddle
        Line region1 = new Line(upperLine.start().getX(), y, upperLine.start().getX() + lineLength / 5, y);
        Line region2 = new Line(region1.end().getX(), y, region1.end().getX() + lineLength / 5, y);
        Line region3 = new Line(region2.end().getX(), y, region2.end().getX() + lineLength / 5, y);
        Line region4 = new Line(region3.end().getX(), y, region3.end().getX() + lineLength / 5, y);
        Line region5 = new Line(region4.end().getX(), y, region4.end().getX() + lineLength / 5, y);
        //finding the ball speed
        final double ballSpeed = Math.sqrt(dx * dx + dy * dy);

        // checking in which region the ball collides with the paddle

        // left most region of the paddle
        if (region1.isOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(240, ballSpeed);

        }
        if (region2.isOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(210, ballSpeed);

        }
        // in the middle region of the paddle
        if (region3.isOnLine(collisionPoint)) {
            dy = -1 * dy;
            return new Velocity(dx, dy);
        }
        if (region4.isOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(150, ballSpeed);

        }
        // right most region of the paddle
        if (region5.isOnLine(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(120, ballSpeed);

        }
        // case of colliding with right/left lines- making the ball to not stuck in the paddle
        dx = dx * -1;
        return new Velocity(dx, dy);

    }


    /**
     * Add this paddle to the game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

}
