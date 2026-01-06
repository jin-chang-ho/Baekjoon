import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int answer = 0;
		int chase = 0;
		
		for (int i = 0; i < 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int minus = Integer.parseInt(st.nextToken());
			int plus = Integer.parseInt(st.nextToken());
			
			chase -= minus;
			chase += plus;
			
			answer = Math.max(answer, chase);
		}
		
		System.out.println(answer);
	}
}