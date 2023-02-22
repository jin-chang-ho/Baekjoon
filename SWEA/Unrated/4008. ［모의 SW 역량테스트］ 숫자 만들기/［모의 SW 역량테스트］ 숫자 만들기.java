import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N;
	static int[] operand;
	static int[] operator;
	static int[] chaseOperator;
	static int max = -100000001;
	static int min = 100000001;
	
	static void dfs(int chase) {
		if (chase == N - 1) {
			int result = 0;
			
			if (chaseOperator[0] == 0) {
				result = operand[0] + operand[1];
 			} else if (chaseOperator[0] == 1) {
 				result = operand[0] - operand[1];
			} else if (chaseOperator[0] == 2) {
				result = operand[0] * operand[1];
			} else {
				result = operand[0] / operand[1];
			}
			
			for (int i = 1; i < N - 1; i++) {
				if (chaseOperator[i] == 0) {
					result = result + operand[i+1];
				} else if (chaseOperator[i] == 1) {
					result = result - operand[i+1];
				} else if (chaseOperator[i] == 2) {
					result = result * operand[i+1];
				} else {
					result = result / operand[i+1];
				}
			}
			
			if (result > max) {
				max = result;
			}
			
			if (result < min) {
				min = result;	
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (operator[i] == 0) {
				continue;
			}
			
			operator[i]--;
			chaseOperator[chase] = i;
			
			dfs(chase + 1);
			
			operator[i]++;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			operator = new int[4];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
 			}
			
			operand = new int[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				operand[i] = Integer.parseInt(st.nextToken());
			}
			
			chaseOperator = new int[N - 1];
			dfs(0);
			
			sb.append("#" + tc + " " + (max - min) + "\n");
			
			max = -100000001;
			min = 100000001;
		}
		
		System.out.println(sb.toString());
	}
}