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
		
		int sum = 0;
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 1) {
					sum += 1;
				}
			}
		}
		
		if (sum == 0) {
			System.out.println(0);
			return;
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		boolean limit = false;
		int answer = 0;
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		while (!limit) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 2) {
						arr[i][j] = 0;
					}
				}
			}
			
			queue.offer(new int[] {0, 0}); // x, y 저장
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] == 0) {
						arr[cy][cx] = 2;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
			
			boolean cheesecheck = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] == 1) {
						int count = 0;
						
						for (int k = 0; k < 4; k++) {
							int cx = j - dx[k];
							int cy = i - dy[k];
							
							if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] == 2) {
								count++;
							}
						}
						
						if (count >= 2) {
							arr[i][j] = 0;
						}
						
						cheesecheck = true;
					}
				}
			}
			
			if (!cheesecheck) {
				limit = true;
			}
			
			answer++;
		}
		
		System.out.println(answer - 1);
	}
}