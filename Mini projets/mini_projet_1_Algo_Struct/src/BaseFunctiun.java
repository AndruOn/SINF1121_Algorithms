public class BaseFunctiun {

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i]; a[i] = a[j]; a[j] = t;
    }

    public static void show(Comparable[] a) {
        //Print the array, on a single line.
        StringBuilder s= new StringBuilder();
        s.append("[");
        for (int i = 0; i < a.length-1; i++) {
            s.append(a[i] + ", ");
        }
        s.append(a[a.length-1] + "]\n");
        System.out.println(s.toString());
    }
}
