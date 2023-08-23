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
		
		int[][] arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int deadLine = Integer.parseInt(st.nextToken());
			int ramen = Integer.parseInt(st.nextToken());
			
			arr[i][0] = deadLine;
			arr[i][1] = ramen;
		}
		
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			}
		});
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int[] item : arr) {
			if (pq.size() < item[0]) {
				pq.offer(item[1]);
			} else if (pq.peek() < item[1]) {
				pq.poll();
				pq.offer(item[1]);
			}
		}
		
		int answer = 0;
		
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
	}
}