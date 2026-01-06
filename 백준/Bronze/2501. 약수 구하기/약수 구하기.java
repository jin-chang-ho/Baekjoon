import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int answer = 0;
		int count = 0;
		
		for (int i = 1; i <= N; i++) {
			if (N % i == 0) {
				count++;
			}
			
			if (count == K) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}
}
