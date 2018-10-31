/**
 * Class for directedcycle.
 */
public class Directedcycle {
    /**
     * boolean array marked.
     */
    private boolean[] marked;
    /**
     * int array edgeto.
     */
    private int[] edgeTo;
    /**
     * boolean array onStack.
     */
    private boolean[] onStack;
    /**
     * Stack with integer generic.
     */
    private Stack<Integer> cycle;

    /**
     * Determines whether the digraph {@code G} has a directed cycle and.
     * finds such a cycle.
     * @param G the digraph
     */
    public Directedcycle(final Digraph g) {
        marked  = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo  = new int[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }

    // check that algorithm computes either the topological order or.
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                assert check();
            }
        }
        onStack[v] = false;
    }

    /**
     * Does the digraph have a directed cycle?
     * @return {@code true} if the digraph has a directed cycle.
     */
    public boolean hasCycle() {
        return cycle != null;
    }

    /**
     * Returns a directed cycle if the digraph has a directed cycle.
     * @return a directed cycle (as an iterable).
     *    and {@code null} otherwise
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }


    // certify that digraph has a directed cycle if it reports one
    private boolean check() {

        if (hasCycle()) {
            // verify cycle
            int first = -1, last = -1;
            for (int v : cycle()) {
                if (first == -1) {
                    first = v;
                }
                last = v;
            }
            if (first != last) {
                System.err.printf("cycle", first, last);
                return false;
            }
        }
        return true;
    }
}
