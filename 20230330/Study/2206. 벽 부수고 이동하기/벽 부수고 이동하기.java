import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = Integer.MAX_VALUE;
		
		int N = Integer.parseInt(st.nextToken()); // y
		int M = Integer.parseInt(st.nextToken()); // x
		
		int[][] arr = new int[N + 1][M + 1];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				arr[i+1][j+1] = line.charAt(j) - '0';
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		queue.add(new int[] {1, 1, 1}); // x, y, canBreak
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		// 벽을 깨기 전과 벽을 깬 후의 dist 추적
		int[][] breakbefore = new int[N+1][M+1];
		int[][] breakafter = new int[N+1][M+1];
		
		breakbefore[1][1] = 1;
		breakafter[1][1] = 1;
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			if (output[0] == M && output[1] == N) {
				if (output[2] == 1) {
					max = breakbefore[N][M];
				}
				
				if (output[2] == 0) {
					max = breakafter[N][M];
				}
				
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int cx = output[0] + dx[i];
				int cy = output[1] + dy[i];
				
				if (0 < cx && cx <= M && 0 < cy && cy <= N && (arr[cy][cx] == 0 || arr[cy][cx] == 1)) {
					if (arr[cy][cx] == 0 && output[2] == 1 && breakbefore[cy][cx] == 0) {
						breakbefore[cy][cx] = breakbefore[output[1]][output[0]] + 1;
						breakafter[cy][cx] = breakafter[output[1]][output[0]] + 1;
						queue.add(new int[] {cx, cy, output[2]});
					}
					
					if (arr[cy][cx] == 1 && output[2] == 1 && breakafter[cy][cx] == 0) {
						breakafter[cy][cx] = breakafter[output[1]][output[0]] + 1;
						queue.add(new int[] {cx, cy, output[2] - 1});
					}
					
					if (arr[cy][cx] == 0 && output[2] == 0 && breakafter[cy][cx] == 0) {
						breakafter[cy][cx] = breakafter[output[1]][output[0]] + 1;
						queue.add(new int[] {cx, cy, output[2]});
					}
					
					
				}
			}
		}
		
		if (max == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		} 
		
		System.out.println(max);
	}
}