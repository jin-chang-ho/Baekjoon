import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			
			boolean[][] check = new boolean[N][N];
			
			// 가로
			for (int i = 0; i < N; i++) {
				int base = arr[i][0];
				boolean canBuild = true;
				
				for (int j = 1; j < N; j++) {
					if (base != arr[i][j]) {
						if (Math.abs(base - arr[i][j]) >= 2) {
							canBuild = false;
							break;
						}
						
						if (base - arr[i][j] == 1) {
							if (j + X > N) {
								canBuild = false;
								break;
							}
							
							for (int k = j; k < j + X; k++) {
								if ((arr[i][j] != arr[i][k]) || check[i][k] == true) {
									canBuild = false;
									break;
								}
								
								check[i][k] = true;
							}
							
							if (canBuild) {
								for (int k = j; k < j + X; k++) {
									check[i][k] = true;
								}
							} else {
								break;
							}
						}
						
						if (base - arr[i][j] == -1) {
							if (j - X < 0) {
								canBuild = false;
								break;
							}
							
							for (int k = j - 1; k >= j - X; k--) {
								if ((arr[i][j-1] != arr[i][k]) || check[i][k] == true) {
									canBuild = false;
									break;
								}
								
								check[i][k] = true;
							}
							
							if (canBuild) {
								for (int k = j - 1; k >= j - X; k--) {
									check[i][k] = true;
								}
							} else {
								break;
							}
						}
					}
					
					base = arr[i][j];
				}
				
				if (!canBuild) {
					continue;
				}
				
				count++;
			}
			
			check = new boolean[N][N];
			
			// 세로
			for (int i = 0; i < N; i++) {
				int base = arr[0][i];
				boolean canBuild = true;
				
				for (int j = 1; j < N; j++) {
					if (base != arr[j][i]) {
						if (Math.abs(base - arr[j][i]) >= 2) {
							canBuild = false;
							break;
						}
						
						if (base - arr[j][i] == 1) {
							if (j + X > N) {
								canBuild = false;
								break;
							}
							
							for (int k = j; k < j + X; k++) {
								if ((arr[j][i] != arr[k][i]) || check[k][i] == true) {
									canBuild = false;
									break;
								}
								
								check[k][i] = true;
							}
							
							if (canBuild) {
								for (int k = j; k < j + X; k++) {
									check[k][i] = true;
								}
							} else {
								break;
							}
						}
						
						if (base - arr[j][i] == -1) {
							if (j - X < 0) {
								canBuild = false;
								break;
							}
							
							for (int k = j - 1; k >= j - X; k--) {
								if ((arr[j-1][i] != arr[k][i]) || check[k][i] == true) {
									canBuild = false;
									break;
								}
								
								check[k][i] = true;
							}
							
							if (canBuild) {
								for (int k = j - 1; k >= j - X; k--) {
									check[k][i] = true;
								}
							} else {
								break;
							}
						}
					}
					
					base = arr[j][i];
				}
				
				if (!canBuild) {
					continue;
				}
				
				count++;
			}			
			
			sb.append("#" + tc + " " + count + "\n");
		}
		
		System.out.println(sb.toString());
	}
}
