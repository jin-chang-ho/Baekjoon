import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] triArr;
	static boolean[] checkArr;
	static int[] chaseArr;
	static int answer = -1;
	static int[][] chaseTri = new int[6][3];
	
	static void check(int order) {
		if (order == 6) {
			if (chaseTri[5][2] == chaseTri[0][2]) {
				answer = Math.max(answer, chaseTri[0][0] + chaseTri[1][2] + chaseTri[2][1] + chaseTri[3][0] + chaseTri[4][2] + chaseTri[5][1]);
			}
			
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if (order == 0) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[0]][(i + j) % 3];
				}
				
				check(1);
			} else if (order == 1) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[1]][(i + j) % 3];
				}
				
				if (chaseTri[0][1] != chaseTri[1][1]) {
					continue;
				}
				
				check(2);
			} else if (order == 2) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[2]][(i + j) % 3];
				}
				
				if (chaseTri[1][0] != chaseTri[2][0]) {
					continue;
				}
				
				check(3);
			} else if (order == 3) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[3]][(i + j) % 3];
				}
				
				if (chaseTri[2][2] != chaseTri[3][2]) {
					continue;
				}
				
				check(4);
			} else if (order == 4) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[4]][(i + j) % 3];
				}
				
				if (chaseTri[3][1] != chaseTri[4][1]) {
					continue;
				}
				
				check(5);
			} else if (order == 5) {
				for (int j = 0; j < 3; j++) {
					chaseTri[order][j] = triArr[chaseArr[5]][(i + j) % 3];
				}
				
				if (chaseTri[4][0] != chaseTri[5][0]) {
					continue;
				}
				
				check(6);
			}
		}
	}
	
	static void dfs(int chase) {
		if (chase == 6) {
			check(0);	
			return;
		}
		
		for (int i = 0; i < 6; i++) {
			if (checkArr[i]) {
				continue;
			}
			
			checkArr[i] = true;
			chaseArr[chase] = i;
			dfs(chase + 1);
			checkArr[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			triArr = new int[6][3];
			
			for (int i = 0; i < 6; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int down = Integer.parseInt(st.nextToken());

				triArr[i][0] = left;
				triArr[i][1] = right;
				triArr[i][2] = down;				
			}
			
			checkArr = new boolean[6];
			chaseArr = new int[6];
			
			dfs(0);
			
			String finish = br.readLine();
			
			if (answer == -1) {
				sb.append("none\n");
			} else {
				sb.append(answer + "\n");
			}
			
			answer = -1;
			
			if (finish.equals("$")) { 
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}