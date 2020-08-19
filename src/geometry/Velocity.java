package geometry; /**
 * @author Nimrod Gabbay
 * ID 318322484
 */
// geometry.Velocity specifies the change in position on the `x` and the `y` axes.

/**
 * setting the class of "velocity".
 */
public class Velocity {
    //fields
    private double dx;
    private double dy;

    /**
     * constructor- setting velocity.
     *
     * @param dx change in x axis
     * @param dy change in y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * applyToPoint
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     *
     * @param p point
     * @return new point with the change
     */
    public Point applyToPoint(Point p) {
        double newX = p.getX() + this.dx;
        double newY = p.getY() + this.dy;
        return new Point(newX, newY);

    }

    /**
     * getDX.
     *
     * @return change in x axis
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getDy.
     *
     * @return change in y axis
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * fromAngleAndSpeed.
     *
     * @param angle the direction of where the ball needs to go
     * @param speed speed
     * @return new velocity according to the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double rad = Math.toRadians(angle);
        double dy = Math.cos(rad) * speed;
        double dx = Math.sin(rad) * speed;
        return new Velocity(dx, dy);
    }


}
