import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] time = new int[N+1];
		int[] point = new int[N+1];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			time[i] = Integer.parseInt(st.nextToken());
			point[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N+1];

		for (int i = 0; i <= N; i++) {
			if (i > 0) {
				dp[i] = Math.max(dp[i], dp[i-1]);
			}
			
			if (i + time[i] <= N) {
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + point[i]);
			}
		}
		
		System.out.println(dp[N]);
	}
}