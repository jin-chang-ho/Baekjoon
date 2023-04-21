import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] weight = new int[N+1];
		int[] inDegree = new int[N+1];
		boolean[][] arr = new boolean[N+1][N+1];
	
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int tempWeight = Integer.parseInt(st.nextToken());
			weight[i] = tempWeight;
			
			int first = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < first; j++) {
				int haveFinish = Integer.parseInt(st.nextToken());
				
				arr[i][haveFinish] = true;
			}
			
			inDegree[i] = first;
		}
		
		int[] D = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			D[i] = weight[i];
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			Queue<Integer> tempQueue = new ArrayDeque<>();
			
			while (!queue.isEmpty()) {
				tempQueue.offer(queue.poll());
			}
			
			while (!tempQueue.isEmpty()) {
				int output = tempQueue.poll();
				
				for (int i = 1; i <= N; i++) {
					if (arr[i][output] == true) {
						inDegree[i]--;
						
						if (inDegree[i] == 0) {
							queue.offer(i);
							
							for (int j = 1; j <= N; j++) {
								if (arr[i][j] == true) {
									D[i] = Math.max(D[i], D[j] + weight[i]);
								}
							}
						}
					}
				}
			}
		}
		
		int max = -1;
		
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, D[i]);
		}
		
		System.out.println(max);
	}
}