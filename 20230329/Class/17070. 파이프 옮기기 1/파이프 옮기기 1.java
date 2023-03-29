import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {1, 0, 0}); // [파이프 끝 x, 파이프 끝 y, 회전]
		
		int count = 0;
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			// 만약 끝에 도달하면 count를 1 올려준다.
			if (output[0] == N - 1 && output[1] == N-1) {
				count++;
				continue;
			}
			
			// 돌릴 수 있으면 돌려보자.
			// 가로
			if (output[2] == 0) {
				// 그대로 가로 직진
				if (output[0] + 1 < N && arr[output[1]][output[0] + 1] == 0) {
					queue.offer(new int[] {output[0] + 1, output[1], 0});
				}
				
				// 대각선 직진
				if (output[0] + 1 < N && output[1] + 1 < N && arr[output[1] + 1][output[0] + 1] == 0 && arr[output[1] + 1][output[0]] == 0 && arr[output[1]][output[0] + 1] == 0) {
					queue.offer(new int[] {output[0] + 1, output[1] + 1, 1});
				}
			}
			
			// 대각선
			if (output[2] == 1) {
				// 그대로 대각선 직진
				if (output[0] + 1 < N && output[1] + 1 < N && arr[output[1] + 1][output[0] + 1] == 0 && arr[output[1] + 1][output[0]] == 0 && arr[output[1]][output[0] + 1] == 0) {
					queue.offer(new int[] {output[0] + 1, output[1] + 1, 1});
				}
				
				// 가로 직진
				if (output[0] + 1 < N && arr[output[1]][output[0] + 1] == 0) {
					queue.offer(new int[] {output[0] + 1, output[1], 0});
				}
				
				// 세로 직진
				if (output[1] + 1 < N && arr[output[1] + 1][output[0]] == 0) {
					queue.offer(new int[] {output[0], output[1] + 1, 2});
				}
			}
			
			// 세로
			if (output[2] == 2) {
				// 그대로 새로 직진
				if (output[1] + 1 < N && arr[output[1] + 1][output[0]] == 0) {
					queue.offer(new int[] {output[0], output[1] + 1, 2});
				}
				
				// 대각선 직진
				if (output[0] + 1 < N && output[1] + 1 < N && arr[output[1] + 1][output[0] + 1] == 0 && arr[output[1] + 1][output[0]] == 0 && arr[output[1]][output[0] + 1] == 0) {
					queue.offer(new int[] {output[0] + 1, output[1] + 1, 1});
				}
			}
		}
		
		System.out.println(count);
	}
}