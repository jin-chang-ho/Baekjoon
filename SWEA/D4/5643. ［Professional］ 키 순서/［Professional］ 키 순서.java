import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N+1][N+1];
			
			int M = Integer.parseInt(br.readLine());
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				arr[first][second] = 1;
			}
			
			int count = 0;
			for (int i = 1; i <= N; i++) {
				boolean[] check = new boolean[N+1];
				check[i] = true;
				
				Queue<Integer> in = new ArrayDeque<>();
				in.offer(i);
				
				while (!in.isEmpty()) {
					int output = in.poll();
					
					for (int j = 1; j <= N; j++) {
						if (check[j] == false && arr[j][output] == 1) {
							in.add(j);
							check[j] = true;
						}
					}
				}
				
				Queue<Integer> out = new ArrayDeque<>();
				out.offer(i);
				
				while (!out.isEmpty()) {
					int output = out.poll();
					
					for (int j = 1; j <= N; j++) {
						if (check[j] == false && arr[output][j] == 1) {
							out.add(j);
							check[j] = true;
						}
					}
				}
				
				boolean checkAdd = true;
				for (int j = 1; j <= N; j++) {
					if (check[j] == false) {
						checkAdd = false;
						break;
					}
				}
				
				if (checkAdd) {
					count++;
				}
			}
			
			sb.append("#" + tc + " " + count + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
