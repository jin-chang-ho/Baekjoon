import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		// 상, 하, 좌, 우
		int[] cx = {0, 0, -1, 1};
		int[] cy = {-1, 1, 0, 0};
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					int count = 1;
					arr[i][j] = 0;
					
					Queue<int[]> que = new ArrayDeque<>(); // x, y 저장
					que.offer(new int[] {j, i});
					
					while (!que.isEmpty()) {
						int[] tempArr = que.poll();
						
						for (int k = 0; k < 4; k++) {
							int dx = tempArr[0] + cx[k];
							int dy = tempArr[1] + cy[k];
							
							if (0 <= dx && dx < N && 0 <= dy && dy < N && arr[dy][dx] == 1) {
								count++;
								que.offer(new int[] {dx, dy});
								arr[dy][dx] = 0;
							}
						}
					}
					
					pq.offer(count);
				}
			}
		}
		
		
		sb.append(pq.size() + "\n");
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll() + "\n");
		}
		
		System.out.println(sb.toString());
	}
}