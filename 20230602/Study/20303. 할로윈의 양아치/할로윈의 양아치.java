import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] candy = new int[N];
		
		for (int i = 0; i < N; i++) {
			candy[i] = Integer.parseInt(st.nextToken());
		}
		
		List<Integer>[] listArray = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			listArray[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			
			listArray[first].add(second);
			listArray[second].add(first);
		}
		
		List<Integer> cry = new ArrayList<>();
		List<Integer> get = new ArrayList<>();
		boolean[] check = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			if (check[i]) {
				continue;
			}
			
			int c = 1;
			int g = candy[i];
			check[i] = true;
			
			Queue<Integer> queue = new ArrayDeque<>();
			queue.offer(i);
			
			while (!queue.isEmpty()) {
				int output = queue.poll();
				
				for (int index : listArray[output]) {
					if (check[index]) {
						continue;
					}
					
					check[index] = true;
					c++;
					g += candy[index];
					queue.offer(index);
				}
			}
			
			cry.add(c);
			get.add(g);
		}
		
		long[] dp = new long[K+1];
		
		for(int i = 0; i < cry.size(); i++) {
			for(int j = K; j - cry.get(i) > 0; j--) {
				dp[j] = Math.max(dp[j], dp[j-cry.get(i)] + get.get(i));
			}
		}
		
		System.out.println(dp[K]);
	}
}