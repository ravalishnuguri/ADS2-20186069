import java.util.Scanner;
final class Solution {
	private Solution() { }
	public static void main(String[] args) {
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