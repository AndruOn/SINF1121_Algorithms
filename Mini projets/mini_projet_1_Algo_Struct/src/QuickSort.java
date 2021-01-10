public class QuickSort {
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a); // Eliminate dependence on input.
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi); // Partition (see page 291).
        sort(a, lo, j-1); // Sort left part a[lo .. j-1].
        sort(a, j+1, hi); // Sort right part a[j+1 .. hi].
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        // Partition into a[lo..i-1], a[i], a[i+1..hi].
        int i = lo, j = hi+1; // left and right scan indices
        Comparable v = a[lo]; // partitioning item 
        while (true) {
            // find item on lo to swap
            while (BaseFunctiun.less(a[++i], v)) {
                if (i == hi) break;
            }
            // find item on hi to swap
            while (BaseFunctiun.less(v, a[--j])) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }
            // check if pointers cross
            if (i >= j) break;
            BaseFunctiun.exch(a, i, j);
        }
        BaseFunctiun.exch(a, lo, j);
        return j;
    }
}




