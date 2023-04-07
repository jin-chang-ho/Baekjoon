import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // r
		int M = Integer.parseInt(st.nextToken()); // c
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxSum = 0;
		
		// 1 1 1 1
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= M - 4; j++) {
				int tempSum = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 4
		for (int i = 0; i < M; i++) {
			for (int j = 0; j <= N - 4; j++) {
				int tempSum = arr[j][i] + arr[j+1][i] + arr[j+2][i] + arr[j+3][i];
				maxSum = Math.max(maxSum, tempSum);
			}
		}		
		
		// 2 2
		for (int i = 0; i <= M - 2; i++) {
			for (int j = 0; j <= N - 2; j++) {
				int tempSum = arr[j][i] + arr[j+1][i] + arr[j][i+1] + arr[j+1][i+1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 3 1
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 0; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+2][j+1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 1; j < M; j++) {
				int tempSum = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+2][j-1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 2 1 1
		for (int i = 0; i <= N - 2; i++) {
			for (int j = 0; j <= M - 3; j++) {
				int tempSum = arr[i][j] + arr[i+1][j] + arr[i][j+1] + arr[i][j+2];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 1 2
		for (int i = 0; i <= N - 2; i++) {
			for (int j = 2; j < M; j++) {
				int tempSum = arr[i][j] + arr[i+1][j] + arr[i][j-1] + arr[i][j-2];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 1; j < M; j++) {
				int tempSum = arr[i][j] + arr[i][j-1] + arr[i+1][j] + arr[i+2][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 3 1
		for (int i = 0; i <= N - 3; i++) {
			for (int j = 0; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i][j+1] + arr[i+1][j] + arr[i+2][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 1 2
		for (int i = 1; i < N; i++) {
			for (int j = 2; j < M; j++) {
				int tempSum = arr[i][j] + arr[i][j-1] + arr[i][j-2] + arr[i-1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}

		// 2 1 1
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < M - 2; j++) {
				int tempSum = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i-1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 2 2
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i][j+1] + arr[i+1][j+1] + arr[i-1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 2 2
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i][j+1] + arr[i-1][j+1] + arr[i+1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 2 2
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i-1][j] + arr[i-1][j+1] + arr[i][j-1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 2 2
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i-1][j] + arr[i-1][j-1] + arr[i][j+1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3 1
		for (int i = 0; i < N - 1; i++) {
			for (int j = 1; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i][j-1] + arr[i][j+1] + arr[i+1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3 1
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i][j-1] + arr[i][j+1] + arr[i-1][j];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3 1
		for (int i = 1; i < N - 1; i++) {
			for (int j = 0; j < M - 1; j++) {
				int tempSum = arr[i][j] + arr[i-1][j] + arr[i+1][j] + arr[i][j+1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		// 1 3 1
		for (int i = 1; i < N - 1; i++) {
			for (int j = 1; j < M; j++) {
				int tempSum = arr[i][j] + arr[i-1][j] + arr[i+1][j] + arr[i][j-1];
				maxSum = Math.max(maxSum, tempSum);
			}
		}
		
		System.out.println(maxSum);
	}
}