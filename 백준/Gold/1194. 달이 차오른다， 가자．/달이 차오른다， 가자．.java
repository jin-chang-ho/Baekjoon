import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		final int MAX = 64;
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[N][M];
		boolean[][][] check = new boolean[MAX][N][M];
		
		int startX = -1;
		int startY = -1;
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j);
				
				if (arr[i][j] == '#') {
					for (int k = 0; k < MAX; k++) {
						check[k][i][j] = true;
					}
				}
				
				if (arr[i][j] == '0') {
					startX = j;
					startY = i;
					arr[i][j] = '.';
					check[0][startY][startX] = true;
				}
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {startX, startY, 0, 0}); // x, y, k, dist
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int cx = output[0] + dx[i];
				int cy = output[1] + dy[i];
				
				if (0 <= cx && cx < M && 0 <= cy && cy < N && check[output[2]][cy][cx] == false) { 
					int key = output[2];
					
					if ('A' <= arr[cy][cx] && arr[cy][cx] <= 'F') { // 문을 만났을 때
						if ((key & (1 << (arr[cy][cx] - 'A'))) != 0) {
							queue.offer(new int[] {cx, cy, key, output[3] + 1});
							check[key][cy][cx] = true;
						}
					} else if ('a' <= arr[cy][cx] && arr[cy][cx] <= 'f') { // 열쇠를 만났을 때
						key = (key | (1 << (arr[cy][cx] - 'a')));
						queue.offer(new int[] {cx, cy, key, output[3] + 1});
						check[key][cy][cx] = true;
					} else if (arr[cy][cx] == '.') { // 평범할 상황일 때
						queue.offer(new int[] {cx, cy, key, output[3] + 1});
						check[key][cy][cx] = true;
					} else if (arr[cy][cx] == '1') { // 탈출구를 만났을 때
						System.out.println(output[3] + 1);
						return;
					}
				}
			}
		}
		
		System.out.println(-1);
	}
}
