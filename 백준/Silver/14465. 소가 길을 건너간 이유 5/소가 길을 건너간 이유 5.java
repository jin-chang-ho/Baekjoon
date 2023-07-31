import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		boolean[] way = new boolean[N+1];
		
		for (int i = 0; i < B; i++) {
			int index = Integer.parseInt(br.readLine());
			way[index] = true;
		}
		
		int answer = 100001;
		
		for (int i = 1; i <= N - K + 1; i++) {
			int sum = 0;
			
			for (int j = i; j < i + K; j++) {
				if (way[j] == false) {
					sum++;
				}
			}
			
			answer = Math.min(answer, K - sum);
		}
		
		System.out.println(answer);
	}
}