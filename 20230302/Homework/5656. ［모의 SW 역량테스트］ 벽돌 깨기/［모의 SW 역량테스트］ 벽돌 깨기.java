import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int W;
	static int H;
	static int[] chaseArr;
	static int[][] arr;
	static int[][] tempArr;
	static int minCount = 0;
	
	static void brickbreak(int x, int y) {	
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {x, y, tempArr[y][x] - 1}); // x, y 저장
		
		while (!queue.isEmpty()) {
			int[] output = queue.poll(); // x, y 출력
			
			int range = output[2];
			tempArr[output[1]][output[0]] = 0;
			
			for (int i = output[1]; i >= output[1] - range; i--) {
				if (0 <= i && tempArr[i][output[0]] > 0) {
					queue.offer(new int[] {output[0], i, tempArr[i][output[0]] - 1});
				}
			}
            
            for (int i = output[1]; i <= output[1] + range; i++) {
				if (i < H && tempArr[i][output[0]] > 0) {
					queue.offer(new int[] {output[0], i, tempArr[i][output[0]] - 1});
				}
			}
			
			for (int i = output[0]; i >= output[0] - range; i--) {
				if (0 <= i && tempArr[output[1]][i] > 0) {
					queue.offer(new int[] {i, output[1], tempArr[output[1]][i] - 1});
				}
			}
			
			for (int i = output[0]; i <= output[0] + range; i++) {
				if (i < W && tempArr[output[1]][i] > 0) {
					queue.offer(new int[] {i, output[1], tempArr[output[1]][i] - 1});
				}
			}
			
			for (int i = output[1] - range; i <= output[1] + range; i++) {
				if (i < 0 || i >= H) {
					continue;
				}
				
				tempArr[i][output[0]] = 0;
			}
			
			for (int i = output[0] - range; i <= output[0] + range; i++) {
				if (i < 0 || i >= W) {
					continue;
				}
				
				tempArr[output[1]][i] = 0;
			}
		}
		
		int[][] changeArr = new int[H][W];
		for (int i = 0; i < W; i++) {
			Queue<Integer> tempQueue = new ArrayDeque<>();
			
			for (int j = 0; j < H; j++) {
				if (tempArr[j][i] > 0) {
					tempQueue.offer(tempArr[j][i]);
				}
			}
			
			for (int j = 0; j < H; j++) {
				if (tempQueue.isEmpty()) {
					break;
				}
				
			changeArr[j][i] = tempQueue.poll();
			}
		}
		
		tempArr = changeArr;
	}
	
	static void permutation(int chase) {
		if (chase == N) {		
			tempArr = new int[H][W];
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					tempArr[i][j] = arr[i][j];
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = H - 1; j >= 0; j--) {
					if (tempArr[j][chaseArr[i]] > 0) {
						brickbreak(chaseArr[i], j); // x, y
						break;
					}
				}
			}
			
			int count = 0;
			for (int i = H - 1; i >= 0; i--) {
				for (int j = 0; j < W; j++) {
					if (tempArr[i][j] > 0) {
						count++;
					}
				}
			}
			
			if (minCount > count) {
				minCount = count;
			}
			
			return;
		}
		
		for (int i = 0; i < W; i++) {
			chaseArr[chase] = i;
			permutation(chase+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			arr = new int[H][W];
			for (int i = H - 1; i >= 0; i--) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
                    
                    if (arr[i][j] > 0) {
						minCount++;
					}
				}
			}
			
			chaseArr = new int[N];
			
			permutation(0);
			
			sb.append("#" + tc + " " + minCount + "\n");
			minCount = 0;
		}
		
		System.out.println(sb.toString());
	}
}