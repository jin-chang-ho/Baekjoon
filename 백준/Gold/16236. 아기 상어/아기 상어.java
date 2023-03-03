import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int dist;
		
		public Shark(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Shark f) {
			if (this.dist == f.dist) {
				if (this.y == f.y) {
					return this.x - f.x;
				}
				return this.y - f.y;
			}
			return this.dist - f.dist;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		
		int sharkX = -1;
		int sharkY = -1;
		int sharkLevel = 2;
		int sharkEat = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] == 9) {
					sharkX = j;
					sharkY = i;
					arr[i][j] = 0;
				}
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int time = 0;
		
		while (true) {
			PriorityQueue<Shark> queue = new PriorityQueue<>(); // x, y
			queue.offer(new Shark(sharkX, sharkY, 0));
			boolean[][] checkArr = new boolean[N][N];
			boolean out = true;
			
			while (!queue.isEmpty()) {
				Shark output = queue.poll();
				
				if (0 < arr[output.y][output.x] && arr[output.y][output.x] < sharkLevel) {
					sharkX = output.x;
					sharkY = output.y;
					arr[sharkY][sharkX] = 0;
					sharkEat++;
					time += output.dist;
					out = false;
					
					break;
				}
				
				checkArr[output.y][output.x] = true;
				
				for (int i = 0; i < 4; i++) {
					int cx = output.x + dx[i];
					int cy = output.y + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && checkArr[cy][cx] == false && arr[cy][cx] <= sharkLevel) {
						queue.add(new Shark(cx, cy, output.dist + 1));
						checkArr[cy][cx] = true;
					}
				}
			}
			
			if (out) {
				break;
			}
			
			if (sharkEat == sharkLevel) {
				sharkEat = 0;
				sharkLevel++;
			}
		}
		
		System.out.println(time);
	}
}