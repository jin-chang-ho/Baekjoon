import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int mountChase;
	static int[] checkArr;
	
	static void makeSet() {
		for (int i = 2; i < mountChase; i++) {
			checkArr[i] = i;
		}
	}
	
	static boolean makeSet(int a, int b) {
		int first = findSet(a);
		int second = findSet(b);
		
		if (first == second) {
			return false;
		}
		
		checkArr[second] = first;
		return true;
	}
	
	static int findSet(int a) {
		if (a == checkArr[a]) {
			return a;
		}
		
		return checkArr[a] = findSet(checkArr[a]);
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		mountChase = 2;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					Queue<int[]> queue = new ArrayDeque<>();
					queue.offer(new int[] {j, i}); // x, y
					arr[i][j] = mountChase;
					
					while (!queue.isEmpty()) {
						int[] output = queue.poll();
						
						for (int k = 0; k < 4; k++) {
							int cx = output[0] + dx[k];
							int cy = output[1] + dy[k];
							
							if (0 <= cx && cx < M && 0 <= cy && cy < N && arr[cy][cx] == 1) {
								queue.offer(new int[] {cx, cy});
								arr[cy][cx] = mountChase;
							}
						}
					}
					
					mountChase++;
				}
			}
		}
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] arr1, int[] arr2) {
				return arr1[2] - arr2[2];
			}
				
		});
		
		// 가로 고려
		for (int i = 0; i < N; i++) {
			int zeroCount = 0;
			int start = -1;
			boolean check = false;
			
			for (int j = 1; j < M; j++) {
				if (check && arr[i][j] != 0) {
					if (zeroCount >= 2 && start != arr[i][j]) {
						pq.offer(new int[] {start, arr[i][j], zeroCount});
					}
					start = -1;
					zeroCount = 0;
					check = false;
				}
				
				if (arr[i][j] == 0 && arr[i][j-1] != 0) {
					start = arr[i][j-1];
					check = true;
				}
				
				if (check) {
					zeroCount++;
				}
			}
		}
		
		// 세로 고려
		for (int i = 0; i < M; i++) {
			int zeroCount = 0;
			int start = -1;
			boolean check = false;
			
			for (int j = 1; j < N; j++) {
				if (check && arr[j][i] != 0) {
					if (zeroCount >= 2 && start != arr[j][i]) {
						pq.offer(new int[] {start, arr[j][i], zeroCount});
					}
					start = -1;
					zeroCount = 0;
					check = false;
				}
				
				if (arr[j][i] == 0 && arr[j-1][i] != 0) {
					start = arr[j-1][i];
					check = true;
				}
				
				if (check) {
					zeroCount++;
				}
			}
		}
		
		checkArr = new int[mountChase];
		makeSet();
		
		int answer = 0;
		while (!pq.isEmpty()) {
			int[] output = pq.poll();
			
			if (makeSet(output[0], output[1])) {
				answer += output[2];
			}
 		}

		int number = findSet(checkArr[2]);
		
		for (int i = 3; i < mountChase; i++) {
			if (findSet(checkArr[i]) != number) {
				System.out.println("-1");
				return;
			}
		}
		
		System.out.println(answer);
	}
}
