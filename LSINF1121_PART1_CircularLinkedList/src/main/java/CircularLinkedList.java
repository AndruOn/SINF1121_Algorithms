import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node node= new Node();
        node.item = item;

        if (n == 0) {
            last = node;
            node.next = last;
        } else {
            node.next = last.next;
            last.next = node;
            last = node;
        }

        n++;

    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if (index < 0 || index > size()-1) {throw new IndexOutOfBoundsException();}
        if (size() == 1) {
            Item item = last.item;
            last = null;
            n = 0;
            return item;
        }
        Node prev = last;
        Node current = last.next;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
            current = current.next;
        }
        Item item = current.item;
        prev.next = current.next;
        if(index==size()){
            last= prev;
        }
        n--;
        return item;
    }



    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = last;
        private final long len = size();
        private int i =0;

        public boolean failfastCheck(){
            return len != size();
        }

        @Override
        public boolean hasNext() {
            return i < len;
        }

        @Override
        public Item next() {
            if (failfastCheck()) {
                throw new ConcurrentModificationException();
            }
            if (hasNext()) {
                current = current.next;
                i++;
                return current.item;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}