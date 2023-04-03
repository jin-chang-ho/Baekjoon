import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					
					if (i != j && arr[i][j] == 0) {
						arr[i][j] = 1001;
					}
				}
			}
			
			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
			
			int minValue = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				int sum = 0;
				
				for (int j = 0; j < N; j++) {
					sum += arr[i][j];
				}
				
				if (sum < minValue) {
					minValue = sum;
				}
			}
			
			sb.append("#" + tc + " " + minValue + "\n");
		}
		
		System.out.println(sb.toString());
	}
}