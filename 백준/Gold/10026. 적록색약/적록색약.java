import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char first[][] = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < N; j++) {
				first[i][j] = line.charAt(j);
			}
		}
		
		char second[][] = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				second[i][j] = first[i][j];
			}
		}
		
		Queue<int[]> firstQue = new ArrayDeque<>(); // x, y
		Queue<int[]> secondQue = new ArrayDeque<>(); // x, y
		
		int firstCount = 0;
		int secondCount = 0;
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (first[i][j] == 'R' || first[i][j] == 'G' || first[i][j] == 'B') {
					char color = first[i][j];
					firstQue.offer(new int[] {j, i});
					
					while (!firstQue.isEmpty()) {
						int[] firstOut = firstQue.poll();
						first[firstOut[1]][firstOut[0]] = 'X';
						
						for (int k = 0; k < 4; k++) {
							int cx = firstOut[0] + dx[k];
							int cy = firstOut[1] + dy[k];
							
							if (0 <= cx && cx < N && 0 <= cy && cy < N && first[cy][cx] == color) {
								firstQue.offer(new int[] {cx, cy});
								first[cy][cx] = 'X';
							}
						}
					}
					
					firstCount++;
				} 
				
				if (second[i][j] == 'R' || second[i][j] == 'G' || second[i][j] == 'B') {
					char color = second[i][j];
					secondQue.offer(new int[] {j, i});
					
					while (!secondQue.isEmpty()) {
						int[] secondOut = secondQue.poll();
						second[secondOut[1]][secondOut[0]] = 'X';
						
						for (int k = 0; k < 4; k++) {
							int cx = secondOut[0] + dx[k];
							int cy = secondOut[1] + dy[k];
							
							if (color == 'R' || color == 'G') {
								if (0 <= cx && cx < N && 0 <= cy && cy < N && (second[cy][cx] == 'R' || second[cy][cx] == 'G')) {
									secondQue.offer(new int[] {cx, cy});
									second[cy][cx] = 'X';
								}
							} else {
								if (0 <= cx && cx < N && 0 <= cy && cy < N && second[cy][cx] == color) {
									secondQue.offer(new int[] {cx, cy});
									second[cy][cx] = 'X';
								}
							}
						}
					}
					
					secondCount++;
				}
			}
		}
		
		System.out.print(firstCount + " " + secondCount);
	}
}