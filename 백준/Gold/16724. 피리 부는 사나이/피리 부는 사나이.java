import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] move = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < M; j++) {
				move[i][j] = line.charAt(j);
			}
		}
		
		int[][] check = new int[N][M];
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (check[i][j] != 0) {
					continue;
				}
				
				Stack<int[]> stack = new Stack<>(); // x, y
				stack.push(new int[] {j, i});
				
				while (check[stack.peek()[1]][stack.peek()[0]] == 0) {
					if (move[stack.peek()[1]][stack.peek()[0]] == 'U') {
						check[stack.peek()[1]][stack.peek()[0]] = 2;
						stack.push(new int[] {stack.peek()[0], stack.peek()[1] - 1});
					} else if (move[stack.peek()[1]][stack.peek()[0]] == 'D') {
						check[stack.peek()[1]][stack.peek()[0]] = 2;
						stack.push(new int[] {stack.peek()[0], stack.peek()[1] + 1});
					} else if (move[stack.peek()[1]][stack.peek()[0]] == 'L') {
						check[stack.peek()[1]][stack.peek()[0]] = 2;
						stack.push(new int[] {stack.peek()[0] - 1, stack.peek()[1]});
					} else {
						check[stack.peek()[1]][stack.peek()[0]] = 2;
						stack.push(new int[] {stack.peek()[0] + 1, stack.peek()[1]});
					}
				}
				
				if (check[stack.peek()[1]][stack.peek()[0]] == 2) {
					answer++;
				}
				
				while (!stack.isEmpty()) {
					int[] output = stack.pop();
					check[output[1]][output[0]] = 1;
				}
			}
		}
		
		System.out.println(answer);
	}
}