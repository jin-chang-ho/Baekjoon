import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] check = new int[N+1];
		int[] answer = new int[N];
		
		List<Integer>[] map = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<Integer>();
		}
		
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<>() {
			@Override
			public int compare(Integer n1, Integer n2) {
				return n1 - n2;
			}
		});
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			
			check[next]++;
			map[first].add(next);
		}
		
		for (int i = 1; i <= N; i++) {
			if (check[i] == 0) {
				pq.offer(i);
			}
		}
		
		int index = 0;
		
		while (!pq.isEmpty()) {
			int output = pq.poll();
			
			for (int value : map[output]) {
				check[value]--;
				
				if (check[value] == 0) {
					pq.offer(value);
				}
			}
			
			answer[index] = output;
			index++;
		}
		
		for (int i = 0; i < N; i++) {
			sb.append(answer[i] + " ");
		}
		
		sb.append("\n");
		
		System.out.println(sb.toString());
	}
}