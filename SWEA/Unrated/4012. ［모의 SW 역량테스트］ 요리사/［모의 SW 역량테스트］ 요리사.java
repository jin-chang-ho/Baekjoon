import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int N;
	static int arr[][];
	static int first[];
	static int minDif = 999999999;
	
	static void dfs(int start, int chase) {
		int firstChase = 0;
		int secondChase = 0;
		int[] second = new int[N / 2];
		
		if (chase == N / 2) {
			for (int i = 0; i < N; i++) {
				if (firstChase < N / 2 && first[firstChase] == i) { 
					firstChase++;
					continue;
				}
				
				second[secondChase] = i;
				secondChase++;
			}
			
			int firstScore = 0;
			int secondScore = 0;
			
			for (int i = 0; i < N / 2 - 1; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					firstScore += arr[first[i]][first[j]];
					firstScore += arr[first[j]][first[i]];
				}
			}
			
			for (int i = 0; i < N / 2 - 1; i++) {
				for (int j = i + 1; j < N / 2; j++) {
					secondScore += arr[second[i]][second[j]];
					secondScore += arr[second[j]][second[i]];
				}
			}
			
			if (minDif > Math.abs(firstScore - secondScore)) {
				minDif = Math.abs(firstScore - secondScore);
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			first[chase] = i;
			dfs(i+1, chase + 1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= TC; i++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N][N];
			first = new int[N/2];
			
			for (int j = 0; j < N; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < N; k++) {
					arr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0, 0);
			
			System.out.println("#" + i + " " + minDif);
			
			minDif = 999999999;
		}
	}
}