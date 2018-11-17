import java.util.Scanner;
/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }

    /**
     * main method for Solution.
     * Complexity : O(n) for n inputs
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String cases = scan.nextLine();
        switch (cases) {
        case "loadDictionary":
            // input000.txt and output000.txt
    BinarySearchST<String, Integer> hash = loadDictionary("/Files/t9.csv");
            while (scan.hasNextLine()) {
                String key = scan.nextLine();
                System.out.println(hash.get(key));
            }
            break;

        case "getAllPrefixes":
            // input001.txt and output001.txt
            T9keypad t9 = new T9keypad(loadDictionary("/Files/t9.csv"));
            while (scan.hasNextLine()) {
                String prefix = scan.nextLine();
                for (String each : t9.getAllWords(prefix)) {
                    System.out.println(each);
                }
            }
            break;

        case "potentialWords":
            // input002.txt and output002.txt
            t9 = new T9keypad(loadDictionary("/Files/t9.csv"));
            int count = 0;
            while (scan.hasNextLine()) {
                String t9Signature = scan.nextLine();
                for (String each : t9.potentialWords(t9Signature)) {
                    count++;
                    System.out.println(each);
                }
            }
            if (count == 0) {
                System.out.println("No valid words found.");
            }
            break;
        case "topK":
            // input003.txt and output003.txt
            t9 = new T9keypad(loadDictionary("/Files/t9.csv"));
            Bag<String> bag = new Bag<String>();
            int k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                bag.add(line);
            }
            for (String each : t9.getSuggestions(bag, k)) {
                System.out.println(each);
            }

            break;

        case "t9Signature":
            // input004.txt and output004.txt
            t9 = new T9keypad(loadDictionary("/Files/t9.csv"));
            bag = new Bag<String>();
            k = Integer.parseInt(scan.nextLine());
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                for (String each : t9.t9(line, k)) {
                    System.out.println(each);
                }
            }
            break;
        default:
            break;

        }
    }

    /**
     * don't modify this method.
     * Complexity : O(n) for n number of lines
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] toReadFile(final String file) {
        In in = new In(file);
        return in.readAllStrings();
    }
    /**
     * Loads a dictionary.
     * Complexity : O(n) for n number of lines
     *
     * @param      file  The file
     *
     * @return     { description_of_the_return_value }
     */
    public static BinarySearchST<String,
    Integer> loadDictionary(final String file) {
        BinarySearchST<String, Integer>  st = new BinarySearchST<String,
        Integer>();
        // your code goes here
        String[] k = toReadFile(file);
        int size = k.length;
        for (int i = 0; i < size; i++) {
            String text = k[i].toLowerCase();
            if (st.contains(text)) {
                st.put(text, st.get(text) + 1);
            } else {
                st.put(text, 1);
            }
        }
        return st;
    }
}
/**
 * Class for t 9.
 */
class T9keypad {
    /**
     * creating object for TST.
     */
    private TST<Integer> tst;
    /**
     * Constructs the object.
     *
     * @param      st    { parameter_description }
     */
    T9keypad(final BinarySearchST<String, Integer> st) {
        // your code goes here
        tst = new  TST<Integer>();
        for (String i: st.keys()) {
            tst.put(i, st.get(i));
        }
    }

    /**
     * get all the prefixes that match with given prefix.
     *
     * @param      prefix  The prefix
     *
     * @return     All words.
     */
    public Iterable<String> getAllWords(final String prefix) {
        // your code goes here
        return tst.keysWithPrefix(prefix);
    }
    /**
     * method for the potential words.
     *
     * @param      t9Signature  The t 9 signature
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<String> potentialWords(final String t9Signature) {
        // your code goes here
        return tst.keysWithPrefix(t9Signature);
    }

    /**
     * Gets the suggestions.
     *
     * @param      words  The words
     * @param      k      { parameter_description }
     *
     * @return     The suggestions.
     */
    public Iterable<String> getSuggestions(final Iterable<String> words,
        final int k) {
        // your code goes here
        return null;
    }

    /**
     * dont modify this method.
     *
     * @param      t9Signature  The t 9 signature
     * @param      k            parameter k
     *
     * @return     returns the entire testcase as one.
     */
    public Iterable<String> t9(final String t9Signature, final int k) {
        return getSuggestions(potentialWords(t9Signature), k);
    }
}
