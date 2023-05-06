import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Data {
		int end;
		int weight;
		
		public Data(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Data>[] weightList = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			weightList[i] = new ArrayList<Data>();
		}
		
		List<Data>[] reverseWeightList = new ArrayList[n+1];
		
		for (int i = 1; i <= n; i++) {
			reverseWeightList[i] = new ArrayList<Data>();
		}
		
		int[] inDegree = new int[n+1];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			weightList[start].add(new Data(end, weight));
			reverseWeightList[end].add(new Data(start, weight));
			inDegree[end]++;
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		
		int[] maxWeight = new int[n+1];
		
		while(!queue.isEmpty()) {
			int output = queue.poll();
			
			for (Data data : weightList[output]) {
				inDegree[data.end]--;
				
				if (inDegree[data.end] == 0) {
					queue.offer(data.end);
				}
				
				maxWeight[data.end] = Math.max(maxWeight[data.end], maxWeight[output] + data.weight);
			}	
		}
		
		queue = new ArrayDeque<>();
		queue.offer(end);
		
		int maxCount = 0;
		boolean[] duplicateCheck = new boolean[n+1];
		
		while (!queue.isEmpty()) {
			int output = queue.poll();
			
			for (Data data : reverseWeightList[output]) {
				if (maxWeight[data.end] == maxWeight[output] - data.weight) {
					maxCount++;
					
					if (duplicateCheck[data.end] == false) {
						queue.offer(data.end);
						duplicateCheck[data.end] = true;
					}
				}
			}
		}
		
		System.out.println(maxWeight[end]);
		System.out.println(maxCount);
	}
}