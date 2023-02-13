import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		String[] arr = new String[N];
		for(int i = 0; i < N; i++) {
			arr[i] = sc.next();
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
		System.out.println(answer);
		
		sc.close();
	}
}