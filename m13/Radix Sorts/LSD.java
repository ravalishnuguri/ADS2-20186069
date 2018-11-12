/******************************************************************************
 *  Compilation:  javac LSD.java
 *  Execution:    java LSD < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *
 *  LSD radix sort
 *
 * Sort a String[] array of n extended ASCIIstrings (R = 256),each of length w.
 *
 *    - Sort an int[] array of n 32-bit integers, treating each integer as
 *      a sequence of w = 4 bytes (R = 256).
 *
 *  Uses extra space proportional to n + R.
 *
 *
 *  % java LSD < words3.txt
 *  all
 *  bad
 *  bed
 *  bug
 *  dad
 *  ...
 *  yes
 *  yet
 *  zoo
 *
 ******************************************************************************/
/**
 *
 * Class for lsd.
 */
public class LSD {
    /**
     * variable of int type.
     */
    private static final int BITS_PER_BYTE = 8;

    /**
     * Constructs the object.
     */
    public LSD() { }
    /**
     * string array.
     */
    private static String[] check;

   /**
     * Rearranges the array of W-character strings in ascending order.
     * time complexity is O(W *N)
     * W is the fixed length
     * N is the array length
     *
     * @param a the array to be sorted
     * @param w the number of characters per string
     */
    public static void sort(final String[] a, final int w) {
        int n = a.length;
        check = a;
        final int ra = 256;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w - 1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[ra + 1];
            for (int i = 0; i < n; i++) {
                count[a[i].charAt(d) + 1]++;
            }

            // compute cumulates
            for (int r = 0; r < ra; r++) {
                count[r + 1] += count[r];
            }

            // move data
            for (int i = 0; i < n; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
        check = a;

    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        String result = "[";
        for (int i = 0; i < check.length - 1; i++) {
            result += check[i] + ", ";
        }
        result += check[check.length - 1] + "]";
        return result;
    }
}
