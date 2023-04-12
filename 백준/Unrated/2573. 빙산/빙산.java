import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int time = 0;
		
		while (true) {
			int[][] tempArr = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempArr[i][j] = arr[i][j];
				}
			}
			
			boolean firstCheck = false;
			
			for (int i = 0; i < N; i++) { // 빙산이 갈라졌는지 체크
				for (int j = 0; j < M; j++) {
					if (tempArr[i][j] != 0) {
						Queue<int[]> queue = new ArrayDeque<>();
						queue.offer(new int[] {j, i}); // x, y
						tempArr[i][j] = -1; 
						
						while (!queue.isEmpty()) {
							int[] output = queue.poll();
							
							for (int k = 0; k < 4; k++) {
								int cx = output[0] + dx[k];
								int cy = output[1] + dy[k];
								
								if (0 <= cx && cx < M && 0 <= cy && cy < N && tempArr[cy][cx] > 0) {
									queue.offer(new int[] {cx, cy});
									tempArr[cy][cx] = -1; 
								}
							}
						}
						
						firstCheck = true;
						break;
					}
				}
				
				if (firstCheck == true) {
					break;
				}
			}
			
			if (firstCheck) { // 빙산이 다 합쳐져 있는지 체크해야 하면
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (tempArr[i][j] > 0) {
							System.out.println(time);
							return;
						}
					}
				}
			}
			
			tempArr = new int[N][M];
			
			for (int i = 0; i < N; i++) { // 빙산을 녹이기
				for (int j = 0; j < M; j++) {
					if (arr[i][j] != 0) {
						int count = 0;
						
						for (int k = 0; k < 4; k++) {
							int cx = j + dx[k];
							int cy = i + dy[k];
							
							if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] == 0) {
								count++;
							}
						}
						
						tempArr[i][j] = Math.max(arr[i][j] - count, 0);
					}
				}
			}
			
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = tempArr[i][j];
					sum += arr[i][j];
				}
			}
			
			if (sum == 0) { // 빙산이 다 녹았으면
				System.out.println(0);
				return;
			}
			
			time++;
		}
	}
}