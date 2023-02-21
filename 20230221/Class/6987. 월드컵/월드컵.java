import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] result = new int[6][3];
	static int[][] chase = new int[6][3];
	static int res;
	
	public static boolean check() {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				if (result[i][j] != chase[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static void dfs(int team1, int team2) {
		if (team1 == 5) {
			if (check()) {
				res = 1;
			}
			
			return;
		}
		
		int nTeam1 = team1;
		int nTeam2 = team2 + 1;
		
		if (nTeam2 == 6) {
			nTeam1 = team1 + 1;
			nTeam2 = nTeam1 + 1;
		}
		
		for (int i = 0; i < 3; i++) { // 승 무 패
			if (i == 0) {
				chase[team1][0]++;
				chase[team2][2]++;
			} else if (i == 1) {
				chase[team1][1]++;
				chase[team2][1]++;
			} else {
				chase[team1][2]++;
				chase[team2][0]++;
			}
			
			dfs(nTeam1, nTeam2);
			
			if (i == 0) {
				chase[team1][0]--;
				chase[team2][2]--;
			} else if (i == 1) {
				chase[team1][1]--;
				chase[team2][1]--;
			} else {
				chase[team1][2]--;
				chase[team2][0]--;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int TC = 1; TC <= 4; TC++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int count = 0;
			
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					result[i][j] = Integer.parseInt(st.nextToken());
					count += result[i][j];
				}
			}
			
			res = 0;
			
			if (count == 30) {
				dfs(0, 1);
			}
			
			System.out.print(res + " ");
		}
	}
}