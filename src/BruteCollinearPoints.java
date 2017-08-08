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
    
    private final ArrayList<LineSegment> segments = new ArrayList<>();
    
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array is null");
        }
        
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);
        
        if (hasNullPoint(pointsCopy)) {
            throw new IllegalArgumentException("Null point in points array");
        }
        if (hasDuplicate(pointsCopy)) {
            throw new IllegalArgumentException("Duplicate points exist");
        }
        
        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {
                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) && 
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {
                            segments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }
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
    
    public int numberOfSegments() {
        return segments.size();
    }
    
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
    
}
