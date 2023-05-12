import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[101][101];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					arr[k][j] = 1;
				}
			}
		}
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {100, 100});
		arr[100][100] = 3;
		
		int answer = 0;
		
		// 상, 하, 좌, 우
		int[] dx = {0, 0, -1, 1};
		int[] dy = {-1, 1, 0, 0};
		
		int i, j, k;
	    for (i = 1; i <= 100; ++i) {
	        for (j = 1; j <= 100; ++j) {
	            if (arr[i][j] == 1) {
	                for (k = 0; k < 4; ++k) {
	                    int cx = j + dx[k];
	                    int cy = i + dy[k];
	                    
	                    if (arr[cy][cx] == 0) {
	                    	answer++;
	                    }
	                }
	            }
	        }
	    }
		
		System.out.println(answer);
	}
}