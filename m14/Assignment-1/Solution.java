import java.util.Scanner;
/**.
solution class
*/
final class Solution {
/**
 * Constructs the object.
 */
    protected Solution() {
    }
/**
 *the time complexity is O(M)
 *M is number of words.
 * main method for the Solution class.
 *
 * @param      args  The arguments
 */
    public static void main(final String[] args) {
        String[] words = loadWords();
        //Your code goes here...
        Scanner scan = new Scanner(System.in);
        String prefix = scan.nextLine();
        TST tst = new TST();
        for (int i = 0; i < words.length; i++) {
            String[] inputs = new String[words[i].length()];
            for (int j = 0; j < words[i].length(); j++) {
                inputs[j] = words[i].substring(j, words[i].length());
                tst.put(inputs[j], 0);
            }
        }
        System.out.println(tst.keysWithPrefix(prefix));
    }
/**
 * Loads words.
 *
 * @return     { description_of_the_return_value }
 */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}

