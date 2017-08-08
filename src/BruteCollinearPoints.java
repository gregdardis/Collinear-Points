import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * This program examines 4 points at a time and checks whether they all lie on the
 * same line segment (are collinear) and returns all such line segments.
 * Four points p, q, r and s are collinear if the slopes between p and q, p and r,
 * and p and s are all equal.
 * 
 * The implementation of this class is a brute force approach, which does not utilize
 * sorting and is thus much slower.
 *
 */
public class BruteCollinearPoints {
    
    private LineSegment[] lineSegments;
    
    public BruteCollinearPoints(Point[] points) {
        checkForNullOrRepeatedPoints(points);
        ArrayList<LineSegment> segments = new ArrayList<>();
        
        Point[] pointsSorted = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsSorted);
        
        for (int p = 0; p < pointsSorted.length - 3 ; p++) {
            for (int q = p + 1; q < pointsSorted.length - 2; q++) {
                for (int r = q + 1; r < pointsSorted.length - 1; r++) {
                    for (int s = r + 1; s < pointsSorted.length; s++) {
                        if (pointsSorted[p].slopeTo(pointsSorted[q]) == pointsSorted[p].slopeTo(pointsSorted[r]) && 
                                pointsSorted[p].slopeTo(pointsSorted[q]) == pointsSorted[p].slopeTo(pointsSorted[s])) {
                            System.out.println("pointsSorted[p].slopeTo(pointsSorted[q]): " + pointsSorted[p].slopeTo(pointsSorted[q]));
                            // how do we know if p and s are the opposite ends of the line segment? i'm not sure we do... maybe sorting fixed that?
                            segments.add(new LineSegment(pointsSorted[p], pointsSorted[s]));
                        }
                    }
                }
            }
        }
        lineSegments = segments.toArray(new LineSegment[segments.size()]);
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
    
    public int numberOfSegments() {
        return lineSegments.length;
    }
    
    public LineSegment[] segments() {
        return lineSegments;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
