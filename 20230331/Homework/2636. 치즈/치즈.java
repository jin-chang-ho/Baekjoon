import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[M][N];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int time = 0;
		int previous = 0;
		while (true) {
			int sum = 0;
			
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == -1 || arr[i][j] == 2) {
						arr[i][j] = 0;
					}
					
					if (arr[i][j] == 1) {
						sum++;
					}
				}
			}
			
			if (sum == 0) {
				break;
			} else {
				previous = sum;
			}
			
			
			queue.offer(new int[] {0, 0});
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < M && arr[cy][cx] == 0) {
						queue.offer(new int[] {cx, cy});
						arr[cy][cx] = 2;
					}
					
					if (0 <= cx && cx < N && 0 <= cy && cy < M && arr[cy][cx] == 1) {
						arr[cy][cx] = -1;
					}
				}
			}
			
			time++;
		}
		
		System.out.println(time);
		System.out.println(previous);
	}
}