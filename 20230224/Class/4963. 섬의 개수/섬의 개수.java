import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int count;
	static int[][] arr;
	static int w;
	static int h;
	// 상, 상좌, 좌, 좌하, 하, 우하, 우, 우상
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static void dfs(int x, int y) {
		arr[y][x] = 0;
		
		int cx, cy;
		for (int i = 0; i < 8; i++) {
			cx = x + dx[i];
			cy = y + dy[i];
			
			if (0 <= cx && cx < w && 0 <= cy && cy < h && arr[cy][cx] == 1) {
				dfs(cx, cy);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				return;
			}
			
			arr = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < w; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (arr[i][j] == 1) {
						dfs(j, i);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
}