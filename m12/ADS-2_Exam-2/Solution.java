import java.util.Scanner;
final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner scan = new Scanner(System.in);
		int vertices = Integer.parseInt(scan.nextLine());
		int edges = Integer.parseInt(scan.nextLine());
		Edge edgeObj;
		EdgeWeightedGraph graph
		= new EdgeWeightedGraph(vertices, edges);
		for(int i = 0; i < edges; i++) {
			String[] tokens = scan.nextLine().split(" ");
            edgeObj = new Edge(Integer.parseInt(tokens[0]),
                               Integer.parseInt(tokens[1]),
                               Double.parseDouble(tokens[2]));
            graph.addEdge(edgeObj);
		}
		String caseToGo = scan.nextLine();
		switch (caseToGo) {
		case "Graph":
			System.out.println(graph);
			break;

		case "DirectedPaths":
			// String[] check = scan.nextLine().split(" ");
			// DijkstrasSP disp
			// = new DijkstrasSP(graph, Integer.parseInt(check[0]));
			// if(disp.hasPathTo(Integer.parseInt(check[1]))) {
			// 	System.out.println(disp.distance(Integer.parseInt(check[1])));
			// } else {
			// 	System.out.println("No Path Found.");
			// }
			// break;
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