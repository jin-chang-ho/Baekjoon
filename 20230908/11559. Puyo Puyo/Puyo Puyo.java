import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] map = new char[12][6];
		
		for (int i = 0; i < 12; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < 6; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int answer = 0;
		
//		for (int i = 0; i < 12; i++) {
//			for (int j = 0; j < 6; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
		
		while (true) {
			boolean[][] bigCheck = new boolean[12][6];
			int breakCount = 0;
			
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && bigCheck[i][j] == false) {
						char color = map[i][j];
						
						boolean[][] check = new boolean[12][6];
						
						// x, y
						Queue<int[]> queue = new ArrayDeque<>();
						Queue<int[]> chase = new ArrayDeque<>();
						
						queue.add(new int[] {j, i});
						chase.add(new int[] {j, i});
						check[i][j] = true; 
						
						while (!queue.isEmpty()) {
							int[] output = queue.poll();
							
							for (int k = 0; k < 4; k++) {
								int cx = output[0] + dx[k];
								int cy = output[1] + dy[k];
								
								if (0 <= cx && cx < 6 && 0 <= cy && cy < 12 && map[cy][cx] == color && check[cy][cx] == false) {
									check[cy][cx] = true;
									queue.add(new int[] {cx, cy});
									chase.add(new int[] {cx, cy});
								}
							}
						}
						
						if (chase.size() >= 4) {
							breakCount++;
							
							while (!chase.isEmpty()) {
								int[] output = chase.poll();
								map[output[1]][output[0]] = '.';
							}
						} else {
							while (!chase.isEmpty()) {
								int[] output = chase.poll();
								bigCheck[output[1]][output[0]] = true;
							}
						}
						
					}
				}
			}
			
			if (breakCount == 0) {
				break;
			}
			
//			for (int i = 0; i < 12; i++) {
//				for (int j = 0; j < 6; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			
			for (int i = 0; i < 6; i++) {
				int index = 11;
				
				for (int j = 11; j >= 0; j--) {
					if (map[j][i] != '.') {
						map[index][i] = map[j][i];
						index--;
					}
				}
				
				for (int j = index; j >= 0; j--) {
					map[j][i] = '.';
				}
			}
			
//			for (int i = 0; i < 12; i++) {
//				for (int j = 0; j < 6; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			answer++;
		}
		
		System.out.println(answer);
	}
}