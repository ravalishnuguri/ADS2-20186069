/**
 *class for edge.
 */
class Edge implements Comparable<Edge> {
    /**
     *the variable to store.
     *vetexOne
     */
    private int vertexOne;
    /**
     *the variable to store.
     *other vertex.
     */
    private int vertexTwo;
    /**
     *the variable to store the weight of.
     *each edge.
     */
    private double weight;
    /**
     *the constructor to initialize the.
     *vertices and their edge weight
     * @param      v  vertexOne
     * @param      w  vertexTwo
     * @param      cost  weight of edge
     */
    Edge(final int v, final int w,
         final double cost) {
        this.vertexOne = v;
        this.vertexTwo = w;
        this.weight = cost;
    }
    /**
     *this method returns the weight of edge.
     *
     * @return  weight of edge
     */
    public double weight() {
        return this.weight;
    }
    /**
     *this method returns one vertex.
     *
     * @return  one end of edge.
     */
    public int either() {
        return vertexOne;
    }
    /**
     *returns the other end of vertex.
     *
     * @param      v already connected vertex
     *
     * @return another vertex
     */
    public int other(final int v) {
        if (v == vertexOne) {
            return vertexTwo;
        } else {
            return vertexOne;
        }
    }
    /**
     *
     *the method is to compare the two weights.
     *of edges.
     * @param      that  another edge
     *
     * @return  if this edge is  greater than that.
     * returns 1.
     * equal returns zero.
     */
    public int compareTo(final Edge that) {
        if (this.weight < that.weight) {
            return -1;
        } else if (this.weight > that.weight) {
            return 1;
        } else {
            return 0;
        }
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        return String.format("%d-%d %.5f",
         vertexOne, vertexTwo, weight);
    }
}
/**
 * Class for edge weighted graph.
 */
class EdgeWeightedGraph {
    /**
     *the variable to store vertices.
     */
    private int vertices;
    /**
     *the variable to store edges.
     */
    private int edges;
    /**
     *the array of bags to store.
     *complete adjacency list.
     */
    private Bag<Edge>[] adj;
    /**
     *the constructor to initialize.
     *
     * @param      v vertices count
     * @param      e edges count
     */
    EdgeWeightedGraph(final int v, final int e) {
        this.vertices = v;
        this.edges = e;
        adj = (Bag<Edge>[]) new Bag[vertices];
        for (int i = 0; i < vertices; i++) {
            adj[i] = new Bag<Edge>();
        }
    }
    /**
     *this method returns the vertices count.
     *
     * @return  number of vertices.
     */
    public int vertices() {
        return this.vertices;
    }
    /**
     *this method is to build the graph.
     *
     * @param      edge  The edge
     */
    public void addEdge(final Edge edge) {
        int vertexOne = edge.either();
        int vertexTwo = edge.other(vertexOne);
        adj[vertexOne].add(edge);
        adj[vertexTwo].add(edge);
    }
    /**
     *returns the iterator to return the bag at that.
     *vertex.
     * @param      vertex  The vertex
     *time complexity is O(V)
     * @return iterator at that vertex.
     */
    public Iterable<Edge> adj(final int vertex) {
        return adj[vertex];
    }
    /**
     *returns all the edges in graph.
     *time complexity is O(E)
     * @return bag of all the edges.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " vertices " + edges + " edges" + "\n");
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }
 }
