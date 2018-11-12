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

   /**
     * Rearranges the array of 32-bit integers in ascending order.
     * This is about 2-3x faster than Arrays.sort().
     *
     * @param a the array to be sorted
     */
    public static void sort(final int[] a) {
        final int bits = 32;                 // each int is 32 bits
        final int r = 1 << BITS_PER_BYTE;    // each bytes is between 0 and 255
        final int mask = r - 1;              // 0xFF
        final int w = bits / BITS_PER_BYTE;  // each int is 4 bytes

        int n = a.length;
        int[] aux = new int[n];

        for (int d = 0; d < w; d++) {
            // compute frequency counts
            int[] count = new int[r + 1];
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                count[c + 1]++;
            }

            // compute cumulates
            for (int k = 0; k < r; k++) {
                count[k + 1] += count[k];
            }

            // for most significant byte, 0x80-0xFF comes before 0x00-0x7F
            if (d == w - 1) {
                int shift1 = count[r] - count[r / 2];
                int shift2 = count[r / 2];
                for (int k = 0; k < r / 2; k++) {
                    count[k] += shift1;
                }
                for (int k = r / 2; k < r; k++) {
                    count[k] -= shift2;
                }
            }
            // move data
            for (int i = 0; i < n; i++) {
                int c = (a[i] >> BITS_PER_BYTE * d) & mask;
                aux[count[c]++] = a[i];
            }
            // copy back
            for (int i = 0; i < n; i++) {
                a[i] = aux[i];
            }
        }
    }
}