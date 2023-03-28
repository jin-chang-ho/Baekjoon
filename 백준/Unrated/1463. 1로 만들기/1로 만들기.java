import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		
		int[] arr = new int[number+1];
		
		for (int i = 2; i <= number; i++) {
			arr[i] = arr[i-1] + 1;
			
			if (i % 2 == 0) { 
				arr[i] = Math.min(arr[i], arr[i/2] + 1);
			}
			
			if (i % 3 == 0) {
				arr[i] = Math.min(arr[i], arr[i/3] + 1);
			}
		}
		
		System.out.println(arr[number]);
	}
}