import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] pop;
	static int[][] arr;
	static boolean[] check;
	static int minDiff = Integer.MAX_VALUE;
	
	static void dfs(int chase) {
		if (chase == N) {
			int sum = 0;
			
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					sum++;
				}
			}
			
			if (0 == sum || N / 2 < sum) {
				return;
			}
			
			List<Integer> first = new ArrayList<>();
			List<Integer> second = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				if (check[i]) {
					first.add(i+1);
				} else {
					second.add(i+1);
				}
			}
			
			boolean[] firstCheck = new boolean[N+1];
			firstCheck[first.get(0)] = true;
			
			Queue<Integer> firstQueue = new ArrayDeque<>();
			firstQueue.offer(first.get(0));
			
			while (!firstQueue.isEmpty()) {
				int output = firstQueue.poll();
				
				for (int i = 1; i <= N; i++) {				
					if (arr[output][i] == 1 && firstCheck[i] == false && first.contains(i)) {
						firstQueue.offer(i);
						firstCheck[i] = true;
					}
				}
			}
			
			int firstSum = 0;
			
			for (int i = 1; i <= N; i++) {
				if (firstCheck[i] == true) {
					firstSum++;
				}
			}
			
			if (firstSum != first.size()) {
				return;
			}
			
			boolean[] secondCheck = new boolean[N+1];
			secondCheck[second.get(0)] = true;
			
			Queue<Integer> secondQueue = new ArrayDeque<>();
			secondQueue.offer(second.get(0));
			
			while (!secondQueue.isEmpty()) {
				int output = secondQueue.poll();
				
				for (int i = 1; i <= N; i++) {				
					if (arr[output][i] == 1 && secondCheck[i] == false && second.contains(i)) {
						secondQueue.offer(i);
						secondCheck[i] = true;
					}
				}
			}
			
			int secondSum = 0;
			
			for (int i = 1; i <= N; i++) {
				if (secondCheck[i] == true) {
					secondSum++;
				}
			}
			
			if (secondSum != second.size()) {
				return;
			}
			
			int firstpop = 0;
			
			for (int index : first) {
				firstpop += pop[index];
			}
			
			int secondpop = 0;
			
			for (int index : second) {
				secondpop += pop[index];
			}
			
			int diff = Math.abs(firstpop - secondpop);
			
			if (minDiff > diff) {
				minDiff = diff;
			}
			
			return;
		}
		
		check[chase] = true;
		dfs(chase + 1);
		check[chase] = false;
		dfs(chase + 1);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		pop = new int[N+1];
		for (int i = 1; i <= N; i++) {
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		arr = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int number = Integer.parseInt(st.nextToken());
			for (int j = 0; j < number; j++) {
				int index = Integer.parseInt(st.nextToken());
				arr[i][index] = 1;
			}
		}
		
		check = new boolean[N];
		dfs(0);
		
		if (minDiff == Integer.MAX_VALUE) {
			System.out.println("-1");
			return;
		}
		
		System.out.println(minDiff);
	}
}