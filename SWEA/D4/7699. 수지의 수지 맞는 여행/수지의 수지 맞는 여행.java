import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	// 상, 하, 좌, 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int R;
	static int C;
	static char arr[][];
	static boolean[] alpha = new boolean[26];
	static int max = -1;
	
	static void dfs(int x, int y, int count) {
		alpha[arr[y][x] - 'A'] = true;
		
		for (int i = 0; i < 4; i++) {
			int cx = x + dx[i];
			int cy = y + dy[i];
			
			if (1 <= cx && cx <= C && 1 <= cy && cy <= R && alpha[arr[cy][cx] - 'A'] == false) {
                alpha[arr[cy][cx] - 'A'] = true;
				dfs(cx, cy, count + 1);
				alpha[arr[cy][cx] - 'A'] = false;
			}
		}
		
		alpha[arr[y][x] - 'A'] = false;
		max = Math.max(max, count);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			arr = new char[R+1][C+1];
			
			for (int i = 1; i <= R; i++) {
				String line = br.readLine();
				
				for (int j = 1; j <= C; j++) {
					arr[i][j] = line.charAt(j-1);
				}
			}
			
			dfs(1, 1, 1);
			
			sb.append("#" + tc + " " + max + "\n");
			max = -1;
		}
		
		System.out.println(sb.toString());
	}
}
