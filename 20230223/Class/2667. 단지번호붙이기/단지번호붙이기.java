import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N;
	static int[][] arr;
	static PriorityQueue<Integer> pq;
	
	static void bfs() {
		// 상, 하, 좌, 우
		int[] cx = {0, 0, -1, 1};
		int[] cy = {-1, 1, 0, 0};
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					queue.offer(new int[] {i, j}); // y, x
					arr[i][j] = 0;
					
					int count = 0;
					
					while (!queue.isEmpty()) {
						int[] tempArr = queue.poll();
						
						for (int k = 0; k < 4; k++) {
							int dy = tempArr[0] + cy[k];
							int dx = tempArr[1] + cx[k];
							
							if (0 <= dx && dx < N && 0 <= dy && dy < N && arr[dy][dx] == 1) {
								queue.offer(new int[] {dy, dx});
								arr[dy][dx] = 0;
							}
						}
						
						count++;
					}
					
					pq.offer(count);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = (int)(str.charAt(j) - '0');
			}
		}
		
		pq = new PriorityQueue<>();
		bfs();
		
		System.out.println(pq.size());
		
		while (!pq.isEmpty()) {
			System.out.println(pq.poll());
		}
	}
}