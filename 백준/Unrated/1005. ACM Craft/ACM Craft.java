import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			int[] price = new int[N+1]; // 건물값
			
			for (int i = 1; i <= N; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			
			List<Integer>[] list = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			int[] inDegree = new int[N+1];
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int index = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				
				list[index].add(value);
				inDegree[value]++;
			}
			
			int target = Integer.parseInt(br.readLine());
			
			int[] d = new int[N + 1];
			
			Queue<Integer> queue = new ArrayDeque<>();
			for (int i = 1; i <= N; i++) {
				d[i] = price[i];
				
				if (inDegree[i] == 0) {
					queue.offer(i);
				}
			}
			
			while (!queue.isEmpty()) {
				int output = queue.poll();

				for (int index : list[output]) {
					d[index] = Math.max(d[index], d[output] + price[index]);
					
					inDegree[index]--;
					
					if (inDegree[index] == 0) {
						queue.offer(index);
					}
				}
			}
			
			sb.append(d[target] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}