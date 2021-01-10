
public class Median {
    static boolean debug_print = false;

    static int mid;


    // quicksort the subarray from a[lo] to a[hi]
    private static void quicksortMedian(Vector a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        if (debug_print) {System.out.println(String.format("mid=%d j=%d a[j]=%d",mid,j,a.get(j))); a.print(""); }
        //Found the median
        if (j == mid) return;
        //Look for the median in the right half
        if (j > mid) quicksortMedian(a, lo, j-1);
        else quicksortMedian(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int partition(Vector a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a.get(lo);
        while (true) {
            // find item on lo to swap
            while (a.get(++i)< v){
                if (i == hi) break;
            }
            // find item on hi to swap
            while (v< a.get(--j)) {
                if (j == lo) break;      // redundant since a[lo] acts as sentinel
            }
            // check if pointers cross
            if (i >= j) break;

            a.swap(i, j);
        }
        // put partitioning item v at a[j]
        a.swap(lo, j);
        return j;
    }

    public static int median(Vector a, int lo, int hi){
        mid = ( (hi+lo)/2 );
        quicksortMedian(a, lo, hi);
        System.out.printf("nbr operation:%d\n",a.nOp());

        return a.get(mid);
    }


    public static void main(String[] args) {
        int size = 11;
        Vector vector = new Vector(size);
        for (int i = 0; i < size; i++) {
            vector.set(i, i);
        }
        //vector.print("\nvect trié:");
        vector.swap(8,9);
        vector.swap(1,2);
        vector.swap(2,4);
        vector.swap(1,6);
        vector.swap(3,2);
        vector.swap(0,7);
        vector.swap(3,7);
        vector.swap(0,8);
        //vector.print("pas trié:");
        System.out.println("\n");

        System.out.println("expected:3 median 0-4: " + median(vector, 0, 4) + "\n");

        System.out.println("expected:7 median 4-tout: "+median(vector,4,size-1)+ "\n");

        System.out.println("expected:5 median tout: "+median(vector,0,size-1)+ "\n");

    }

}
