import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		
		int aCount = 0;
		
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'a') {
				aCount++;
			}
		}
		
		int answer = Integer.MAX_VALUE;
		
		for (int i = 0; i < str.length(); i++) {
			int count = 0;
			
			for (int j = 0; j < aCount; j++) {
				int index = (i + j) % str.length();
				
				if (str.charAt(index) == 'b') {
					count++;
				}
			}
			
			answer = Math.min(answer, count);
		}
		
		System.out.println(answer);
	}
}