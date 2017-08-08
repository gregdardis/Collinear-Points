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
        checkForNullOrRepeatedPoints(points);
        ArrayList<LineSegment> segments = new ArrayList<>();
        
        // possibly supposed to copy array and sort that instead and use that below
        Arrays.sort(points);
        
        for (int p = 0; p < points.length - 3 ; p++) {
            for (int q = p + 1; q < points.length - 2; q++) {
                for (int r = q + 1; r < points.length - 1; r++) {
                    for (int s = r + 1; s < points.length; s++) {
                        if (points[p].slopeTo(points[q]) == points[p].slopeTo(points[r]) && 
                                points[p].slopeTo(points[q]) == points[p].slopeTo(points[s])) {
                            segments.add(new LineSegment(points[p], points[s]));
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
    
}
