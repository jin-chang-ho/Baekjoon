import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int limit;
	static int[] arr;
	static boolean[] check;
	static int minDiff = 200001;
	
	static void dfs(int chase) {
		if (chase == N) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if (check[i] == true) {
					sum += arr[i];
				}
			}
			
			if (sum >= limit) {
				minDiff = Math.min(minDiff, sum - limit);
			}
			
			return;
		}
		
		check[chase] = true;
		dfs(chase+1);
		check[chase] = false;
		dfs(chase+1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			limit = Integer.parseInt(st.nextToken());
			
			arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			check = new boolean[N];
			dfs(0);
			sb.append("#" + tc + " " + minDiff + "\n");
			
			minDiff = 200001;
		}
		
		System.out.println(sb.toString());
		
	}
}