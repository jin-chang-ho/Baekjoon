import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int L;
	static int R;
	static int X;
	static int[] A;
	
	static int answer = 0;
	static int chaseVal;
	static int[] chaseArr;
	
	static void com(int start, int chase) {
		if (chase == chaseVal) {
			int sum = 0;
			int minVal = Integer.MAX_VALUE;
			int maxVal = Integer.MIN_VALUE;
			
			for (int cha : chaseArr) {
				sum += cha;
				minVal = Math.min(minVal, cha);
				maxVal = Math.max(maxVal, cha);
			}
			
			if (sum < L || sum > R) {
				return;
			}
			
			if ((maxVal - minVal) < X) {
				return;
			}
			
			answer++;
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			chaseArr[chase] = A[i];
			com(i+1, chase+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 2; i <= N; i++) {
			chaseArr = new int[i];
			chaseVal = i;
			
			com(0, 0);
		}
		
		System.out.println(answer);
	}
}