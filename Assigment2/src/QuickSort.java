import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        if (startIndex < lastIndex) {
            int pivotIndex;
            if (orderBy.equals("compareX")) {
                pivotIndex = partitionX(arr, startIndex, lastIndex);
            } else if (orderBy.equals("compareY")) {
                pivotIndex = partitionY(arr, startIndex, lastIndex);
            } else {
                throw new IllegalArgumentException("Invalid orderBy parameter");
            }
            sort(arr, startIndex, pivotIndex - 1, orderBy);
            sort(arr, pivotIndex + 1, lastIndex, orderBy);
        }
    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        //Write your codes here
        Point2D.Double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        //Write your codes here
        int mid = (left + right) / 2;

        if (arr[left].getX() > arr[mid].getX()) {
            swap(arr, left, mid);
        }
        if (arr[left].getX() > arr[right].getX()) {
            swap(arr, left, right);
        }
        if (arr[mid].getX() > arr[right].getX()) {
            swap(arr, mid, right);
        }

        return arr[mid];
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        int mid = (left + right) / 2;

        if (arr[left].getY() > arr[mid].getY()) {
            swap(arr, left, mid);
        }
        if (arr[left].getY() > arr[right].getY()) {
            swap(arr, left, right);
        }
        if (arr[mid].getY() > arr[right].getY()) {
            swap(arr, mid, right);
        }

        return arr[mid];
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivot = getMedianX(arr, startIndex, lastIndex);
        int i = startIndex - 1;

        for (int j = startIndex; j <= lastIndex - 1; j++) {
            if (arr[j].getX() < pivot.getX()) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, lastIndex);
        return i + 1;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivot = getMedianY(arr, startIndex, lastIndex);
        int i = startIndex - 1;
        for (int j = startIndex; j <= lastIndex - 1; j++) {
            if (arr[j].getY() < pivot.getY()) {
                i++;
                swap(arr, i, j);
            } else if (arr[j].getY() == pivot.getY() && arr[j].getX() < pivot.getX()) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, lastIndex);
        return i + 1;
    }

}
