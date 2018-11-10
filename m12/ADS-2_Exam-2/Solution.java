import java.util.Scanner;
/**
 * code for Solution class.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
    }
    /**
     * main method for the Solution class
     * time complexity is O(E + E).
     * E is the edges.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int v = Integer.parseInt(s.nextLine());
        int e = Integer.parseInt(s.nextLine());
        Edge edge;
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(v, e);
        for (int i = 0; i < e; i++) {
            String[] tokens = s.nextLine().split(" ");
            edge = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            ewg.addEdge(edge);
        }
        String caseToGo = s.nextLine();
        switch (caseToGo) {
        case "Graph":
            System.out.println(ewg);
            break;

        case "DirectedPaths":
            String[] input = s.nextLine().split(" ");
            DijkstrasSP dj
            = new DijkstrasSP(ewg, Integer.parseInt(input[0]));
            if (dj.hasPathTo(Integer.parseInt(input[1]))) {
                System.out.println(dj.distance(Integer.parseInt(input[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        case "ViaPaths":
            String[] viapath = s.nextLine().split(" ");
        DijkstrasSP dj1 = new DijkstrasSP(ewg, Integer.parseInt(viapath[0]));
            if (dj1.hasPathTo(Integer.parseInt(viapath[2])) && dj1.hasPathTo
                (Integer.parseInt(viapath[1]))) {
            System.out.println(dj1.distance(Integer.parseInt(viapath[2])));
                dj1.print(Integer.parseInt(viapath[2]));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}
