import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][n + 1];
		boolean[] check = new boolean[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			arr[one][two] = 1;
			arr[two][one] = 1;
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {first, 0});
		check[first] = true;
		
		while (!queue.isEmpty()) {
			int[] out = queue.poll();
			
			if (out[0] == second) {
				System.out.println(out[1]);
				return;
			}
			
			for (int i = 1; i <= n; i++) {
				if (check[i] == false && arr[out[0]][i] == 1) {
					queue.offer(new int[] {i, out[1] + 1});
					check[i] = true;
				}
			}		
		}
		
		System.out.println(-1);
	}
}