import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		int targetNumber = (int) Math.pow(2, 10) - 1;
		
		for (int t = 1; t <= tc; t++) {
			int N = Integer.parseInt(br.readLine());
			int count = 1;
			int value = 0;
			
			while (true) {
				int number = N * count;
				
				while (number != 0) {
					value = value | (1 << (number % 10));
					number = number / 10;
				}
				
				if (value == targetNumber) {
					break;
				}
				
				count++;
			}
			
			sb.append("#" + t + " " + N * count + "\n");
		}
		
		System.out.println(sb.toString());
	}
}