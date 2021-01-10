public class ConnectedComponents {
    private static boolean[] marked; // marked[v] = is there an s-v path?
    private static int nbComponents;


    /**
     * @return the number of connected components in g
     */
    public static int numberOfConnectedComponents(Graph g) {
        marked = new boolean[g.V()];
        nbComponents = 0;
        for (int i = 0; i < g.V(); i++) {
            if (!marked[i]) {
                dfs(g, i);
                nbComponents++;
            }
        }
        return nbComponents;
    }

    // Depth first search from v
    private static void dfs(Graph G, int v) {
        marked[v] = true;
        for (Integer vertex : G.adj(v)) {
            if (!marked[vertex]) {
                dfs(G, vertex);
            }
        }
    }

}