import java.util.*;

public class BreadthFirstShortestPaths {
    public static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked; // marked[v] = is there an s-v path
    private int[] distTo;     // distTo[v] = number of edges shortest s-v path

    /**
     * Computes the shortest path between any
     * one of the sources and very other vertex
     * @param G the graph
     * @param sources the source vertices
     */
    public BreadthFirstShortestPaths(Graph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        for (int v = 0;v < G.V();v++) {
            distTo[v] = INFINITY;
        }
        bfs(G, sources);
    }

    // Breadth-first search from multiple sources
    private void bfs(Graph G, Iterable<Integer> sources) {
        Queue<Integer> queue = new LinkedList<>();
        for (Integer source : sources) {
            marked[source] = true;
            distTo[source] = 0;
            queue.add(source);
        }
        while (!queue.isEmpty()) {
            int v = queue.poll(); // Remove next vertex from the queue.
            for (int w : G.adj(v))
                if (!marked[w]) { //for every unmarked adjacent vertex,
                    distTo[w] = distTo[v] + 1; //save last edge on a shortest path,
                    marked[w] = true; // mark it because path is known,
                    queue.add(w); // and add it to the queue.
                }
        }
    }



    /**
     * Is there a path between (at least one of) the sources and vertex v?
     * @param v the vertex
     * @return true if there is a path, and false otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    /**
     * Returns the number of edges in a shortest path
     * between one of the sources and vertex v?
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        return distTo[v];
    }
}
