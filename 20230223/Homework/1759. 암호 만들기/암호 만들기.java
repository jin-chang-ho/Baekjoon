import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L;
	static int C;
	static char[] arr;
	static char[] chaseArr;
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int start, int chase) {
		if (chase == L) {
			int owCount = 0;
			int anCount = 0;
			
			for (int i = 0; i < L; i++) {
				if (chaseArr[i] == 'a' || chaseArr[i] == 'e' || chaseArr[i] == 'i' || chaseArr[i] == 'o' || chaseArr[i] == 'u') {
					owCount++;
				} else {
					anCount++;
				}
			}
			
			if (owCount >= 1 && anCount >= 2) {
				for (int i = 0; i < L; i++) {
					sb.append(chaseArr[i]);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		for (int i = start; i < C; i++) {
			chaseArr[chase] = arr[i];
			dfs(i + 1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		arr = new char[C];
		for (int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		chaseArr = new char[L];
		Arrays.sort(arr);
		
		dfs(0, 0);
		
		System.out.println(sb.toString());
	}
}