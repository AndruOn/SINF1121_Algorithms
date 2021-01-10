/**
 * A binary heap where the content is placed in the array `content`.
 *
 * The array `content` represents a tree and is based on the methods we have seen in the course:
 * The ith node of the tree has indices 2*i and 2*i+1 as children.
 *
 * remarks:
 * - This heap uses a 1-indexing, ie. the first index in the array is 1 instead of 0 as usual. This could ease your computations.
 *   By this way, the index O of the array `content` must always stay empty.
 * - You can use the function increaseSize() to double the size of the array `content`, if needed.
 * - The expected complexity is O(log n) for the insertion in the heap.
 */
public class Heap {
    protected int[] content;
    protected int size;

    public Heap(int initSize) {
        size = 0;
        content = new int[initSize];
    }

    /**
     * Doubles the size of the inner storage array
     */
    protected void increaseSize() {
        int[] newContent = new int[content.length*2];
        System.arraycopy(content, 0, newContent, 0, content.length);
        content = newContent;
    }

    /**
     * Add a new value to the heap.
     * @param val value to add
     */
    public void pushWithSwim(int val) {
        //TODO
        //operation on this.content.
        //use increaseSize() if needed.
        if(size+2 == content.length){
            increaseSize();
        }
        content[++size ]=val;
        //this.print_heap(String.format("avant swim inserted:%d   ",val));
        swim(size);
        //this.print_heap("maxheap");
    }

    void swim(int k){
        if(size == 1){
            return;
        }
        while(k > 1 && content[k/2]>content[k]){
            int inter= content[k/2];
            content[k/2] = content[k];
            content[k] = inter;
            k = k/2;
        }
    }

    /**
     * Add a new value to the heap.
     * @param val value to add
     */
    public void push(int val) {

        if(size+2 == content.length){
            increaseSize();
        }
        content[++size ]=val;
        if(size == 1){
            return;
        }
        int k=size;
        while(k > 1 && content[k/2]>content[k]){
            int inter= content[k/2];
            content[k/2] = content[k];
            content[k] = inter;
            k = k/2;
        }
    }


    //You can add other functions if needed here

    /**
     * Returns the content of the inner array
     */
    public int[] getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }

    public void print_heap(String commentaire){
        Heap heap = this;
        System.out.print(commentaire + ": ");
        System.out.println(String.format("heap: size:%d",heap.getSize()));
        System.out.print(" [");
        for(int i = 1; i < heap.getSize()+1; i++) {
            System.out.print(String.format("%d, ",heap.getContent()[i]));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.push(5);
        heap.push(1);
        heap.push(2);
        heap.push(3);
        heap.push(8);
        heap.push(10);
        heap.push(6);
        heap.push(0);
        heap.push(220);
        heap.push(230);
        heap.push(20);

        int[] output = new int[]{0, 1, 2, 3, 8, 10, 6, 5};
        System.out.println(String.format("SIZE: actual:%d expected:%d",11, heap.getSize()));
        heap.print_heap("FINAL");
    }
}
