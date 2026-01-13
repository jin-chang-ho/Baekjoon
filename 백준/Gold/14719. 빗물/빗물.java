import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		if (W <= 1) {
			System.out.println(0);
		}
		
		st = new StringTokenizer(br.readLine());
		
		int[] height = new int[W];
		
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;

		for (int i = 0; i < W - 1; i++) {	
			int leftIndex = -1;
			
			for (int j = i-1; j >= 0; j--) {
				if (height[j] > height[i]) {
					if (leftIndex == -1) {
						leftIndex = j;
					} else {
						if (height[j] > height[leftIndex]) {
							leftIndex = j;
						}
					}
				}
			}
			
			if (leftIndex == -1) {
				continue;
			}
			
			int rightIndex = -1;
			
			for (int j = i+1; j < W; j++) {
				if (height[j] > height[i]) {
					if (rightIndex == -1) {
						rightIndex = j;
					} else {
						if (height[j] > height[rightIndex]) {
							rightIndex = j;
						}
					}
				}
			}
			
			if (rightIndex == -1) {
				continue;
			}
			
			//System.out.println("i : " + i);
			//System.out.println("leftIndex : " + leftIndex);
			//System.out.println("rightIndex : " + rightIndex);
			
			answer += (Math.min(height[leftIndex], height[rightIndex]) - height[i]);
			
			//System.out.println("answer : " + answer);
		}
		
		System.out.println(answer);
	}
}