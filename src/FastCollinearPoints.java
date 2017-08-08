import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private final ArrayList<LineSegment> segments = new ArrayList<>();
    
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }
        
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        if (hasNullPoint(pointsCopy)) {
            throw new IllegalArgumentException("Null point in points array");
        }
        if (hasDuplicate(pointsCopy)) {
            throw new IllegalArgumentException("Duplicate points exist");
        }
        
        for (int i = 0; i < pointsCopy.length - 3; i++) {
            Arrays.sort(pointsCopy);
            Arrays.sort(pointsCopy, pointsCopy[i].slopeOrder());
            
            for (int p = 0, first = 1, last = 2; last < pointsCopy.length; last++) {
                
                while (last < pointsCopy.length &&
                        Double.compare(pointsCopy[p].slopeTo(pointsCopy[first]), pointsCopy[p].slopeTo(pointsCopy[last])) == 0) {
                    last++;
                }
                
                if (last - first >= 3 && pointsCopy[p].compareTo(pointsCopy[first]) < 0) {
                    segments.add(new LineSegment(pointsCopy[p], pointsCopy[last - 1]));
                }
                
                first = last;
            }
        }
    }
    
    public int numberOfSegments() {
        return segments.size();
    }
    
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
    
    /**
     * Compares a point in the sorted points array to the entry next to it to make sure
     * they are not the same point.
     * 
     * @param points    Sorted points array
     * @return  True if the array has duplicate points, false otherwise
     */
    private boolean hasDuplicate(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks every element in an array of points to see if any of them are null
     * 
     * @param points    Points array
     * @return  True if the array has a null point, false otherwise
     */
    private boolean hasNullPoint(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null){
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
