/**graphimplementation.**/
interface Graph {
    /**.
     * method
     * @return value
     */
    int vertex();
    /**.
     * method
     * @return value
     */
    int edge();
    /**.
     * method
     * @param v value
     * @param w value
     */
    void addEdge(int v, int w);

    // public Iterable<Integer> adj(int v);
    //public boolean hasEdge(int v, int w);
}
/**graphimplementation.**/
public class Graphs implements Graph {
    /**
     * variable.
     */
    private final int vertices;
    /**
     * variable.
     */
    private int edges;
    /**
     * variable.
     */
    private int size;
    /**
     * variable.
     */
    private Bag<Integer>[] adj;
    /**
     * variable.
     */
    //private String[] vertex;
    /**
     * @brief [brief description]
     * @details [long description]
     * @param vert value.
     */
     public Graphs(final int vert) {
        if (vert < 0) {
        throw new IllegalArgumentException(
            "Number of vertices must be nonnegative");
    }
        this.vertices = vert;
        this.edges = 0;
        adj = (Bag<Integer>[]) new Bag[vert];
        //vertex = new String[vert];
        for (int v = 0; v < vert; v++) {
            adj[v] = new Bag<Integer>();
        }
        size = 0;

    }
    /**
     * @param v value
     * @return value
     */
    public Iterable<Integer> adj(int v) {
        //validateVertex(v);
        return adj[v];
    }

    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @return value
     */
     public int vertex() {
        return vertices;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @return value
     */
     public int edge() {
        return edges;
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param v value
     * @param w value
     */
    public void addEdge(final int v, final int w) {
        edges++;
        adj[v].add(w);
       adj[w].add(v);
    }
    /**
     * @brief [brief description]
     * @details [long description]
     * Time complexity is O(1)
     * @param s value
     */
    public boolean hasEdge(int v,int w) {
        for(int i : adj[w]) {
            if(i == w) {
                return true;
            }
        }
        return false;
    }
}
