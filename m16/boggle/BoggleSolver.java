import java.util.ArrayList;
import java.util.HashMap;

public class BoggleSolver {
    private Trie<Integer> dict;

    private BoggleBoard board;
    private HashMap<String, Integer> map;

    private ArrayList<String> list; 

    public BoggleSolver(String[] dictionary) {
        dict = new Trie<>();
        for (int i = 0; i < dictionary.length; i++) {
            dict.put(dictionary[i], getScore(dictionary[i]));
        }
    }
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        this.board = board;
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
    private void verifyword(boolean[][] array, String prefix,
        int i,
        int j,
        int count) {
        char c = board.getLetter(i, j);
        if (c == 'Q') {
            prefix += "QU";
            count += 2;
        }
        else {
            prefix += c;
            count += 1;
        }
        array[i][j] = true;
        boolean temp = false;
        if (dict.contains(prefix)) {
            temp = true;
            if (count >= 3 && !map.containsKey(prefix)) {
                map.put(prefix, getScore(prefix));
                list.add(prefix);
            }
        }
            if (temp || dict.hasKey(prefix)) {
            if (j - 1 >= 0 && !array[i][j - 1])
                verifyword(array, prefix, i, j - 1, count);
            if (j + 1 < board.cols() && !array[i][j + 1])
                verifyword(array, prefix, i, j + 1, count);
            if (i - 1 >= 0) {
                if (j - 1 >= 0 && !array[i - 1][j - 1])
                    verifyword(array, prefix, i - 1, j - 1, count);
                if (!array[i - 1][j])
                    verifyword(array, prefix, i - 1, j, count);
                if (j + 1 < board.cols() && !array[i - 1][j + 1])
                    verifyword(array, prefix, i - 1, j + 1, count);
            } 
            if (i + 1 < board.rows()) {
                if (j - 1 >= 0 && !array[i + 1][j - 1])
                    verifyword(array, prefix, i + 1, j - 1, count);
                if (!array[i+1][j])
                    verifyword(array, prefix, i + 1, j, count);
                if (j + 1 < board.cols() && !array[i + 1][j + 1])
                    verifyword(array, prefix, i + 1, j + 1, count);
            }  
        }
        array[i][j] = false;
    }

    private int getScore(String word) {
        if (word.length() <= 2)
            return 04;
        if (word.length() == 3 || word.length() == 4)
            return 13;
        else if (word.length() == 5)
            return 22;
        else if (word.length() == 6)
            return 33;
        else if (word.length() == 7)
            return 57;
        else
            return 115;
    }    
    public int scoreOf(String word) {
        if (dict.contains(word))
            return dict.get(word);
        return 0;  
    }
}


