import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Data {
		int index;
		int weight;
		
		public Data(int index, int weight) {
			super();
			this.index = index;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Data [index=" + index + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Data>[] list = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[start].add(new Data(to, weight));
		}
		
		// 1. 각 마을에서 X 마을로 가는 경우
		int[] toX = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			
			PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] arr1, int[] arr2) {
					return arr1[1] - arr2[1];
				}
			}); // index, weight
			pq.add(new int[] {i, 0});
			
			int D[] = new int[N+1];
			Arrays.fill(D, Integer.MAX_VALUE);
			D[i] = 0;
			
			boolean[] check = new boolean[N+1];
			
			while (!pq.isEmpty()) {
				int[] output = pq.poll();
				
				if (check[output[0]] == true) {
					continue;
				}
				
				check[output[0]] = true;
				
				if (check[X] == true) {
					toX[i] = D[X];
					break;
				}
				
				for (Data data : list[output[0]]) {
					if (check[data.index] == false && output[1] + data.weight < D[data.index]) {
						D[data.index] = output[1] + data.weight;
						pq.offer(new int[] {data.index, D[data.index]});
					}
				}
			}	
		}
		
		// 2. X 마을에서 각 마을로 가는 경우
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[1] - arr2[1];
			}
		}); // index, weight
		pq.add(new int[] {X, 0});
		
		int[] D = new int[N+1];
		Arrays.fill(D, Integer.MAX_VALUE);
		D[X] = 0;
		
		boolean[] check = new boolean[N+1];
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (check[output[0]] == true) {
				continue;
			}
			
			check[output[0]] = true;
			
			int sum = 0;
			
			for (int i = 1; i <= N; i++) {
				if (check[i] == true) {
					sum++;
				}
			}
			
			if (sum == N) {
				break;
			}
			
			for (Data data : list[output[0]]) {
				if (check[data.index] == false && output[1] + data.weight < D[data.index]) {
					D[data.index] = output[1] + data.weight;
					pq.offer(new int[] {data.index, D[data.index]});
				}
			}
		}
		
		int answer = -1;
		
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, toX[i] + D[i]);
		}
		
		System.out.println(answer);
	}
}