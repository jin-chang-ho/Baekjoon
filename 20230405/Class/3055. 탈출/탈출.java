import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		char[][] arr = new char[R][C];
		
		Queue<int[]> dotch = new ArrayDeque<>(); // x, y
		Queue<int[]> water = new ArrayDeque<>(); // x, y
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
				
				if (arr[i][j] == 'S') {
					dotch.offer(new int[] {j, i, 0});
					arr[i][j] = '&';
				} else if (arr[i][j] == '*') {
					water.offer(new int[] {j, i});
				}
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		while (!dotch.isEmpty()) {
			Queue<int[]> tempDotch = new ArrayDeque<>();
			
			while (!dotch.isEmpty()) {
				tempDotch.offer(dotch.poll());
			}
			
			// 물 먼저 범람시키기
			
			Queue<int[]> tempWater = new ArrayDeque<>();
			
			while (!water.isEmpty()) {
				tempWater.offer(water.poll());
			}
			
			while (!tempWater.isEmpty()) {
				int[] waterOutput = tempWater.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = waterOutput[0] + dx[i];
					int cy = waterOutput[1] + dy[i];
					
					if (0 <= cx && cx < C && 0 <= cy && cy < R && (arr[cy][cx] == '.' || arr[cy][cx] == '&')) {
						water.offer(new int[] {cx, cy});
						arr[cy][cx] = '*';
					}
				}
			}
			
			
			while (!tempDotch.isEmpty()) {
				int[] dotchOutput = tempDotch.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = dotchOutput[0] + dx[i];
					int cy = dotchOutput[1] + dy[i];
					
					if (0 <= cx && cx < C && 0 <= cy && cy < R && (arr[cy][cx] == '.' || arr[cy][cx] == 'D')) {
						if (arr[cy][cx] == 'D') {
							System.out.println(dotchOutput[2] + 1);
							
							return;
						}
						
						dotch.offer(new int[] {cx, cy, dotchOutput[2] + 1});
						arr[cy][cx] = '&';
					}
				}
			}
		}
		
		System.out.println("KAKTUS");
	}
}	