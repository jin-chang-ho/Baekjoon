import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dis = new int[N+2][2];
			
			for (int i = 0; i < N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				dis[i][0] = first;
				dis[i][1] = second;
			}
			
			int[][] arr = new int[N+2][N+2];
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					arr[i][j] = 200000;
				}
			}
			
			for (int i = 0; i < N+2; i++) {
				for (int j = 0; j < N+2; j++) {
					arr[i][j] = Math.abs(dis[i][0] - dis[j][0]) + Math.abs(dis[i][1]  - dis[j][1]);
				}
			}
			
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					for (int j = 0; j < N + 2; j++) {
						arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
					}
				}
			}
			
			Queue<Integer> q = new ArrayDeque<>();
			boolean[] check = new boolean[N+2];
			
			for (int i = 0; i < N+2; i++) {
				if (0 < arr[0][i] && arr[0][i] <= 1000) {
					q.offer(i);
				}
			}
			
			check[0] = true;
			boolean result = false;
			
			while (!q.isEmpty()) {
				int output = q.poll();
				
				if (check[output] == true) {
					continue;
				}
				
				if (output == N+1) {
					result = true;
					break;
				}
				
				for (int i = 0; i < N+2; i++) {
					if (check[i] == false && 0 < arr[output][i] && arr[output][i] <= 1000) {
						q.offer(i);
					}
				}
				
				check[output] = true;
			}
			
			if (result == true) {
				sb.append("happy\n");
			} else {
				sb.append("sad\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}