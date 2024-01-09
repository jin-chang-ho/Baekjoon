import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2]; // Limit, Score
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int[] a : arr) {
			if (pq.size() < a[0]) {
				pq.offer(a[1]);
			} else {
				if (pq.peek() < a[1]) {
					pq.poll();
					pq.offer(a[1]);
				}
			}
		}
		
		int answer = 0;
		
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
	}
}