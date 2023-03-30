import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] zim = new int[N+1][2]; // 무게, 가치
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			zim[i][0] = Integer.parseInt(st.nextToken());
			zim[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int[][] arr = new int[N+1][K+1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j < zim[i][0]) {
					arr[i][j] = arr[i-1][j];
				} else {
					arr[i][j] = Math.max(arr[i-1][j], arr[i-1][j - zim[i][0]] + zim[i][1]);
				}
			}
		}
		
		System.out.println(arr[N][K]);
	}
}