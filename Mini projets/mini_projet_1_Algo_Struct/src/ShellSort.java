public class  ShellSort {

         public static void shell(int[] a){
            int[] espacements ={701, 301, 132, 57, 23, 10, 4, 1};
            for (int e: espacements) {
                for (int i = e; i < a.length; i++) {
                    int t = a[i];
                    int j = i;

                    while (j >= e && a[j - e] > t) {
                        a[j] = a[j - e];
                        j -= e;
                    }
                    a[j] = t;
                }
            }
        }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static void sort(Comparable[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && BaseFunctiun.less(a[j], a[j-h]); j -= h) {
                    BaseFunctiun.exch(a, j, j-h);
                }
            }
            h /= 3;
        }
    }



}
