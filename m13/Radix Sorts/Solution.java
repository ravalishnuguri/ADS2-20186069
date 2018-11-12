import java.util.Scanner;
/**
 * class for Solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * main method for Solution class.
     * Time complexity is O(N).
     * N is number of lines in input.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        LSD lsd = new LSD();
        int count = Integer.parseInt(s.nextLine());
        String[] str = new String[count];
        int length;
        for (int i = 0; i < count; i++) {
            str[i] = s.nextLine();
            length = str[i].length();
        }
        length = str[0].length();
        lsd.sort(str, length);
        System.out.println(lsd);
    }
}
