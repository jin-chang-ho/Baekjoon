import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int count = 0;
			
			while (n != 0) {
				if (n % 2 == 1) {
					sb.append(count + " ");
				}
				
				n /= 2;
				count++;
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
}