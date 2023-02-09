import java.util.Scanner;

public class Main {
	static int[] arr, chaseArr;
	
	static void combinate(int start, int chase) {
		if (chase == 7) {
			int sum = 0;
			for (int i = 0; i < 7; i++) {
				sum += chaseArr[i];
			}
			
			if (sum == 100) {
				for (int i = 0; i < 7; i++) {
					System.out.println(chaseArr[i]);
				}
			}
			return;
		}
		else {
			for (int i = start; i < 9; i++) {
				chaseArr[chase] = arr[i];
				combinate(i + 1, chase + 1);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = new int[9];
		
		for (int i = 0; i < 9; i++) {
			arr[i] = sc.nextInt();
		}
		
		chaseArr = new int[7];
		
		combinate(0, 0);
		
		sc.close();
	}
}