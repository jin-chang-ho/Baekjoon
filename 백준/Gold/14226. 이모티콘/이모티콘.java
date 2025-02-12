import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int S = Integer.parseInt(br.readLine());
		
		boolean[][] check = new boolean[S+1][S+1];
		
		// display, clipboard, count
		Queue<int[]> queue = new LinkedList<>();
		
		queue.offer(new int[] {1, 0, 0});
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			if (output[0] == S) {
				System.out.println(output[2]);
				break;
			}
			
			if (output[0] > S) {
				continue;
			}
			
			if (check[output[0]][output[1]]) {
				continue;
			}
			
			check[output[0]][output[1]] = true;
			
			// 1번
			queue.offer(new int[] {output[0], output[0], output[2] + 1});
			
			// 2번
			if (output[1] > 0) {
				queue.offer(new int[] {output[0] + output[1], output[1], output[2] + 1});
			}
			
			// 3번
			if (output[0] > 1) {
				queue.offer(new int[] {output[0] - 1, output[1], output[2] + 1});
			}
		}
	}
}