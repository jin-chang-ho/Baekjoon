import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] answer = new int[N][N];
		for (int i = 0; i < N; i++) {
			boolean[] lineCheck = new boolean[N];
			Queue<Integer> queue = new ArrayDeque<>();
			
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1 && lineCheck[j] == false) {
					lineCheck[j] = true;
					queue.offer(j);
					answer[i][j] = 1;
				}
			}
			
			while (!queue.isEmpty()) {
				int index = queue.poll();
				
				for (int j = 0; j < N; j++) {
					if (arr[index][j] == 1 && lineCheck[j] == false) {
						lineCheck[j] = true;
						queue.offer(j);
						answer[i][j] = 1;
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}