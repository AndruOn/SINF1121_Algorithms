import java.util.*;

public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node root;             // root of BST

    private class Node {
        private final Key key;       // sorted by key
        private Value val;           // associated data
        private Node left, right;    // left and right subtrees
        private int size;            // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public BST() {}

    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return size(root);
    }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     * Search for key, update value if key is found. Grow table if key is new.
     *
     * @param  key the key
     * @param  val the value
     */
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }







    /**
     * Returns an iterator that iterates through the keys in Incresing order
     * (In-Order transversal).
     * @return an iterator that iterates through the items in FIFO order.
     */
    @Override
    public Iterator<Key> iterator() {
        return new BSTIterator();
    }

    // TODO STUDDENT: Implement the BSTIterator class

    // an iterator, doesn't implement remove() since it's optional
    /**/
    private class BSTIterator implements Iterator<Key> {

        private int size;

        Stack<Node> stack;

        public BSTIterator() {
            // TODO COMPLEXITY EXPECTED: O(h) h is the height of the BST
            stack = new Stack<Node>();
            BST<Key, Value>.Node current = root;
            if (root != null) {
                size = root.size;
            }
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
        }

        public boolean hasNext() {
            // TODO COMPLEXITY EXPECTED: O(1)
            if (root != null && root.size != size) {
                throw new ConcurrentModificationException();
            }
            return !stack.empty();
        }

        public Key next() {
            // TODO COMPLEXITY EXPECTED: O(h) h is the height of the BST
            if (!hasNext()) throw new NoSuchElementException();

            Node node = stack.pop();
            if (node.right != null) {
                stack.push(node.right);
                Node temp = node.right.left;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return node.key;
        }
    }


        public static void main(String[] args) {
        /*
        BST<Integer,Integer> bst = new BST();
        Iterator<Integer> iter = bst.iterator();
        System.out.println("premier bst vide");
        while (iter.hasNext()){
            Integer i = iter.next();
            System.out.println(String.format("%d, %d\n", i, bst.get(i)));
        }


         */

        int size = 10;
        BST<Integer,Integer> b = new BST();
        System.out.println("deuxieme i->size");
        b.put(12,12);
        b.put(5,5);
        b.put(10,10);
        b.put(3,3);
        b.put(13,13);
        b.put(14,14);
        b.put(15,15);
        b.put(17,17);
        b.put(18,18);
        Iterator<Integer> iter2 = b.iterator();
        while (iter2.hasNext()){
            Integer i = iter2.next();
            System.out.println(String.format("%d, %d", i, b.get(i)));
        }
    }
}