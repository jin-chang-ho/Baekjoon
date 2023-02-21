import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){

			@Override
			public int compare(int[] val1, int[] val2) {
				if (val1[0] == val2[0]) {
					return val1[1] - val2[1];
				}
				
				return val1[0] - val2[0];
			}
		});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			pq.offer(new int[] {first, second});
		}
		
		int count = 0;
		Set<Integer> chaseN = new HashSet<>();
		
		while (!pq.isEmpty()) {
			int[] tempArr = pq.poll();
			
			if (chaseN.contains(tempArr[0]) || chaseN.contains(tempArr[1])) {
				chaseN.add(tempArr[0]);
				chaseN.add(tempArr[1]);
				continue;
			}
			
			chaseN.add(tempArr[0]);
			chaseN.add(tempArr[1]);			
			count++;
		}
		
		count = count + (N - chaseN.size());
		
		System.out.println(count);
	}
}