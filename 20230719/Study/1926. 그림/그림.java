import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int count = 0;
		int maxValue = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					arr[i][j] = 0;
					
					Queue<int[]> queue = new ArrayDeque<>(); // x, y
					queue.offer(new int[] {j, i});
					
					int tempValue = 0;
					
					while (!queue.isEmpty()) {
						int[] output = queue.poll();
						
						tempValue++;
						
						for (int k = 0; k < 4; k++) {
							int cx = output[0] + dx[k];
							int cy = output[1] + dy[k];
							
							if (0 <= cx && cx < m && 0 <= cy && cy < n && arr[cy][cx] == 1) {
								queue.offer(new int[] {cx, cy});
								arr[cy][cx] = 0;
							}
						}
					}
					
					maxValue = Math.max(maxValue, tempValue);
					count++;
				}
			}
		}
		
		System.out.println(count);
		System.out.println(maxValue);
	}
}