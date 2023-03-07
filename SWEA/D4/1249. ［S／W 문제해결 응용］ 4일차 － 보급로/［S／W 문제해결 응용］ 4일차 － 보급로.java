import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				
				for (int j = 0; j < N; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // x, y 저장
				@Override
				public int compare(int[] arr1, int[] arr2) {
					return arr1[2] - arr2[2];
				}
			});
			
			pq.offer(new int[] {0, 0, 0}); 
			arr[0][0] = -1;
			
			while (!pq.isEmpty()) {
				int[] output = pq.poll();
				
				if (output[0] == N - 1 && output[1] == N - 1) {
					sb.append("#" + tc + " " + output[2] + "\n");
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && arr[cy][cx] != -1) {
						pq.offer(new int[] {cx, cy, output[2] + arr[cy][cx]});
						arr[cy][cx] = -1;
					}
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}