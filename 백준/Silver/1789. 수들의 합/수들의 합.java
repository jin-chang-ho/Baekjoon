import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(br.readLine());
		
		long n = 1;
		
		while (true) {
			if (S < (n+1)*(n+2)/2) {
				break;
			}
			
			n++;
		}
		
		System.out.println(n);
	}
}