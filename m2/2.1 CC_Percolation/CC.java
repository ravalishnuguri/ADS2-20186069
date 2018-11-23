public class CC {
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public CC(Graphs G) {
        marked = new boolean[G.vertex()];
        id = new int[G.vertex()];
        size = new int[G.vertex()];
        for (int v = 0; v < G.vertex(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * the method is to perform the depth.
     * first search.
     * time complexity is O(E)
     * E is the edges.
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(Graphs G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    /**
     * { function_description }.
     * time complexity is O(1).
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int id(int v) {

        return id[v];
    }
    /**
     * time complexity is O(1).
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size(int v) {

        return size[id[v]];
    }

    /**
     * time complexity is O(1).
     *
     * @return     { description_of_the_return_value }
     */
    public int count() {
        return count;
    }
    /**
     * time complexity is O(E).
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean connected(int v, int w) {

        return id(v) == id(w);
    }
    /**
     * time complexity is O(E).
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean areConnected(int v, int w) {
        return id(v) == id(w);
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}