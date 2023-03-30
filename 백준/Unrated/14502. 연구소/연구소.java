import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static List<int[]> canBuild; // x, y
	static List<int[]> virus; // x, y
	static int[][] chaseArr;
	static int maxSafe = -1;
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void dfs(int start, int chase) {
		if (chase == 3) {
			int[][] tempMap = new int[N][M];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			for (int i = 0; i < 3; i++) {
				tempMap[chaseArr[i][1]][chaseArr[i][0]] = 1;
			}
			
			Queue<int[]> queue = new ArrayDeque<>();
			
			for (int i = 0; i < virus.size(); i++) {
				queue.offer(virus.get(i));
			}
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < M && 0 <= cy && cy < N && tempMap[cy][cx] == 0) {
						queue.offer(new int[] {cx, cy});
						tempMap[cy][cx] = 3;
					}
				}
			}
			
			int tempMax = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tempMap[i][j] == 0) {
						tempMax++;
					}
				}
			}
			
			maxSafe = Math.max(maxSafe, tempMax);
			
			return;
		}
		
		for (int i = start; i < canBuild.size(); i++) {
			chaseArr[chase] = canBuild.get(i);
			dfs(i + 1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		canBuild = new ArrayList<>();
		virus = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					canBuild.add(new int[] {j, i});
				} else if (map[i][j] == 2) {
					virus.add(new int[] {j, i});
				}
			}
		}
		
		chaseArr = new int[3][2];
		dfs(0, 0);
		
		System.out.println(maxSafe);
	}
}