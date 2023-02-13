import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}
		
		int count = 1;
		int chaseIndex = 0;
		
		System.out.print("<");
		for (int i = 1; i <= N; i++) {
			while (count % K != 0) {
				count++;
				chaseIndex = (chaseIndex + 1) % list.size();
			}
			
			if (i == N) {
				System.out.println(list.remove(chaseIndex % list.size()) + ">");
			} else {
				System.out.print(list.remove(chaseIndex % list.size()) + ", ");
			}
			
			count = 1;
		}
			
		sc.close();
	}
}