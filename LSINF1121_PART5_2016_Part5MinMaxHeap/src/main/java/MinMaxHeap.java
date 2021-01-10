import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinMaxHeap<Key extends Comparable<Key>> {

    // ---------------------------------------------------------------------------------------
    // Instance variables
    // ---------------------------------------------------------------------------------------

    // You are not allowed to add/delete variables, nor modifying their names or visibility.
    public Key[] pq;          // contains the elements starting at position 1
    public int N = 0;         // number of elements in the heap
    public int height = 0;    // should help you to know if you are at a level min or max

    // ---------------------------------------------------------------------------------------------------
    // Functions that you should implement. This is the only part of this class that you should modify ;-)
    // ---------------------------------------------------------------------------------------------------

    /**
     * @pre size() >= 1
     */
    public Key min() {
        return pq[1];
    }

    /**
     * @pre size() >= 1
     */
    public Key max() {
        if(N==1){return pq[1];}
        int index_to_remove = 2;
        if(N>=3){
            if(pq[2].compareTo(pq[3]) < 0){ index_to_remove=3;}
        }
        return pq[index_to_remove];
    }

    /**
     * @pre 1 <= k <= size()
     */
    private void swim(int k) {
        //System.out.println("N:"+N+" inserted:"+pq[k].toString());
        if(N==1){ return; }
        if(N<4){
            if(less(k,1)){ exch(k , 1); }
            return;
        }
        //au moins 3 elements
        if (height % 2 == 1) { //ajout dans min level
            if(less(k/2,k)){ // max au dessus plus petit
                exch(k / 2, k);
                k=k/2;
                while (k > 3 && less(k/4, k)) {
                    //System.out.println("k:"+k);
                    exch(k / 4, k);
                    k = k/4;
                }
            } else if (less(k,k/4)) { //min audessus dessus plus grand

                while (k > 1 && less(k, k/4)) {
                    //System.out.println("k:"+k);
                    exch(k / 4, k);
                    k = k/4;
                }
            }
        }else { //ajout dans max level
            if(less(k/4,k)){ // max au dessus dessus plus petit
                while (k > 3 && less(k/4, k)) {
                    //System.out.println("k:"+k);
                    exch(k / 4, k);
                    k = k/4;
                }
            } else if (less(k,k/2)) { //min au dessus plus grand
                exch(k / 2, k);
                k=k/2;

                while (k > 1 && less(k, k/4)) {
                    exch(k / 4, k);
                    k = k/4;
                }
            }
        }
    }

    // ---------------------------------------------------------------------------------------
    // Constructor and helpers. You should not modify this. However, using them may be useful.
    // ---------------------------------------------------------------------------------------

    public MinMaxHeap(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    /**
     * @return pq[i] < pq[j]
     */
    public boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    /**
     * Exchanges positions i and j in pq
     */
    private void exch(int i, int j) {
        Key e = pq[i];
        pq[i] = pq[j];
        pq[j] = e;
    }

    /**
     * @return true if the heap is empty, false else
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * @return the size of the heap
     */
    public int size() {
        return N;
    }

    /**
     * @param v value to insert in the heap. v != null.
     */
    public void insert(Key v) {
        pq[++N] = v;
        if (N >= (1 << height)) height++;
        swim(N);
    }

    @Override
    public String toString() {
        return Arrays.toString(pq);
    }

    void print_heap_int(){
        int level = 0;
        Key[] a= Arrays.copyOfRange(pq,1,pq.length);
        while(level<=height){
            int start = (int) Math.pow(2,level);
            int end = (int) Math.pow(2,level+1);
            //System.out.println("level:"+level+"  height:"+height+"  start:"+start+"  end:"+end);

            StringBuilder decalage = new StringBuilder();
            for (int i = 1; i < Math.pow(2,height-level)+2; i=i*2) { decalage.append("   "); }

            for (int i = start; i < end; i++) {
                if(i>=pq.length){break;}
                if(pq[i]!=null){
                    decalage.append(String.format("%d",(Integer) pq[i]));
                }else{
                    decalage.append("nn");
                }
                for (int j = 0; j < height-level+1; j++) {decalage.append(" ");}
            }
            System.out.println(decalage.toString());
            level++;
        }
        System.out.println(String.format("max:%d min:%d height:%d N:%d",(Integer) max(),(Integer) min(),height,N));
    }
    void print_heap_int_array(){
        System.out.println(toString());
        System.out.println(String.format("max:%d min:%d height:%d N:%d",(Integer) max(),(Integer) min(),height,N));
    }

    public static void main(String[] args) {
        MinMaxHeap<Integer> heap = new MinMaxHeap<>(25);

        heap.insert(51);heap.print_heap_int();
        heap.insert(31);heap.print_heap_int();
        heap.insert(21);heap.print_heap_int();
        heap.insert(13);heap.print_heap_int();
        heap.insert(12);heap.print_heap_int();
        heap.insert(17);
        heap.insert(18);
        heap.insert(35);heap.print_heap_int();
        heap.insert(38);heap.print_heap_int();
        heap.insert(40);heap.print_heap_int();
        heap.insert(8);heap.print_heap_int();
        heap.insert(71);heap.print_heap_int();
        heap.insert(41);heap.print_heap_int();
        heap.insert(31);heap.print_heap_int();
        heap.insert(10);heap.print_heap_int();
        heap.insert(11);heap.print_heap_int();
        heap.insert(16);heap.print_heap_int();
        heap.insert(46);heap.print_heap_int();


        heap.print_heap_int();
        heap.print_heap_int_array();


    }
}