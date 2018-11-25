import java.util.*;

final class Solution {
	Solution() { }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of vertices and edges");
        int v = scan.nextInt();
        int e = scan.nextInt();
        int condition = (v * (v - 1)) / 2;
        if (e <= condition) {
            System.out.println("For " + v + " no. of vertices we have " + e + " number of edges");
            DigraphGenerator dg = new DigraphGenerator();
            System.out.println("Enter the number for the type of graph");
            int casenum = scan.nextInt();
            switch (casenum) {
                case 1:
                    KosarajuSharirSCC kscc = new KosarajuSharirSCC(dg.complete(v));
                    int components = kscc.count();
                    System.out.println("For complete graph, No. of strong components are " + components);
                    // break;
                case 2:
                    kscc = new KosarajuSharirSCC(dg.simple(v, e));
                    components = kscc.count();
                    System.out.println("For simple graph, No. of strong components are " + components);
                    // break;
                case 3:
                    kscc = new KosarajuSharirSCC(dg.path(v));
                    components = kscc.count();
                    System.out.println("For path graph, No. of strng components are " + components);
                    // break;
                case 4:
                    kscc = new KosarajuSharirSCC(dg.cycle(v));
                    components = kscc.count();
                    System.out.println("For cycle graph, No. of strng components are " + components);
                    // break;
                case 5:
                    kscc = new KosarajuSharirSCC(dg.eulerianPath(v, e));
                    components = kscc.count();
                    System.out.println("For Eulierian path graph, No. of strng components are " + components);
                    // break;
                case 6:
                    kscc = new KosarajuSharirSCC(dg.eulerianCycle(v, e));
                    components = kscc.count();
                    System.out.println("For Eulierian cycle graph, No. of strng components are " + components);
                    // break;
                case 7:
                    kscc = new KosarajuSharirSCC(dg.binaryTree(v));
                    components = kscc.count();
                    System.out.println("For binary tree, No. of strng components are " + components);
                    // break;
                case 8:
                    kscc = new KosarajuSharirSCC(dg.tournament(v));
                    components = kscc.count();
                    System.out.println("For tournament, No. of strng components are " + components);
                    // break;
                case 9:
                    kscc = new KosarajuSharirSCC(dg.dag(v, e));
                    components = kscc.count();
                    System.out.println("For DAG, No. of strng components are " + components);
                    // break;
                case 10:
                    kscc = new KosarajuSharirSCC(dg.rootedInDAG(v, e));
                    components = kscc.count();
                    System.out.println("For rooted-in DAG, No. of strng components are " + components);
                    // break;
                case 11:
                    kscc = new KosarajuSharirSCC(dg.rootedOutDAG(v, e));
                    components = kscc.count();
                    System.out.println("For rooted-out DAG, No. of strng components are " + components);
                    // break;
                case 12:
                    kscc = new KosarajuSharirSCC(dg.rootedInTree(v));
                    components = kscc.count();
                    System.out.println("For rooted-in tree, No. of strng components are " + components);
                    // break;
                case 13:
                    kscc = new KosarajuSharirSCC(dg.rootedOutTree(v));
                    components = kscc.count();
                    System.out.println("For rooted-out tree, No. of strng components are " + components);
                    break;
                default:
                    System.out.println("Wrong case number");
                    break;
            } 
        } else {
            System.out.println("Number of edges for vertices " + v + " must be less than " + condition);
        }
    }
}