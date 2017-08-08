import java.util.ArrayList;
import java.util.Arrays;

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
        
        ArrayList<LineSegment> segments = new ArrayList<>();
        
        Point[] pointsSorted = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsSorted);
        
        for (int p = 0; p < points.length - 3 ; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (pointsSorted[p].slopeTo(pointsSorted[q]) == pointsSorted[p].slopeTo(pointsSorted[r]) 
                                && pointsSorted[p].slopeTo(pointsSorted[q]) == pointsSorted[p].slopeTo(pointsSorted[s])) {
                            segments.add(new LineSegment(pointsSorted[p], pointsSorted[q]));
                        }
                    }
                }
            }
        }
        lineSegments = segments.toArray(new LineSegment[segments.size()]);
    }
    
    public int numberOfSegments() {
        return lineSegments.length;
    }
    
    public LineSegment[] segments() {
        return lineSegments;
    }
}
