import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] score;
	static int[] chaseArr;
	
	static int[] lineArr;
	static int[] booleanArr;
	static int[] chaseArr2;
	static boolean[] checkArr;
	
	static int startSum;
	static int lineSum;
	
	static int answer = Integer.MAX_VALUE;
	
	static void per(int chase, int whatCase) {
		if (chase == 2) {
			if (whatCase == 0) {
				startSum += score[chaseArr2[0]][chaseArr2[1]];
			} else {
				lineSum += score[chaseArr2[0]][chaseArr2[1]];
			}
			
			
			return;
		}
		
		for (int i = 0; i < N / 2; i++) {
			if (checkArr[i]) {
				continue;
			}
			
			checkArr[i] = true;
			
			if (whatCase == 0) {
				chaseArr2[chase] = chaseArr[i];
			} else {
				chaseArr2[chase] = lineArr[i];
			}
			
			per(chase + 1, whatCase);
			checkArr[i] = false;
		}
	}
	
	static void com(int start, int chase) {
		if (chase == N/2) {
			startSum = 0;
			lineSum = 0;
			
			chaseArr2 = new int[2];
			checkArr = new boolean[N/2];
			
			per(0, 0);
			
			lineArr = new int[N/2];
			int linkIndex = 0;
			
			for (int i = 0; i < N; i++) {
				boolean iCheck = false;
				
				for (int j = 0; j < N/2; j++) {
					if (i == chaseArr[j]) {
						iCheck = true;
						break;
					}
				}
				
				if (iCheck) {
					continue;
				}
				
				lineArr[linkIndex] = i;
				linkIndex++;
			}
			
			per(0, 1);
			
			answer = Math.min(answer, Math.abs(startSum - lineSum));
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			chaseArr[chase] = i;
			com(i + 1, chase + 1);
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		chaseArr = new int[N/2];
		
		com(0, 0);
		
		System.out.println(answer);
	}
}