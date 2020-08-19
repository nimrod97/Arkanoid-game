package geometry;
/**
 * @author Nimrod Gabbay
 * ID: 318322484
 */
/**
 * setting the class of "point".
 */
public class Point {
    //fields
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x value
     * @param y value
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance.
     *
     * @param other point
     * @return distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.getX()) * (this.x - other.getX()))
                + ((this.y - other.getY()) * (this.y - other.getY())));
    }

    /**
     * equals.
     *
     * @param other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.getX() && this.y == other.getY()) {
            return true;
        }
        return false;

    }

    /**
     * getX.
     *
     * @return x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * getY.
     *
     * @return y value of this point
     */
    public double getY() {
        return this.y;
    }

}
