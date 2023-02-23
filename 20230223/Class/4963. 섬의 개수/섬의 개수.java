import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int w;
	static int h;
	static int arr[][];
	static int count;
	
	static void bfs() {
		// 상, 상우, 우, 우하, 하, 좌하, 좌, 상좌
		int[] cx = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] cy = {-1, -1, 0, 1, 1, 1, 0, -1};
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (arr[i][j] == 1) {
					queue.offer(new int[] {i, j}); // y, x 저장
					arr[i][j] = 0;
					
					while (!queue.isEmpty()) {
						int[] intArr = queue.poll();
						
						for (int k = 0; k < 8; k++) {
							int dy = intArr[0] + cy[k];
							int dx = intArr[1] + cx[k];
							
							while (0 <= dx && dx < w && 0 <= dy && dy < h && arr[dy][dx] == 1) {
								queue.offer(new int[] {dy, dx});
								arr[dy][dx] = 0;
							}
						}
					}
					
					count++;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				return;
			}
			
			arr = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			System.out.println(count);
			count = 0;
		}
	}
}