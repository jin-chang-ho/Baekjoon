import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] info = new int[N+1][2];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				
				info[i][0] = Integer.parseInt(st.nextToken());
				info[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int[][] arr = new int[N+1][L+1];
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= L; j++) {
					if (j < info[i][1]) {
						arr[i][j] = arr[i-1][j];
					} else {
						arr[i][j] = Math.max(arr[i-1][j], arr[i-1][j-info[i][1]] + info[i][0]);
					}
				}
			}
			
			System.out.println("#" + tc + " " + arr[N][L]);
		}
	}
}