import java.util.Scanner;
import java.util.NoSuchElementException;
/**
 * Class for digraph.
 */
class Digraph {
    /**
     * variabel.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * variable V.
     */
    private final int ve;
    /**
     * variabel e.
     */
    private int ed;
    /**
     * variable bag.
     */
    private Bag<Integer>[] adj;
    /**
     * int array.
     */
    private int[] indegree;
    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  ver the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    Digraph(final int ver) {
        if (ver < 0) {
            throw new IllegalArgumentException("Number.");
        }
        this.ve = ver;
        this.ed = 0;
        indegree = new int[ver];
        adj = (Bag<Integer>[]) new Bag[ver];
        for (int v = 0; v < ver; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if the endpoints of any edge are not.
     * @throws IllegalArgumentException if the number of vertices or edges.
     * @throws IllegalArgumentException if the input stream is in the wrong.
     */
    Digraph(final Scanner in) {
        try {
            this.ve = Integer.parseInt(in.nextLine());
            if (ve < 0) {
                throw new IllegalArgumentException("number of.");
            }
            indegree = new int[ve];
            adj = (Bag<Integer>[]) new Bag[ve];
            for (int v = 0; v < ve; v++) {
                adj[v] = new Bag<Integer>();
            }
            int ed1 = Integer.parseInt(in.nextLine());
            if (ed1 < 0) {
                throw new IllegalArgumentException("number of");
            }
            for (int i = 0; i < ed1; i++) {
                String[] inputs = in.nextLine().split(" ");
                int v = Integer.parseInt(inputs[0]);
                int w = Integer.parseInt(inputs[1]);
                addEdge(v, w);
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid.", e);
        }
    }

    /**
     * Initializes a new digraph that is a deep copy of the specified digraph.
     *
     * @param  g the digraph to copy
     */
    Digraph(final Digraph g) {
        this(g.vertice());
        this.ed = g.edge();
        for (int v = 0; v < ve; v++) {
            this.indegree[v] = g.indegree(v);
        }
        for (int v = 0; v < g.vertice(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : g.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }
    /**
     * Returns the number of vertices in this digraph.
     *  time complexity is O(1).
     *
     * @return the number of vertices in this digraph
     */
    public int vertice() {
        return ve;
    }

    /**
     * Returns the number of edges in this digraph.
     *time complexity is O(1).
     *
     * @return the number of edges in this digraph
     */
    public int edge() {
        return ed;
    }
    /**
     * method to validate vertex.
     * time complexity is O(1).
     *
     * @param      v     { parameter_description }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= ve) {
            throw new IllegalArgumentException("verte");
        }
    }

    /**
     * Adds the directed edge v→w to this digraph.
     * time complexity is O(1).
     *
     * @param  v the tail vertex
     * @param  w the head vertex
     * @throws IllegalArgumentException unless.
     */
    public void addEdge(final int v, final int w) {
        validateVertex(v);
        validateVertex(w);
        adj[v].add(w);
        indegree[w]++;
        ed++;
    }

    /**
     * Returns the vertices adjacent from vertex {@code v} in this digraph.
     *time complexity is O(N).
     *
     * @param  v the vertex
     * @return the vertices adjacent from vertex.
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *time complexity is O(1).
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *time complexity is O(1).
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns the reverse of the digraph.
     *
     * @return the reverse of the digraph
     */
    public Digraph reverse() {
        Digraph reverse = new Digraph(ve);
        for (int v = 0; v < ve; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>.
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(ve + " vertices, " + ed + " edges " + NEWLINE);
        for (int v = 0; v < ve; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * main method.
     * time complexity is O(N).
     * N is the number of inputs.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        Digraph dg = new Digraph(in);
        // System.out.println(dg);
        Directedcycle finder = new Directedcycle(dg);
        if (finder.hasCycle()) {
            System.out.println("Cycle exists.");
        } else {
            System.out.println("Cycle doesn't exists.");
        }


    }
}
