import java.util.ArrayList;
import java.util.HashMap;
/**.
BoggleSolver class for boggle
*/
public class BoggleSolver {
/**.
dict the trie dictionary
*/
private Trie<Integer> dict;
/**.
board the boggleboard object
*/
private BoggleBoard board;
/**.
map the hashmap for storng the key
*/
private HashMap<String, Integer> map;
/**.
list for storing the final list of keys
*/
private ArrayList<String> list;
    /**
     * magic number.
     */
    private final int three = 3;
    /**
     * magic number.
     */
    private final int five = 5;
    /**
     * magic number.
     */
    private final int eleven = 11;
    /**
     * magic number.
     */
    private final int four = 4;
    /**
     * magic number.
     */
    private final int six = 6;
    /**
     * magic number.
     */
    private final int seven = 7;
/**
 * Constructs the object.
 * Complexity is O(l) l is length of dictionary.
 * @param      dictionary  The dictionary
 */
public BoggleSolver(final String[] dictionary) {
        dict = new Trie<>();
        for (int i = 0; i < dictionary.length; i++) {
            dict.put(dictionary[i], getScore(dictionary[i]));
        }
    }
/**
 * Gets all valid words.
 * Compelxity O(r*c) r:no: of rows, c: no: of cols.
 *
 * @param      board1  The board
 *
 * @return     All valid words.
 */
    public Iterable<String> getAllValidWords(final BoggleBoard board1) {
        this.board = board1;
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
        boolean[][] marked = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                verifyword(marked, "", i, j, 0);
            }
        }
        return list;
    }
/**
 * method for verifying the word.
 * Complexity O(1) : functional called for every cell once,
 * steps are executed for that cell, no loops.
 *
 * @param      array   The array
 * @param      prefix  The prefix
 * @param      i       parameter i
 * @param      j       parameter j
 * @param      count   The count
 */
    private void verifyword(final boolean[][] array, String prefix,
        final int i,
        final int j,
        int count) {
        char c = board.getLetter(i, j);
        if (c == 'Q') {
            prefix += "QU";
            count += 2;
        } else {
            prefix += c;
            count += 1;
        }
        array[i][j] = true;
        boolean temp = false;
        if (dict.contains(prefix)) {
            temp = true;
            if (count >= three && !map.containsKey(prefix)) {
                map.put(prefix, getScore(prefix));
                list.add(prefix);
            }
        }
            if (temp || dict.hasKey(prefix)) {
            if (j - 1 >= 0 && !array[i][j - 1]) {
                verifyword(array, prefix, i, j - 1, count);
            }
            if (j + 1 < board.cols() && !array[i][j + 1]) {
                verifyword(array, prefix, i, j + 1, count);
            }
            if (i - 1 >= 0) {
                if (j - 1 >= 0 && !array[i - 1][j - 1]) {
                    verifyword(array, prefix, i - 1, j - 1, count);
                }
                if (!array[i - 1][j]) {
                    verifyword(array, prefix, i - 1, j, count);
                }
                if (j + 1 < board.cols() && !array[i - 1][j + 1]) {
                    verifyword(array, prefix, i - 1, j + 1, count);
                }
            }
            if (i + 1 < board.rows()) {
                if (j - 1 >= 0 && !array[i + 1][j - 1]) {
                    verifyword(array, prefix, i + 1, j - 1, count);
                }
                if (!array[i + 1][j]) {
                    verifyword(array, prefix, i + 1, j, count);
                }
                if (j + 1 < board.cols() && !array[i + 1][j + 1]) {
                    verifyword(array, prefix, i + 1, j + 1, count);
                }
            }
        }
        array[i][j] = false;
    }
/**
 * Gets the score.
 * Complexity O(1) : score the word appropriately and return
 *
 * @param      word  The word
 *
 * @return     The score.
 */
    private int getScore(final String word) {
        if (word.length() <= 2) {
            return 0;
        }
        if (word.length() == three || word.length() == four) {
            return 1;
        } else if (word.length() == five) {
            return 2;
        } else if (word.length() == six) {
            return three;
        } else if (word.length() == seven) {
            return five;
        } else {
            return eleven;
        }
    }
/**
 * method to return the score of the word.
 * Complexity O(1) : finds the score and returns
 *
 * @param      word  The word
 *
 * @return     returns the Score.
 */
    public int scoreOf(final String word) {
        if (dict.contains(word)) {
            return dict.get(word);
        }
        return 0;
    }
}
