import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * Digraph object.
     */
    private Digraph g;
    /**
     * hashtable.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> ht;
    /**
     * hashtable2.
     */
    private LinearProbingHashST<Integer, String> ht1;
     /**
      * int v variable.
      */
    private int v;
    /**
     * sap object.
     */
    private SAP sap;
    /**
     * boolean flag.
     */
    private boolean flag = false;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     * @throws     Exception   for exceptions
     */
    public WordNet(final String synsets,
        final String hypernyms) throws Exception {
        buildht(synsets);
        buildg(hypernyms);
    }
    /**
     * method for buildg.
     *
     * @param      hypernyms  The hypernyms
     *
     * @throws     Exception  { exception_description }
     */
    private void buildg(final String hypernyms)throws Exception {
        g = new Digraph(v);
        Scanner sc = new Scanner(new File(hypernyms));
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(",");
            if (tokens.length > 1) {
                for (int i = 1; i < tokens.length; i++) {
g.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
                }
            }
        }
        isrooteddigraph(g);
        iscycle(g);
    }
    /**
     * method for a flag.
     *
     * @return     { description_of_the_return_value }
     */
    private boolean isflag() {
        return flag;
    }
    /**
     * method to check a cycle.
     *
     * @param      g1    { parameter_description }
     */
    private void iscycle(final Digraph g1) {
        DirectedCycle obj = new DirectedCycle(g1);
        if (obj.hasCycle()) {
            System.out.println("Cycle detected");
            flag = true;
            return;
        }
    }
    /**
     * method for rooted graph.
     *
     * @param      g2     { parameter_description }
     */
    private void isrooteddigraph(final Digraph g2) {
        int count = 0;
        for (int i = 0; i < g2.V(); i++) {
            if (g2.outdegree(i) == 0) {
                count++;
            }
            if (count > 1) {
                System.out.println("Multiple roots");
                flag = true;
                return;
            }
        }
    }
    /**
     * method for buildht.
     *
     * @param      synsets    The synsets
     *
     * @throws     Exception  { exception_description }
     */
    private void buildht(final String synsets) throws Exception {
        ht = new LinearProbingHashST<String, ArrayList<Integer>>();
        ht1 = new LinearProbingHashST<Integer, String>();
        Scanner sc = new Scanner(new File(synsets));
        while (sc.hasNextLine()) {
            String[] tokens = sc.nextLine().split(",");
            ht1.put(Integer.parseInt(tokens[0]), tokens[1]);
            String[] input = tokens[1].split(" ");
            for (int i = 0; i < input.length; i++) {
                if (ht.contains(input[i])) {
                   ArrayList<Integer> list = ht.get(input[i]);
                   list.add(Integer.parseInt(tokens[0]));
                   ht.put(input[i], list);
                } else {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                   list.add(Integer.parseInt(tokens[0]));
                   ht.put(input[i], list);
                }
            }
            v++;
        }
    }

    /**
     * returns all WordNet nouns.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> nouns() {
        return null;
    }

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(final String word) {
        return false;
    }

    /**
     * method for distance.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public int distance(final String nounA, final String nounB) {
        sap = new SAP(g);
        int dist = sap.length(ht.get(nounA), ht.get(nounB));
        return dist;
    }
    /**
     * method for sap.
     *
     * @param      nounA  The noun a
     * @param      nounB  The noun b
     *
     * @return     { description_of_the_return_value }
     */
    public String sap(final String nounA, final String nounB) {
         sap = new SAP(g);
        String str = "";
        int id = sap.ancestor(ht.get(nounA), ht.get(nounB));
        return ht1.get(id);
    }
    /**
     * method for printing graph.
     */
    public void print() {
        System.out.println(g);
    }

    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String file1 = "Files" + "\\" + sc.nextLine();
        String file2 = "Files" + "\\" + sc.nextLine();
        String input = sc.nextLine();
        try {
            WordNet obj = new WordNet(file1, file2);
            if (input.equals("Graph")) {
                if (!obj.isflag()) {
                    obj.print();
                }
            } else if (input.equals("Queries")) {
                while (sc.hasNextLine()) {
                    String[] tokens = sc.nextLine().split(" ");
                    String str = obj.sap(tokens[0], tokens[1]);
                    int dis = obj.distance(tokens[0], tokens[1]);
            System.out.println("distance = " + dis + ", ancestor = " + str);
                }
            }
        } catch (Exception e) {
           System.out.println("IllegalArgumentException");
        }

    }
}
