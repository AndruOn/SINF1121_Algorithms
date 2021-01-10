public class Huffman {
    private Huffman() { }

    // Huffman trie node
    public static class Node implements Comparable<Node>{
        public final int ch;
        public final int freq;
        public final Node left, right;

        Node(int ch, int freq, Node left, Node right) {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.freq - o.freq;
        }

        // is the node a leaf node?
        public boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return (left == null) && (right == null);
        }
    }

    /**
     * build the Huffman trie given frequencies
     * corresponding to each character codes from 0 to R-1.
     * freq[i] is the frequency for the character with code i
     * freq.length = R.
     * R is either 256 or 65536, depending on whether the user choose to use unicode or ASCII.
     */
    public static Node buildTrie(int R, int[] freq) {
        // TODO
        return new Node(0,0,null,null);
    }
}
