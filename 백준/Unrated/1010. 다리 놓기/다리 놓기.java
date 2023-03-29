import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[31][31];
		
		for (int i = 1; i <= 30; i++) {
			arr[i][i] = 1;
			arr[1][i] = i;
		}
		
		for (int i = 2; i < 30; i++) {
			for (int j = i + 1; j <= 30; j++) {
				arr[i][j] = arr[i-1][j-1] + arr[i][j-1];
			}
		}
		
		
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			sb.append(arr[N][M] + "\n");
		}
		
		System.out.println(sb.toString());
	}
}