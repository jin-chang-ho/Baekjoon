import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] chaseArr;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n][];
		
		for (int i = 1; i <= n; i++) {
			arr[i-1] = new int[i]; 
		}
		
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < i; j++) {
				arr[i-1][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0) {
					arr[i][j] = arr[i][j] + arr[i-1][j];
					continue;
				}
				
				if (j == i) {
					arr[i][j] = arr[i][j] + arr[i-1][j-1];
					continue;
				}
				
				int first = arr[i][j] + arr[i-1][j];
				int second = arr[i][j] + arr[i-1][j-1];
				
				arr[i][j] = Math.max(first, second);
			}
		}
		
		int answer = -1;
		
		for (int i = 0; i < n; i++) {
			if (answer < arr[n-1][i]) {
				answer = arr[n-1][i];
			}
		}

		System.out.println(answer);
	}
}