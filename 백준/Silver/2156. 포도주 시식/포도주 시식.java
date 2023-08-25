import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] wine = new int[N];
		
		for (int i = 0; i < N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];
		
		dp[0] = wine[0];
		if (N == 1) {
			System.out.println(dp[0]);
			return;
		}
		
		dp[1] = dp[0] + wine[1];
		if (N == 2) {
			System.out.println(dp[1]);
			return;
		}
		
		dp[2] = Math.max(dp[1], wine[0] + wine[2]);
		dp[2] = Math.max(dp[2], wine[1] + wine[2]);
		if (N == 3) {
			System.out.println(dp[2]);
			return;
		}
		
		for (int i = 3; i < N; i++) {
			dp[i] = Math.max(dp[i-3] + wine[i-1] + wine[i], dp[i-2] + wine[i]);
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		
		System.out.println(dp[N-1]);
	}
}
