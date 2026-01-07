import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int first  = Integer.parseInt(st.nextToken());
		int second = Integer.parseInt(st.nextToken());
		
		if (first < second) {
			int temp = second;
			second = first;
			first = temp;
		}
		
		int maxDiv = 1;
		int minCar = 1;
		
		for (int i = 1; i <= second; i++) {
			if ((first % i == 0) && (second % i == 0)) {
				maxDiv = i;
			}
		}
		
		minCar = maxDiv * (first / maxDiv) * (second / maxDiv);
		
		System.out.println(maxDiv);
		System.out.println(minCar);
	}
}