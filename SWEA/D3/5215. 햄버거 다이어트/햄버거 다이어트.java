import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static int N;
	static int L;
	static int[] score, cal;
	static boolean[] check;
	static int maxScore = -1;
	
	static void dfs(int chase, int sumScore, int sumCal) {
		if (sumCal > L) {
			return;
		}
		
		if (chase == N) {
			if (maxScore < sumScore) {
				maxScore = sumScore;
			}
			
			return;
		}
		
		check[chase] = true;
		dfs(chase + 1, sumScore + score[chase], sumCal + cal[chase]);
		check[chase] = false;
		dfs(chase + 1, sumScore, sumCal);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= TC; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			score = new int[N];
			cal = new int[N];
			check = new boolean[N];
			
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				
				score[j] = Integer.parseInt(st.nextToken());
				cal[j] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			
			System.out.println("#" + i + " " + maxScore);
			maxScore = -1;
		}
	}
}