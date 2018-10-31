import java.util.Scanner;
class Solution {
	private Solution() { }
	public static void main(String[] args) {
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
		Cycle finder = new Cycle(gp);
		if (finder.hasCycle()) {
            for (int v : finder.cycle()) {
               System.out.println(v + " ");
            }
            System.out.println();
        }
	}
}