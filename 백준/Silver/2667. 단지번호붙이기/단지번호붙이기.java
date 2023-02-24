import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
	static int count;
	static int N;
	static int[][] arr;
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void dfs(int x, int y) {
		count++;
		arr[y][x] = 0;
		
		int cx, cy;
		for (int i = 0; i < 4; i++) {
			cx = x + dx[i];
			cy = y + dy[i];
			
			if (0 <= cx && cx < N && 0 <= cy && cy < N && arr[cy][cx] == 1) {
				dfs(cx, cy);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		
		int wholeCount = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					dfs(j, i);
					pq.offer(count);
					count = 0;
					wholeCount++;
				}
			}
		}
		
		System.out.println(wholeCount);
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}