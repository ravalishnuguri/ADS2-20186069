import java.util.Scanner;
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
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner in = new Scanner(System.in);
        int ve = Integer.parseInt(in.nextLine());
        Graph gp = new Graph(ve);
        int ed = Integer.parseInt(in.nextLine());
        for (int i = 0; i < ed; i++) {
            String[] inputs = in.nextLine().split(" ");
            int v = Integer.parseInt(inputs[0]);
            int w = Integer.parseInt(inputs[1]);
            gp.addEdge(v, w);
        }
        Bipartite b = new Bipartite(gp);
        if (b.isBipartite()) {
            System.out.println("Graph is bipartite");
        } else {
            System.out.println("Graph is not a bipartite");
        }
    }
}
