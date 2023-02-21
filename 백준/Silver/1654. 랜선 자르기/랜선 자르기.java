import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int N; 
	static int K;
	
	public static long findMax(long min, long max) {
		while (min < max) {
			long mid = (long)(min + max) / 2;
			
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += arr[i] / mid;
			}
			
			if (sum < K) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}
		
		return min - 1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		System.out.println(findMax(0, (long)arr[N-1] + 1));
	}
}