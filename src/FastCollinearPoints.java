import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private LineSegment[] lineSegments;
    
    public FastCollinearPoints(Point[] points) {
        checkForNullOrRepeatedPoints(points);
        double[] slopeToP = new double[points.length];
        ArrayList<LineSegment> segments = new ArrayList<>();
        
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            
            // determine slope q makes with p
            for (int j = 0; j < points.length; j++) {
                slopeToP[j] = p.slopeTo(points[j]);
            }
            // sort points according to slope they make with p
            Arrays.sort(slopeToP);
            
            // check if any 3+ adjacent points in the sorted array have equal slopes
            // if so, these points and p are collinear. 
            for (int k = 0; k < slopeToP.length - 2; k++) {
                if (slopeToP[k] == slopeToP[k+1] && slopeToP[k+1] == slopeToP[k+2]) {
                    if (slopeToP[k+2] != slopeToP[k+3]) {
                        // thats 3 adjacent points in a row, they are collinear with p
                        segments.add(new LineSegment(p, points[k+2]));
                    } else {
                        // check if there are more than 3 in a row, and include them in collinearity
                    }
                }
            }
            
        }
        
    }
    
    public int numberOfSegments() {
        return lineSegments.length;
    }
    
    public LineSegment[] segments() {
        return lineSegments;
    }
    
    /**
     * Throws an IllegalArgumentException in the following cases:
     * The points array is null.
     * Any point in the points array is null.
     * Any two points in the points array are the same point.
     * 
     * @param points    Points array
     */
    private void checkForNullOrRepeatedPoints(Point[] points) {
        if (points == null || points[points.length - 1] == null) {
            throw new IllegalArgumentException("Either the points array is null or the last point in the array is null");
        }
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Two points in the array are the same.");
                }
            }
        }
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
