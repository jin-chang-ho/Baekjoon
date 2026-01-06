import java.io.*;
import java.util.*;

public class Main {
	static int[] valueArr;
	static int[] chaseArr;
	static boolean answer = false;
	
	static void comb(int n, int chase) {
		if (chase == 7) {
			int sum = 0;
			
			for (int i = 0; i < 7; i++) {
				sum += chaseArr[i];
			}
			
			if (sum == 100) {
				Arrays.sort(chaseArr);
				
				for (int i = 0; i < 7; i++) {
					System.out.println(chaseArr[i]);
					answer = true;
				}
			}
			
			return;
		}
		
		for (int i = n; i < 9; i++) {
			if (answer) {
				return;
			}
			
			chaseArr[chase] = valueArr[i];
			comb(i + 1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		valueArr = new int[9];
		for (int i = 0; i < 9; i++) {
			valueArr[i] = Integer.parseInt(br.readLine());
		}
		
		chaseArr = new int[7];
		comb(0, 0);
	}
}