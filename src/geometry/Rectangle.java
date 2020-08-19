package geometry;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Nimrod Gabbay
 * ID 318322484
 */

/**
 * implementation of "Geometry.Rectangle" class.
 */
public class Rectangle {
    // fields
    private Point upperLeft;
    private double width;
    private double height;


    /**
     * constructor- Create a new rectangle with location and width/height.
     * @param upperLeft point of the rectangle
     * @param width rectangle width
     * @param height rectangle height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }


    /**
     * finding intersection points between this rectangle and the given line.
     * @param line line
     * @return (possibly empty) List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> lst = new LinkedList<Point>();
        Line[] arrLines = this.getRecLines();
        Point interPoint = null;
        for (int i = 0; i < arrLines.length; i++) {
            //finding the point
            interPoint = line.intersectionWithRecLine(arrLines[i]);
            // add to the list
            if (interPoint != null && !this.isInList(interPoint, lst)) {
                lst.add(interPoint);
            }
        }
        return lst;

    }

    /**
     *
     * @return rectangle width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     *
     * @return rectangle height
     */
    public double getHeight() {
        return this.height;
    }


    /**
     *
     * @return upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * creating an array that will contain every line of the rectangle.
     * @return array of the 4 lines that every rectangle has
     */
    public Line[] getRecLines() {
        Line[] arrLines = new Line[4];
        Point upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(upperLeft.getX() + this.width, upperLeft.getY() + this.height);
        arrLines[0] = new Line(this.upperLeft, upperRight); //up
        arrLines[1] = new Line(upperRight, lowerRight); //right
        arrLines[2] = new Line(lowerLeft, lowerRight); //down
        arrLines[3] = new Line(this.upperLeft, lowerLeft); //left
        return arrLines;

    }

    /**
     *
     * check if the point is already in the list.
     * @param p point
     * @param lst list
     * @return true if the point is in list, false otherwise
     */
    public boolean isInList(Point p, List<Point> lst) {
        for (int i = 0; i < lst.size(); i++) {
            if (p.equals(lst.get(i))) {
                return true;
            }
        }
        return false;
    }


}
