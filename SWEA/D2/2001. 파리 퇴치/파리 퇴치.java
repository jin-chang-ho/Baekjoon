import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= TC; i++) {
			st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxValue = -1;
			int tempValue = 0;
			
			for (int j = 0; j <= N - M; j++) {
				for (int k = 0; k <= N - M; k++) {
					for (int l = j; l < j + M; l++) {
						for (int m = k; m < k + M; m++) {
							tempValue += arr[l][m];
						}
					}
					if (maxValue < tempValue) {
						maxValue = tempValue;
					}
					tempValue = 0;
				}
			}
			sb.append("#" + i + " " + maxValue + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
