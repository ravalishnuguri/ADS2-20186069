import java.util.*;
import java.io.*;
public class WordNet {
    Digraph g;
    LinearProbingHashST<String, ArrayList<Integer>> ht;
     LinearProbingHashST<Integer, String> ht1;
    int v;
    SAP sap;
    boolean flag = false;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) throws Exception{
        buildht(synsets);
        buildg(hypernyms);
    }

    private void buildg(String hypernyms)throws Exception{
        g = new Digraph(v);
        Scanner sc = new Scanner(new File(hypernyms));
        while(sc.hasNextLine()){
            String[] tokens = sc.nextLine().split(",");
            if(tokens.length > 1){
                for(int i = 1; i < tokens.length; i++){
                    g.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
                }
            }
        }
        isrooteddigraph(g);
        iscycle(g);
    }
    private boolean isflag(){
        return flag;
    }
    private void iscycle(Digraph g){
        DirectedCycle obj = new DirectedCycle(g);
        if(obj.hasCycle()){
            System.out.println("Cycle detected");
            flag = true ;
            return;
        }
    }
    private void isrooteddigraph(Digraph g){
        int count = 0;
        for(int i = 0; i < g.V(); i++){
            if(g.outdegree(i) == 0){
                count++;
            }
            if(count>1){
                System.out.println("Multiple roots");
                flag = true ;
                return;
            }
        }
    }

    private void buildht(String synsets)throws Exception{
        ht = new LinearProbingHashST<String, ArrayList<Integer>>();
        ht1 = new LinearProbingHashST<Integer, String>();
        Scanner sc = new Scanner(new File(synsets));
        while(sc.hasNextLine()){
            String[] tokens = sc.nextLine().split(",");
            ht1.put(Integer.parseInt(tokens[0]), tokens[1]);
            String[] input = tokens[1].split(" ");
            for(int i = 0; i < input.length; i++){
                if(ht.contains(input[i])){
                   ArrayList<Integer> list = ht.get(input[i]);
                   list.add(Integer.parseInt(tokens[0]));
                   ht.put(input[i], list);
                }else{
                    ArrayList<Integer> list = new ArrayList<Integer>();
                   list.add(Integer.parseInt(tokens[0]));
                   ht.put(input[i], list);
                }

            }
            v++;
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns(){
        return null;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word){
        return false;
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB){
        sap = new SAP(g);
        int dist = sap.length(ht.get(nounA), ht.get(nounB));
        return dist;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB){
         sap = new SAP(g);
        String str = "";
        int id = sap.ancestor(ht.get(nounA), ht.get(nounB));
        return ht1.get(id);
    }

    public void print(){
        System.out.println(g);
    }

    // do unit testing of this class
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String file1 = "Files"+"\\" + sc.nextLine();
        String file2 = "Files"+"\\" + sc.nextLine();
        String input = sc.nextLine();
        try{
            WordNet obj = new WordNet(file1, file2);
            if(input.equals("Graph")){
                if(obj.isflag() == false) obj.print();
            }
            else if(input.equals("Queries")){
                while(sc.hasNextLine()){
                    String[] tokens = sc.nextLine().split(" ");
                    String str = obj.sap(tokens[0], tokens[1]);
                    int dis = obj.distance(tokens[0], tokens[1]);
                    System.out.println("distance = "+dis+", ancestor = " + str);
                }
            }
        }catch(Exception e){
           System.out.println("IllegalArgumentException");;
        }

    }
}




