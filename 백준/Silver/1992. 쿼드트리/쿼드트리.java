import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] arr;
	
	public static void find(int start, int end, int length) {
		int sum = 0;
		
		for (int i = start; i < start + length; i++) {
			for (int j = end; j < end + length; j++) {
				sum += arr[i][j];
			}
		}
		
		if (sum == 0) {
			System.out.print("0");
			
			return;
		}
		
		if (sum == length * length) {
			System.out.print("1");
			return;
		}
		
		System.out.print("(");
		find(start, end, length / 2);
		find(start, end + length / 2, length / 2);
		find(start + length / 2, end, length / 2);
		find(start + length / 2, end + length / 2, length / 2);
		System.out.print(")");
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String tempStr = br.readLine();
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = tempStr.charAt(j) - '0';
			}
		}
		
		find(0, 0, N);
	}
}