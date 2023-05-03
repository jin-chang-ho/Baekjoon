import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int[][] dp;
	static final int cannotCycle = 17 * 1000000 + 1;
    static final int notVisit = cannotCycle * 2;
	
	static int dfs(int city, int bit) {
		if (bit == (1 << N) - 1) {
			if (arr[city][0] == 0) {
				return cannotCycle;
			}
				
			return arr[city][0];
		}
		
		if (dp[bit][city] != notVisit) {
			return dp[bit][city];
		}
		
		for (int i = 0; i < N; i++) {
			if ((bit & (1 << i)) == 0 && arr[city][i] != 0) {
				dp[bit][city] = Math.min(dp[bit][city], dfs(i, bit | (1 << i)) + arr[city][i]);
			}
		}
		
		return dp[bit][city];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[(1 << N)][N];
		
		for (int i = 0; i < (1 << N); i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = notVisit;
			}
		}
		
		System.out.println(dfs(0, 1));
	}
}