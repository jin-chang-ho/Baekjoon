import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[] arr;
	
	static void makeArr() {
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
	}
	
	static boolean unionArr(int f, int s) {
		int first = findArr(f);
		int second = findArr(s);
		
		if (first == second) {
			return false;
		}
		
		arr[second] = first;
		return true;
	}
	
	static int findArr(int x) {
		if (arr[x] == x) {
			return x;
		}
		
		return arr[x] = findArr(arr[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
		});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] {start, end, weight});
		}	
		
		arr = new int[N+1];
		makeArr();
		
		int answer = 0;
		int maxWeight = 0;
		int count = 0;
		
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (unionArr(output[0], output[1])) {
				answer += output[2];
				maxWeight = Math.max(maxWeight, output[2]);
				count++;
				if (count == N - 1) {
					break;
				}
			}
		}
		
		System.out.println(answer - maxWeight);
	}
}