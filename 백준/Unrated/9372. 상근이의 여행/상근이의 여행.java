import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	
	static void makeSet() {
		for (int i = 1; i <= N; i++) {
			arr[i] = i;
		}
	}
	
	static boolean unionSet(int first, int second) {
		int f = findSet(first);
		int s = findSet(second);
		
		if (f == s) {
			return false;
		} else {
			arr[s] = f;
			return true;
		}
	}
	
	static int findSet(int find) {
		if (find == arr[find]) {
			return find;
		}
		
		return arr[find] = findSet(arr[find]);
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] E = new int[M][2];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				
				E[i] = new int[] {first, second};
			}
			
			arr = new int[N+1];
			
			makeSet();
			
			int answer = 0;
			
			for (int i = 0; i < M; i++) {
				if (unionSet(E[i][0], E[i][1])) {
					answer++;
					
					if (answer == N - 1) {
						break;
					}
				}
			}
			
			System.out.println(answer);
		}
	}
}