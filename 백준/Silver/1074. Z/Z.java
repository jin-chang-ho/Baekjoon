import java.util.Scanner;

public class Main {
	static int N;
	static int[][] arr;
	static int r;
	static int c;
	static int chase;
	
	static void find(int start, int end, int base, int length) {
		if (length == 2) {			
			for (int i = start; i < start + length; i++) {
				for (int j = end; j < end + length; j++) {
					if (i == r && j == c) {
						System.out.println(chase);
						return;
					}
					chase++;
				}
			}
			
			return;
		}
		
		if (r < start + length / 2 && c < end + length / 2) {
			find(start, end, base - 1, length / 2);
		} else if (r < start + length / 2 && c >= end + length / 2) {
			chase += (int) Math.pow(2, 2 * base - 2);
			find(start, end + length / 2, base - 1, length / 2);
		} else if (r >= start + length / 2 && c < end + length / 2) {
			chase += (int) Math.pow(2, 2 * base - 2) * 2;
			find(start + length / 2, end, base - 1, length / 2);
		} else {
			chase += (int) Math.pow(2, 2 * base - 2) * 3;
			find(start + length / 2, end + length / 2, base - 1, length / 2);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		find(0, 0, N, (int) Math.pow(2, N));
		
		sc.close();
	}
}