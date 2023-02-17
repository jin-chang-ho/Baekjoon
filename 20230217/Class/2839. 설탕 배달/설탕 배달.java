import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int count = 0;
		
		while (N > 0) {
			if (N % 5 == 0) {
				count += N / 5;
				N = 0;
				break;
			}
			
			N -= 3;
			count += 1;
		}
		
		if (N == 0) {
			System.out.println(count);
			sc.close();
			return;
		}
		
		System.out.println(-1);
		sc.close();
	}
}