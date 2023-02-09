import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr, brr;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void permutation(int chase) {
		if (chase == M) {
			for (int i = 0; i < M; i++) {
				sb.append(brr[i] + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (check[i] == true) {
				continue;
			}
			
			check[i] = true;
			brr[chase] = arr[i];
			permutation(chase + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		brr = new int[M];
		check = new boolean[N];
		
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = i;
			check[i - 1] = false;
		}
		
		permutation(0);
		
		System.out.println(sb.toString());
	}
}