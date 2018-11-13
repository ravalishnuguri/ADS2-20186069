import java.util.Scanner;
/**.
Solution the solution class
*/
final class Solution {
/**.
@Solution() the constructor
*/
    protected Solution() {
    }
/**.
@param args the command line argumnets
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
/**.
loadwords() the method that loads words from the file
@return the return for words
*/
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}