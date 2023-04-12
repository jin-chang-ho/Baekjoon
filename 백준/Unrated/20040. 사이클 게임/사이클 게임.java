import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	
	static void makeSet() {
		for (int i = 0; i < N; i++) {
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
		if (arr[x] == x) {
			return x;
		}
		
		return arr[x] = findSet(arr[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			queue.offer(new int[] {first, second});
		}
		
		arr = new int[N];
		makeSet();

		int answer = 0;
		for (int i = 1; i <= M; i++) {
			int[] output = queue.poll();
			
			int first = output[0];
			int second = output[1];
			
			if (!unionSet(first, second)) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}
}