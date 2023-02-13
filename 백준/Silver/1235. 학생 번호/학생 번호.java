import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		String[] arr = new String[N];
		for(int i = 0; i < N; i++) {
			arr[i] = br.readLine();
		}
		
		int answer = 1;
		for (int i = arr[0].length()-1; i >= 0; i--) {
			Set<String> set = new HashSet<>();
			
			for (int j = 0; j < N; j++) {
				set.add(arr[j].substring(i));
			}
			
			if (set.size() == N) {
				break;
			}
			
			answer += 1;
		}
		sb.append(answer);
		System.out.println(sb.toString());
	}
}