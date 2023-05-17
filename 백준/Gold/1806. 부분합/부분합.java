import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		long[] arr = new long[N];
		long[] sumArr = new long[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
			sumArr[i] = arr[i];
		}
		
		for (int i = 1; i < N; i++) {
			sumArr[i] = sumArr[i] + sumArr[i-1];
		}
		
		if (sumArr[N-1] < M) {
			System.out.println(0);
			return;
		}
		
		int length = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			if (i != 0 && sumArr[N-1] - sumArr[i-1] < M) {
				break;
			}
			
			long findNumber = 0;
			
			if (i == 0) {
				findNumber = M;
			} else {
				findNumber = sumArr[i-1] + M;
			}
		
			int left = i;
			int right = N - 1;
			
			boolean exactFind = false;
			
			while (left <= right) {
				int mid = (left + right) / 2;
				
				if (sumArr[mid] == findNumber) {
					length = Math.min(length, mid - i + 1);
					exactFind = true;
					break;
				} else if (sumArr[mid] < findNumber) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			
			if (!exactFind) {
				length = Math.min(length, left - i + 1);
			}
		}
		
		System.out.println(length);
	}
}