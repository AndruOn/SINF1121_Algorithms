import java.util.HashSet;
import java.util.Set;

public class DigraphImplem implements Digraph {
    private final int V;
    private int E;
    private Set<Integer>[] adj;

    public DigraphImplem(int V) {
        this.V = V;
        this.E = 0;
        this.adj = new HashSet[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new HashSet<>();
        }
    }

    /**
     * The number of vertices
     */
    public int V() {
        return V;
    }

    /**
     * The number of edges
     */
    public int E() {
        return E;
    }

    /**
     * Add the edge v->w
     */
    public void addEdge(int v, int w) {
        if(!adj[v].contains(w)){
            adj[v].add(w);
            E++;
        }
    }

    /**
     * The nodes adjacent to node v
     * that is the nodes w such that there is an edge v->w
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /**
     * A copy of the digraph with all edges reversed
     */
    public Digraph reverse() {
        Digraph R = new DigraphImplem(V);
        for (int v = 0; v < V; v++)
            for (int w : adj(v))
                R.addEdge(w, v);
        return R;
    }


}
