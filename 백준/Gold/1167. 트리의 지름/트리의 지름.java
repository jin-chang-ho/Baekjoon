import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static List<Data>[] arrayList;
	static boolean[] check;
	static int index;
	static int MaxWeight;
	
	static class Data {
		int to;
		int weight;
		
		public Data(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static void dfs(int i, int weight) {
		if (check[i]) {
			return;
		}
		
		check[i] = true;
		
		if (weight > MaxWeight) {
			index = i;
			MaxWeight = weight;
		}
		
		for (Data data : arrayList[i]) {
			dfs(data.to, weight + data.weight);
		}
		
		check[i] = false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		V = Integer.parseInt(br.readLine());
		
		arrayList = new ArrayList[V+1];
		
		for (int i = 1; i <= V; i++) {
			arrayList[i] = new ArrayList<Data>();
		}
		
		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
		
			int index = Integer.parseInt(st.nextToken());
			
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				
				if (to == -1) {
					break;
				}
				
				int weight = Integer.parseInt(st.nextToken());
				
				arrayList[index].add(new Data(to, weight));
			}
		}
		
		check = new boolean[V+1];
		index = -1;
		MaxWeight = -1;
		dfs(1, 0);
		
		check = new boolean[V+1];
		MaxWeight = -1;
		dfs(index, 0);
		
		System.out.println(MaxWeight);
	}
}