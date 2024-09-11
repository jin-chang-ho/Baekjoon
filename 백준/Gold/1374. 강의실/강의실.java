import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		long[][] listArr = new long[N][2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			long start = Long.parseLong(st.nextToken());
			long end = Long.parseLong(st.nextToken());
			
			listArr[i][0] = start;
			listArr[i][1] = end;
		}
		
		Arrays.sort(listArr, (a, b) -> {
			if (a[0] == b[0]) {
				return Long.compare(a[1], b[1]);
			}
			
			return Long.compare(a[0], b[0]);
		});
		
		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
	
		for (int i = 0; i < N; i++) {
			if (!pq.isEmpty() && pq.peek()[1] <= listArr[i][0]) {
				pq.poll();
			}
			
			pq.offer(new long[] {listArr[i][0], listArr[i][1]});
		}
		
		System.out.println(pq.size());
	}
}