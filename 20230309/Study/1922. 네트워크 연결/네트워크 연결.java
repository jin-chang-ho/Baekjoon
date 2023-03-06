import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	
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
		for (int i = 1; i <= N; i++) {
			arr[i] = i; 
		}
	}
	
	static boolean unionSet(int first, int second) {
		int one = findSet(first);
		int two = findSet(second);
		
		if (one == two) {
			return false;
		}
		
		arr[two] = one;
		return true;
	}
	
	static int findSet(int find) {
		if (arr[find] == find) {
			return find;
		}
		
		return arr[find] = findSet(arr[find]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		Data[] dataArr = new Data[M];
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			dataArr[i] = new Data(from, to, weight);
		}
		
		Arrays.sort(dataArr);
		
		arr = new int[N+1];
		
		makeSet();
		
		int result = 0;
		int V = 0;
		
		for (int i = 0; i < M; i++) {
			if (unionSet(arr[dataArr[i].from], arr[dataArr[i].to])) {
				result += dataArr[i].weight;
				V++;
			}
			
			if (V == N - 1) {
				break;
			}
		}
		
		System.out.println(result);
	}
}