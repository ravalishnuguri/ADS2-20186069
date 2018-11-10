import java.util.Scanner;
final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int v = Integer.parseInt(s.nextLine());
        int e = Integer.parseInt(s.nextLine());
        Edge edge;
        EdgeWeightedGraph ewg
        = new EdgeWeightedGraph(v, e);
        for(int i = 0; i < e; i++) {
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
            if(dj.hasPathTo(Integer.parseInt(input[1]))) {
                System.out.println(dj.distance(Integer.parseInt(input[1])));
            } else {
                System.out.println("No Path Found.");
            }
            break;
        case "ViaPaths":
            // Handle the case of ViaPaths, where three integers are given.
            // First is the source and second is the via is the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            break;

        default:
            break;
        }

    }
}