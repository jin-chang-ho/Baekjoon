import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		Queue<int[]> que = new ArrayDeque<>();
		Map<Integer, Boolean> map = new HashMap<>();
		
		int[] start = {N, 0};
		que.offer(start);
		
		map.put(N, true);
		
		while (true) {
			int[] check = que.poll();
			
			if (!map.containsKey(check[0] - 1) && check[0] - 1 >= 0) {
				int[] first = {check[0] - 1, check[1] + 1};
				que.offer(first);
				map.put(check[0] - 1, true);
			}
			
			if (!map.containsKey(check[0] + 1) && check[0] + 1 <= 100000) {
				int[] second = {check[0] + 1, check[1] + 1};
				que.offer(second);
				map.put(check[0] + 1, true);
			}
			
			if (!map.containsKey(check[0] * 2) && check[0] != 0 && check[0] * 2 <= 100000) {
				int[] third = {check[0] * 2, check[1] + 1};
				que.offer(third);
				map.put(check[0] * 2, true);
			}
			
			if (check[0] == K) {
				System.out.println(check[1]);
				break;
			}
		}
		
		sc.close();
	}
}