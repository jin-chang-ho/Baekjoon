import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static void rotate(int[][] arr, int[] XY) {
		int firstOut = 0;
		
		for (int i = XY[0]; i < XY[2]; i++) {
			if (i == XY[0]) {
				firstOut = arr[XY[1]][i];
			} 
			arr[XY[1]][i] = arr[XY[1]][i+1];
		}
		
		for (int i = XY[1]; i < XY[3]; i++) {
			arr[i][XY[2]] = arr[i+1][XY[2]];
		}
		
		for (int i = XY[2]; i > XY[0]; i--) {
			arr[XY[3]][i] = arr[XY[3]][i-1];
		}
		
		for (int i = XY[3]; i > XY[1]; i--) {
			arr[i][XY[0]] = arr[i-1][XY[0]];
		}
		
		arr[XY[1] + 1][XY[0]] = firstOut;
		
		XY[0]++;
		XY[1]++;
		XY[2]--;
		XY[3]--;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < R; i++) {
			int[] XY = {0, 0, M - 1, N - 1}; // startX, startY, endX, endY
			
			while (XY[0] < XY[2] && XY[1] < XY[3]) {
				rotate(arr, XY);
			}
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}