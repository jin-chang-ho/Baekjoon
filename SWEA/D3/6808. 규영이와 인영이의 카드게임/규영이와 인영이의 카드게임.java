import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution
{
	static int[] gu;
	static int[] in;
	static int[] order;
	static boolean[] check;
	
	static int winScore;
	static int loseScore;
	
	static void dfs(int chase) {
		if (chase == 9) {
			int guScore = 0;
			int inScore = 0;
			
			for (int i = 0; i < 9; i++) {
				if (gu[i] > order[i]) {
					guScore += gu[i];
					guScore += order[i];
				} else {
					inScore += gu[i];
					inScore += order[i];
				}
			}
			
			if (guScore > inScore) {
				winScore++;
			}
			
			if (guScore < inScore) {
				loseScore++;
			}
			
			return;
		}
		
		for (int i = 0; i < 9; i++) {
			if (check[i] == true) {
				continue;
			}
			
			check[i] = true;
			order[chase] = in[i];
			dfs(chase + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			gu = new int[9];
			
			for (int j = 0; j < 9; j++) {
				gu[j] = Integer.parseInt(st.nextToken());
			}
			
			in = new int[9];
			int guChase = 0;
			int inChase = 0;
			
			int[] orderGu = new int[9];
			for (int j = 0; j < 9; j++) {
				orderGu[j] = gu[j];
			}
			
			Arrays.sort(orderGu);
			
			for (int j = 1; j <= 18; j++) {
				if (guChase < 9 && orderGu[guChase] == j) {
					guChase++;
					continue;
				}
				
				in[inChase++] = j;
			}
			
			order = new int[9];
			check = new boolean[9];
			
			dfs(0);
			
			System.out.println("#" + i + " " + winScore + " " + loseScore);
			
			winScore = 0;
			loseScore = 0;
		}
	}
}