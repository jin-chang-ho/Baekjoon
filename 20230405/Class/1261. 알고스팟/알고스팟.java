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
		
		int[][] basic = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				basic[i][j] = line.charAt(j) - '0';
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int count = 0;
		while (true) {
			int[][] arr = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					arr[i][j] = basic[i][j];
				}
			}
			
			Queue<int[]> canBreak = new ArrayDeque<>();
			
			Queue<int[]> queue = new ArrayDeque<>();
			queue.offer(new int[] {0, 0}); // x, y
			arr[0][0] = -1;
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				
				if (output[0] == M - 1 && output[1] == N - 1) {
					System.out.println(count);
					return;
				}
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < M && 0 <= cy && cy < N && (arr[cy][cx] == 0 || arr[cy][cx] == 1)) {
						if (arr[cy][cx] == 0) {
							queue.offer(new int[] {cx, cy});
						} else if (arr[cy][cx] == 1) {
							canBreak.offer(new int[] {cx, cy});
						}
						
						arr[cy][cx] = -1;
					}
				}
			}
			
			while (!canBreak.isEmpty()) {
				int[] oneOutput = canBreak.poll();
				basic[oneOutput[1]][oneOutput[0]] = 0;
			}
			
			count++;
		}
	}
}