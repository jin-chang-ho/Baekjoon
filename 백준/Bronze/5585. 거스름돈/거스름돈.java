import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int pay = sc.nextInt();
		
		int charge = 1000 - pay;
		
		int count = 0;
		
		while (charge >= 500) {
			charge = charge - 500;
			count++;
		}
		
		while (charge >= 100) {
			charge = charge - 100;
			count++;
		}
		
		while (charge >= 50) {
			charge = charge - 50;
			count++;
		}
		
		while (charge >= 10) {
			charge = charge - 10;
			count++;
		}
		
		while (charge >= 5) {
			charge = charge - 5;
			count++;
		}
		
		while (charge >= 1) {
			charge = charge - 1;
			count++;
		}
		
		System.out.println(count);
		
		sc.close();
	}
}