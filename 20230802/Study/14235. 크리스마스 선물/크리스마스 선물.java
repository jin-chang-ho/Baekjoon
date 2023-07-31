import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return -(i1 - i2);
			}
			
		});
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int choice = Integer.parseInt(st.nextToken());
			
			if (choice == 0) {
				if (pq.isEmpty()) {
					sb.append("-1\n");
				} else {
					int output = pq.poll();
					sb.append(output + "\n");
				}
			} else {
				for (int j = 0; j < choice; j++) {
					int number = Integer.parseInt(st.nextToken());
					pq.offer(number);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}