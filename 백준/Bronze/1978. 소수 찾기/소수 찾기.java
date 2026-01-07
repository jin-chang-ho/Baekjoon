import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int answer = 0;
		
		for (int n = 1; n <= N; n++) {
			int number = Integer.parseInt(st.nextToken());
			
			if (number <= 1) {
				continue;
			}
			
			boolean check = false;
			
			for (int i = 2; i <= (int) Math.sqrt(number); i++) {
				if (number % i == 0) {
					check = true;
					break;
				}
			}
			
			if (check) {
				continue;
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
}