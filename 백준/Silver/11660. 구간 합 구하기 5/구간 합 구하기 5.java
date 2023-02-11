import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] sumArr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			sumArr[i][0] = arr[i][0];
			for (int j = 1; j < N; j++) {
				sumArr[i][j] = sumArr[i][j-1] + arr[i][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int firstRow = Integer.parseInt(st.nextToken()) - 1;
			int firstCol = Integer.parseInt(st.nextToken()) - 1;
			int secondRow = Integer.parseInt(st.nextToken()) - 1;
			int secondCol = Integer.parseInt(st.nextToken()) - 1;
			
			int sum = 0;
			
			for (int j = firstRow; j <= secondRow; j++) {
				int rowSum = sumArr[j][secondCol];
				
				if (firstCol != 0) {
					rowSum -= sumArr[j][firstCol-1];
				}
				
				sum += rowSum;
				
				
			}
			sb.append(sum + " " + '\n');
		}
		
		System.out.println(sb.toString());
	}
}