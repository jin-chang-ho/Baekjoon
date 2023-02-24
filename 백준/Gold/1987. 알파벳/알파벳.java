import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static char[][] arr;
	static boolean[] check;
	static int maxCount;
	
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void dfs(int x, int y, int count) {
		check[arr[y][x] - 'A'] = true;
		
		if (count > maxCount) {
			maxCount = count;
		}
		
		int cx, cy;
		for (int i = 0; i < 4; i++) {
			cx = x + dx[i];
			cy = y + dy[i];
			
			if (1 <= cx && cx <= C && 1 <= cy && cy <= R && check[arr[cy][cx] - 'A'] == false) {
				dfs(cx, cy, count + 1);
				check[arr[cy][cx] - 'A'] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R+1][C+1];
		check = new boolean[30];
		
		for (int i = 1; i <= R; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < C; j++) {
				arr[i][j+1] = line.charAt(j);
			}
		}
			
		dfs(1, 1, 1);
		System.out.println(maxCount);
	}
}