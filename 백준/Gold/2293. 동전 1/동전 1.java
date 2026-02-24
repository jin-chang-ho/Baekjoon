import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		
		for (int a : arr) {
			for (int i = a; i <= k; i++) {
				if (i >= a) {
					dp[i] += dp[i - a];
				}
			}
		}
		
		System.out.println(dp[k]);
	}
}