import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			String order = br.readLine();
			int N = Integer.parseInt(br.readLine());
			
			Deque<Integer> queue = new ArrayDeque<>();
			
			if (N == 0) {
				String temp = br.readLine();
			} else if (N == 1) {
				String temp = br.readLine();
				queue.add(Integer.parseInt(temp.substring(1, temp.length() - 1)));
			} else if (N > 1) {
				StringTokenizer st = new StringTokenizer(br.readLine(), ",");
				
				for (int i = 0; i < N; i++) {
					if (i == 0) {
						String temp = st.nextToken();
						queue.add(Integer.parseInt(temp.substring(1)));
						continue;
					}
					
					if (i == N - 1) {
						String temp = st.nextToken();
						queue.add(Integer.parseInt(temp.substring(0, temp.length() - 1)));
						break;
					}
					
					queue.add(Integer.parseInt(st.nextToken()));
				}
			}
			
			boolean can = true;
			boolean checkWhere = false; // false면 정방향, true면 역방향
			
			for (int i = 0; i < order.length(); i++) {
				char o = order.charAt(i);
				
				if (o == 'R') {
					if (checkWhere == false) {
						checkWhere = true;
					} else {
						checkWhere = false;
					}
					
				} else if (o == 'D') {
					if (queue.isEmpty()) {
						can = false;
						break;
					} else {
						if (checkWhere == false) {
							queue.pollFirst();
						} else {
							queue.pollLast();
						}
					}
				}
			}
			
			if (can) {
				sb.append("[");
				
				while (!queue.isEmpty()) {
					if (queue.size() == 1) {
						sb.append(queue.poll());
						break;
					}
					
					if (checkWhere == false) {
						sb.append(queue.pollFirst() + ",");
					} else {
						sb.append(queue.pollLast() + ",");
					}
				}
				
				sb.append("]\n");
				
			} else {
				sb.append("error\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}