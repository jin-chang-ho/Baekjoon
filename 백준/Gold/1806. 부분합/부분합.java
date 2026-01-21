import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = 0;
		int sum = 0;
		int answer = Integer.MAX_VALUE;
		
		while (right < N) {
			if (sum < S) {
				sum += arr[right];
				right++;
			} else {
				while (sum >= S) {
					sum -= arr[left];
					left++;
				}
				
				left--;
				sum += arr[left];
				
				answer = Math.min(answer, right - left);
				
				sum += arr[right];
				right++;
			}
		}
		
		while (sum >= S) {
			sum -= arr[left];
			left++;
		}
		
		if (left == 0) {
			answer = 0;
		} else {
			left--;
			sum += arr[left];
			
			answer = Math.min(answer, right - left);
		}
		
		System.out.println(answer);
	}
}