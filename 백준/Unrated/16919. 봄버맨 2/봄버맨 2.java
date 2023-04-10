import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long N = Long.parseLong(st.nextToken());
		
		char[][] arr = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		
		if (N == 1) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append(arr[i][j]);
				}
				
				sb.append("\n");
			}
		} else if (N % 2 == 0) {
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					sb.append("O");
				}
				
				sb.append("\n");
			}
		} else if (N >= 3) {
			// 상, 하, 좌, 우
			int[] dx = {0, 0, -1, 1};
			int[] dy = {-1, 1, 0, 0};
			
			char[][] tempArr = new char[R][C];
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					tempArr[i][j] = 'O';
				}
			}
			
			Queue<int[]> queue = new ArrayDeque<>(); // x, y
			
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] == 'O') {
						queue.offer(new int[] {j, i});
					}
				}
			}
			
			while (!queue.isEmpty()) {
				int[] output = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int cx = output[0] + dx[i];
					int cy = output[1] + dy[i];
					
					if (0 <= cx && cx < C && 0 <= cy && cy < R) {
						tempArr[cy][cx] = '.';
					}
				}
				
				tempArr[output[1]][output[0]] = '.';
 			}
			
			if (N == 3) {
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(tempArr[i][j]);
					}
					
					sb.append("\n");
				}
			} else if (N % 4 == 1) {
				char[][] tempArr2 = new char[R][C];
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						tempArr2[i][j] = 'O';
					}
				}
				
				queue = new ArrayDeque<>(); // x, y
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (tempArr[i][j] == 'O') {
							queue.offer(new int[] {j, i});
						}
					}
				}
				
				while (!queue.isEmpty()) {
					int[] output = queue.poll();
					
					for (int i = 0; i < 4; i++) {
						int cx = output[0] + dx[i];
						int cy = output[1] + dy[i];
						
						if (0 <= cx && cx < C && 0 <= cy && cy < R) {
							tempArr2[cy][cx] = '.';
						}
					}
					
					tempArr2[output[1]][output[0]] = '.';
	 			}
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(tempArr2[i][j]);
					}
					
					sb.append("\n");
				}
			} else if (N % 4 == 3) {
				char[][] tempArr2 = new char[R][C];
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						tempArr2[i][j] = 'O';
					}
				}
				
				queue = new ArrayDeque<>(); // x, y
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (tempArr[i][j] == 'O') {
							queue.offer(new int[] {j, i});
						}
					}
				}
				
				while (!queue.isEmpty()) {
					int[] output = queue.poll();
					
					for (int i = 0; i < 4; i++) {
						int cx = output[0] + dx[i];
						int cy = output[1] + dy[i];
						
						if (0 <= cx && cx < C && 0 <= cy && cy < R) {
							tempArr2[cy][cx] = '.';
						}
					}
					
					tempArr2[output[1]][output[0]] = '.';
	 			}
				
				char[][] tempArr3 = new char[R][C];
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						tempArr3[i][j] = 'O';
					}
				}
				
				queue = new ArrayDeque<>(); // x, y
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						if (tempArr2[i][j] == 'O') {
							queue.offer(new int[] {j, i});
						}
					}
				}
				
				while (!queue.isEmpty()) {
					int[] output = queue.poll();
					
					for (int i = 0; i < 4; i++) {
						int cx = output[0] + dx[i];
						int cy = output[1] + dy[i];
						
						if (0 <= cx && cx < C && 0 <= cy && cy < R) {
							tempArr3[cy][cx] = '.';
						}
					}
					
					tempArr3[output[1]][output[0]] = '.';
	 			}
				
				for (int i = 0; i < R; i++) {
					for (int j = 0; j < C; j++) {
						sb.append(tempArr3[i][j]);
					}
					
					sb.append("\n");
				}
			}
		} 
		
		System.out.println(sb.toString());
	}
}