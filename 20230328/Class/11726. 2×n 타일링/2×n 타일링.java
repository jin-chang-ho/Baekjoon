import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number = Integer.parseInt(br.readLine());
		
		if (number == 1) {
			System.out.println(1);
			return;
		}
		
		if (number == 2) {
			System.out.println(2);
			return;
		}
		
		BigInteger[] arr = new BigInteger[number];
		
		arr[0] = new BigInteger("1");
		arr[1] = new BigInteger("2");
		
		for (int i = 2; i < number; i++) {
			arr[i] = arr[i-2].add(arr[i-1]);
		}
		
		System.out.println(arr[number-1].mod(new BigInteger("10007")));
	}
}
