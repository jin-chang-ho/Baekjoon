import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] numArr;
	static char[] opeArr;
	
	static char[] chaseArr;
	static boolean[] checkArr;
	
	static int maxValue = Integer.MIN_VALUE;
	static int minValue = Integer.MAX_VALUE;
	
	static void per(int n, int chase) {
		if (n == N - 1) {
			int firstVal = numArr[0];
			
			for (int i = 1; i < N; i++) {
				int secondVal = numArr[i];
				
				if (chaseArr[i-1] == '+') {
					firstVal += secondVal;
				} else if (chaseArr[i-1] == '-') {
					firstVal -= secondVal;
				} else if (chaseArr[i-1] == '*') {
					firstVal *= secondVal;
				} else {
					boolean minus = false;
					if (firstVal < 0) {
						minus = true;
					}
					
					firstVal = Math.abs(firstVal) / secondVal;
					if (minus) {
						firstVal = firstVal * -1;
					}
				}
			}
			
			maxValue = Math.max(maxValue, firstVal);
			minValue = Math.min(minValue, firstVal);
			
			return;
		}
		
		for (int i = 0; i < N-1; i++) {
			if (checkArr[i]) {
				continue;
			}
			
			chaseArr[chase] = opeArr[i];
			checkArr[i] = true;
			per(n + 1, chase + 1);
			checkArr[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		numArr = new int[N];
		for (int i = 0; i < N; i++) {
			numArr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		
		opeArr = new char[N-1];
		int chaseIndex = 0;
		for  (int i = 0; i < 4; i++) {
			int opeCount = Integer.parseInt(st.nextToken());
			
			if (opeCount != 0) {
				for (int j = 0; j < opeCount; j++) {
					if (i == 0) {
						opeArr[chaseIndex] = '+';
					} else if (i == 1) {
						opeArr[chaseIndex] = '-';
					} else if (i == 2) {
						opeArr[chaseIndex] = '*';
					} else {
						opeArr[chaseIndex] = '/';
					}
					
					chaseIndex++;
				}
			}
		}
		
		chaseArr = new char[N-1];
		checkArr = new boolean[N-1];
		per(0, 0);
		
		System.out.println(maxValue);
		System.out.println(minValue);
	}
}