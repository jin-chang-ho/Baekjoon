import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		
		int sum = 0;
		int minVal = -1;
		
		for (int i = M; i <= N; i++) {
			if (i <= 1) {
				continue;
			}
			
			boolean check = true;
			
			for (int j = 2; j <= (int) Math.sqrt(i); j++) {
				if (i % j == 0) {
					check = false;
					break;
				}
			}
			
			if (check) {
				sum += i;
				
				if (minVal == -1) {
					minVal = i;
				}
			}
			
		}
		
		if (minVal != -1) {
			System.out.println(sum);
		}
		
		System.out.println(minVal);
		
	}
}