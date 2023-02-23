import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {N, 0});
		
		int[] arr = new int[1000001];
		
		while (true) {
			int[] info = queue.poll();
			arr[info[0]] = 1;
			
			if (info[0] == K) {
				System.out.println(info[1]);
				sc.close();
				return;
			}
			
			if (0 <= info[0] - 1 && arr[info[0] - 1] == 0) {
				queue.offer(new int[] {info[0] - 1, info[1] + 1});
				arr[info[0] - 1] = 1;
			}
			
			if (info[0] + 1 <= 100000 && arr[info[0] + 1] == 0) {
				queue.offer(new int[] {info[0] + 1, info[1] + 1});
				arr[info[0] + 1] = 1;
			}
			
			if (info[0] != 0 && info[0] * 2 <= 100000 && arr[info[0] * 2] == 0) {
				queue.offer(new int[] {info[0] * 2, info[1] + 1});
				arr[info[0] * 2] = 1;
			}
		}
	}
}