package geometry;
/**
 * @author Nimrod Gabbay
 * ID: 318322484
 */

import java.util.List;

/**
 * setting the class of "line".
 */
public class Line {
    //fields
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start point
     * @param end   point
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * another constructor- getting 2 points and creating a line.
     *
     * @param x1 value
     * @param y1 value
     * @param x2 value
     * @param y2 value
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length.
     *
     * @return length of the line
     */

    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle.
     *
     * @return middle point of the line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2, (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * start.
     *
     * @return start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * end.
     *
     * @return end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * isIntersecting.
     *
     * @param other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * intersectionWith (was used in ass2).
     *
     * @param other line
     * @return intersection point if the lines intersect,and null otherwise
     */

    public Point intersectionWith(Line other) {
        double m1, m2, b1, b2;
        double solutionX, solutionY;
        double epsilon = Math.pow(10, -12);
        // if the first line doesnt have a slope
        if (this.start.getX() - this.end.getX() == 0) {
            //if the second line doesnt have a slope
            if (other.start().getX() - other.end().getX() == 0) {
                return null;
            } else { // the second line has a slope
                m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
                b2 = (m2 * -other.start().getX()) + other.start().getY();
                solutionX = this.start.getX();
                solutionY = m2 * this.start.getX() + b2;
                if (solutionX >= Math.min(other.start().getX(), other.end().getX() - epsilon)
                        && solutionX <= Math.max(other.start().getX(), other.end().getX()) + epsilon) {
                    return new Point(solutionX, solutionY);
                }
                return null;
            }
        } else {
            //the first line has a slope and the second doesnt
            if (other.start().getX() - other.end().getX() == 0) {
                m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                b1 = (m1 * -this.start.getX()) + this.start.getY();
                solutionX = other.start().getX();
                solutionY = m1 * other.start().getX() + b1;
                if (solutionX >= Math.min(this.start.getX(), this.end().getX()) - epsilon
                        && solutionX <= Math.max(this.start.getX(), this.end.getX()) + epsilon) {
                    return new Point(solutionX, solutionY);
                }
                return null;
            } else { // both have a slope
                m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
                m2 = (other.start().getY() - other.end().getY()) / (other.start().getX() - other.end().getX());
                b1 = (m1 * -this.start.getX()) + this.start.getY();
                b2 = (m2 * -other.start().getX()) + other.start().getY();
                if (m1 == m2) {
                    return null;
                }
                // solving the equation
                double incommonM = m1 - m2;
                double incommonB = (b1 - b2) * -1;
                solutionX = incommonB / incommonM;
                if (solutionX >= Math.min(this.start.getX(), this.end.getX()) - epsilon
                        && solutionX <= Math.max(this.start.getX(), this.end.getX()) + epsilon
                        && solutionX >= Math.min(other.start().getX(), other.end().getX()) - epsilon
                        && solutionX <= Math.max(other.start().getX(), other.end().getX()) + epsilon) {
                    solutionY = m1 * solutionX + b1;
                    return new Point(solutionX, solutionY);
                }

                return null;


            }

        }
    }

    /**
     * @param recLine rectangle
     * @return intersection point between this line and the
     * given rectangle (if there is).
     */
    public Point intersectionWithRecLine(Line recLine) {
        double m, b;
        double solX, solY;
        // the line doesnt have a slope
        if (this.start.getX() == this.end.getX()) {
            if (recLine.start().getX() == recLine.end().getX()) //case of left/right line of the rec
            {
                return null;
            } else { //case of top/bottom line of the rectangle with slope 0
                if (recLine.start().getY() >= Math.min(this.start.getY(), this.end.getY())
                        && recLine.start().getY() <= Math.max(this.start.getY(), this.end.getY())) {
                    solX = this.start.getX();
                    solY = recLine.start().getY();
                    // if the x point is on the rec line
                    if (solX >= Math.min(recLine.start().getX(), recLine.end().getX())
                            && solX <= Math.max(recLine.start().getX(), recLine.end().getX())) {
                        return new Point(solX, solY);
                    }
                    return null;

                }
            }
        } else { // the line has a slope
            m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            b = (m * -this.start.getX()) + this.start.getY();
            //case of left/right line of the rec with no slope
            if (recLine.start().getX() == recLine.end().getX()) {
                if (recLine.start().getX() >= Math.min(this.start.getX(), this.end.getX())
                        && recLine.start().getX() <= Math.max(this.start.getX(), this.end.getX())) {
                    solX = recLine.start().getX();
                    solY = m * solX + b;
                    // if the y point is on the rec line
                    if (solY >= Math.min(recLine.start().getY(), recLine.end().getY())
                            && solY <= Math.max(recLine.start().getY(), recLine.end().getY())) {
                        return new Point(solX, solY);
                    }
                    return null;
                }
            } else { //case of top/bottom line of the rec with slope 0
                if (recLine.start().getY() >= Math.min(this.start.getY(), this.end.getY())
                        && recLine.start().getY() <= Math.max(this.start.getY(), this.end.getY())) {
                    solY = recLine.start().getY();
                    solX = (solY - b) / m;
                    // if the x point is on the rec line
                    if (solX >= Math.min(recLine.start().getX(), recLine.end().getX())
                            && solX <= Math.max(recLine.start().getX(), recLine.end().getX())) {
                        return new Point(solX, solY);
                    }
                    return null;
                }
            }

        }
        return null;
    }

    /**
     * @param p point
     * @return true if the given point is on this line, false otherwise
     */
    public boolean isOnLine(Point p) {
        if (p.getX() == this.start.getX() && p.getX() == this.end.getX()) {
            if ((p.getY() >= Math.min(this.start.getY(), this.end.getY()))
                    && (p.getY() <= Math.max(this.start.getY(), this.end.getY()))) {
                return true;
            }
            return false;
        } else {
            if (p.getY() == this.start.getY() && p.getY() == this.end.getY()) {
                if ((p.getX() >= Math.min(this.start.getX(), this.end.getX()))
                        && (p.getX() <= Math.max(this.start.getX(), this.end.getX()))) {
                    return true;
                }
                return false;

            }
        }
        return false;

    }

    /**
     * equals.
     *
     * @param other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        if (this.start.equals(other.start()) && this.end.equals(other.end())) {
            return true;
        }
        return false;
    }

    /**
     * @param rect rectangle
     * @return If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> lst = rect.intersectionPoints(this);
        if (lst.isEmpty()) {
            return null;
        }
        int i = 0;
        Point returnPoint = lst.get(i);
        for (i = 1; i < lst.size(); i++) {
            if (this.start.distance(lst.get(i)) < this.start.distance(returnPoint)) {
                returnPoint = lst.get(i);
            }
        }
        return returnPoint;


    }
}
