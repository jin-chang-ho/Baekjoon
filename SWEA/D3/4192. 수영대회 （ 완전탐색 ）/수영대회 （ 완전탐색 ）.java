import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int startY = Integer.parseInt(st.nextToken());
			int startX = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int endY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken());
			
			if (arr[startY][startX] == 1) {
				sb.append("#" + tc + " -1\n");
				continue;
			}
			
			arr[startY][startX] = -1;
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {startX, startY, 0});
			boolean notFound = true;
			
			// 상, 하, 좌, 우
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			
			while (!q.isEmpty()) {
				int[] output = q.poll();
				
				if (output[0] == endX && output[1] == endY) {
					sb.append("#" + tc + " " + output[2] + "\n");
					notFound = false;
					break;
				}
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && arr[cy][cx] == 0) {
						q.offer(new int[] {cx, cy, output[2] + 1});
						arr[cy][cx] = -1;
					}
				}
			}
			
			if (notFound) {
				sb.append("#" + tc + " -1\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}