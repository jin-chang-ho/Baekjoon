import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		for (int i = 1; i <= N; i++) { 
			queue.offer(i);
		}
		
		while (queue.size() != 1) {
			queue.poll();
			
			int next = queue.poll();
			queue.offer(next);
		}
		
		System.out.println(queue.poll());
		
		sc.close();
	}
}