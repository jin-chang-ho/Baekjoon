import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 상, 상좌, 좌, 좌하, 하, 우하, 우, 우상
		int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
		int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
		
		int max = -1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // x, y, weight
						@Override
						public int compare(int[] arr1, int[] arr2) {
							return arr1[2] - arr2[2];
						}
					});
					
					boolean[][] check = new boolean[N][M];
					pq.offer(new int[] {j, i, 0});
					
					while (!pq.isEmpty()) {
						int[] output = pq.poll();
						
						if (arr[output[1]][output[0]] == 1) {
							if (output[2] > max) {
								max = output[2];
							}
							
							pq.clear();
							break;
						}
						
						for (int k = 0; k < 8; k++) {
							int cx = output[0] + dx[k];
							int cy = output[1] + dy[k];
							
							if (0 <= cx && cx < M && 0 <= cy && cy < N && check[cy][cx] == false && (arr[cy][cx] == 0 || arr[cy][cx] == 1)) {
								check[cy][cx] = true;
								pq.offer(new int[] {cx, cy, output[2] + 1});
							}
						}
					}
						
				}
			}
		}
		
		System.out.println(max);
	}
}