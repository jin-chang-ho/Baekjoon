import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	static int arr[];
	
	static void makeSet() {
		for (int i = 1; i <= V; i++) {
			arr[i] = i;
		}
	}
	
	static boolean unionSet(int f, int s) {
		int first = findSet(f);
		int second = findSet(s);
		
		if (first == second) {
			return false;
		}
		
		arr[second] = first;
		return true;
	}
	
	static int findSet(int x) {
		if (x == arr[x]) {
			return x;
		}
		
		return arr[x] = findSet(arr[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
		});

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] {start, end, weight});
		}
		
		arr = new int[V+1];
		makeSet();
		
		int answer = 0;
		int count = 0;
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (unionSet(output[0], output[1])) {
				answer += output[2];
				count++;

				if (count == V - 1) {
					break;
				}
			}
		}
		
		System.out.println(answer);
	}
}