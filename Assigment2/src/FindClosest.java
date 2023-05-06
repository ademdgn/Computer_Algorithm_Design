import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /** Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points)
    {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /** Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair()
    {
        return this.closestPointPair;
    }

    /** Main method for calculate and return closest point pair
     *
     * @param p --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex)
    {
        if (lastIndex - startIndex < 3) {
            // If there are only two or three points, calculate the closest pair directly
            if (lastIndex - startIndex == 1) {
                return new PointPair(p[startIndex], p[lastIndex]);
            } else if (lastIndex - startIndex == 2) {
                return getClosestPointPair(p[startIndex], p[startIndex + 1], p[lastIndex]);
            }
        }
        // Divide the set into two subsets, left and right
        int midIndex = (startIndex + lastIndex) / 2;
        PointPair leftPair = calculateClosestPointPair(p, startIndex, midIndex);
        PointPair rightPair = calculateClosestPointPair(p, midIndex + 1, lastIndex);

        // Find the closest pair between the two subsets
        PointPair closestPair;
        if (leftPair.getDistance() < rightPair.getDistance()) {
            closestPair = leftPair;
        } else {
            closestPair = rightPair;
        }

        // Find the closest pair that spans the two subsets
        Point2D.Double[] strip = new Point2D.Double[lastIndex - startIndex + 1];
        int j = 0;
        for (int i = startIndex; i <= lastIndex; i++) {
            if (Math.abs(p[i].x - p[midIndex].x) < closestPair.getDistance()) {
                strip[j++] = p[i];
            }
        }
        return stripClosest(strip, j, closestPair);
    }

    /** calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        // Distance between p1 and p2
        double d1 = p1.distance(p2);

        // Distance between p1 and p3
        double d2 = p1.distance(p3);

        // Distance between p2 and p3
        double d3 = p2.distance(p3);

        // Find the smallest distance
        double minDistance = Math.min(Math.min(d1, d2), d3);

        // Return the PointPair object
        if (minDistance == d1) {
            return new PointPair(p1, p2);
        } else if (minDistance == d2) {
            return new PointPair(p1, p3);
        } else {
            return new PointPair(p2, p3);
        }
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2){
        // Distance between p1 and p2
        double d1 = p1.getDistance();

        // Distance between p1 and p2.p1
        double d2 = p1.getPoint1().distance(p2.getPoint1());

        // Distance between p1 and p2.p2
        double d3 = p1.getPoint1().distance(p2.getPoint2());

        // Distance between p1.p2 and p2.p1
        double d4 = p1.getPoint2().distance(p2.getPoint1());

        // Distance between p1.p2 and p2.p2
        double d5 = p1.getPoint2().distance(p2.getPoint2());

        // Find the smallest distance
        double minDistance = Math.min(Math.min(Math.min(d1, d2), Math.min(d3, d4)), d5);

        // Return the PointPair object
        if (minDistance == d1) {
            return p1;
        } else if (minDistance == d2) {
            return new PointPair(p1.getPoint1(), p2.getPoint1());
        } else if (minDistance == d3) {
            return new PointPair(p1.getPoint1(), p2.getPoint2());
        } else if (minDistance == d4) {
            return new PointPair(p1.getPoint2(), p2.getPoint1());
        } else {
            return new PointPair(p1.getPoint2(), p2.getPoint2());
        }
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip --> point array
     * @param size --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {
        double minDist = shortestLine.getDistance();
        PointPair minPair = shortestLine;

        // Her bir nokta için diğer noktalardan en fazla 7 adet nokta kontrol edilecektir.
        for (int i = 0; i < size; ++i) {
            for (int j = i+1; j < size && (strip[j].y - strip[i].y) < minDist; ++j) {
                PointPair pair = new PointPair(strip[i], strip[j]);
                if (pair.getDistance() < minDist) {
                    minDist = pair.getDistance();
                    minPair = pair;
                }
            }
        }

        return minPair;
    }

}