public class SelectionSort {

    public static void sort(Comparable[] tab){
        int N = tab.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i+1; j < N; j++) {
                if(BaseFunctiun.less(tab[j], tab[min])) min = j;
            }
            BaseFunctiun.exch(tab,i,min);
        }
    }
}
