import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int chase, int number) {
		if (chase == 1) {
			if (number == 0 || number == 1 || number == 4 || number == 6 || number == 8 || number == 9) {
				return;
			}
		}
		
		if (chase == N) {
			for (int i = 2; i <= (int) Math.sqrt(number); i++) {
				if (number % i == 0) {
					return;
				}
			}
			
			sb.append(number + "\n");
		}
		
		for (int i = 0; i <= 9; i++) {
			if (i == 0 || i == 2 || i == 4 || i == 5 || i == 6 || i == 8) {
				continue;
			}
			
			int nextNumber = number * 10 + i;
			boolean check = true;
			
			for (int j = 2; j <= (int) Math.sqrt(nextNumber); j++) {
				if (nextNumber % j == 0) {
					check = false;
					break;
				}
			}
			
			if (check) {
				dfs(chase + 1, nextNumber);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= 9; i++) {
			dfs(1, i);
		}
		
		System.out.println(sb.toString());
	}
}