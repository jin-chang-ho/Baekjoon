import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader st = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer I1, Integer I2) {
				if (Math.abs(I1) == Math.abs(I2)) {
					return I1 - I2;
				}
				
				return Math.abs(I1) - Math.abs(I2);
			}	
		});
		
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.readLine());
			
			if (input == 0) {
				if (pq.isEmpty()) {
					sb.append(0 + "\n");
				} else {
					sb.append(pq.poll() + "\n");
				}
			} else {
				pq.offer(input);
			}
		}
		
		System.out.println(sb.toString());
	}
}