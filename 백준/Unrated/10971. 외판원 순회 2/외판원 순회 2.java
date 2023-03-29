import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int[] chaseArr;
	static boolean[] check;
	static int answer = Integer.MAX_VALUE;
	
	static void dfs(int chase) {
		if (chase == N) {
			int value = 0;
			
			for (int i = 0; i < N - 1; i++) {
				if (arr[chaseArr[i]][chaseArr[i+1]] == 0) {
					return;
				}
				
				value += arr[chaseArr[i]][chaseArr[i+1]];
			}
			
			if (arr[chaseArr[N-1]][chaseArr[0]] == 0) {
				return;
			}
			
			value += arr[chaseArr[N-1]][chaseArr[0]];
			
			if (answer > value) {
				answer = value;
			}
		}
		
		for (int i = 0; i < N; i++) {
			if (check[i] == true) {
				continue;
			}
			
			chaseArr[chase] = i;
			check[i] = true;
			dfs(chase + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}			
		}
		
		chaseArr = new int[N];
		check = new boolean[N]; 
		dfs(0);
	
		System.out.println(answer);
	}
}