import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	static PriorityQueue<int[]> wholePQ; // 전체 블록 그룹을 넣을 pq
	
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void checkGroup(int x, int y, int[][] arr) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]> () { // 블럭의 x, y 저장할 예정
			@Override
			public int compare(int[] arr1, int[] arr2) {
				if (arr1[1] == arr2[1]) {
					return arr1[0] - arr2[0];
				}
				
				return arr1[1] - arr2[1];
			}
		});
		
		int base = arr[y][x];
		pq.offer(new int[] {x, y});
		
		arr[y][x] = -1;
		
		Queue<int[]> queue = new ArrayDeque<>(); // x, y
		queue.offer(new int[] {x, y});
		
		int count = 0; // 블럭 수를 셀 변수
		int rainbowCount = 0; // 무지개 블럭을 셀 변수
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int cx = output[0] + dx[i];
				int cy = output[1] + dy[i];
				
				if (0 <= cx && cx < N && 0 <= cy && cy < N && (arr[cy][cx] == base || arr[cy][cx] == 0)) {
					if (arr[cy][cx] == base) {
						pq.offer(new int[] {cx, cy});
					}
					
					if (arr[cy][cx] == 0) {
						rainbowCount++;
					}
					
					arr[cy][cx] = -1;
					queue.offer(new int[] {cx, cy});
				}
			}
			
			count++;
		}
		
		if (count >= 2) {
			int[] output = pq.poll();
			
			wholePQ.offer(new int[] {output[0], output[1], rainbowCount, count});
		}
	}
	
	static void gravity() {
		boolean check = false;
		int base = -1;
		
		for (int i = 0 ; i < N; i++) {
			for (int j = N - 1; j >= 0; j--) {
				if (check == false && arr[j][i] == -2) {
					base = j;
					check = true;
					continue;
				}
				
				if (arr[j][i] == -1) {
					base = j;
					check = false;
					continue;
				}
			
				if (check == true && arr[j][i] >= 0) {
					arr[base][i] = arr[j][i];
					arr[j][i] = -2;
					
					check = false;
					
					for (int k = base - 1; k >= j; k--) {
						if (arr[k][i] == -2) {
							base = k;
							check = true;
							break;
						}
					}
				}
			}
			
			check = false;
			base = -1;
		}
	}
	
	static void rotate() {
		int[][] tempArr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempArr[i][j] = arr[j][N-1-i];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = tempArr[i][j];
			}
		}
		
	}
	 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int score = 0;
		while (true) {
			wholePQ = new PriorityQueue<>(new Comparator<int[]> () {
				@Override
				public int compare(int[] arr1, int[] arr2) { // 기준 블럭의 x, y, 무지개 블럭 갯수, 일반 블럭 갯수
					if (arr1[3] == arr2[3]) {
						if (arr1[2] == arr2[2]) {
							if (arr1[1] == arr2[1]) {
								return -(arr1[0] - arr2[0]);
							}
							
							return -(arr1[1] - arr2[1]);
						}	
						return -(arr1[2] - arr2[2]);
					}
					return -(arr1[3] - arr2[3]);
				}	
			});
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0) {
						int[][] tempArr = new int[N][N];
						
						for (int k = 0; k < N; k++) {
							for (int l = 0; l < N; l++) {
								tempArr[k][l] = arr[k][l];
							}
						}
						
						checkGroup(j, i, tempArr);
					}
				}
			}
			
			int[] output = new int[4]; // 가장 큰 블럭 그룹
			if (wholePQ.isEmpty()) {
				break;
			} else {
				output = wholePQ.poll();
			}
			
			score += (output[3] * output[3]);
			
			Queue<int[]> queue = new ArrayDeque<>(); // x, y
			queue.offer(new int[] {output[0], output[1]});
			
			int base = arr[output[1]][output[0]];
			arr[output[1]][output[0]] = -2;
			
			while (!queue.isEmpty()) {
				int[] queueOutput = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = queueOutput[0] + dx[i];
					int cy = queueOutput[1] + dy[i];
					
					if (0 <= cx && cx < N && 0 <= cy && cy < N && (arr[cy][cx] == base || arr[cy][cx] == 0)) {
						arr[cy][cx] = -2;
						queue.offer(new int[] {cx, cy});
					}
				}
			}
			
			gravity();

			rotate();
			
			gravity();
		}
		
		System.out.println(score);
	}
}
