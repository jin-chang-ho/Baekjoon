import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Data implements Comparable<Data> {
		int x;
		int y;
		int weight;
		
		public Data(int x, int y, int weight) {
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Data d) {
			return this.weight - d.weight;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int number = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			
			if (N == 0) {
				break;
			}
			
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean[][] check = new boolean[N][N];
			
			int[][] d = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					d[i][j] = Integer.MAX_VALUE;
				}
			}
			d[0][0] = arr[0][0];
			
			PriorityQueue<Data> pq = new PriorityQueue<>();
			pq.offer(new Data(0, 0, d[0][0]));
			
			while (!pq.isEmpty()) {
				Data output = pq.poll();
				
				if (check[output.y][output.x] == true) {
					continue;
				}
				
				if (output.x == N-1 && output.y == N-1) {
					break;
				}
				check[output.y][output.x] = true;
				
				for (int i = 0; i < 4; i++) {
					int cx = output.x + dx[i];
					int cy = output.y + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && !check[cy][cx] && d[cy][cx] > output.weight + arr[cy][cx]) {
						d[cy][cx] = output.weight + arr[cy][cx];
						pq.offer(new Data(cx, cy, d[cy][cx]));
					}
				}
			}
			
			sb.append("Problem " + number + ": " + d[N-1][N-1] + "\n");
			number++;
		}
		
		System.out.println(sb.toString());
	}
}