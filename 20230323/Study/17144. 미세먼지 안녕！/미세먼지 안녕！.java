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
		int T = Integer.parseInt(st.nextToken());
		
		int cirUpY = -1;
		int cirDownY = -1;
		Queue<int[]> dustQueue = new ArrayDeque<>(); // x, y
		
		int[][] arr = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				
				if (arr[i][j] != 0) {
					if (arr[i][j] == -1) {
						if (cirUpY == -1) {
							cirUpY = i;
							cirDownY = i+1;
						}	
					}
					else {
						if (arr[i][j] < 5) {
							continue;
						}
					
					dustQueue.offer(new int[] {j, i});
					}
				}
			}
		}
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		for (int i = 0; i < T; i++) {
			int[][] tempArr = new int[R][C];
			
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					tempArr[j][k] = arr[j][k];
				}
			}
				
			while (!dustQueue.isEmpty()) {
				int[] temp = dustQueue.poll(); // x, y
				
				int amount = arr[temp[1]][temp[0]] / 5;
				int count = 0;
					
				for (int j = 0; j < 4; j++) {
					int cx = temp[0] + dx[j];
					int cy = temp[1] + dy[j];
						
					if (0 <= cx && cx < C && 0 <= cy && cy < R && arr[cy][cx] != -1) {
						tempArr[cy][cx] += amount;
						count++;
					}
				}
				
				tempArr[temp[1]][temp[0]] -= amount * count;
			}
			
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					arr[j][k] = tempArr[j][k];
				}
			}
			
			int firstOut = -1;
			
			for (int j = C - 1; j > 0; j--) {
				if (j == C - 1) {
					firstOut = arr[cirUpY][j];
				}
				
				if (j == 1) {
					arr[cirUpY][j] = 0;
				} else {
					arr[cirUpY][j] = arr[cirUpY][j-1];
				}
			}
			
			int secondOut = -1;
			
			for (int j = 0; j < cirUpY - 1; j++) {
				if (j == 0) {
					secondOut = arr[j][C - 1];
				}
				
				arr[j][C - 1] = arr[j + 1][C - 1];
			}
			
			arr[cirUpY - 1][C - 1] = firstOut;
			
			int thirdOut = -1;
			
			for (int j = 0; j < C - 2; j++) {
				if (j == 0) {
					thirdOut = arr[0][j];
				}
				
				arr[0][j] = arr[0][j+1];
			}
			
			arr[0][C - 2] = secondOut;
			
			for (int j = cirUpY - 1; j > 1; j--) {
				arr[j][0] = arr[j - 1][0];
			}
			
			arr[1][0] = thirdOut;
			
			firstOut = -1;
			
			for (int j = C - 1; j > 0; j--) {
				if (j == C - 1) {
					firstOut = arr[cirDownY][j];
				}
				
				if (j == 1) {
					arr[cirDownY][j] = 0;
				} else {
					arr[cirDownY][j] = arr[cirDownY][j-1];
				}
			}
			
			secondOut = -1;
			
			for (int j = R - 1; j > cirDownY + 1; j--) {
				if (j == R - 1) {
					secondOut = arr[j][C - 1];
				}
				
				arr[j][C - 1] = arr[j - 1][C - 1];
			}
			
			arr[cirDownY + 1][C - 1] = firstOut;
			
			thirdOut = -1;
			
			for (int j = 0; j < C - 2; j++) {
				if (j == 0) {
					thirdOut = arr[R - 1][j];
				}
				
				arr[R - 1][j] = arr[R - 1][j+1];
			}
			
			arr[R - 1][C - 2] = secondOut;
			
			for (int j = cirDownY + 1; j < R - 2; j++) {
				arr[j][0] = arr[j + 1][0];
			}
			
			arr[R - 2][0] = thirdOut;
			
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					if (arr[j][k] < 5) {
						continue;
					}
					
					dustQueue.offer(new int[] {k, j});
				}
			}
		}
		
		int sum = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] != -1) {
					sum += arr[i][j];
				}
			}
		}
		
		System.out.println(sum);
	}
}