import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int D;
	static int endLine;
	static int[][] arr;
	static int[] chaseArr;
	static int maxKill = 0;
	
	static class Data implements Comparable<Data> {
		int x;
		int y;
		int dist;
		
		public Data(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Data d) {
			if (this.dist == d.dist) {
				return this.x - d.x;
			}
			
			return this.dist - d.dist;
		}
	}
	
	static void combination(int start, int chase) {
		if (chase == 3) {
			int[][] tempArr = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempArr[i][j] = arr[i][j];
				}
			}
			
			int end = endLine;
			int count = 0;
			
			while (end > 0) {
				List<int[]> enemy = new ArrayList<>(); // x, y 저장
				
				for (int i = 1; i <= end; i++) {
					for (int j = 0; j < M; j++) {
						if (tempArr[i][j] == 1) {
							enemy.add(new int[] {j, i});
						}
					}
				}
				
				PriorityQueue<Data> first = new PriorityQueue<>();
				
				int dist = 0;
				for (int[] arrE : enemy) {
					dist = Math.abs(arrE[0] - chaseArr[0]) + Math.abs(arrE[1] - 0);
					
					if (dist <= D) {
						first.offer(new Data(arrE[0], arrE[1], dist));
					}
				}
				
				PriorityQueue<Data> second = new PriorityQueue<>();
				
				for (int[] arrE : enemy) {
					dist = Math.abs(arrE[0] - chaseArr[1]) + Math.abs(arrE[1] - 0);
					
					if (dist <= D) {
						second.offer(new Data(arrE[0], arrE[1], dist));
					}
				}
				
				PriorityQueue<Data> third = new PriorityQueue<>();
				
				for (int[] arrE : enemy) {
					dist = Math.abs(arrE[0] - chaseArr[2]) + Math.abs(arrE[1] - 0);
					
					if (dist <= D) {
						third.offer(new Data(arrE[0], arrE[1], dist));
					}
				}
				
				Data output;
				
				if (!first.isEmpty()) {
					output = first.poll();
					tempArr[output.y][output.x] = 0;
					count++;
				}
				
				if (!second.isEmpty()) {
					output = second.poll();
					
					if (tempArr[output.y][output.x] != 0) {
						count++;
					}
					
					tempArr[output.y][output.x] = 0;
				}
				
				if (!third.isEmpty()) {
					output = third.poll();
					
					if (tempArr[output.y][output.x] != 0) {
						count++;
					}
					
					tempArr[output.y][output.x] = 0;
				}
				
				for (int i = 1; i <= end; i++) {
					for (int j = 0; j < M; j++) {
						if (tempArr[i][j] == 1) {
							tempArr[i-1][j] = 1;
							tempArr[i][j] = 0;
						}
					}
				}
				
				end--;
			}
			
			if (maxKill < count) {
				maxKill = count;
			}
			
			return;
		}
		
		for (int i = start; i < M; i++) {
			chaseArr[chase] = i;
			combination(i + 1, chase + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()) + 1;
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		endLine = 0;
		boolean linecheck = false;
		arr = new int[N][M];
		for (int i = N - 1; i > 0; i--) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (!linecheck && arr[i][j] == 1) {
					endLine = i;
					linecheck = true;
				}
			}
		}
		
		chaseArr = new int[3];
		combination(0, 0);
		System.out.println(maxKill);
	}
}