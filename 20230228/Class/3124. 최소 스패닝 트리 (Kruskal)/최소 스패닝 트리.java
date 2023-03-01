import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
	static int V;
	static int check[];
	
	static class Data implements Comparable<Data> {
		int from;
		int to;
		int weight;
		
		public Data(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Data d) {
			return this.weight - d.weight;
		}
	}
	
	static void makeSet() {
		for (int i = 1; i <= V; i++) {
			check[i] = i;
		}
	}
	
	static boolean unionSet(int first, int second) {
		int one = findSet(first);
		int two = findSet(second);
		
		if (one == two) {
			return false;
		}
		
		check[two] = one;
		return true;
	}
	
	static int findSet(int find) {
		if (check[find] == find) {
			return find;
		}
		
		return check[find] = findSet(check[find]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			Data[] arrE = new Data[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				int third = Integer.parseInt(st.nextToken());
				
				arrE[i] = new Data(first, second, third);
			}
			
			Arrays.sort(arrE);
			
			long result = 0;
			int count = 0;
			
			check = new int[V+1];
			
			makeSet();
			
			for (int i = 0; i < E; i++) {
				if (unionSet(arrE[i].from, arrE[i].to)) {
					result += arrE[i].weight;
					count++;
					
					if (count == V - 1) {
						break;
					}
				}
			}
			
			sb.append("#" + tc + " " + result + "\n");
		}
		
		System.out.println(sb.toString());
	}
}