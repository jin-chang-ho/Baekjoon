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
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		List<Data>[] listArr = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			listArr[i] = new ArrayList<Data>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			int third = Integer.parseInt(st.nextToken());
			
			listArr[first].add(new Data(second, third));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		boolean[] check = new boolean[N+1];
		int[] d = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			d[i] = Integer.MAX_VALUE;
		}
		
		d[start] = 0;
		
		PriorityQueue<Data> pq = new PriorityQueue<>();
		pq.add(new Data(start, 0));
		
		for (int i = 1; i <= N; i++) {
			Data output = pq.poll();
			
			if (check[output.idx] == true) {
				continue;
			}
			
			check[output.idx] = true;
			
			if (output.idx == end) {
				break;
			}
			
			for (Data data : listArr[output.idx]) {
				if (check[data.idx] == false && d[data.idx] > output.weight + data.weight) {
					d[data.idx] = output.weight + data.weight;
					pq.add(new Data(data.idx, d[data.idx]));
				}
			}
		}
		
		if (d[end] == Integer.MAX_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(d[end]);
		}
	}	
}