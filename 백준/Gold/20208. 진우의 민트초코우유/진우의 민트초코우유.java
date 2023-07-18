import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int H;
	static int map[][];
	static int homeX;
	static int homeY;
	static int mintCount;
	static boolean[] check;
	static int[] chaseArr;
	static int[][] mint;
	static int answer = 0;
	
	static void dfs(int chase) {
		if (chase == mintCount) {
			if (answer == mintCount) {
				return;
			}
			
			int dis = M;
			int currentX = homeX;
			int currentY = homeY;
			int count = 0;
			
			for (int i = 0; i < mintCount; i++) {
				if ((Math.abs(currentX - mint[chaseArr[i]][0]) + Math.abs(currentY - mint[chaseArr[i]][1])) > dis) {
					if ((Math.abs(homeX - currentX) + Math.abs(homeY - currentY)) <= dis) {
						answer = Math.max(answer, count);
					}
					
					return;
				}
				
				if ((Math.abs(homeX - currentX) + Math.abs(homeY - currentY)) <= dis) {
					answer = Math.max(answer, count);
				}
				
				dis = dis - (Math.abs(currentX - mint[chaseArr[i]][0]) + Math.abs(currentY - mint[chaseArr[i]][1])) + H;
				count++;
				currentX = mint[chaseArr[i]][0];
				currentY = mint[chaseArr[i]][1];
			}
			
			if ((Math.abs(homeX - currentX) + Math.abs(homeY - currentY)) <= dis) {
				answer = mintCount;
			}
			
			return;
		}
		
		for (int i = 0; i < mintCount; i++) {
			if (check[i]) {
				continue;
			}
			
			check[i] = true;
			chaseArr[chase] = i;
			dfs(chase + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		mintCount = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					homeX = j;
					homeY = i;
				}
				
				if (map[i][j] == 2) {
					mintCount++;
				}
			}
		}
		
		mint = new int[mintCount][2];
		
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					mint[count][0] = j;
					mint[count][1] = i;
					count++;
				}
			}
		}
		
		check = new boolean[mintCount];
		chaseArr = new int[mintCount];
		
		dfs(0);
		
		System.out.println(answer);
	}
}
