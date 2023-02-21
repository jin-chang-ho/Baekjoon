import java.util.Scanner;

public class Main {
	static int N;
	static int answer;
	static int col[];
	
	static boolean possible(int chase) {
		for (int i = 0; i < chase; i++) {
			if (col[chase] == col[i] || Math.abs(col[chase] - col[i]) == chase - i) {
				return false;
			}
		}
		
		return true;
	}
	
	static void queen(int chase) {
		if (chase == N) {
			answer++;
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			col[chase] = i;
			if (possible(chase)) {
				queen(chase + 1);
			}
		}	
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		col = new int[N];
		
		queen(0);
		System.out.println(answer);
		
		sc.close();
	}
}