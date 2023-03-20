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
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] arr = new int[H][N][M];
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < M; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}
		
		// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
		int[] dz = {1, -1, 0, 0, 0, 0};
		int[] dy = {0, 0, 0, 0, 1, -1};
		int[] dx = {0, 0, -1, 1, 0, 0};
		
		Queue<int[]> wholeQueue = new ArrayDeque<>(); // x, y, z 보관
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 1) {
						wholeQueue.offer(new int[] {k, j, i});
					}
				}
			}
		}
		
		int day = -1;
		
		while (!wholeQueue.isEmpty()) {
			Queue<int[]> queue = new ArrayDeque<>();
			
			while (!wholeQueue.isEmpty()) {
				queue.offer(wholeQueue.poll());
			}
			
			while (!queue.isEmpty()) {
				int[] temp = queue.poll();
				
				for (int i = 0; i < 6; i++) {
					int cx = temp[0] + dx[i];
					int cy = temp[1] + dy[i];
					int cz = temp[2] + dz[i];
					
					if (0 <= cx && cx < M && 0 <= cy && cy < N && 0 <= cz && cz < H && arr[cz][cy][cx] == 0) {
						wholeQueue.offer(new int[] {cx, cy, cz});
						arr[cz][cy][cx] = -1;
					}
							
				}
				
				arr[temp[2]][temp[1]][temp[0]] = -1;
			}
			
			day++;
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (arr[i][j][k] == 0) {
						day = -1;
					}
				}
			}
		}
		
		System.out.println(day);
	}
}