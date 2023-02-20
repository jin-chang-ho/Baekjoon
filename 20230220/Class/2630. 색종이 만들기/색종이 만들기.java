import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int white;
	static int blue;
	
	static void find(int start, int end, int length) {
		int sum = 0;
		
		for (int i = start; i < start + length; i++) {
			for (int j = end; j < end + length; j++) {
				sum += arr[i][j];
			}
		}
		
		if (sum == 0) {
			white++;
			return;
		}
		
		if (sum == length * length) {
			blue++;
			return;
		}
		
		find(start, end, length / 2);
		find(start + length / 2, end, length / 2);
		find(start, end + length / 2, length / 2);
		find(start + length / 2, end + length / 2, length / 2);
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
		
		find(0, 0, N);
		
		System.out.println(white);
		System.out.println(blue);
	}
}