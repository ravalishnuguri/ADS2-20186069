import java.util.Scanner;
class PageRank {
	
}

class WebSearch {

}


public class Solution {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int v = Integer.parseInt(s.nextLine());
		int e;
		Digraph dg = new Digraph(v);
		while(s.hasNextLine()) {
			String[] tokens = s.nextLine().split(" ");
			int e1 = Integer.parseInt(tokens[0]);
			int size = tokens.length;
			if (size > 2) {
				for(int i = 1; i < tokens.length; i++) {
					dg.addEdge(e1, Integer.parseInt(tokens[i]));
				}
			} else {
				dg.addEdge(e1, Integer.parseInt(tokens[1]));
			}
		}
		System.out.println(dg);

		// read the first line of the input to get the number of vertices

		// iterate count of vertices times 
		// to read the adjacency list from std input
		// and build the graph
		
		
		// Create page rank object and pass the graph object to the constructor
		
		// print the page rank object
		
		// This part is only for the final test case
		
		// File path to the web content
		String file = "WebContent.txt";
		
		// instantiate web search object
		// and pass the page rank object and the file path to the constructor
		
		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky
		
	}
}
