public class InsertionSort {
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && BaseFunctiun.less(a[j], a[j-1]); j--) {
                BaseFunctiun.exch(a, j, j-1);
            }
        }
    }

}
