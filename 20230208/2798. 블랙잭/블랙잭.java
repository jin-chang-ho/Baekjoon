import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] normalArr, chaseArr;
	static int maxSum = -1;
	
	static void combination(int start, int chase) {
		if (chase == 3) {
			int sum = 0;
			for (int i = 0; i < 3; i++) {
				sum += chaseArr[i];
			}
			
			
			if (sum <= M && maxSum < sum) {
				maxSum = sum;
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			chaseArr[chase] = normalArr[i];
			combination(i + 1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		normalArr = new int[N];
		chaseArr = new int[3];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			normalArr[i] = Integer.parseInt(st.nextToken());
		}
		
		combination(0, 0);
		
		System.out.println(maxSum);
	}
}