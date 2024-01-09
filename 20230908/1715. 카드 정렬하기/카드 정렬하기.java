import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int number = Integer.parseInt(br.readLine());
			pq.offer(number);
		}
		
		int answer = 0;
		
		while (pq.size() != 1) {
			int first = pq.poll();
			int second = pq.poll();
			
			answer += first;
			answer += second;
			
			pq.offer(first + second);
		}
		
		System.out.println(answer);
	}
}