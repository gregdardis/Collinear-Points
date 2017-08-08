import java.util.Comparator;

public class Point implements Comparable<Point> {
    
    public Point(int x, int y) {
        // constructs the point (x, y)
    }

    public void draw() {
        // draws this point
    }
    
    public void drawTo(Point that) {
        // draws the line segment from this point to that point
    }
    
    public String toString() {
        return null;
        // string representation
    }

    public int compareTo(Point that) {
        return 0;
        // compare two points by y-coordinates, breaking ties by x-coordinates
    }
    
    public double slopeTo(Point that) {
        return 0;
        // the slope between this point and that point
    }
    
    public Comparator<Point> slopeOrder() {
        return null;
        // compare two points by slopes they make with this point
    }
}
