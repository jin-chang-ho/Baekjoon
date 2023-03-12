import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int maxScore = -1;
	static int[][] score;
	static int[] chaseArr;
	static boolean[] checkArr;
	
	static void playgame(int chase) {
		if (chase == 8) {		
			int[] order = new int[9];
			
			for (int i = 0; i < 3; i++) {
				order[i] = chaseArr[i];
			}
			order[3] = 0;
			for (int i = 3; i < 8; i++) {
				order[i+1] = chaseArr[i];
			}
			
			int get = 0;
			int hitNumber = 0;
			for (int i = 0; i < N; i++) {
				boolean[] base = new boolean[3];
				int out = 0;
				
				while (out != 3) {
					if (score[i][order[hitNumber]] == 1) {
						if (base[2] == true) {
							get++;
						}
						
						for (int j = 1; j >= 0; j--) {
							base[j+1] = base[j];
						}
						
						base[0] = true;
					} else if (score[i][order[hitNumber]] == 2) {
						if (base[2] == true) {
							get++;
						}
						if (base[1] == true) {
							get++;
						}
						
						base[2] = base[0];
						
						if (base[0] == true) {
							base[0] = false;
						}
						
						base[1] = true;
					} else if (score[i][order[hitNumber]] == 3) {
						if (base[2] == true) {
							get++;
						}
						if (base[1] == true) {
							get++;
							base[1] = false;
						}
						if (base[0] == true) {
							get++;
							base[0] = false;
						}
						
						base[2] = true;
					} else if (score[i][order[hitNumber]] == 4) {
						if (base[2] == true) {
							get++;
							base[2] = false;
						}
						if (base[1] == true) {
							get++;
							base[1] = false;
						}
						if (base[0] == true) {
							get++;
							base[0] = false;
						}
						get++;
					} else {
						out++;
					}
					
					hitNumber++;
					if (hitNumber == 9) {
						hitNumber = 0;
					}
				}
			}
			
			if (maxScore < get) {
				maxScore = get;
			}
			
			return;
		}
		
		for (int i = 1; i < 9; i++) {
			if (checkArr[i] == true) {
				continue;
			}
			
			checkArr[i] = true;
			chaseArr[chase] = i;
			playgame(chase + 1);
			checkArr[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		score = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 9; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		chaseArr = new int[8];
		checkArr = new boolean[9];
		playgame(0);
		System.out.println(maxScore);
	}
}