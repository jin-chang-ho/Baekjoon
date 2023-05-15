import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;
	static boolean[][] check;
	static int answer = 0;
	// 오위, 오, 오아
	static int[] dx = {1, 1, 1};
	static int[] dy = {-1, 0, 1};
	
	static boolean dfs(int x, int y) {
		if (x == C - 1) {
			return true;
		}
		
		check[y][x] = true;
		
		for (int i = 0; i < 3; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			
			if (0 <= cx && cx < C && 0 <= cy && cy < R && check[cy][cx] == false) {
				if (dfs(cx, cy)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		check = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for (int j = 0; j < C; j++) {
				char temp = line.charAt(j);
				
				if (temp == 'x') {
					check[i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			if (dfs(0, i)) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}
}