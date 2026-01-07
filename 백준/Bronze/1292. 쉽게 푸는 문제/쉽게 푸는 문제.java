import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[1001];
		
		int maxCount = 1;
		int tempCount = maxCount;
		
		for (int i = 1; i <= 1000; i++) {
			if (tempCount == 0) {
				maxCount++;
				tempCount = maxCount;
			}
			
			arr[i] = maxCount;
			
			tempCount--;
		}
		
		int[] sumArr = new int[1001];
		
		for (int i = 1; i <= 1000; i++) {
			sumArr[i] = sumArr[i-1] + arr[i];
		}
		
		System.out.println(sumArr[B] - sumArr[A-1]);
	}
}