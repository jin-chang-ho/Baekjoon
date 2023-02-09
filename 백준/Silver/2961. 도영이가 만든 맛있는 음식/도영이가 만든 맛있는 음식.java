import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int minDiff = 1000000000;
	static int N;
	static int arr[][];
	static boolean[] check;
	
	static void dfs(int chase) {
		if (chase == N) {
			int sour = 1;
			int wo = 0;
			boolean swi = false;
			
			for (int i = 0; i < N; i++) {
				if (check[i] == true) {
					sour *= arr[i][0];
					wo += arr[i][1];
					swi = true;
				}
			}
			
			if (swi) {
				int diff = Math.abs(sour - wo);
				
				if (diff < minDiff) {
					minDiff = diff;
				}
			}
			
			return;
		}
		
		check[chase] = true;
		dfs(chase + 1);
		check[chase] = false;
		dfs(chase + 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][2]; 
		check = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			int[] rowArr = new int[2];
			
			st = new StringTokenizer(br.readLine());
			rowArr[0] = Integer.parseInt(st.nextToken());
			rowArr[1] = Integer.parseInt(st.nextToken());
			
			arr[i] = rowArr;
		}
		
		dfs(0);
		
		System.out.println(minDiff);
	}
}