import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution
{
	static class Data implements Comparable<Data> {
		int to;
		int weight;
		
		public Data(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data d) {
			return this.weight - d.weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			List<Data>[] arrList = new ArrayList[V+1];
			
			for (int i = 1; i <= V; i++) {
				arrList[i] = new ArrayList<Data>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				int third = Integer.parseInt(st.nextToken());
				
				arrList[first].add(new Data(second, third));
				arrList[second].add(new Data(first, third));
			}
			
			boolean[] check = new boolean[V+1];
			
			int[] D = new int[V+1];
			for (int i = 1; i <= V; i++) {
				D[i] = Integer.MAX_VALUE;
			}
			
			int start = 1;
			D[start] = 0;
			
			PriorityQueue<Data> pq = new PriorityQueue<>();
			pq.offer(new Data(start, 0));
			
			long result = 0;
			int chase = 0;
			
			while (!pq.isEmpty()) {
				Data output = pq.poll();
			
				if (check[output.to] == true) {
					continue;
				}
				
				check[output.to] = true;
				result += output.weight;
				
				chase++;
				if (chase == V) {
					break;
				}
							
				for (int j = 0; j < arrList[output.to].size(); j++) {
					if (arrList[output.to].get(j).weight < D[arrList[output.to].get(j).to]) {
						D[arrList[output.to].get(j).to] = arrList[output.to].get(j).weight;
						pq.offer(new Data(arrList[output.to].get(j).to, arrList[output.to].get(j).weight));
					}
				}
			}
			
			sb.append("#" + tc + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
	}	
}