import java.util.Scanner;
/**.
Solution the class
*/
final class Solution {
/**
 * Constructs the object.
 */
    private Solution() {
    }
/**
 * Main method for Solution.
 * Time compleity is O(N)
 * N is the number of inputs
 *
 * @param      args  The arguments
 */
public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        int n = Integer.parseInt(s.nextLine());
        Percolation p = new Percolation(n);
        while (s.hasNextLine()) {
        String[] tokens = s.nextLine().split(" ");
        p.open(Integer.parseInt(tokens[0]) - 1,
            Integer.parseInt(tokens[1]) - 1);
    }
    System.out.println(p.percolates());
    }
}

