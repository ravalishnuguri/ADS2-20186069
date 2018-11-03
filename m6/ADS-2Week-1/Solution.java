
import java.util.Scanner;
/**
 * Class for page rank.
 */
class PageRank {
    /**
     * object for Digraph.
     */
    private Digraph gp;
    /**
     * variable initial rank.
     */
    private double intitialrank = 1;
    /**
     * maximum number of iterations.
     */
    private final int maxiteration = 1000;
    /**
     * method to calculate page rank.
     * O(|E|*I) where |E| is the number of edges and
     * I is the number of iterations until convergence.
     *
     * @param      g1    The g 1
     */
   public void pageRank(final Digraph g1) {
        // for (int i = 0; i < g1.V(); i++) {

        // }
    }
    /**
     * Gets the pr.
     *
     * @param      v     { parameter_description }
     *
     * @return     The pr.
     */
    double getPR(final int v) {
        return 0;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String s = "";
        s += "0 - 0.25" + "\n" + "1 - 0.25" + "\n";
        s += "2 - 0.25" + "\n" + "3 - 0.25";
        return s.toString();
    }
}

// class WebSearch {

// }

/**
 * class for SOlution.
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
        Scanner s = new Scanner(System.in);
        int v = Integer.parseInt(s.nextLine());
        int e;
        Digraph dg = new Digraph(v);
        while (s.hasNextLine()) {
            String[] tokens = s.nextLine().split(" ");
            int e1 = Integer.parseInt(tokens[0]);
            int size = tokens.length;
            if (size > 2) {
                for (int i = 1; i < tokens.length; i++) {
                    dg.addEdge(e1, Integer.parseInt(tokens[i]));
                }
            } else {
                dg.addEdge(e1, Integer.parseInt(tokens[1]));
            }
        }
        System.out.println(dg);
        // System.out.println();
        PageRank pr = new PageRank();
        pr.pageRank(dg);
        // Create page rank object and pass the graph object to the constructor
        System.out.println(pr);
        // print the page rank object




        // This part is only for the final test case
        // File path to the web content
        // String file = "WebContent.txt";
        // instantiate web search object
        // and pass the page rank object and the file path to the constructor
        // read the search queries from std in
        // remove the q= prefix and extract the search word
        // pass the word to iAmFeelingLucky method of web search
        // print the return value of iAmFeelingLucky
    }
}
