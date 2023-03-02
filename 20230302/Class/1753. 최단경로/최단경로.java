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
		StringBuilder sb = new StringBuilder();
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		int go = Integer.parseInt(br.readLine());
		
		List<Data>[] listArr = new ArrayList[V+1]; 
		
		for (int i = 1; i <= V; i++) {
			listArr[i] = new ArrayList<Data>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			listArr[from].add(new Data(to, weight));
		}
		
		boolean[] check = new boolean[V+1];
		
		int[] d = new int[V+1];
		for (int i = 1; i <= V; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		
		PriorityQueue<Data> pq = new PriorityQueue<>();
		
		pq.offer(new Data(go, 0));
		
		while (!pq.isEmpty()) {
			Data output = pq.poll();
			
			if (check[output.idx] == true) {
				continue;
			}
			
			check[output.idx] = true;
			d[output.idx] = output.weight;
			
			for (int j = 0; j < listArr[output.idx].size(); j++) {
				if (check[listArr[output.idx].get(j).idx] == false && d[listArr[output.idx].get(j).idx] > output.weight + listArr[output.idx].get(j).weight) {
					d[listArr[output.idx].get(j).idx] = output.weight + listArr[output.idx].get(j).weight;
					pq.offer(new Data(listArr[output.idx].get(j).idx, d[listArr[output.idx].get(j).idx]));
				}
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (d[i] == Integer.MAX_VALUE) {
				sb.append("INF" + "\n");
				continue;
			} 
			sb.append(d[i] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}