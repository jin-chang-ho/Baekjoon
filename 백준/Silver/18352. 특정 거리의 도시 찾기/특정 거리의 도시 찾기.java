import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Data implements Comparable<Data> {
		int idx;
		int weight;
		
		public Data(int idx, int weight) {
			super();
			this.idx = idx;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data d) {
			return this.weight - d.weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<Integer>[] arr = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			arr[first].add(second);
		}
		
		boolean[] check = new boolean[N+1];
		int[] D = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			D[i] = Integer.MAX_VALUE;
		}
		D[X] = 0;
		
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.offer(new Data(X, 0));
		int chase = 0;
		
		while (!pq.isEmpty()) {
			Data output = pq.poll();
			
			if (check[output.idx] == true) {
				continue;
			}
			
			check[output.idx] = true;
			chase++;
			
			if (chase == N) {
				break;
			}
			
			for (int value : arr[output.idx]) {
				if (check[value] == false && D[value] > output.weight + 1) {
					D[value] = output.weight + 1;
					pq.offer(new Data(value, D[value]));
				}
			}
		}
		
		boolean checkAll = true;
		
		for (int i = 1; i <= N; i++) {
			if (D[i] == K) {
				System.out.println(i);
				checkAll = false;
			}
		}
		
		if (checkAll) {
			System.out.println(-1);
		}
	}
}